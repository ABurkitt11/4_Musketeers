import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.*;

public class DynamicTable {
    private int capacity;
    private int itemAmount;
    public Item[][] DTable;

    public DynamicTable(int newCapacity, int newItemAmount) {
        itemAmount = newItemAmount;
        capacity = newCapacity;

        DTable = new Item[itemAmount + 1][capacity + 1];

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

    // part 3, even price odd weight
    public void buildDynamicTable(ArrayList<Item> datastorage) throws FileNotFoundException {
        for (int w = 0; w <= capacity; w++) {
            DTable[0][w] = new Item(0,0);              
        }

        for (int i = 1; i <= itemAmount; i++) {
            for (int w = 0; w <= capacity; w++) {
                Item tableEntry = new Item(DTable[i - 1][w].getWeight(),DTable[i - 1][w].getPrice());
                Item largestCombination = new Item(DTable[i - 1][w].getWeight(),DTable[i - 1][w].getPrice());
                if ((datastorage.get(i - 1).getWeight() <= w)) {
                    for(int j = i - 1; j >= 0 ; j --){
                        if ((datastorage.get(j).getWeight() <= w)) {
                            int price = datastorage.get(i - 1).getPrice() + DTable[j][w - datastorage.get(j).getWeight()].getPrice(); 
                            int weight = datastorage.get(i - 1).getWeight() + DTable[j][w - datastorage.get(j).getWeight()].getWeight();
                            if(price % 2 == 0 && price > largestCombination.getPrice() && weight % 2 != 0){
                                largestCombination.setPrice(price);
                                largestCombination.setWeight(weight);
                            }
                        }
                    }
                    
                    if(largestCombination.getPrice() > tableEntry.getPrice()){
                        tableEntry.setPrice(largestCombination.getPrice());
                        tableEntry.setWeight(largestCombination.getWeight());
                    }
                }
                DTable[i][w] = new Item(tableEntry.getWeight(), tableEntry.getPrice());
            }
        }

        System.out.println("Total Value: " + DTable[itemAmount][capacity].getPrice());
        System.out.println("Total Weight: " + DTable[itemAmount][capacity].getWeight());
        countTheMoney(datastorage);
        printAr(DTable);
    }

    public void printAr(Item[][] A) throws FileNotFoundException {
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
    public void init_table() {
        for (int i = 0; i <= itemAmount; i++) {
            for (int w = 0; w <= capacity; w++) {
                DTable[i][w] = new Item(0, 0);
            }
        }
    }
    
    public void countTheMoney(ArrayList<Item> datastorage) {
        int i = itemAmount;
        int k = capacity;
        ArrayList<Integer> answer = new ArrayList<>();
    
        while (i > 0 && k > 0) {
            if (DTable[i][k].getPrice() != DTable[i - 1][k].getPrice()) {
                answer.add(i);
                k = k - datastorage.get(i - 1).getWeight();
                i = i - 1;
            } else
                i = i - 1;
    
        }
    
        System.out.print("Item ID List: ");
        for (int j = answer.size()-1; j >=0; j--) {
            System.out.print(answer.get(j) + " ");
        }
    }
    
    
    }

