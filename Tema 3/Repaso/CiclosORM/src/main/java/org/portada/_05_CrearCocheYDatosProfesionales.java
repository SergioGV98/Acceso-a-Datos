package org.portada;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.portada.modelo.CocheEmpresa;
import org.portada.modelo.DatosProfesionales;
import org.portada.modelo.Departamento;
import org.portada.modelo.Empleado;

import java.time.LocalDate;

public class _05_CrearCocheYDatosProfesionales {
    public static void main(String[] args) {
        try (
                SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Empleado e1 = new Empleado("Paco",4);
                CocheEmpresa c1 = new CocheEmpresa("4234MD", LocalDate.of(2022,2,2));
                c1.setEmpleado(e1);
                session.persist(c1);

                DatosProfesionales datos = new DatosProfesionales("Programador", 2313.12f);
                session.persist(datos);

                Departamento departamento = session.get(Departamento.class, 1);
                e1.setDatosProfesionales(datos);
                e1.setDepartamento(departamento);
                session.persist(e1);
                transaction.commit();
            } catch (Exception ex) {
                if (transaction != null) {
                    transaction.rollback();
                }
                ex.printStackTrace();
            }
        }
    }
}

