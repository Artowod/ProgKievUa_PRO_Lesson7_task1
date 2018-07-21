import javax.persistence.*;

@Entity
@Table(name="RestaurantMenu")
public class RestaurantMenu {
    @Id
    @GeneratedValue
    @Column(name="id", nullable = false)
    private long id;

    @Column(name="dish", nullable = false)
    private String dishName;

    @Column(name = "weight")
    private double dishWeight;

    @Column(name="prise")
    private double dishPrise;

    @Column(name="discount")
    private double dishDiscount;

    public RestaurantMenu() {
    }

    public RestaurantMenu(String dishName, double dishWeight, double dishPrise, double dishDiscount) {
        this.dishName = dishName;
        this.dishWeight = dishWeight;
        this.dishPrise = dishPrise;
        this.dishDiscount = dishDiscount;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public double getDishWeight() {
        return dishWeight;
    }

    public void setDishWeight(double dishWeight) {
        this.dishWeight = dishWeight;
    }

    public double getDishPrise() {
        return dishPrise;
    }

    public void setDishPrise(double dishPrise) {
        this.dishPrise = dishPrise;
    }

    public double getDishDiscount() {
        return dishDiscount;
    }

    public void setDishDiscount(double dishDiscount) {
        this.dishDiscount = dishDiscount;
    }

    @Override
    public String toString() {
        return "RestaurantMenu{" +
                "id=" + id +
                ", dishName='" + dishName + '\'' +
                ", dishWeight=" + dishWeight +
                ", dishPrise=" + dishPrise +
                ", dishDiscount=" + dishDiscount +
                '}';
    }
}
