import modelo.Sede;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class _02_CreaSede {

    public static void main(String[] args) {
        try (
                SessionFactory sessionFactory = new Configuration().configure().
                        buildSessionFactory();
                Session s = sessionFactory.openSession()) {
            Transaction t = null;
            try {
                t = s.beginTransaction();
                Sede s1 = new Sede("Riot games", new ArrayList<>());
                Sede s2 = new Sede("Ubisoft",  new ArrayList<>());
                s.persist(s1);
                s.persist(s2);
                t.commit();
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


