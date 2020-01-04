package com.company;

import java.io.IOException;
import java.util.*;

class Solution {
    public static List<Integer> topKFrequent(int[] nums, int k) {
        // build hash map : character and how often it appears
        HashMap<Integer, Integer> count = new HashMap();
        for (int n : nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        // init heap 'the less frequent element first'
        PriorityQueue<Integer> heap =
                new PriorityQueue<Integer>((n1, n2) -> count.get(n1) - count.get(n2));

        // keep k top frequent elements in the heap
        for (int n : count.keySet()) {
            heap.add(n);
            if (heap.size() > k)
                heap.poll();
        }

        // build output list
        List<Integer> top_k = new LinkedList();
        while (!heap.isEmpty())
            top_k.add(heap.poll());
        Collections.reverse(top_k);
        return top_k;
    }

    public static void moveZeroes(int[] nums) {


        int size = nums.length;
        int i = 0, j = 0;
        while (true) {
            i = 0;
            while (nums[i] != 0) {
                i++;
            }
            j = i;
            while (nums[j] == 0 && j < size - 1) {
                j++;
            }
            if (j == size) {
                break;
            } else {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        return;

    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    /*public static boolean isPalindrome(ListNode head) {
        Stack<Integer> ll=new Stack<Integer>();
        ListNode curr=head;
        while(curr!=null){
            ll.push(curr.val);
            curr=curr.next;
        }
        //Collections.reverse(ll);
        curr=head;
        while(!ll.isEmpty()){
            if(ll.pop()!=curr.val){

                return false;
            }
            curr=curr.next;
        }
        return true;
    }*/

    public static boolean isPalinUtil(ListNode left, ListNode right) {
        if (right == null) {
            return true;
        }

        boolean isBool = isPalinUtil(left, right.next);
        if (isBool == false)
            return false;

        boolean isBool1 = (left.val == right.val);
        left = left.next;
        return isBool1;

    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean found_null_right = false, found_null_left = false;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if (temp.left != null) {
                if (found_null_right) {
                    return false;
                }
                queue.add(temp.left);
            } else {
                found_null_left = true;
            }
            if (temp.right != null) {
                if (found_null_left) {
                    return false;
                }
                queue.add(temp.right);
            } else {
                found_null_right = true;
            }
        }
        return true;
    }


    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lh = height(root.left);
        int rh = height(root.right);

        return 1 + Math.max(lh, rh);
    }

    public void levelOrderUtil(TreeNode root, int level, List<Integer> result) {
        if (root == null) {
            return;
        }
        if (level == 1) {
            result.add(root.val);
        }

        levelOrderUtil(root.left, level - 1, result);
        levelOrderUtil(root.right, level - 1, result);

    }
    /*public List<List<Integer>> levelOrder(TreeNode root) {
        int height=height(root);
        List<Integer> result=new ArrayList<>();
        List<List<Integer>> final_res=new ArrayList<>();
        while(height--!=0){
            levelOrderUtil(root,height,result);
            final_res.add(result);
            result=new ArrayList<>();
        }
        return final_res;
    }*/

    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> wrapList = new LinkedList<List<Integer>>();

        if (root == null) return wrapList;

        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            List<Integer> subList = new LinkedList<Integer>();
            for (int i = 0; i < levelNum; i++) {
                if (queue.peek().left != null) queue.offer(queue.peek().left);
                if (queue.peek().right != null) queue.offer(queue.peek().right);
                subList.add(queue.poll().val);
                for (Integer ia : subList) {
                    //ia.intValue()
                }
            }
            wrapList.add(subList);
        }
        return wrapList;
    }


    public static boolean isPalindrome(ListNode head) {
        return isPalinUtil(head, head);
    }

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for (int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }


    static int getSum(int a, int b) {
        return b == 0 ? a : getSum(a ^ b, (a & b) << 1); //be careful about the terminating condition;
    }


    public static String decodeString(String s) {
        char enc[] = s.toCharArray();
        int i = 0;
        boolean count_rec = false;
        int num = 0, times = 0;
        StringBuilder res = new StringBuilder();
        StringBuilder to_print = new StringBuilder();
        while (i < s.length()) {
            if (count_rec == false) {
                if (Character.isAlphabetic(s.charAt(i))) {
                    res.append(s.charAt(i));
                    i++;
                    continue;
                }
            }
            if (Character.isDigit(s.charAt(i))) {
                times = Integer.parseInt(String.valueOf(s.charAt(i)));
                count_rec = true;
                i++;
            }
            if (s.charAt(i) == '[') {
                i++;
                while (s.charAt(i) != ']') {
                    to_print.append(s.charAt(i));
                    i++;
                }
                i++;
            }
            if (count_rec == true) {
                while (times-- != 0) {
                    res.append(to_print.toString());
                }
                to_print = new StringBuilder();
                count_rec = false;
            }
        }
        return res.toString();
    }

