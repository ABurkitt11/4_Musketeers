public class DynamicTable {
    private int capacity;
    private int itemAmount;
    public int[][] DTable;

    public DynamicTable(int newCapacity, int newItemAmount) {
        itemAmount = newItemAmount;
        capacity = newCapacity;
        DTable = new int[capacity+1][itemAmount+1];
        
        for (int i = 0; i <= itemAmount.length(); i++) {
            for (int j = 0; j <= capacity; j++) {
                DTable[i][j] = Math.max();
            }
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

}
