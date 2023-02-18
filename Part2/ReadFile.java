import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


//Reads and stores the file in an arraylist of Items
public class ReadFile {
    /**
     * Reads and stores the file in an arraylist of Items
     * @Exception FileNotFoundException
     */
    public static ArrayList<Item> readfile(File filePath) throws FileNotFoundException {
        Scanner sc = new Scanner(filePath);
        ArrayList<Item> dataStorage = new ArrayList<>();


        //this is to just store the backpack capacity.
        dataStorage.add(new Item(sc.nextInt(),0));



        //This is to read the data and add it to the array list as an Item
        while (sc.hasNextInt())
        {
            sc.nextInt();
            dataStorage.add(new Item(sc.nextInt(), sc.nextInt()));

        }

        return dataStorage;

    }
    
}
