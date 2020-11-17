import java.util.Random;

/**
 * Minesweeper Command line game
 * @author Brooskiey
 * @version 0.0
 * @date 11/15/2020
 */
public class Minesweeper {

    /** The length of the grid */
    private final int length;
    /** The height of t he grid */
    private final int height;
    /** Number of mines on the grid */
    private final int num_mines;
    /** The entire grid */
    private final int[][] grid;

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

            //cover the corner cases when x = 0 and y = 0
            if(x == 0 && y ==0){
                grid[x][y + 1] = grid[x][y + 1] + 1; //south
                grid[x + 1][y] = grid[x + 1][y] + 1; //east
                grid[x + 1][y + 1] = grid[x + 1][y + 1] + 1; //southeast
            } else if(x == 0 && y < height - 1){
                grid[x][y - 1] = grid[x][y - 1] + 1; //north
                grid[x][y + 1] = grid[x][y + 1] + 1; //south
                grid[x + 1][y] = grid[x + 1][y] + 1; //east
                grid[x + 1][y - 1] = grid[x + 1][y - 1] + 1; //northeast
                grid[x + 1][y + 1] = grid[x + 1][y + 1] + 1; //southeast
            } else if(y == 0 && x < length - 1){
                grid[x][y + 1] = grid[x][y + 1] + 1; //south
                grid[x + 1][y] = grid[x + 1][y] + 1; //east
                grid[x - 1][y] = grid[x - 1][y] + 1; //west
                grid[x - 1][y + 1] = grid[x - 1][y + 1] + 1; //southwest
                grid[x + 1][y + 1] = grid[x + 1][y + 1] + 1; //southeast
            } else if(y == height - 1 && x > 0 && x < length) {
                grid[x][y - 1] = grid[x][y - 1] + 1; //north
                grid[x + 1][y] = grid[x + 1][y] + 1; //east
                grid[x - 1][y] = grid[x - 1][y] + 1; //west
                grid[x + 1][y - 1] = grid[x + 1][y - 1] + 1; //northeast
                grid[x - 1][y - 1] = grid[x - 1][y - 1] + 1; //northwest
            } else if(x == length - 1 && y > 0 && y < height){
                grid[x][y - 1] = grid[x][y - 1] + 1; //north
                grid[x][y + 1] = grid[x][y + 1] + 1; //south
                grid[x - 1][y] = grid[x - 1][y] + 1; //west
                grid[x - 1][y - 1] = grid[x - 1][y - 1] + 1; //northwest
                grid[x - 1][y + 1] = grid[x - 1][y + 1] + 1; //southwest
            } else {
                grid[x][y - 1] = grid[x][y - 1] + 1; //north
                grid[x][y + 1] = grid[x][y + 1] + 1; //south
                grid[x + 1][y] = grid[x + 1][y] + 1; //east
                grid[x - 1][y] = grid[x - 1][y] + 1; //west
                grid[x + 1][y - 1] = grid[x + 1][y - 1] + 1; //northeast
                grid[x - 1][y - 1] = grid[x - 1][y - 1] + 1; //northwest
                grid[x - 1][y + 1] = grid[x - 1][y + 1] + 1; //southwest
                grid[x + 1][y + 1] = grid[x + 1][y + 1] + 1; //southeast
            }
            mines--;
            grid[x][y] = 100;
        }
    }

    public void printGrid(){
        int x = 0;
        int y = 0;
        String row = "";
        while(x < length){
            while(y < height) {
                row += "\t" + grid[x][y] + "\t";
                y++;
            }
            System.out.println(row);
            row = "";
            x++;
            y = 0;
        }
    }

    /**
     * Main method for the Minesweeper game
     * @param args command line arguments
     */
    public static void main(String[] args){
        Minesweeper minesweeper = new Minesweeper(5, 5);
        minesweeper.make_grid();
        minesweeper.printGrid();
    }


}
