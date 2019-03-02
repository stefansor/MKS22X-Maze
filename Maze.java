import java.util.*;
import java.io.*;
public class Maze{


    private char[][]maze;
    private boolean animate;//false by default
    private int[][] moves = new int[][]{
      {-1, 0},
      {1, 0},
      {0, -1},
      {0, 1}
    };


    public Maze(String filename) throws FileNotFoundException{
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



    public void setAnimate(boolean b){
      animate = b;
    }

    public void clearTerminal(){
    //erase terminal, go to top left of screen.
      System.out.println("\033[2J\033[1;1H");
    }

    private void wait(int millis){
         try {
             Thread.sleep(millis);
         }
         catch (InterruptedException e) {
         }
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


    /*Wrapper Solve Function returns the helper function

      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.
    */
    public int solve(){
      int Srow = 0;
      int Scol = 0;
      for(int i = 0; i < maze.length; i++){
        for(int j = 0; j < maze[0].length; j++){
          if(maze[i][j] == 'S'){
            Srow = i;                   //finds S and erases it
            Scol = j;
            maze[i][j] = ' ';
          }
        }
      }
      return solve(Srow, Scol);
    }





    private boolean validMove(int row, int col){
      if(maze[row][col] == ' '){
        return true;
      }
      return false;
    }
    private boolean atExists(int row, int col){
      if(!canMove(row, col)){
        if(maze[row - 1][col] == '@'
        || maze[row + 1][col] == '@'
        || maze[row][col + 1] == '@'
        || maze[row][col - 1] == '@'){
          return true;
        }
      }
      return false;
    }


    private boolean canMove(int row, int col){
      if(maze[row - 1][col] == ' '
      || maze[row + 1][col] == ' '
      || maze[row][col + 1] == ' '
      || maze[row][col - 1] == ' '){
        return true;
      }
      return false;
    }

    /*
      Recursive Solve function:

      A solved maze has a path marked with '@' from S to E.

      Returns the number of @ symbols from S to E when the maze is solved,
      Returns -1 when the maze has no solution.


      Postcondition:

        The S is replaced with '@' but the 'E' is not.

        All visited spots that were not part of the solution are changed to '.'

        All visited spots that are part of the solution are changed to '@'
    */
    private int solve(int row, int col){ //you can add more parameters since this is private
      //animate = true;

        //automatic animation! You are welcome.
        if(animate){

            clearTerminal();
            System.out.println(this);

            wait(100);

            if(maze[row - 1][col] == 'E'
            || maze[row + 1][col] == 'E'
            || maze[row][col - 1] == 'E'
            || maze[row][col + 1] == 'E'){
              maze[row][col] = '@';
              int yea = 0;
              for(int i = 0; i < maze.length; i++){
                for(int j = 0; j < maze[0].length; j++){
                  if(maze[i][j] == '@'){
                    yea++;
                  }
                }
              }
              return yea;
            }
            if(validMove(row, col)){
              maze[row][col] = '@';
              for(int i = 0; i < moves.length; i++){
                if(validMove(row + moves[i][0], col + moves[i][1])){
                  return solve(row + moves[i][0], col + moves[i][1]);
                }
              }
            }
            if(atExists(row, col)){
              maze[row][col] = '.';
              if(maze[row - 1][col] == '@'){
                return solve(row - 1, col);
              }
              if(maze[row + 1][col] == '@'){
                return solve(row + 1, col);
              }
              if(maze[row][col + 1] == '@'){
                return solve(row, col + 1);
              }
              if(maze[row][col - 1] == '@'){
                return solve(row, col - 1);
              }
          }
          else if(canMove(row, col) && maze[row][col] == '@'){
            for(int i = 0; i < moves.length; i++){
              if(validMove(row + moves[i][0], col + moves[i][1])){
                return solve(row + moves[i][0], col + moves[i][1]);
              }
            }
          }
          return -1;
          //COMPLETE SOLVE
        }
        return -1; //so it compiles
    }




    }
