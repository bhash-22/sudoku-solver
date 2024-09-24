public class SudokuSolver {

    // define the constant size of the sudoku grid
    static int N = 9;

    /* Takes a partially filled-in grid and attempts to assign values to all 
    unassigned locations to meet the requirements for Sudoku */

    static boolean solveSudoku(int grid[][], int row, int col) {
       
        // base case for termination of backtracking
        if (row == N - 1 && col == N) {
            return true;
        }

        // Checking for max column value
        if (col == N) {
            row++;
            col = 0;
        }

        // Check if the current position of the grid already contains 0
        if (grid[row][col] != 0) {
            return solveSudoku(grid, row, col + 1);
        }

        for (int num = 1; num < 10; num++) {

            // Check if it is safe to place the num (1-9)  in the given row ,col 
            // or we move to next column
            if (isSafe(grid, row, col, num)) {

                grid[row][col] = num;

                // Using recursion to check for the next column till base case
                if (solveSudoku(grid, row, col + 1)) {
                    return true;
                }
            }
            // if the assumption was wrong, reassign 0 to the box
            grid[row][col] = 0;
        }
        return false;
    }

    /* A utility function to print grid */
    static void print(int[][] grid)
    {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Check whether it will be legal to assign num to the given box
    static boolean isSafe(int[][] grid, int row, int col, int num)
    {

        // Check if we find the same number in the row
        for (int x = 0; x <= 8; x++) {
            if (grid[row][x] == num) {
                return false;
            }
        }

        // Check if we find the same number in the column
        for (int x = 0; x <= 8; x++) {
            if (grid[x][col] == num) {
                return false;
            }
        }

        // Check if we find the same number in the 3x3 subgrid
        int startRow = row - row % 3, startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }
 
    // Driver Code
    public static void main(String[] args)
    {
        int grid[][] = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
                         { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
                         { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
                         { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
                         { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
                         { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
                         { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
                         { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
                         { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };

        if (solveSudoku(grid, 0, 0)) {
            print(grid);
        }
        else {
            System.out.println("No Solution exists");
        }
    }
}
