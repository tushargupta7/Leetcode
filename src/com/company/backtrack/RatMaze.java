package com.company.backtrack;

public class RatMaze {
    static final int N=4;
    public static void main(String[] args)
    {
        int maze[][] = { { 1, 0, 0, 0 },
                { 1, 1, 0, 1 },
                { 1, 1, 1, 1 },
                { 1, 0, 0, 1 } };

        solveMaze(maze);
    }

    private static void solveMaze(int[][] maze) {

        int sol[][] = new int[N][N];

        if (solveMazeUtil(maze, 0, 0, sol) == false) {
            System.out.print("Solution doesn't exist");
        }

    }

    private static void printSolution(int[][] sol) {
        for (int i=0;i<sol.length;i++
        ) {
            for (int j = 0; j <sol[0].length ; j++) {
                System.out.print(sol[i][j]+" ");
            }
            System.out.println("");
        }
        System.out.println();
        System.out.println();
    }

    private static boolean solveMazeUtil(int[][] maze, int row, int col, int[][] sol) {
        if(row==maze.length-1 && col==maze.length-1){
            sol[row][col]=1;
            printSolution(sol);
            return true;
        }
        boolean res=false;
        if(isSafe(maze,row,col)){
            sol[row][col]=1;
            res=solveMazeUtil(maze,row,col+1,sol)||solveMazeUtil(maze,row+1,col,sol);
            sol[row][col]=0;
        }
        return res;
    }

    private static boolean isSafe(int[][] maze, int row, int col) {
        return (row<N && col<N && maze[row][col]==1);
    }
}
