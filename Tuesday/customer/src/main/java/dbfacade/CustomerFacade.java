package dbfacade;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import entity.Customer;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Persistence;

public class CustomerFacade {

    private static EntityManagerFactory emf;
    private static CustomerFacade instance;
    
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");      
    CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);
    Date date = new Date();
    // Add customer to database
    Customer c1 = facade.addCustomer(date, "Mathias", "Clausen");
    Customer c2 = facade.addCustomer(date, "Gustav", "Kristensen");
    //Find customer by ID
    System.out.println("Customer 1: "+facade.findById(c1.getId()));
    System.out.println("Customer 2: "+facade.findById(c2.getId()));
    //Find number of customers
    System.out.println("Number of customers: "+facade.allCustomers().size());
    // Print all customers
    System.out.println("All customers "+ facade.allCustomers());
    // Find by lastname
    System.out.println("Customer by lastname "+facade.findByLastName(c1.getLastName()));
    System.out.println("Customer by lastname "+facade.findByLastName(c2.getLastName()));
      
    }
    

    private CustomerFacade() {
    }

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    public Customer addCustomer(Date date, String firstName, String lastName) {

        Customer cus = new Customer(date, firstName, lastName);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cus);
            em.getTransaction().commit();
            return cus;
        } finally {
            em.close();
        }
    }

    public Customer findById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer cus = em.find(Customer.class, id);
            return cus;
        } finally {
            em.close();
        }
    }

    public List<Customer> findByLastName(String lastName) {
        EntityManager em = emf.createEntityManager();
             List<Customer> all = allCustomers();
             List<Customer> specificLastName = new ArrayList<>();
             
            for (Customer customer : all) {
                if (customer.getLastName().equals(lastName)) {
                    specificLastName.add(customer);
                }
            }                           
             return specificLastName;
    }

    public int getNumberOfCustomers() {

        return 1;
    }

    public List<Customer> allCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query
                    = em.createQuery("Select customer from Customer customer", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
