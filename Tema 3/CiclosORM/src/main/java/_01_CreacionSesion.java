import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
public class _01_CreacionSesion {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure().
                buildSessionFactory();
             Session s = sessionFactory.openSession()) {
            System.out.println("Sesión iniciada");
        }
    }
}
