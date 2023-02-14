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
    public void buildDynamicTable(ArrayList<Item> datastorage) throws FileNotFoundException {
        for (int w = 0; w <= capacity; w++) {
            DTable[0][w] = new Item(0,0);              
        }

        for (int i = 1; i <= itemAmount; i++) {
            for (int w = 0; w <= capacity; w++) {
                Item tableEntry = new Item(DTable[i - 1][w].getWeight(),DTable[i - 1][w].getPrice());
                Item t = new Item(DTable[i - 1][w].getWeight(),DTable[i - 1][w].getPrice());
                Item t2 = new Item(DTable[i - 1][w].getWeight(),DTable[i - 1][w].getPrice());//stores the entry one above incase a better one isnt found
                if ((datastorage.get(i - 1).getWeight() <= w)) {
                    for(int  j = i - 1; j >= 0 ; j --){// loops backwards through available items and finds the largest legal combination ... if it worked
                        //i think t needs to be saved outside of this loop and reset at the end of the loop. or maybe stored as a 3rd variable with items
                        //t stores the largest combination, and ideally once a legal one is found the loop ends. currently it keeps looping, but it also dosnt work
                    int price = datastorage.get(i - 1).getPrice() + DTable[j][w - datastorage.get(j).getWeight()].getPrice(); 
                    int weight =datastorage.get(i - 1).getWeight() + DTable[j][w - datastorage.get(j).getWeight()].getWeight();
                        if(t.getPrice()<=price){
                        t.setPrice(price);
                        t.setWeight(weight);
                        if((price % 2 == 0) && (weight % 2 != 0)){
                            if(price > tableEntry.getPrice()){
                                tableEntry.setPrice(price);
                                tableEntry.setWeight(weight);
                        }
                    }
                    
                }
                }
                if(tableEntry.getPrice()>t2.getPrice()){
//                    DTable[i][w] = tableEntry; probably same as v
                    DTable[i][w] = new Item(tableEntry.getWeight(),tableEntry.getPrice());
                }
                else{
//                    DTable[i][w] = t2;
                    DTable[i][w] = new Item(t2.getWeight(),t2.getPrice());
                }
                
                }
                else{
//                    DTable[i][w] = t2;
                    DTable[i][w] = new Item(t2.getWeight(),t2.getPrice());
                }
            }
        }



        System.out.println("Total Value: " + DTable[itemAmount][capacity].getPrice());
        System.out.println("Total Weight: " + DTable[itemAmount][capacity].getWeight());
        countTheMoney(datastorage);
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
    

    public void countTheMoney(ArrayList<Item> datastorage){
        int i = itemAmount;
        int k = capacity;
        ArrayList<Item> answer = new ArrayList<>();


        while (i > 0 && k > 0) {
            if(DTable[i][k].getPrice() != DTable[i-1][k].getPrice())
            {
                answer.add(datastorage.get(i-1));
                k = k - datastorage.get(i-1).getWeight();
                i = i - 1;
            }
            else i = i-1;

        }
        System.out.print("Item ID List: ");
        for(int j = 0; j < datastorage.size(); j++)
        {
            for(int i1 = 0; i1 < answer.size(); i1++)
            {
            	if(datastorage.get(j).getWeight() == answer.get(i1).getWeight() && datastorage.get(j).getPrice()==answer.get(i1).getPrice()){
            		 System.out.print(j+1 + " ");
            	}
            }
        }
    }




}