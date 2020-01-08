package com.company.hashmap;

import java.util.HashMap;

public class Subset {


    public static void main(String[] args)
    {
        int arr1[] = {11, 1, 13, 21, 3, 7};
        int arr2[] = {};

        int m = arr1.length;
        int n = arr2.length;

        if(isSubset(arr1, arr2, m, n))
            System.out.println("arr2 is a subset of arr1");
        else
            System.out.println("arr2 is not a subset of arr1");
    }

    private static boolean isSubset(int[] arr1, int[] arr2, int m, int n) {
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        for (int i:arr1
             ) {
            hashMap.putIfAbsent(i,1);
        }
        for (int j:arr2
             ) {
            if(!hashMap.containsKey(j)){
                return false;
            }
        }
        return true;
    }
}
