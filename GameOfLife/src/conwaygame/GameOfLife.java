package conwaygame;

import java.util.ArrayList;

/**
 * Conway's Game of Life Class holds various methods that will
 * progress the state of the game's board through it's many
 * iterations/generations.
 *
 * Rules
 * Alive cells with 0-1 neighbors die of loneliness.
 * Alive cells with >=4 neighbors die of overpopulation.
 * Alive cells with 2-3 neighbors survive.
 * Dead cells with exactly 3 neighbors become alive by reproduction.
 * 
 * @author Seth Kelley
 * @author Maxwell Goldberg
 */
public class GameOfLife {

    // Instance variables
    private static final boolean ALIVE = true;
    private static final boolean DEAD = false;

    private boolean[][] grid; // The board has the current generation of cells
    private int totalAliveCells; // Total number of alive cells in the grid (board)

    /**
     * Default Constructor which creates a small 5x5 grid with five alive cells.
     * This variation does not exceed bounds and dies off after four iterations.
     */
    public GameOfLife() {
        grid = new boolean[5][5];
        totalAliveCells = 5;
        grid[1][1] = ALIVE;
        grid[1][3] = ALIVE;
        grid[2][2] = ALIVE;
        grid[3][2] = ALIVE;
        grid[3][3] = ALIVE;
    }

