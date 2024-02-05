package org.portada;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.portada.modelo.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class _05_RelacionCoche_DatosProfesionales {


    //Todo. Pon hibernate.hbm2ddl.auto en update y ejecuta primero el anterior
    public static void main(String[] args) {

        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session s = sessionFactory.openSession()) {
            Transaction t = null;
            try {
                Empleado emp = s.find(Empleado.class, 1);

                if (emp == null) {
                    System.out.println("Empleado no encontrado.");
                    return;
                }

                t = s.beginTransaction();

                DatosProfesionales datProf = new
                        DatosProfesionales("Programador", BigDecimal.valueOf(20122.00));

                s.persist(datProf);

                //Asignamos datos profesionales al empleado
                emp.setDatosProfesionales(datProf);
                s.persist(emp);


                CocheEmpresa coche = new CocheEmpresa();
                coche.setMatricula("ABC123");
                coche.setAsignadoHasta(LocalDate.now().plusYears(1));
                coche.setEmpleado(emp);

                s.persist(coche);


                t.commit();

                CocheEmpresa fcoche = s.find(CocheEmpresa.class, 1);

                System.out.printf("Empleado %s\n, ", emp);
                System.out.println();
                System.out.printf("Coche empleado %s\n, ", fcoche);


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




