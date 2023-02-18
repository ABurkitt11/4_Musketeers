import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.*;
import java.io.PrintWriter;

public class DynamicTable {
    private int capacity;
    private int itemAmount;
    public int[][] DTable;
    public DynamicTable(int newCapacity, int newItemAmount) {
        itemAmount = newItemAmount;
        capacity = newCapacity;
        DTable = new int[itemAmount+1][capacity+1];

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
    
    /**
    * Building the Dynamic Table.
    * @param datastorage
    * @throws FileNotFoundException
    * Fill out the table with multiple selections of each items
    **/
    
    public void buildDynamicTable(ArrayList<Item> datastorage) throws FileNotFoundException {


    	for (int i = 1; i <= itemAmount; i++) {
    	    for (int w = 0; w <= capacity; w++) {
    	        int t = DTable[i - 1][w];
    	        if (datastorage.get(i - 1).getWeight() <= w) {
    	            t = Math.max(t, DTable[i][w - datastorage.get(i - 1).getWeight()] + datastorage.get(i - 1).getPrice());
    	        }
    	        DTable[i][w] = Math.max(t, DTable[i - 1][w]);
    	    }
    	}


    	System.out.println("Total Value: "+DTable[itemAmount][capacity]);
    	countTheMoney(datastorage);
    	printAr(DTable);
        

        
    }
    /**
    * @param Array
    * @Exception FileNotFoundException
    * prints out the dynamic table.
    **/

    public void printAr(int [][] A) throws FileNotFoundException
    {
    	System.out.println("\n============================================");
        System.out.println("Outputting DynamicTable.txt..");
        PrintWriter pw = new PrintWriter(new File("DynamicTable.txt"));
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                pw.print(A[i][j] + " ");
            }
            pw.println();
        }
        pw.close();
        System.out.println("Done!");
        System.out.println("End of Processing.");
        
        
        
    }
     /**
     * Reads through the table and produces the weight and price of the
     * chosen items
     * @param dataStorage arraylist that all the items provided with their weight and price
     */

    public void countTheMoney(ArrayList<Item> datastorage){
        int i = itemAmount;
        int k = capacity;
        ArrayList<Item> answer = new ArrayList<>();


        while (i != 0 && k != 0) {
            if(DTable[i][k] != DTable[i-1][k])
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
