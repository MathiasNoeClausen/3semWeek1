package entity;


import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityTested {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        Date date = new Date();
        Customer cus = new Customer(date, "Mathias", "Clausen");
        Customer cus1 = new Customer(date, "Gustav", "Kristensen");
        Customer cus2 = new Customer(date, "Rasmus", "Ulstrup");
        em.getTransaction().begin();
        
        em.persist(cus);
        em.persist(cus1);
        em.persist(cus2);
        
        em.getTransaction().commit();
    }
}
