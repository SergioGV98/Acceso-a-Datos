package org.portada;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.portada.modelo.Sede;

//Todo. Pon hibernate.hbm2ddl.auto en update
public class _03_BorrarUpdateSede {

    public static void main(String[] args) {
        int codSedeBorrar = 3;
        int codSedeAct = 2;

        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session s = sessionFactory.openSession()) {

            Sede sedeBorrar = s.find(Sede.class, codSedeBorrar);
            Sede sedeAct = s.find(Sede.class, codSedeAct);


            if (sedeAct == null || sedeBorrar == null) {
                System.out.print("ERROR: Una de las sedes no existe\n");
                return;
            }

            Transaction t = null;
            try {
                t = s.beginTransaction();

                s.remove(sedeBorrar);

                t.commit();
                System.out.printf("Sede %d borrada\n", sedeBorrar.getId());



                t= s.beginTransaction();

                sedeAct.setNombre("Sede2Modificado");
                s.persist(sedeAct);
                t.commit();

                System.out.printf("Sede después de cambiar el nombre: %s.\n",
                        s.find(Sede.class, sedeAct.getId()));

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
