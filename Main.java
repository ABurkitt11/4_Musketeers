import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    //Sets up the file read and calls the methods
    public static void main(String args[]) throws FileNotFoundException {
        //        Scanner sc = new Scanner(System.in);
        //        File f = new File(sc.nextLine());
                Scanner sc = new Scanner(System.in);
                System.out.println("0-1 Knapsack Problem");
                System.out.print("Please enter the data file name: ");
                File f = new File(sc.nextLine());
                sc.close();
                
                System.out.println("Processing..");
                ArrayList<Item> dataStorage = new ArrayList<>();
                ArrayList<Item> temp = new ArrayList<>();
                dataStorage = ReadFile.readfile(f);
                System.out.println("Done!");
                System.out.println("Result:");
        
                DynamicTable dynamicTable = new DynamicTable(dataStorage.remove(0).getWeight(), dataStorage.size());
                
                
                
                dynamicTable.buildDynamicTable(dataStorage);
                temp = dynamicTable.countTheMoney(dataStorage);
        
                System.out.println("items used: ");
                for(int i = 0; i < temp.size(); i++)
                {
                    System.out.println("W: "+temp.get(i).getWeight() + " P: " + temp.get(i).getPrice());
                }
        









    }

}