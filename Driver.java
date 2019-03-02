import java.util.*;
import java.io.*;

public class Driver{
    public static void main(String[]args){
      String filename = "data2.dat";
      try{
        Maze f;
        f = new Maze(filename);//true animates the maze.

        f.setAnimate(true);
        int i = f.solve();
        System.out.println(f);
        System.out.println(i);
      }catch(FileNotFoundException e){
        System.out.println("Invalid filename: "+filename);
      }
    }
}
