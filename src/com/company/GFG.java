package com.company;

/* A Backtracking program in
Java to solve Sudoku problem */
// An LCS based Java program to find minimum
// number insertions needed to make a string
// palindrome
class GFG
{
    /* Returns length of LCS for X[0..m-1],
    Y[0..n-1]. See http://goo.gl/bHQVP for
    details of this function */
    static int lcs( String X, String Y, int m, int n )
    {
        int L[][] = new int[n+1][n+1];
        int i, j;

		/* Following steps build L[m+1][n+1] in
		bottom up fashion. Note that L[i][j]
		contains length of LCS of X[0..i-1]
		and Y[0..j-1] */
        for (i=0; i<=m; i++)
        {
            for (j=0; j<=n; j++)
            {
                if (i == 0 || j == 0)
                    L[i][j] = 0;

                else if (X.charAt(i-1) == Y.charAt(j-1))
                    L[i][j] = L[i-1][j-1] + 1;

                else
                    L[i][j] = Integer.max(L[i-1][j], L[i][j-1]);
            }
        }

		/* L[m][n] contains length of LCS for
		X[0..n-1] and Y[0..m-1] */
        return L[m][n];
    }

    // LCS based function to find minimum number
    // of insersions
    static int findMinInsertionsLCS(String str, int n)
    {
        // Using StringBuffer to reverse a String
        StringBuffer sb = new StringBuffer(str);
        sb.reverse();
        String revString = sb.toString();

        // The output is length of string minus
        // length of lcs of str and it reverse
        return (n - lcs(str, revString , n, n));
    }

    // Driver program to test above functions
    public static void main(String args[])
    {
        String str = "geeks";
        System.out.println(
                findMinInsertionsLCS(str, str.length()));
    }
}
// This code is contributed by Sumit Ghosh
