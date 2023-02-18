/**
 * A datatype that holds the weight and price of an item
 */
public class Item {

    private int weight;
    private int price;

    public Item(int newWeight, int newPrice) {
        weight = newWeight;
        price = newPrice;
    }

    public int getWeight() {
        return weight;
    }

    public int getPrice() {
        return price;
    }
}
