import java.util.*;
import java.io.*;
public class Maze{


    private char[][]maze;
    private boolean animate;//false by default

    public Maze(String filename) throws FileNotFoundException{
      animate = false;
      
    }
