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
    public void buildDynamicTable(ArrayList<Item> datastorage) throws FileNotFoundException {

//        for (int w = 0; w <= capacity; w++) {
//            DTable[w][0] = 0;
//        }

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
        printAr(DTable);
//        return DTable; 
    }

    public void printAr(int [][] A) throws FileNotFoundException
    {
    	System.out.println("\n ==============================");
        System.out.println("Outputting DynamicTable.txt...");
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



    public ArrayList<Item> countTheMoney(ArrayList<Item> datastorage){
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
        return answer;
    }




}