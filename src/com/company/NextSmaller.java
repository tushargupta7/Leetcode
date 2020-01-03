package com.company;

import java.util.Stack;

public class NextSmaller {


    public static void main(String args[]){
        int arr[]={10,11,9,6,5,4};
        printNextGreater(arr);
    }



    private static void printNextGreater(int[] arr) {
        Stack<Integer> stack=new Stack<>();
        stack.add(arr[0]);

        for (int i=1;i<arr.length;i++) {
            int next= arr[i];

            while (!stack.isEmpty() && next>stack.peek())
            {
                System.out.println(stack.pop() + "--->" +next);
            }
            stack.add(next);
        }

        while (!stack.isEmpty()){
            System.out.println(stack.pop()+ "---> -1" );
        }
    }
}
