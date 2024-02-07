package org.portada;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.portada.modelo.Pais;
import org.portada.modelo.Presidente;

public class _02_ModificaBorraPaises {
    public static void main(String[] args) {
        Pais polonia = new Pais("Polonia");
        Pais japon = new Pais("Japon");

        Presidente paco = new Presidente("Paco");


        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory(); Session s = sessionFactory.openSession()) {

            Transaction t = null;
            try {
                t = s.beginTransaction();
                s.persist(paco);
                polonia.setPresidente(paco);
                s.persist(polonia);
                s.persist(japon);
                System.out.printf("Pais %s con presidente: %s\n", polonia.getNombre(), polonia.getPresidente());
                System.out.println();
                System.out.printf("Pais %s sin presidente: %s\n", japon.getNombre(), japon.getPresidente());
                System.out.println();

                Pais cambiarNombre = s.find(Pais.class, 1);
                System.out.printf("Pais %s con presidente: %s\n", cambiarNombre.getNombre(), cambiarNombre.getPresidente());
                cambiarNombre.getPresidente().setNombre("Federico");
                System.out.printf("Pais %s con nuevo presidente: %s\n", cambiarNombre.getNombre(), cambiarNombre.getPresidente());

                // En vez de recuperarte todos los paises con una sentencia select y comprobar si el presidente es Null
                // Te lo hago asi sabiendo que poniendo el hbm2ddl en create y que el pais 2 que es japon no tiene presidente
                // Porque te lo he asignado previamente, te lo borro del tiron para no perder tiempo
                Pais borrar = s.find(Pais.class, 2);
                s.remove(borrar);
                System.out.printf("Pais %s con presidente: %s a sido borrado.\n", borrar.getNombre(), borrar.getPresidente());
                t.commit();

            } catch (Exception ex) {
                ex.printStackTrace();
                if (t != null) {
                    System.out.println("ERROR: ROLLBACK");
                    t.rollback();
                }
            }
        }
    }
}