    /**
     * Constructor used that will take in values to create a grid with a given
     * number
     * of alive cells
     * 
     * @param file is the input file with the initial game pattern formatted as
     *             follows:
     *             An integer representing the number of grid rows, say r
     *             An integer representing the number of grid columns, say c
     *             Number of r lines, each containing c true or false values (true
     *             denotes an ALIVE cell)
     */
    public GameOfLife(String file) {

        // WRITE YOUR CODE HERE
        StdIn.setFile(file);
        int rows = StdIn.readInt();
        int col = StdIn.readInt();
        grid = new boolean[rows][col];
        totalAliveCells = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                grid[i][j] = StdIn.readBoolean();
                if (grid[i][j] == true) {
                    totalAliveCells++;
                }
            }
        }
    }

    /**
     * Returns grid
     * 
     * @return boolean[][] for current grid
     */
    public boolean[][] getGrid() {
        return grid;
    }

    /**
     * Returns totalAliveCells
     * 
     * @return int for total number of alive cells in grid
     */
    public int getTotalAliveCells() {

        return totalAliveCells;
    }

    /**
     * Returns the status of the cell at (row,col): ALIVE or DEAD
     * 
     * @param row row position of the cell
     * @param col column position of the cell
     * @return true or false value "ALIVE" or "DEAD" (state of the cell)
     */
    public boolean getCellState(int row, int col) {

        // WRITE YOUR CODE HERE
        if (grid[row][col] == false) {
            return false;
        } else {
            return true;
        }
        // return true; // update this line, provided so that code compiles
    }

    /**
     * Returns true if there are any alive cells in the grid
     * 
     * @return true if there is at least one cell alive, otherwise returns false
     */
    public boolean isAlive() {

        // WRITE YOUR CODE HERE
        if (totalAliveCells <= 0) {
            return false;
        } else {
            return true; // update this line, provided so that code compiles
        }
    }

    /**
     * Determines the number of alive cells around a given cell.
     * Each cell has 8 neighbor cells which are the cells that are
     * horizontally, vertically, or diagonally adjacent.
     * 
     * @param col column position of the cell
     * @param row row position of the cell
     * @return neighboringCells, the number of alive cells (at most 8).
     */
    public int numOfAliveNeighbors(int row, int col) {

        // WRITE YOUR CODE HERE
        int neighborsAlive = 0;

        // this is for index not at a edge

        for (int r = Math.max(0, row - 1); r <= Math.min(row + 1, grid.length - 1); r++) {
            for (int c = Math.max(0, col - 1); c <= Math.min(col + 1, grid[0].length - 1); c++) {

                if (r != row || c != col) {

                    int wrappedRow = (r + grid.length) % grid.length;
                    int wrappedCol = (c + grid[0].length) % grid[0].length;

                    if (grid[wrappedRow][wrappedCol]) {
                        neighborsAlive++;
                    }
                }
            }
        }
        if (col == 0) {
            // this is bottom left for first col
            if (row == grid.length - 1) {
                if (grid[0][0] == true) {
                    neighborsAlive++;
                }
                if (grid[0][1] == true) {
                    neighborsAlive++;
                }
                if (grid[row][grid[0].length - 1] == true) {
                    neighborsAlive++;
                }
                if (grid[row - 1][grid[0].length - 1] == true) {
                    neighborsAlive++;
                }
                if (grid[0][grid[0].length - 1] == true) {
                    neighborsAlive++;
                }

            }
            // top left hereeee for first col
            if (row == 0) {
                if (grid[grid.length - 1][col] == true) {
                    neighborsAlive++;
                }
                if (grid[grid.length - 1][col + 1] == true) {
                    neighborsAlive++;
                }
                if (grid[row][grid[0].length - 1] == true) {
                    neighborsAlive++;
                }
                if (grid[row + 1][grid[0].length - 1] == true) {
                    neighborsAlive++;
                }
                if (grid[grid.length - 1][grid[0].length - 1]) {
                    neighborsAlive++;
                }
                // middle in first column
            }
            if (row < grid.length - 1 && row > 0) {
                if (grid[row][grid[0].length - 1] == true) {
                    neighborsAlive++;
                }
                if (grid[row + 1][grid[0].length - 1] == true) {
                    neighborsAlive++;
                }
                if (grid[row - 1][grid[0].length - 1] == true) {
                    neighborsAlive++;
                }
            }
        }
        // ok bottom right last col
        if (col == grid[0].length - 1) {
            if (row == grid.length - 1) {
                if (grid[0][grid[0].length - 1] == true) {
                    neighborsAlive++;
                }
                if (grid[0][grid[0].length - 2] == true) {
                    neighborsAlive++;
                }
                if (grid[row][0] == true) {
                    neighborsAlive++;
                }
                if (grid[0][0] == true) {
                    neighborsAlive++;
                }
                if (grid[row - 1][0] == true) {
                    neighborsAlive++;
                }
            }
            // this is top right last col
            if (row == 0) {
                if (grid[grid.length - 1][col] == true) {
                    neighborsAlive++;
                }
                if (grid[grid.length - 1][col - 1] == true) {
                    neighborsAlive++;
                }
                if (grid[grid.length - 1][0] == true) {
                    neighborsAlive++;
                }
                if (grid[0][0] == true) {
                    neighborsAlive++;
                }
                if (grid[1][0] == true) {
                    neighborsAlive++;
                }
            }
            // this is for the middle of last column
            if (row < grid.length - 1 && row > 0) {
                if (grid[row][0] == true) {
                    neighborsAlive++;
                }
                if (grid[row + 1][0] == true) {
                    neighborsAlive++;
                }
                if (grid[row - 1][0] == true) {
                    neighborsAlive++;
                }
            }
        }

        // middle colums in top and bottom rows
        if (col < grid[0].length - 1 && col > 0 && row == 0) {

            if (grid[grid.length - 1][col - 1] == true) {
                neighborsAlive++;
            }
            if (grid[grid.length - 1][col + 1] == true) {
                neighborsAlive++;
            }
            if (grid[grid.length - 1][col] == true) {
                neighborsAlive++;
            }
        }
        if (col < grid[0].length - 1 && col > 0 && row == grid.length - 1) {
            if (grid[0][col] == true) {
                neighborsAlive++;
            }
            if (grid[0][col - 1] == true) {
                neighborsAlive++;
            }
            if (grid[0][col + 1] == true) {
                neighborsAlive++;
            }
        }

        return neighborsAlive;
    }

    /**
     * Creates a new grid with the next generation of the current grid using
     * the rules for Conway's Game of Life.
     * 
     * @return boolean[][] of new grid (this is a new 2D array)
     */
    public boolean[][] computeNewGrid() {

        // WRITE YOUR CODE HERE
        boolean[][] newgrid = new boolean[grid.length][grid[0].length];
        for (int i = 0; i <= grid.length - 1; i++) {
            for (int j = 0; j <= grid[0].length - 1; j++) {
                if (grid[i][j] == ALIVE) {
                    if (numOfAliveNeighbors(i, j) <= 1) {
                        newgrid[i][j] = DEAD;
                    }
                    if (numOfAliveNeighbors(i, j) == 3) {
                        newgrid[i][j] = ALIVE;
                    }
                    if (numOfAliveNeighbors(i, j) == 2) {
                        newgrid[i][j] = ALIVE;
                    }
                    if (numOfAliveNeighbors(i, j) >= 4) {
                        newgrid[i][j] = DEAD;
                    }
                }
                if (grid[i][j] == DEAD) {
                    if (numOfAliveNeighbors(i, j) == 3) {
                        newgrid[i][j] = ALIVE;
                    }
                }

            }
        }
        return newgrid;// update this line, provided so that code compiles
    }

    /**
     * Updates the current grid (the grid instance variable) with the grid denoting
     * the next generation of cells computed by computeNewGrid().
     * 
     * Updates totalAliveCells instance variable
     */
    public void nextGeneration() {

        // WRITE YOUR CODE HERE
        grid = computeNewGrid();
        totalAliveCells = 0;

        for (int i = 0; i < grid.length - 1; i++) {
            for (int j = 0; j < grid[0].length - 1; j++) {
                if (grid[i][j] == true) {
                    totalAliveCells++;
                }
            }
        }
    }

    /**
     * Updates the current grid with the grid computed after multiple (n)
     * generations.
     * 
     * @param n number of iterations that the grid will go through to compute a new
     *          grid
     */
    public void nextGeneration(int n) {

        for (int i = 0; i < n; i++) {
            grid = computeNewGrid();
        }
    }

    /**
     * Determines the number of separate cell communities in the grid
     * 
     * @return the number of communities in the grid, communities can be formed from
     *         edges
     */
    public int numOfCommunities() {

        // WRITE YOUR CODE HERE
        WeightedQuickUnionUF UnionFind = new WeightedQuickUnionUF(grid.length, grid[0].length);

        for (int row = 0; row < grid.length - 1; row++) {
            for (int col = 0; col < grid[0].length - 1; col++) {
                if (col == 0) {
                    // this is bottom left for first col
                    if (row == grid.length - 1) {
                        if (grid[0][0] == true && grid[grid.length - 1][0] == true) {
                            UnionFind.union(row, col, 0, 0);

                        }
                        if (grid[0][1] == true && grid[grid.length - 1][0] == true) {
                            UnionFind.union(row, col, 0, 1);

                        }
                        if (grid[row][grid[0].length - 1] == true && grid[grid.length - 1][0] == true) {
                            UnionFind.union(row, col, row, grid[0].length - 1);

                        }
                        if (grid[row - 1][grid[0].length - 1] == true && grid[grid.length - 1][0] == true) {
                            UnionFind.union(row, col, row - 1, grid[0].length - 1);

                        }
                        if (grid[0][grid[0].length - 1] == true && grid[grid.length - 1][0] == true) {
                            UnionFind.union(row, col, 0, grid[0].length - 1);

                        }

                    }
                    // top left hereeee for first col
                    if (row == 0) {
                        if (grid[grid.length - 1][col] == true && grid[0][0] == true) {
                            UnionFind.union(row, col, grid.length - 1, col);

                        }
                        if (grid[grid.length - 1][col + 1] == true && grid[0][0] == true) {
                            UnionFind.union(row, col, grid.length - 1, col + 1);
                        }
                        if (grid[row][grid[0].length - 1] == true && grid[0][0] == true) {
                            UnionFind.union(row, col, row, grid[0].length - 1);
                        }
                        if (grid[row + 1][grid[0].length - 1] == true && grid[0][0] == true) {
                            UnionFind.union(row, col, row + 1, grid[0].length - 1);
                        }
                        if (grid[grid.length - 1][grid[0].length - 1] == true && grid[0][0] == true) {
                            UnionFind.union(row, col, grid.length - 1, grid[0].length - 1);
                        }
                        // middle in first column
                    }
                    if (row < grid.length - 2 && row > 0) {
                        if (grid[row][grid[0].length - 1] == true && (row < grid.length - 2 && row > 0) == true) {
                            UnionFind.union(row, col, row, grid[0].length - 1);
                        }
                        if (grid[row + 1][grid[0].length - 1] == true && (row < grid.length - 2 && row > 0) == true) {
                            UnionFind.union(row, col, row + 1, grid[0].length);
                        }
                        if (grid[row - 1][grid[0].length - 1] == true && (row < grid.length - 2 && row > 0) == true) {
                            UnionFind.union(row, col, row - 1, grid[0].length - 1);
                        }
                    }
                }
                // ok bottom right last col
                if (col == grid[0].length - 1) {
                    if (row == grid.length - 1) {
                        if (grid[0][grid[0].length - 1] == true && grid[grid.length - 1][grid[0].length - 1] == true) {
                            UnionFind.union(row, col, 0, grid[0].length - 1);
                        }
                        if (grid[0][grid[0].length - 2] == true && grid[grid.length - 1][grid[0].length - 1] == true) {
                            UnionFind.union(row, col, 0, grid[0].length - 2);
                        }
                        if (grid[row][0] == true && grid[grid.length - 1][grid[0].length - 1] == true) {
                            UnionFind.union(row, col, row, 0);
                        }
                        if (grid[0][0] == true && grid[grid.length - 1][grid[0].length - 1] == true) {
                            UnionFind.union(row, col, 0, 0);
                        }
                        if (grid[row - 1][0] == true && grid[grid.length - 1][grid[0].length - 1] == true) {
                            UnionFind.union(row, col, row - 1, 0);
                        }
                    }
                    // this is top right last col
                    if (row == 0) {
                        if (grid[grid.length - 1][col] == true && grid[0][grid[0].length - 1] == true) {
                            UnionFind.union(row, col, grid.length - 1, col);
                        }
                        if (grid[grid.length - 1][col - 1] == true && grid[0][grid[0].length - 1] == true) {
                            UnionFind.union(row, col, 0, col - 1);
                        }
                        if (grid[grid.length - 1][0] == true && grid[0][grid[0].length - 1] == true) {
                            UnionFind.union(row, col, grid.length - 1, 0);
                        }
                        if (grid[0][0] == true && grid[0][grid[0].length - 1] == true) {
                            UnionFind.union(row, col, 0, 0);
                        }
                        if (grid[1][0] == true && grid[0][grid[0].length - 1] == true) {
                            UnionFind.union(row, col, 1, 0);
                        }
                    }
                    // this is for the middle of last column
                    if (row < grid.length - 1 && row > 0) {
                        if (grid[row][0] == true && grid[row][col] == true) {
                            UnionFind.union(row, col, row, 0);
                        }
                        if (grid[row + 1][0] == true && grid[row][col] == true) {
                            UnionFind.union(row, col, row + 1, 0);
                        }
                        if (grid[row - 1][0] == true && grid[row][col] == true) {
                            UnionFind.union(row, col, row - 1, 0);
                        }
                    }
                }

                // middle colums in top and bottom rows
                if (col < grid[0].length - 1 && col > 0 && row == 0) {

                    if (grid[grid.length - 1][col - 1] == true && grid[row][col] == true) {
                        UnionFind.union(row, col, grid.length - 1, col - 1);
                    }
                    if (grid[grid.length - 1][col + 1] == true && grid[row][col] == true) {
                        UnionFind.union(row, col, grid.length - 1, col + 1);
                        ;
                    }
                    if (grid[grid.length - 1][col] == true && grid[row][col] == true) {
                        UnionFind.union(row, col, grid.length - 1, col);
                        ;
                        ;
                    }
                }
                if (col < grid[0].length - 1 && col > 0 && row == grid.length - 1) {
                    if (grid[0][col] == true && grid[row][col] == true) {
                        UnionFind.union(row, col, 0, col);
                    }
                    if (grid[0][col - 1] == true && grid[row][col] == true) {
                        UnionFind.union(row, col, 0, col - 1);
                    }
                    if (grid[0][col + 1] == true && grid[row][col] == true) {
                        UnionFind.union(row, col, grid.length - 1, col + 1);
                    }
                }
                // UnionFind.union(row, col, row + 1, col);
            }
        }
        // System.out.println(UnionFind.find(0, 0));
        ArrayList<Integer> arrayList = new ArrayList<>();
        // Count distinct communities
        for (int i = 0; i < grid.length - 1; i++) {
            for (int j = 0; j < grid[0].length - 1; j++) {
                // System.out.println(UnionFind.find(i, j));
                if (!arrayList.contains(UnionFind.find(i, j)))
                    if (grid[i][j] == true) {
                        arrayList.add(UnionFind.find(i, j));
                    }
            }
        }
        System.out.println(arrayList);
        return arrayList.size();

    }
}
