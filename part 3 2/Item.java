import java.util.ArrayList;
import java.util.*;
public class Item {

    private int weight;
    private int price;
    private int id;
    
    private ArrayList<Integer>Items;
    
    public Item(int newWeight, int newPrice) {
        weight = newWeight;
        price = newPrice;
        Items = new ArrayList<>();
        id = 0;
    }
    public Item(int newt, int newWeight, int newPrice){
        id = newt;
        weight = newWeight;
        price = newPrice;
        Items = new ArrayList<>();
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
    public void addID(int i) {
        this.id= this.id + i;
    }

    public int getID() {
        return this.id;
    }
    public int getItem(int i){
        return Items.get(i);
    }
    public Boolean addItem(int itemID) {
        ArrayList<Integer> t= new ArrayList<Integer>(); 
        t = Items;
        t.add(itemID);
        if(noDupes(t)){
            Items.add(itemID);
            return true;
            
        }
        return false;
    }
/*     public void addItems(ArrayList<Integer> i){
        ArrayList<Integer> t = new ArrayList<Integer>();
        t = Items;
        t.addAll(i);
        if(noDupes(t)){
            
        }
        Items.addAll(i);
    }*/
    public void setItems(ArrayList<Integer> i){
        Items.clear();
        Items.addAll(i);
        //Items = is;
    }

    public ArrayList<Integer> getItems(){
        return Items;
    }
    public void reset(){
        this.price = 0;
        this.weight = 0;
        Items.clear();
    }
    public void resetItems(){
        Items.clear();
    }
     public int totalPrice(ArrayList<Item> b){
        
        int p = 0;
        for(int j = 1;j < b.size(); j++){
            if(Items.contains(j)){
                p = p + b.get(j).getPrice(); // returns the sum of the prices from the member item ids
            }
        }
        return p;
    }
    public int totalWeight(ArrayList<Item> b){
        
        int p = 0;
        for(int j = 1;j < b.size(); j++){
            if(Items.contains(j)){
                p = p + b.get(j).getWeight();
            }
        }
        return p;
    }

    
    boolean noDupes(ArrayList<Integer> list) {//from stack overflow,should change to just use a loop but idk if this is even needed
        for(int i = 0; i< list.size(); i++){
            for(int j = i + 1; j < i; j++){
                if(list.get(i) == list.get(j)){
                    return true;
                }
            }
        }

        return false;
    }

    
}