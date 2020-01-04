package com.company.dynamic;

public class CoverDistance {
    // driver program
    public static void main (String[] args)
    {
        int dist = 4;
        System.out.println(printCountRecur(dist));
    }

    private static int printCountDP(int dist) {

        int dp[]=new int[dist+1];

        dp[0]=1;
        dp[1]=1;
        dp[2]=2;
        for (int i=3;i<=dist;i++){
            dp[i]=dp[i-1]+dp[i-2]+dp[i-3];
        }
        return dp[dist];
    }

    private static int printCountRecur(int dist){

        if(dist==0 || dist==1){
            return 1;
        }
        else if(dist==2){
            return 2;
        }

        else return printCountRecur(dist-1)+printCountRecur(dist-2)+printCountRecur(dist-3);
    }
}
