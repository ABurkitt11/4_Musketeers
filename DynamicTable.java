import java.util.ArrayList;
import java.math.*;

public class DynamicTable {
    private int capacity;
    private int itemAmount;
    public int[][] DTable;

    public DynamicTable(int newCapacity, int newItemAmount) {
        itemAmount = newItemAmount;
        capacity = newCapacity;
        DTable = new int[capacity + 1][itemAmount + 1];

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

        for (int w = 0; w <= capacity; w++) {
        DTable[w][0] = 0; 
        }

        for (int i = 1; i <= itemAmount; i++) {
            for (int w = 0; w <= capacity; w++) {
                int t = DTable[w][i - 1];
                if (datastorage.get(i - 1).getWeight() <= w) {
                    t = Math.max(t, datastorage.get(i - 1).getPrice() + DTable[w - datastorage.get(i - 1).getWeight()][i - 1]);
                }
                DTable[w][i] = t;
            }
        }

    }

    public ArrayList<Item> countTheMoney(ArrayList<Item> datastorage){
        i = itemAmount;
        k = capacity;
        ArrayList<Item> answer = new ArrayList<>();
        while (i != 0 && k != 0) {
            if(DTable[i][k] != DTable[i-1][k]) {
                answer.add(datastorage.get(i));
                k = k-datastorage.get(i).getWeight();
                i = i-1;
            }
            else i = i-1;
        }
        return answer;
    }



}