    public static int maxProfit(int[] prices, int fee) {

        int dp[][] = new int[prices.length / 2 + 1][prices.length];
        for (int i = 0; i < prices.length; i++) {
            dp[0][i] = 0;
        }
        for (int i = 0; i < prices.length / 2 + 1; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i < prices.length / 2 + 1; i++) {
            for (int j = 1; j < prices.length; j++) {
                dp[i][j] = dp[i][j - 1];
                for (int k = 0; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], prices[j] - prices[k] - fee + dp[i - 1][k]);
                }
            }
        }
        for (int i = 0; i < prices.length / 2 + 1; i++) {
            for (int j = 0; j < prices.length; j++) {
                System.out.print(" " + dp[i][j]);
            }
            System.out.println("");
        }
        return dp[prices.length / 2][prices.length - 1];

    }

    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }


    public class NestedIterator implements Iterator<Integer> {
        Stack<NestedInteger> stack = new Stack<>();

        public NestedIterator(List<NestedInteger> nestedList) {
            for (int i = nestedList.size() - 1; i >= 0; i--) {
                stack.push(nestedList.get(i));
            }
        }

        @Override
        public Integer next() {
            return stack.pop().getInteger();
        }

        @Override
        public boolean hasNext() {
            while (!stack.isEmpty()) {
                NestedInteger curr = stack.peek();
                if (curr.isInteger()) {
                    return true;
                }
                stack.pop();
                for (int i = curr.getList().size() - 1; i >= 0; i--) {
                    stack.push(curr.getList().get(i));
                }
            }
            return false;
        }
    }


    public static double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        HashMap<String, ArrayList<String>> pairs = new HashMap<String, ArrayList<String>>();
        HashMap<String, ArrayList<Double>> valuesPair = new HashMap<String, ArrayList<Double>>();
        for (int i = 0; i < equations.length; i++) {
            String[] equation = equations[i];
            if (!pairs.containsKey(equation[0])) {
                pairs.put(equation[0], new ArrayList<String>());
                valuesPair.put(equation[0], new ArrayList<Double>());
            }
            if (!pairs.containsKey(equation[1])) {
                pairs.put(equation[1], new ArrayList<String>());
                valuesPair.put(equation[1], new ArrayList<Double>());
            }
            pairs.get(equation[0]).add(equation[1]);
            pairs.get(equation[1]).add(equation[0]);
            valuesPair.get(equation[0]).add(values[i]);
            valuesPair.get(equation[1]).add(1 / values[i]);
        }

        double[] result = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String[] query = queries[i];
            result[i] = dfs(query[0], query[1], pairs, valuesPair, new HashSet<String>(), 1.0);
            if (result[i] == 0.0) result[i] = -1.0;
        }
        return result;
    }

    private static double dfs(String start, String end, HashMap<String, ArrayList<String>> pairs, HashMap<String, ArrayList<Double>> values, HashSet<String> set, double value) {
        if (set.contains(start)) return 0.0;
        if (!pairs.containsKey(start)) return 0.0;
        if (start.equals(end)) return value;
        set.add(start);

        ArrayList<String> strList = pairs.get(start);
        ArrayList<Double> valueList = values.get(start);
        double tmp = 0.0;
        for (int i = 0; i < strList.size(); i++) {
            tmp = dfs(strList.get(i), end, pairs, values, set, value * valueList.get(i));
            if (tmp != 0.0) {
                break;
            }
        }
        set.remove(start);
        return tmp;
    }

    public static int maxProfitSimple(int[] prices, int fee) {
        int cash = 0, hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            cash = Math.max(cash, hold + prices[i] - fee);
            hold = Math.max(hold, cash - prices[i]);
        }
        return cash;
    }


    public static boolean isAnagram(String s, String t) {
        List<Integer> list = new ArrayList<>();
        char str1[] = s.toCharArray();
        char str2[] = t.toCharArray();


        Arrays.sort(str1);
        Arrays.sort(str2);

        return Arrays.equals(str1, str2);
    }


    public static boolean duplicates(int[] nums) {
        List<Integer> set = new ArrayList<>();
        for (int n : nums) {
            if (set.contains(n)) {
                return true;
            }
            set.add(n);
        }
        return false;
    }


    public static int maxProfit(int[] prices) {
        for (int i = 0; i < prices.length; i++) {

            System.out.print(prices[i] + " ");
        }
        int num_days = prices.length;
        int num_transact = (prices.length / 2);
        int dp[][] = new int[num_transact + 1][num_days];

        for (int i = 1; i <= num_transact; i++) {
            for (int j = 1; j < num_days; j++) {
                System.out.print(dp[i][j] + " ");
                dp[i][j] = dp[i][j - 1];
                for (int k = 0; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], prices[j] - prices[k] + dp[i - 1][k]);
                }
            }
            System.out.println(" ");
        }
        return dp[num_transact + 1][num_days];
    }


    public static String defangIPaddr(String address) {
        String[] addr = address.split("\\.");
        StringBuilder strBuilder = new StringBuilder();
        int count = 1;
        for (String s : addr) {
            System.out.println(s);
            if (count > 4) {
                return null;
            }
            if (Integer.parseInt(s) > 255) {
                return null;
            }
            strBuilder.append(s);
            if (count == 4) {
                break;
            }
            strBuilder.append("[.]");
            count++;
        }
        return strBuilder.toString();
    }


    public int numJewelsInStones(String J, String S) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            map.put(S.charAt(i), map.getOrDefault(0, map.get(S.charAt(i))) + 1);
        }
        int count = 0;
        for (int i = 0; i < J.length(); i++) {
            count += map.getOrDefault(0, map.get(J.charAt(i)));
        }
        return count;
    }

    public static String toLowerCase(String str) {
        int diff = Math.abs('A' - 'a');
        char[] arr = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 'a' && arr[i] < 'z') {
                continue;
            } else {
                arr[i] = (char) (arr[i] + diff);
            }
        }
        return new String(arr);
    }


    public static boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int a : arr)
            count.put(a, 1 + count.getOrDefault(a, 0));
        return count.size() == new HashSet<>(count.values()).size();
    }

    public static boolean judgeCircle(String moves) {
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < moves.length(); i++) {
            map.put(moves.charAt(i), map.getOrDefault(moves.charAt(i), 0) + 1);
        }
        map.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue());
        });
        System.out.println(map.getOrDefault('D', 0));
        System.out.println(map.getOrDefault('L', 0));
        System.out.println(map.getOrDefault('R', 0));
        System.out.println(map.getOrDefault('U', 0));


        if (map.getOrDefault('U', 0) != map.getOrDefault('D', 0)) {
            System.out.println("there");
            return false;
        }
        if (map.getOrDefault('L', 0) != map.getOrDefault('R', 0)) {
            System.out.println("here");
            return false;
        }
        return true;

    }


    public static int myAtoi(String str) {
        str = str.trim();
        if (!Character.isDigit(str.charAt(0)) && str.charAt(0) != '-') {
            return 0;
        }
        int pow = 0;
        int num = 0;
        boolean neg = false, num_found = false;
        for (char c : str.toCharArray()) {
            if ((int) c > 48 && (int) c < 58) {
                pow++;
                num_found = true;
            } else {
                if (num_found == true) {
                    break;
                }
            }
        }
        num_found = false;
        pow--;
        if (pow >= 10) {
            return Integer.MIN_VALUE;
        }
        for (char c : str.toCharArray()) {
            if (c == '-') {
                neg = true;
                continue;
            }
            if ((int) c > 48 && (int) c < 58) {
                num += (int) (c - 48) * Math.pow(10, pow--);
                num_found = true;
            } else {
                if (num_found == true) {
                    break;
                }
            }
        }
        return neg == false ? num : -1 * num;

    }


    static int i = 0;

    public static TreeNode bstFromPreorder(int[] A) {
        return bstFromPreorder(A, Integer.MAX_VALUE);
    }

    public static TreeNode bstFromPreorder(int[] A, int bound) {
        if (i == A.length || A[i] > bound) return null;
        TreeNode root = new TreeNode(A[i++]);
        root.left = bstFromPreorder(A, root.val);
        root.right = bstFromPreorder(A, bound);
        return root;
    }


    enum Result {
        TRUE, FALSE
    }

    static Result[][] memo;



    public static boolean dp(int i, int j, String text, String pattern) {
        if (memo[i][j] != null) {
            return memo[i][j] == Result.TRUE;
        }
        boolean ans;
        if (j == pattern.length()) {
            ans = i == text.length();
        } else {
            boolean first_match = (i < text.length() &&
                    (pattern.charAt(j) == text.charAt(i) ||
                            pattern.charAt(j) == '.'));

            if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                ans = (dp(i, j + 2, text, pattern) ||
                        first_match && dp(i + 1, j, text, pattern));
            } else {
                ans = first_match && dp(i + 1, j + 1, text, pattern);
            }
        }
        memo[i][j] = ans ? Result.TRUE : Result.FALSE;
        return ans;
    }


    public static boolean isMatch(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;

        for (int i = text.length(); i >= 0; i--){
            for (int j = pattern.length() - 1; j >= 0; j--){
                boolean first_match = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) ||
                                pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                } else {
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }



    public static void main(String args[]) throws IOException {

        System.out.println(isMatch("abcdddddddddddb","ab.d*b"));
        //System.out.println(maxProfit(price));
        //judgeCircle("RULDDLLDLRDUUUURULRURRRRLRULRLULLLRRULULDDRDLRULDRRULLUDDURDLRRUDRUDDURLLLUUDULRUDRLURRDRLLDDLLLDLRLLRUUDUURDRLDUDRUDRLUDULRLUDRLDDUULDDLDURULUDUUDDRRDUURRLRDLDLRLLDRRUUURDLULLURRRRDRRURDUURDLRRUULRURRUULULUUDURUDLRDDDDDURRRLRUDRUULUUUULDURDRULLRRRUDDDUUULUURRDRDDRLLDRLDULDLUUDRDLULLDLDDRUUUUDDRRRDLLLLURUURLRUUULRDDULUULUURDURDDDRRURLURDLLLRLULRDLDDLRDRRRRLUURRRRLDUDLLRUDLDRDLDRUULDRDULRULRRDLDLLLUDLDLULLDURUURRLLULUURLRLRDUDULLDURRUDDLDDLLUDURLLRLDLDUDLURLLDRRURRDUDLDUULDUDRRUDULLUUDURRRURLULDDLRRURULUURURRDULUULDDDUUDRLDDRLULDUDDLLLDLDURDLRLUURDDRLUDRLUDLRRLUUULLDUUDUDURRUULLDDUDLURRDDLURLDRDRUDRLDDLDULDRULUDRRDRLLUURULURRRUDRLLUURULURRLUULRDDDRDDLDRLDRLDUDRLDRLDDLDUDDURUDUDDDLRRDLUUUDUDURLRDRURUDUDDRDRRLUDURULDULDDRLDLUURUULUDRLRLRLLLLRLDRURRRUULRDURDRRDDURULLRDUDRLULRRLLLDRLRLRRDULDDUDUURLRULUUUULURULDLDRDRLDDLRLURRUULRRLDULLUULUDUDRLDUDRDLLDULURLUDDUURULDURRUURLRDRRRLDDULLLLDDRRLRRDRDLRUDUUDLRLDRDRURULDLULRRDLLURDLLDLRDRURLRUDURDRRRULURDRURLDRRRDUDUDUDURUUUUULURDUDDRRDULRDDLULRDRULDRUURRURLUDDDDLDRLDLLLLRLDRLRDRRRLLDRDRUULURLDRULLDRRDUUDLURLLDULDUUDLRRRDDUDRLDULRDLLULRRUURRRURLRRLDDUDDLULRUDULDULRDUDRLRDULRUUDDRUURUDLDRDUDDUULLUDDLLRLURURLRRULLDDDLURDRRDLLLLULLDLUDDLURLLDDRLDLLDDRDRDDUDLDURLUUUUUDLLLRLDULDDRDDDDRUDLULDRRLLLDUUUDDDRDDLLULUULRRULRUDRURDDULURDRRURUULDDDDUULLLURRRRDLDDLRLDDDRLUUDRDDRDDLUDLUUULLDLRDLURRRLRDRLURUURLULLLLRDDLLLLRUDURRLDURULURULDDRULUDRLDRLLURURRRDURURDRRUDLDDLLRRDRDDLRLRLUDUDRRUDLLDUURUURRDUDLRRLRURUDURDLRRULLDLLUDURUDDRUDULLDUDRRDDUDLLLDLRDRUURLLDLDRDDLDLLUDRDDRUUUDDULRUULRDRUDUURRRURUDLURLRDDLUULRDULRDURLLRDDDRRUDDUDUDLLDDRRUUDURDLLUURDLRULULDULRUURUDRULDRDULLULRRDDLDRDLLLDULRRDDLDRDLLRDDRLUUULUURULRULRUDULRULRUURUDUUDLDUDUUURLLURDDDUDUDLRLULDLDUDUULULLRDUDLDRUDRUULRURDDLDDRDULRLRLRRRRLRULDLLLDDRLUDLULLUUDLDRRLUDULRDRLLRRRULRLRLLUDRUUDUDDLRLDRDDDDRDLDRURULULRUURLRDLLDDRLLRUDRRDDRDUDULRUDULURRUDRDLRDUUDDLDRUDLLDDLRLULLLRUUDRRRRUULLRLLULURLDUDDURLRULULDLDRURDRLLURRDLURRURLULDLRLDUDLULLLDRDLULDLRULLLUDUDUDUDLDDDDDRDLUDUULLUDRLUURDRLULD");
    }

    private static void preTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(" " + root.val);
        preTraversal(root.left);
        preTraversal(root.right);
    }

}