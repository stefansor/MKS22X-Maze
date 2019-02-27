import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class ReadFile {
  public static void main(String args[]) throws FileNotFoundException {
        File text = new File("Hm.txt");
        // can be a path like: "/full/path/to/file.txt" or "../data/file.txt"


        //inf stands for the input file

        Scanner inf = new Scanner(text);
        int numlines = 0;
        int lengthline = 0;

        while(inf.hasNextLine()){
            numlines++;
            String line = inf.nextLine();
            System.out.println(line);//hopefully you can do other things with the line
            lengthline = line.length();
        }
        System.out.println(numlines);
        System.out.println(lengthline);

    }
}
