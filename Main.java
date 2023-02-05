import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    //Sets up the file read and calls the methods
    public static void main(String args[]) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        File f = new File(sc.nextLine());
        ArrayList<int[]> dataStorage = new ArrayList<>();

        dataStorage = ReadFile.readfile(f);



        sc.close();
    }

}
