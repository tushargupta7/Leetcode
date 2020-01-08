package com.company.backtrack;


public class NQueen {
    /* This function solves the N Queen problem using
Backtracking. It mainly uses solveNQUtil() to
solve the problem. It returns false if queens
cannot be placed, otherwise return true and
prints placement of queens in the form of 1s.
Please note that there may be more than one
solutions, this function prints one of the
feasible solutions.*/
    static void solveNQ() {
        int N = 4;
        int board[][] = new int[N][N];

        if (solveNQUtil(board, 0) == false) {
            System.out.printf("Solution does not exist");
            return;
        }

        return;
    }

    private static boolean solveNQUtil(int[][] board, int col) {

        if(col==board[0].length){
            printBoard(board);
            return true;
        }
        boolean res=false;
        for (int i = 0; i < board.length; i++) {
            if (isSafe(board, i, col)) {
                board[i][col] = 1;
                res= solveNQUtil(board,col+1) || res;
                board[i][col]=0;
            }
        }
        return res;
    }
    private static boolean isSafe(int[][] board, int row, int col) {
        int i, j;

        /* Check this row on left side */
        for (i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;

        /* Check upper diagonal on left side */
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        /* Check lower diagonal on left side */
        for (i = row, j = col; j >= 0 && i < board.length; i++, j--)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    // Driver code
    public static void main (String[] args)
    {
        solveNQ();
    }

    private static void printBoard(int[][] board) {
        for (int i=0;i<board.length;i++
             ) {
            for (int j = 0; j <board[0].length ; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println("");
        }
        System.out.println();
        System.out.println();
    }
}
