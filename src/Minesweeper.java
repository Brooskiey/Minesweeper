import java.util.Random;

/**
 * Minesweeper Command line game
 * @author Brooskiey
 * @version 0.0
 * @date 11/15/2020
 */
public class Minesweeper {

    /** The length of the grid */
    private int length;
    /** The height of t he grid */
    private int height;
    /** Number of mines on the grid */
    private int num_mines;
    /** The entire grid */
    private int[][] grid;

    /** Constructor for Minesweeper */
    public Minesweeper(int len, int hght){
        length = len;
        height = hght;
        grid = new int[length][height];
        num_mines = new Random().nextInt(len);
    }

    /** Make the grid and put the mines of the grid. A value of 100 will signify a mine.
     *   ___________________________________________
     *  |  (x-1)(y-1)  |  (x)(y-1)  |  (x+1)(y-1)  |
     *  --------------------------------------------
     *  |   (x-1)(y)   |    (xy)    |   (x+1)(y)   |
     *  --------------------------------------------
     *  |  (x-1)(y+1)  |  (x)(y+1)  |  (x+1)(y+1)  |
     *  --------------------------------------------
     */
    private void make_grid(){
        int mines = num_mines;
        Random random = new Random();

        //populate board with mines
        while(mines != 0){
            int x = random.nextInt(length);
            int y = random.nextInt(height);

            //make sure a mine isn't already there
            while(grid[x][y] == 100){
                x = random.nextInt(length);
                y = random.nextInt(height);
            }
            grid[x][y] = 100;

            //cover the corner cases when x = 0 and y = 0
            if(x == 0 && y ==0){
                grid[x][y + 1]++; //south
                grid[x + 1][y]++; //east
                grid[x + 1][y + 1]++; //southeast
            } else if(x == 0){
                grid[x][y - 1]++; //north
                grid[x][y + 1]++; //south
                grid[x + 1][y]++; //east
                grid[x + 1][y - 1]++; //northeast
                grid[x + 1][y + 1]++; //southeast
            } else if(y == 0){
                grid[x][y + 1]++; //south
                grid[x + 1][y]++; //east
                grid[x - 1][y]++; //west
                grid[x - 1][y + 1]++; //southwest
                grid[x + 1][y + 1]++; //southeast
            } else {
                grid[x][y - 1]++; //north
                grid[x][y + 1]++; //south
                grid[x + 1][y]++; //east
                grid[x - 1][y]++; //west
                grid[x + 1][y - 1]++; //northeast
                grid[x - 1][y - 1]++; //northwest
                grid[x - 1][y + 1]++; //southwest
                grid[x + 1][y + 1]++; //southeast
            }
            mines--;
        }
    }

    public static void main(String[] args){
        Minesweeper minesweeper = new Minesweeper(5, 5);
        minesweeper.make_grid();
    }


}
