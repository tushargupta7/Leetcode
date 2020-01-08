package com.company.dynamic;

public class isSubsetSum {

    public static boolean isSubsetSum(int arr[], int length, int sum) {
        boolean dp[][] = new boolean[ sum+1][length + 1];

        for (int j = 1; j <= sum; j++) {
            dp[j][0]=false;
        }
        for (int i = 0; i <= length; i++) {
            dp[0][i] = true;
        }

        for (int i = 1; i <=sum ; i++) {
            for (int j = 1; j <= length; j++) {
                dp[i][j]=dp[i][j-1];
                if(arr[j-1]<=i){
                    dp[i][j]=dp[i][j] || dp[i-arr[j-1]][j-1];
                }
            }
        }
        return dp[sum][length];
    }

    public static void main(String args[]) {
        int set[] = {3, 34, 4, 12, 5, 2};
        int sum = 9;
        int n = set.length;
        if (isSubsetSum(set, n, sum) == true)
            System.out.println("Found a subset"
                    + " with given sum");
        else
            System.out.println("No subset with"
                    + " given sum");
    }
}
