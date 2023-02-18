import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.*;
import java.util.*;
public class DynamicTable {
    private int capacity;
    private int itemAmount;
    public Item t;
    public Item[][] DTable;
    public DynamicTable(int newCapacity, int newItemAmount) {
        itemAmount = newItemAmount;
        capacity = newCapacity;
        DTable = new Item[itemAmount+1][capacity+1];
    }

    public void setCapacity(int newCapacity) {
        capacity = newCapacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setItemAmount(int newItemAmount) {
        itemAmount = newItemAmount;
    }

    public int getItemAmount() {
        return itemAmount;
    }
            //part 3, even price odd weight
    public void buildDynamicTable(ArrayList<Item> datastorage) throws FileNotFoundException  {
        Item t = new Item(0,0);
        for (int w = 0; w <= capacity; w++) {
            DTable[0][w] = new Item(0,0);              
        }

        for (int i = 1; i <= itemAmount; i++) {
            for (int w = 0; w <= capacity; w++) {
                Item tableEntry = new Item(DTable[i - 1][w].getWeight(),DTable[i - 1][w].getPrice());
                Item t2 = new Item(DTable[i - 1][w].getWeight(),DTable[i - 1][w].getPrice());  //redundant atm
                if ((datastorage.get(i - 1).getWeight() <= w)) {
                    // loops backwards through available items and finds the largest legal combination ... if it worked
                        t.reset();
                        for(int j = i - 1; j >= 0 ; j --){
                        t.reset();// this aint it, do i really need another t
                        for(int p = 0; p <= j; p++){
                  //      if(DTable[i-1][w - datastorage.get(p).getWeight()].getItems().size() > 0){    
                            for(int l = 0; l < DTable[i-1][w - datastorage.get(p).getWeight()].getItems().size(); l++){
                                t.addItem(DTable[i-1][w - datastorage.get(p).getWeight()].getItem(l));
                          //      System.out.println(l);
                                System.out.println(DTable[i-1][w - datastorage.get(p).getWeight()].getItem(l));
                            }
                            t.addItem(datastorage.get(j).getID());       // the new "int price = datastorage.get(i - 1).getPrice() + DTable[j][w - datastorage.get(j).getWeight()].getPrice();"               
                                int price = t.totalPrice(datastorage);
                                int weight = t.totalWeight(datastorage);
                                t2.setItems(t.getItems());
                                if((price % 2 == 0) && (weight % 2 != 0) && (weight <= capacity)){//wieght check really shouldnt need to be here but it does
                                    if(price > tableEntry.getPrice()){
                                        tableEntry.setPrice(price);
                                        tableEntry.setWeight(weight);
                               //         t.addItem(datastorage.get(j).getID());
                                    }
                                }
                    }
                }
                
                DTable[i][w] = new Item(tableEntry.getWeight(),tableEntry.getPrice());
                DTable[i][w].setItems(t.getItems());

                }
                 else{
                    DTable[i][w] = new Item(DTable[i - 1][w].getWeight(),DTable[i - 1][w].getPrice());
                    DTable[i][w].setItems(DTable[i - 1][w].getItems());
                }
            }
        }



        System.out.println("Total Value: " + DTable[itemAmount][capacity].getPrice());
        System.out.println("Total Weight: " + DTable[itemAmount][capacity].getWeight());
        countTheMoney(DTable[itemAmount][capacity].getItems());
        printAr(DTable); 
    }

    public void printAr(Item [][] A) throws FileNotFoundException
    {
        System.out.println("\n============================================");
        System.out.println("Outputting DynamicTable.txt..");
        PrintWriter pw = new PrintWriter(new File("DynamicTable.txt"));
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                pw.print(A[i][j].getPrice() + " ");
            }
            pw.println();
        }
        pw.close();
        System.out.println("Done!");
        System.out.println("End of Processing.");
        
        
        
    }
    

    public void countTheMoney(ArrayList<Integer> items){
        System.out.print("Item ID List: ");
        for(int j = 0; j < items.size(); j++)
        {
            System.out.print(items.get(j)+" ");
        }
    }




}
