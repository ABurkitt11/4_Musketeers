import java.util.ArrayList;
/**Items from the file are saved as items, and items in the dynamic table are saved as items. Holds a weight and a price.(ints)**/

public class Item {
    private int weight;
    private int price;
    public Item(int newWeight, int newPrice) {
        weight = newWeight;
        price = newPrice;
    }
    public void setWeight(int newWeight) {
        weight = newWeight;
    }
    public int getWeight() {
        return weight;
    }
    public void setPrice(int newPrice) {
        price = newPrice;
    }
    public int getPrice() {
        return price;
    }
}