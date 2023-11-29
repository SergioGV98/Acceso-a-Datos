package org.example;

import org.example.orm.Departamento;
import org.example.orm.Empleado;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class _7_ {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure().
                buildSessionFactory();
             Session s = sessionFactory.openSession()) {

            Transaction t = null;
            try {
                t = s.beginTransaction();

                Departamento marketing = s.get(Departamento.class, 1);
                Departamento programacion = s.get(Departamento.class, 2);

                Empleado e1 = new Empleado();
                e1.setDni("26524624N");
                e1.setNomEmp("Sergio");
                e1.setDepartamentoByIdDepto(programacion);
                s.save(e1);
                Empleado e2 = new Empleado();
                e2.setDni("26125129C");
                e2.setNomEmp("Paco");
                e2.setDepartamentoByIdDepto(marketing);
                s.save(e2);
                Empleado e3 = new Empleado();
                e3.setDni("19561802A");
                e3.setNomEmp("Isabel");
                e3.setDepartamentoByIdDepto(programacion);
                s.save(e3);
                Empleado e4 = new Empleado();
                e4.setDni("62175035C");
                e4.setNomEmp("Maria");
                e4.setDepartamentoByIdDepto(marketing);
                s.save(e4);

                t.commit();
                System.out.println("Datos introducidos correctamente.");
            } catch (Exception ex) {
                ex.printStackTrace(System.err);
                if (t != null) {
                    System.out.println("ERROR: ROLLBACK");
                    t.rollback();
                }
            }
        }

    }
}
