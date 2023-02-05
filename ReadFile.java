import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


//Reads and stores the file
public class ReadFile {

    public static ArrayList<int[]> readfile(File filePath) throws FileNotFoundException {
        Scanner sc = new Scanner(filePath);
        int backpackSize = 0;
        backpackSize =  sc.nextInt();
        ArrayList<int[]> dataStorage = new ArrayList<>();
        int[] temp;
        int i = 0;


        while (sc.hasNextInt())
        {
            dataStorage.add(new int[3]);
            temp = dataStorage.get(i);
            temp[0] = sc.nextInt();
            temp[1] = sc.nextInt();
            temp[2] = sc.nextInt();

            dataStorage.set(i, temp);
            i++;
        }

        //Print the data to make sure it is right
        for(int j = 0; j < dataStorage.size(); j++)
        {
            temp = dataStorage.get(j);
            System.out.print(temp[0]);
            System.out.print(temp[1]);
            System.out.println(temp[2]);

        }


        return dataStorage;

    }


}
