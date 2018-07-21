import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RestaurantManager {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;


    public RestaurantManager() {
    }

    public RestaurantManager(EntityManagerFactory entityManagerFactory, EntityManager entityManager) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManager;
    }

    private void showQueryResult(List<RestaurantMenu> result) {
        for (RestaurantMenu resultString : result) {
            System.out.println("[Name: " + resultString.getDishName() + ", Weight: " + resultString.getDishWeight() +
            ", Prise: " + resultString.getDishPrise() + ", Discount: " + resultString.getDishDiscount() + "]");
        }
    }

    public void getDishByPrise(double fromPrise, double toPrise) {
        List<RestaurantMenu> resultList;
        try {
            RestaurantMenu rm;
            Query query = entityManager.createQuery("SELECT rm FROM RestaurantMenu rm WHERE rm.dishPrise > :priseFrom AND rm.dishPrise < :priseTo", RestaurantMenu.class);
            query.setParameter("priseFrom", fromPrise);
            query.setParameter("priseTo", toPrise);
            resultList = (List<RestaurantMenu>) query.getResultList();
        } catch (NoResultException ex) {
            System.out.println("No such Dishes in menu");
            return;
        }
        System.out.println("Dishes with prise between " + fromPrise + " and " + toPrise + ":");
        showQueryResult(resultList);
    }

    public void getDishWithDiscount() {
        List<RestaurantMenu> resultList;
        try {
            RestaurantMenu rm;
            Query query = entityManager.createQuery("SELECT rm FROM RestaurantMenu rm WHERE rm.dishDiscount > 0", RestaurantMenu.class);
            resultList = (List<RestaurantMenu>) query.getResultList();
        } catch (NoResultException ex) {
            System.out.println("No such Dishes in menu");
            return;
        }
        System.out.println("Dishes with discount:");
        showQueryResult(resultList);
    }


    private boolean isDishExists(String dishName) {
        try {
            RestaurantMenu rm;
            Query query = entityManager.createQuery("SELECT rm FROM RestaurantMenu rm WHERE rm.dishName = :name", RestaurantMenu.class);
            query.setParameter("name", dishName);
            rm = (RestaurantMenu) query.getSingleResult();
        } catch (NoResultException ex) {
            return false;
        }
        System.out.println("Such Dish is already in.");
        return true;
    }


    public void addNewDishRecord() {
        System.out.println("Adding the new Dish to the menu:");
        try {
            System.out.print("Enter name of a dish: ");
            String dishName = (new Scanner(System.in)).nextLine();
            if (isDishExists(dishName)) {
                return;
            }
            System.out.print("Enter weight: ");
            double weight = Double.valueOf((new Scanner(System.in)).nextLine());
            System.out.print("Enter prise: ");
            double prise = Double.valueOf((new Scanner(System.in)).nextLine());
            System.out.print("Enter discount (put zero if non): ");
            double discount = Double.valueOf((new Scanner(System.in)).nextLine());

            entityManager.getTransaction().begin();
            try {
                RestaurantMenu c = new RestaurantMenu(dishName, weight, prise, discount);

                entityManager.persist(c);
                entityManager.getTransaction().commit();
            } catch (Exception ex) {
                entityManager.getTransaction().rollback();
            }

        } catch (NumberFormatException nfe) {
            System.out.println("Entered data is incorrect.");
            return;
        }

    }
}
