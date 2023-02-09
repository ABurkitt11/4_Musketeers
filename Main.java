import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    //Sets up the file read and calls the methods
    public static void main(String args[]) throws FileNotFoundException {
//        Scanner sc = new Scanner(System.in);
//        File f = new File(sc.nextLine());
        File f = new File("D:\\java\\AI\\Knapsack\\src\\SampleKnapsackData.txt");
//        sc.close();


        ArrayList<Item> dataStorage = new ArrayList<>();
        ArrayList<Item> temp = new ArrayList<>();
        dataStorage = ReadFile.readfile(f);

        DynamicTable dynamicTable = new DynamicTable(dataStorage.remove(0).getWeight(), dataStorage.size());
        System.out.println("test");

        dynamicTable.buildDynamicTable(dataStorage);
        temp = dynamicTable.countTheMoney(dataStorage);

        System.out.println("items used: ");
        for(int i = 0; i < temp.size(); i++)
        {
            System.out.println("W: "+temp.get(i).getWeight() + " P: " + temp.get(i).getPrice());
        }









    }

}