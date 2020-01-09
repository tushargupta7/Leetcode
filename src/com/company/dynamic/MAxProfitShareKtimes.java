package com.company.dynamic;

/**
 * Created by Tushar on 09-01-2020.
 */
public class MAxProfitShareKtimes {

    public static void main(String []args)
    {
        int k = 2;
        int[] price = { 10, 22, 5, 75, 65, 80 };
        int n = price.length;
        System.out.println("Maximum profit is: " +
                maxProfit(price, n, k));
    }

    private static int maxProfit(int[] price, int n, int k) {
        int dp[][]=new int[k+1][n+1];
        for(int i=0;i<n;i++){
            dp[0][i]=0;
        }

        for(int i=0;i<k;i++){
            dp[i][0]=0;
        }

        for(int i=1;i<=k;i++){
            for (int j=1;j<n;j++){
                int maxProfit=0;
                for(int l=0;l<j;l++){
                    maxProfit=Math.max(maxProfit,price[j]-price[l]+dp[i-1][l]);
                    dp[i][j]=Math.max(maxProfit,dp[i][j-1]);
                }


            }
        }
        return dp[k][n-1];
    }
}
