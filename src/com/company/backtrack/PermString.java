package com.company.backtrack;

public class PermString {
    public static void main(String[] args)
    {
        String str = "ABCD";
        int n = str.length();
        permute(str, 0, n-1);
    }

    private static void permute(String str, int i, int i1) {

        if(i==i1) {
            System.out.println(str);
        }else {
            for (int j = i; j < i1; j++) {
                str=swap(str,i,j);
                permute(str,j+1,i1);
                str=swap(str,j,i);
            }
        }
    }

    private static String swap(String str, int i, int j) {
        char[] arr=str.toCharArray();
        char tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
        return String.valueOf(arr);
    }
}
