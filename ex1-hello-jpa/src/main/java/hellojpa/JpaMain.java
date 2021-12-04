package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        // persistence.xml의 <persistence-unit name="hello"> 값을 넘기면 됨
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member foundMember = em.find(Member.class, 1L);
            foundMember.setName("구르르릉막");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            // 내부에서 DB 컬렉션을 물고 동작하기 때문에, 작업이 끝나면 무조건 닫아주어야 한다.
            em.close();
        }

        emf.close();
    }
}
