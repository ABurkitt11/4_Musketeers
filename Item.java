import java.util.ArrayList;

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


    public String toString(ArrayList<Item> items)
    {
        for(int i = 0; i < items.size(); i++)
        {
            System.out.println("W: "+items.get(i).getWeight() + " P: " + items.get(i).getPrice());
        }

        return null;
    }
    public int getPrice() {
        return price;
    }
}