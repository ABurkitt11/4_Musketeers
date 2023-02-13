import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.*;
public class DynamicTable {
    private int capacity;
    private int itemAmount;
    private int left;// how far left of the original result the p3 result is
    private int up;// if its up
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
    public void buildDynamicTable(ArrayList<Item> datastorage) throws FileNotFoundException {
        for (int w = 0; w <= capacity; w++) {
            DTable[0][w] = new Item(0,0);              
        }

        for (int i = 1; i <= itemAmount; i++) {
            for (int w = 0; w <= capacity; w++) {
                Item t = new Item(DTable[i - 1][w].getWeight(),DTable[i - 1][w].getPrice());// this is scuffed
                if (datastorage.get(i - 1).getWeight() <= w) {
                    int price = datastorage.get(i - 1).getPrice() + DTable[i - 1][w - datastorage.get(i - 1).getWeight()].getPrice();
                    int weight =datastorage.get(i - 1).getWeight() + DTable[i - 1][w - datastorage.get(i - 1).getWeight()].getWeight();
                    if(t.getPrice()<=price){ //hmm
                        t.setPrice(price);
                        t.setWeight(weight);
                        
                    }
                    
                }
                DTable[i][w] = t;
            }
        }
        //part 3, even price odd weight
        Item result = new Item(0,0);
        if((DTable[itemAmount][capacity].getPrice()%2 == 0) && (DTable[itemAmount][capacity].getWeight()%2 != 0)){
                result.setPrice(DTable[itemAmount][capacity].getPrice());
                result.setWeight(DTable[itemAmount][capacity].getWeight());
            
        }
        else{
            Item h = new Item(0,0);
            Item v = new Item(0,0);
            for(int i = itemAmount; i > 0;i --){
                int p = DTable[i][capacity].getPrice();
                int w = DTable[i][capacity].getWeight();
                if((p % 2 == 0) && (w % 2 != 0)){
                    if(p>v.getPrice()){
                        v.setPrice(p); // maybe should be v = DTable[i][capacity]
                        v.setWeight(w);
                        up++;
                    }
                }
                
            }
            for(int i = capacity; i > 0; i--){
                int p = DTable[itemAmount][i].getPrice();
                int w = DTable[itemAmount][i].getWeight();
                if((p % 2 == 0) && (w % 2 != 0)){
                    if(p>h.getPrice()){
                        h.setPrice(p);
                        h.setWeight(w);
                        left++;
                    }
                }
                
            }
            if(h.getPrice() > v.getPrice()){
                result.setPrice(h.getPrice());
                result.setWeight(h.getWeight());
                up = 0;
            }
            else{
                result.setPrice(v.getPrice());
                result.setWeight(v.getWeight());
                left = 0;
            }

        }

        System.out.println("Total Value: "+result.getPrice());
        System.out.println("Total Weight: "+result.getWeight());
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
        System.out.println(DTable[itemAmount][capacity].getWeight());
        
        
        
    }
    

    public void countTheMoney(ArrayList<Item> datastorage){
        int i = itemAmount - up;
        int k = capacity - left;
        ArrayList<Item> answer = new ArrayList<>();


        while (i != 0 && k != 0) {
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