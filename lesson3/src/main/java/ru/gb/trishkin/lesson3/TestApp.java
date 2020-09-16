package ru.gb.trishkin.lesson3;

import org.hibernate.cfg.Configuration;
import ru.gb.trishkin.lesson3.domain.Client;
import ru.gb.trishkin.lesson3.domain.Order;
import ru.gb.trishkin.lesson3.domain.Product;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class TestApp {

    public static void main(String[] args) {
        EntityManagerFactory managerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = managerFactory.createEntityManager();

        createClient(em, "Petr");
        createClient(em, "Vasily");
        createClient(em, "Roman");

        createProduct(em, "Milk", 50.25f);
        createProduct(em, "Bread", 20.50f);
        createProduct(em, "Apple", 45.80f);

        createOrder(em, 1, 1);
        createOrder(em, 1, 2);
        createOrder(em, 1, 3);

        createOrder(em, 2, 1);
        createOrder(em, 2, 3);
        createOrder(em, 3, 2);

//        deleteClientById(em, 3);
        deleteProductById(em, 1);
        findByClient(em, 1);
        findByProduct(em, 3);

    }

    private static <T> void createEntity(EntityManager em, T entity){
        System.out.println("Start create entity");
        //Начало транзакции
        em.getTransaction().begin();
        //Добавление объекта в контекст постоянства
        em.persist(entity);
        //Добавление в базу данных и закрытие транзакции
        em.getTransaction().commit();
        System.out.println("Entity created");
    }

    private static <T> T readEntity(EntityManager em, Class<T> tClass, long id){
        System.out.println("Start reading");

        em.getTransaction().begin();
        T person = em.find(tClass, id);
        em.getTransaction().commit();

        System.out.println("Reading completed->" + person);
        return person;
    }

    private static void createClient(EntityManager em, String name){
        Client client = new Client();
        client.setName(name);
        List<Order> order = em.createQuery("SELECT o FROM Order o WHERE o.client.id = client.id").getResultList();
        client.setOrders(order);
        createEntity(em, client);
    }

    private static void createProduct(EntityManager em, String title, float price){
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        List<Order> order = em.createQuery("SELECT o FROM Order o WHERE o.product.id = product.id").getResultList();
        product.setOrders(order);
        createEntity(em, product);
    }

    private static void createOrder(EntityManager em, long clientId, long productId){
        Order order = new Order();
        Client client = readEntity(em, Client.class, clientId);
        Product product = readEntity(em, Product.class, productId);
        client.getOrders().add(order);
        product.getOrders().add(order);
        order.setClient(client);
        order.setProduct(product);
        createEntity(em, order);
    }

    private static void findByClient(EntityManager em, long id){
        em.getTransaction().begin();
        Client client = em.find(Client.class, id);
        System.out.println("======================================");
        System.out.println(client);
        System.out.println("______________________________________");
        System.out.println("Products: ");
        for (Order o: client.getOrders()) {
            System.out.println(o.getProduct().getTitle());
        }
        System.out.println("======================================");
        em.getTransaction().commit();
    }

    private static void findByProduct(EntityManager em, long id){
        em.getTransaction().begin();
        Product product = em.find(Product.class, id);
        System.out.println("======================================");
        System.out.println(product);
        System.out.println("______________________________________");
        System.out.println("Clients: ");
        for (Order o: product.getOrders()) {
            System.out.println(o.getClient().getName());
        }
        System.out.println("======================================");
        em.getTransaction().commit();
    }

    private static void deleteClientById(EntityManager em, long id){
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Order o WHERE o.client.id = :id").setParameter("id", id).executeUpdate();
        em.createQuery("DELETE FROM Client c WHERE id = :id").setParameter("id", id).executeUpdate();
        em.getTransaction().commit();
    }
    private static void deleteProductById(EntityManager em, long id){
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Order o WHERE o.product.id = :id").setParameter("id", id).executeUpdate();
        em.createQuery("DELETE FROM Product c WHERE id = :id").setParameter("id", id).executeUpdate();
        em.getTransaction().commit();
    }
}
