import java.util.ArrayList;
import java.math.*;
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
    public void buildDynamicTable(ArrayList<Item> datastorage) {

//        for (int w = 0; w <= capacity; w++) {
//            DTable[w][0] = 0;
//        }

        for (int i = 1; i <= itemAmount; i++) {
            for (int w = 0; w <= capacity; w++) {
                int t = DTable[i - 1][w];
                if (datastorage.get(i - 1).getWeight() <= w) {
                    t = Math.max(t, datastorage.get(i - 1).getPrice() + DTable[i - 1][w - datastorage.get(i - 1).getWeight()]);
                }
                DTable[i][w] = t;
            }
        }


        printAr(DTable);
    }

    public void printAr(int [][] A)
    {
        for(int i = 0; i < A.length; i++)
        {
            for(int j = 0; j < A[i].length; j++)
            {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
    }



}