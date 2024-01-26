import modelo.Alumno;
import modelo.Grupo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class _04_CreaObjetos {
    public static void main(String[] args) {
        try (
                SessionFactory sessionFactory = new Configuration().configure().
                        buildSessionFactory();
                Session s = sessionFactory.openSession()) {
            Transaction t = null;
            try {
                t = s.beginTransaction();

                //s.persist();
                //t.commit();
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
