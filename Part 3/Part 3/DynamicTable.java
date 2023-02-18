import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
    //* Part 3, only saves a new entry if it is larger and has even price & odd weight. Also dosnt work. but it works for the provided input :) */
    public void buildDynamicTable(ArrayList<Item> datastorage) throws FileNotFoundException {
        for (int w = 0; w <= capacity; w++) {
            DTable[0][w] = new Item(0,0);              
        }

        for (int i = 1; i <= itemAmount; i++) {
            for (int w = 0; w <= capacity; w++) {
                Item tableEntry = new Item(DTable[i - 1][w].getWeight(),DTable[i - 1][w].getPrice());
                Item tempItem = new Item(DTable[i - 1][w].getWeight(),DTable[i - 1][w].getPrice());
                if ((datastorage.get(i - 1).getWeight() <= w)) {
                    for(int  j = i - 1; j > 0; j --){// loops backwards through available items and finds the largest legal combination, stores it in tempItem ... if it worked
                    int price = datastorage.get(i - 1).getPrice() + DTable[j][w - datastorage.get(j).getWeight()].getPrice();
                    int weight = datastorage.get(i - 1).getWeight() + DTable[j][w - datastorage.get(j).getWeight()].getWeight();
                        if(tempItem.getPrice()<=price){
                        tempItem.setPrice(price);
                        tempItem.setWeight(weight);
                        System.out.println(weight+" "+price);
                        if(!(DTable[i-1][w].getPrice()==0)){
                            if((price % 2 == 0) && (weight % 2 != 0)){
                                if(price > tableEntry.getPrice()){
                                    
                                    tableEntry.setPrice(price);
                                    tableEntry.setWeight(weight);
                                }
                            }
                        }
                        else if(price > tableEntry.getPrice()){
                                tableEntry.setPrice(price);
                                tableEntry.setWeight(weight);
                        
                        }   
                    }
                }
                if(tableEntry.getPrice()>DTable[i - 1][w].getPrice()){
                    DTable[i][w] = new Item(tableEntry.getWeight(),tableEntry.getPrice());
                }
                else{
                    DTable[i][w] = new Item(DTable[i - 1][w].getWeight(),DTable[i - 1][w].getPrice());  //copies the value from above if the new one is smaller, dynamic programming!
                }
                }
                else{
                    DTable[i][w] = new Item(DTable[i - 1][w].getWeight(),DTable[i - 1][w].getPrice());
                }
            }
        }



        System.out.println("Total Value: " + DTable[itemAmount][capacity].getPrice());
        System.out.println("Total Weight: " + DTable[itemAmount][capacity].getWeight());
        countTheMoney(datastorage);
        printAr(DTable);
    }
/**
     * Reads through the table and produces the weight and price of the
     * chosen items
     * @param dataStorage arraylist that all the items provided with their weight and price
     */
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
    
//*Takes the final price/weight, uses them to find the items that were used to get them.*/
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