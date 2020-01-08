package com.company.backtrack;

public class mColoring {
    /* A utility function to print solution */
    static void  printSolution(int color[])
    {
        System.out.println("Solution Exists: Following" +
                " are the assigned colors");
        for (int i = 0; i < V; i++)
            System.out.print(" " + color[i] + " ");
        System.out.println();
    }

    // driver program to test above function
    public static void main(String args[])
    {
        /* Create following graph and test whether it is
           3 colorable
          (3)---(2)
           |   / |
           |  /  |
           | /   |
          (0)---(1)
        */
        int graph[][] = {{0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 1},
                {1, 0, 1, 0},
        };
        int m = 3; // Number of colors
        graphColoring(graph, m);
    }

    static int V=4;
    private static void graphColoring(int[][] graph, int m) {
        int color[]=new int[graph.length];
        if(graphColoringUtil(graph,0,color,m)){
            printSolution(color);
        }else {
            System.out.println("No solution found");
        }
    }

    private static boolean graphColoringUtil(int[][] graph, int index, int[] color, int m) {

        if(V==index){
            return true;
        }else {
            for (int i=1;i<=m;i++){
                if(isSafe(graph,index,color,i)){
                    color[index]=i;
                    if(graphColoringUtil(graph,index+1,color,m)){
                        return true;
                    }
                    color[index]=0;
                }
            }
        }
        return false;
    }

    private static boolean isSafe(int[][] graph, int index, int[] color, int i) {
        for (int j = 0; j < graph.length; j++) {
            if(graph[index][j]==1 && color[j]==i){
                return false;
            }
        }
        return true;
    }
}
