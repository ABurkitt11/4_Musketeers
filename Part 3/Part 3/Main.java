import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    /**
     * Sets up the file read and calls the methods
     * @throws FileNotFoundException
     **/
    public static void main(String args[]) throws FileNotFoundException {
        
    	Scanner sc = new Scanner(System.in);
        System.out.println("0-1 Knapsack Problem but the final item list has odd weight and even price ");
        System.out.print("Please enter the data file name: ");
        File f = new File(sc.nextLine());
        System.out.println("Processing..");
        ArrayList<Item> dataStorage = new ArrayList<>();
        ArrayList<Item> temp = new ArrayList<>();
        dataStorage = ReadFile.readfile(f);
        System.out.println("Done!");
        System.out.println("Result:");
        System.out.println("============================================");
        DynamicTable dynamicTable = new DynamicTable(dataStorage.remove(0).getWeight(), dataStorage.size());
        dynamicTable.buildDynamicTable(dataStorage);
        

    }

}