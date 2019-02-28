import java.util.*;
import java.io.*;
public class Maze{


    private char[][]maze;
    private boolean animate;//false by default


    public Maze(String filename){
      try{
        animate = false;
        File text = new File(filename);
        Scanner inf = new Scanner(text);
        int rows = 0;
        int cols = 0;
        String chunk = "";
        while(inf.hasNextLine()){
          String line = inf.nextLine();
          rows++;
          cols = line.length();
          chunk += line;
        }
        int index = 0;
        maze = new char[rows][cols];
        for(int i = 0; i < rows; i++){
          for(int j = 0; j < cols; j++){
            maze[i][j] = chunk.charAt(index);
            index++;
          }
        }
      }
      catch(FileNotFoundException e){
        System.out.println("Must input a file that exists, your file was not found");
        System.exit(1);
      }
    }



    public void setAnimate(boolean b){
      animate = b;
    }


    public void clearTerminal(){
    //erase terminal, go to top left of screen.
      System.out.println("\033[2J\033[1;1H");
    }

    public String toString(){
      String show = "";
      for(int i = 0; i < maze.length; i++){
        for(int j = 0; j < maze[0].length; j++){
          show += maze[i][j];
        }
        show += "\n";
      }
      return show;
    }







    public static void main(String[] args){
      Maze one = new Maze("Hm.txt");
      System.out.println(one);
    }



    }
