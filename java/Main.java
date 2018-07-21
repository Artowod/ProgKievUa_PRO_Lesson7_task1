import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {

    static EntityManagerFactory entityMF;
    static EntityManager entityManager;

    public static void main(String[] args) {
        entityMF = Persistence.createEntityManagerFactory("JPAHibernate");
        entityManager = entityMF.createEntityManager();
        RestaurantManager restaurantManager = new RestaurantManager(entityMF,entityManager);
        restaurantManager.addNewDishRecord();
        restaurantManager.getDishByPrise(100,800);
        restaurantManager.getDishWithDiscount();
        entityManager.close();
        entityMF.close();
    }
}
