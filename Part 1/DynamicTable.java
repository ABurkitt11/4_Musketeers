import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * A datatype that holds the table as well as the amount of items
 * and the capacity of the knapsack
 */
public class DynamicTable {
    private int capacity;
    private int itemAmount;
    public int[][] DTable;


    /**
     * Creates the dynamic table with the specifications of capacity and item amount.
     * @param newCapacity The capacity of the knapsack
     * @param newItemAmount The amount of possible items
     */
    public DynamicTable(int newCapacity, int newItemAmount) {
        itemAmount = newItemAmount;
        capacity = newCapacity;
        DTable = new int[itemAmount+1][capacity+1];

    }


    /**
     * Fills out the dynamic table, with the largest value that is within the weight capacity
     * @param dataStorage arraylist that all the items provided with their weight and price
     * @throws FileNotFoundException if it cannot find the file
     */
    public void buildDynamicTable(ArrayList<Item> dataStorage) throws FileNotFoundException {


        for (int i = 1; i <= itemAmount; i++) {
            for (int w = 0; w <= capacity; w++) {
                int t = DTable[i - 1][w];
                if (dataStorage.get(i - 1).getWeight() <= w) {
                    t = Math.max(t, dataStorage.get(i - 1).getPrice() + DTable[i - 1][w - dataStorage.get(i - 1).getWeight()]);
                }
                DTable[i][w] = t;
            }
        }


        System.out.println("Total Value: "+DTable[itemAmount][capacity]);
        countTheMoney(dataStorage);
        printAr(DTable);
    }


    /**
     * Reads through the table and produces the weight and price of the
     * chosen items
     * @param dataStorage arraylist that all the items provided with their weight and price
     */
    public void countTheMoney(ArrayList<Item> dataStorage){
        int i = itemAmount;
        int k = capacity;
        ArrayList<Item> answer = new ArrayList<>();


        while (i != 0 && k != 0) {
            if(DTable[i][k] != DTable[i-1][k])
            {
                answer.add(dataStorage.get(i-1));
                k = k - dataStorage.get(i-1).getWeight();
                i = i - 1;
            }
            else i = i-1;

        }
        System.out.print("Item ID List: ");
        for(int j = 0; j < dataStorage.size(); j++)
        {
            for (Item item : answer) {
                if (dataStorage.get(j).getWeight() == item.getWeight() && dataStorage.get(j).getPrice() == item.getPrice()) {
                    System.out.print(j + 1 + " ");
                }
            }
        }
    }


    /**
     * Prints an array to new file DynamicTable.txt
     * @param A Array that will be output to DynamicTable.txt
     * @throws FileNotFoundException if it cannot find the file
     */
    public void printAr(int [][] A) throws FileNotFoundException
    {
        System.out.println("\n============================================");
        System.out.println("Outputting DynamicTable.txt..");
        PrintWriter pw = new PrintWriter(new File("DynamicTable.txt"));
        for (int[] ints : A) {
            for (int anInt : ints) {
                pw.print(anInt + " ");
            }
            pw.println();
        }
        pw.close();
        System.out.println("Done!");
        System.out.println("End of Processing.");
    }

}