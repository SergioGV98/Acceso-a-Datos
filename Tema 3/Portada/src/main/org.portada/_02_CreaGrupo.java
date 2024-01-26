import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class _02_CreaGrupo {

    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("ERROR: indicar nombre de grupo");
            return;
        }
        String nomGrupo = args[0];

        try (
                SessionFactory sessionFactory = new Configuration().configure().
                        buildSessionFactory();
                Session s = sessionFactory.openSession()) {
            Transaction t = null;
            try {
                t = s.beginTransaction();
                Grupo grupo = new Grupo();
                grupo.setNombre(nomGrupo);
                grupo.setMaxAlumnos(32);
                s.persist(grupo);
                t.commit();
                System.out.printf("Nuevo grupo creado con id %d: %s\n", grupo.getId(), grupo);
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


