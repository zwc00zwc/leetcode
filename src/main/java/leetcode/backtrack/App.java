package leetcode.backtrack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: siskin_zh
 * @Date: 2020 2020-12-15 09:29
 */
public class App {
    public static void main(String[] args){
        //List<List<Integer>> result = combine(4,2);
        //removeDuplicates("abbaca");

//        List<List<String>> res = solveNQueens(4);
        //int[] coins = new int[]{2};
        //int res = coinChange(coins,3);
        //int count = numberOfMatches(7);
        int res = 0;
        res = numWays(1);
        System.out.println(res);
        res = numWays(2);
        System.out.println(res);
        res = numWays(3);
        System.out.println(res);
        res = numWays(4);
        System.out.println(res);
        res = numWays(5);
        System.out.println(res);
    }

    /**
     * 组合（回溯算法）
     * @param n
     * @param k
     * @return
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> v = new ArrayList<>();
        back(n,k,1,v,result);
        return result;
    }

    public static void back(int n,int k,int startIndex,List<Integer> v,List<List<Integer>> result){
        if (v.size() == k){
            List<Integer> a = new ArrayList();
            a.addAll(v);
            result.add(a);
            return;
        }
        for (int i=startIndex;i<=(n-(k-v.size())+1);i++){
            v.add(i);
            back(n,k,i+1,v,result);
            v.remove(v.size()-1);
        }
    }

    /**
     * N皇后
     * @param n
     * @return
     */
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();

        int[][] temp = new int[n][n];
        backQueens(res,temp,n,0);
        return res;
    }

    public static void backQueens(List<List<String>> res,int[][] temp,int n,int row){
        if (row >= n){
            res.add(converToStr(temp));
            return;
        }
        for (int i = 0;i<n;i++){
            if (isQueens(temp,n,row,i)){
                temp[row][i] = 1;
                backQueens(res,temp,n,row+1);
                temp[row][i] = 0;
            }
        }
    }

    /**
     * 判断当前点是否符合皇后
     * @param temp
     * @param n
     * @param row
     * @param col
     * @return
     */
    public static boolean isQueens(int[][] temp,int n,int row,int col){
        int k = 0;
        //判断行
        for (int i = 0;i<n;i++){
            if (temp[i][col]==1){
                k++;
            }
            if (k>0){
                return false;
            }
        }
        //判断列
        for (int i = 0;i<n;i++){
            if (temp[row][i] == 1){
                k++;
            }
            if (k>0){
                return false;
            }
        }

        int r = row;
        int c = col;
        //判断45度
        while (r >= 0 && c >=0 && r<n && c < n){
            if (temp[r++][c++] == 1){
                k++;
            }
        }
        r = row;
        c = col;
        while (r >= 0 && c >=0 && r<n && c < n){
            if (temp[r--][c--] == 1){
                k++;
            }
        }
        if (k>0){
            return false;
        }

        k = 0;
        r = row;
        c = col;
        while (r >= 0 && c >=0 && r<n && c < n){
            if (temp[r++][c--] == 1){
                k++;
            }
        }
        r = row;
        c = col;
        while (r >= 0 && c >=0 && r<n && c < n){
            if (temp[r--][c++]==1){
                k++;
            }
        }
        if (k>0){
            return false;
        }

        return true;
    }

    /**
     * 数组转成字符串
     * @param temp
     * @return
     */
    public static List<String> converToStr(int[][] temp){
        List<String> res = new ArrayList<>();
        StringBuilder stringBuilder = null;
        for (int i = 0;i<temp.length;i++){
            stringBuilder = new StringBuilder();
            for (int k = 0;k<temp[i].length;k++){
                if (temp[i][k] == 1){
                    stringBuilder.append("Q");
                }else {
                    stringBuilder.append(".");
                }
            }
            res.add(stringBuilder.toString());
        }
        return res;
    }

    /**
     * 回溯 比赛配对次数
     * @param n
     * @return
     */
    public static int numberOfMatches(int n) {
        List<Integer> list = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        for (int i=1;i<=n;i++){
            list.add(i);
        }
        int count = backMatches(list,temp);
        return count;
    }

    /**
     * 回溯 比赛配对次数递归逻辑
     * @param list
     * @param temp
     * @return
     */
    public static int backMatches(List<Integer> list,List<Integer> temp){
        int count = 0;
        if (list.size() == 1){
            return count;
        }
        if (list.size() == 2){
            count++;
            return count;
        }

        temp = new ArrayList<>();
        count = list.size()/2;
        for (int i=0;i<list.size();i++){
            temp.add(list.get(i));
            i++;
        }
        list = temp;
        count = count + backMatches(list,temp);
        return count;
    }

    /**
     * 移除重复字符，对对碰 指针操作法
     * @param S
     * @return
     */
    public static String removeDuplicates(String S) {
        int i = 1;
        while (true){
            if (S.length() == 0){
                return S;
            }
            if (i == S.length()){
                break;
            }
            if (S.charAt(i)==S.charAt(i-1)){
                S = S.substring(0,i-1)+S.substring(i+1,S.length());
                i = 1;
                continue;
            }
            i++;
        }
        return S;
    }

    /**
     * 零钱兑换 动态规划
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        if (amount == 0){
            return 0;
        }

        int[] dp = new int[amount+1];
        for (int i = 1;i<=amount;i++){
            for (int k = 0;k<coins.length;k++){
                if (i == coins[k]){
                    dp[i] = 1;
                    continue;
                }
                if (i-coins[k]<0 || dp[i-coins[k]]<1){
                    continue;
                }
                if (dp[i]>0){
                    if (dp[i-coins[k]]+1 < dp[i]){
                        dp[i] = dp[i-coins[k]]+1;
                    }
                }else {
                    dp[i] = dp[i-coins[k]]+1;
                }
            }
        }
        if (dp[amount] == 0){
            return -1;
        }
        return dp[amount];
    }

    /**
     * 青蛙跳台阶问题 回溯法可惜超时了
     * @param n
     * @return
     */
    public static int numWays(int n) {
        int[] temp = new int[1];
        int[] res = new int[1];
        backNumWays(temp,n,res);
        return res[0];
    }

    public static void backNumWays(int[] temp,int n,int[] res){
        if (temp[0] == n){
            res[0]++;
            return;
        }
        if (temp[0] > n){
            return;
        }

        for (int i = 1;i<3;i++){
            temp[0] = temp[0] + i;
            backNumWays(temp,n,res);
            temp[0] = temp[0] - i;
        }
    }
}
