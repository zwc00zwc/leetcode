package leetcode.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: siskin_zh
 * @Date: 2020 2020-12-16 18:15
 */
public class App {
    public static void main(String[] args){
        //int[] array = new int[]{1,17,5,10,13,15,10,5,16,8};
        //int rs = wiggleMaxLength(array);
        //int rs = lengthOfLongestSubstring("abba");
        //String[] array = new String[]{"10", "0001", "111001", "1", "0"};
        //int rs = findMaxForm(array,5,3);
        int[] a = new int[]{1,3,6,7,9,4,10,5,6};
        int rs = lengthOfLIS(a);
        System.out.println(rs);
    }

    /**
     * 最长不重复字符 双指针窗口
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        Map<Character,Integer> map = new HashMap<>();
        int i = 0;
        int k = 0;
        int max = 0;
        while (true){
            if (i>=s.length()){
                break;
            }
            char v = s.charAt(i);
            if (map.containsKey(v)){
                //关键点，需要确定坐指针的位置，通过比较那个大
                //abba 当走到最后a的时候指针应该指向第二点b，而不是第一个a
                k = Math.max(k,map.get(v)+1);
            }
            if (i - k + 1>max){
                max = i - k + 1;
            }
            map.put(v,i);
            i++;
        }
        return max;
    }


    /**
     * 474 1和0
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public static int findMaxForm(String[] strs, int m, int n){
        //状态转移，之前子集最多加上当前字符串即当前字符串下最大
        // 0~(i-1)背包能放下的最大值 加上i能满足背包容量，即0~i背包能放下的最大值，一步一步知道整个数组
        int[][][] dp = new int[strs.length+1][m+1][n+1];

        for (int i = 1; i<=strs.length;i++){
            int[] count = strCount(strs[i-1]);
            for (int j = 0; j<=m;j++){
                for (int k = 0;k<=n;k++){
                    //当前字符串是否大于背包容量，如果大于背包容量(背包放不下了)，则继承之前子集的最大数量
                    if (count[0]>j || count[1]>k){
                        dp[i][j][k] = dp[i-1][j][k];
                    }else {
                        //如果当前字符串可以放进背包容量，则状态转移,之前子集最大的数量和
                        //之前子集减去当前字符串容量的加1（相当于把当前字符串放到之前子集中组成新的子集）比较最大值
                        dp[i][j][k] = Math.max(dp[i-1][j][k],dp[i-1][j-count[0]][k-count[1]]+1);
                    }
                }
            }
        }

        return dp[strs.length][m][n];
    }

    public static int[] strCount(String s){
        int[] temp = new int[2];
        for (int i = 0;i<s.length();i++){
            if (s.charAt(i)=='0'){
                temp[0]++;
            }
            if (s.charAt(i) == '1'){
                temp[1]++;
            }
        }
        return temp;
    }

    /**
     * 300最长递增子序列
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        //使用动态规划，dp是以i结尾的数组子集有最大的递增子序列
        //转换方程是
        // dp[i] 需要和 nums[0]~nums[i-1]进行比较，
        //当nums[i] > nums[j], dp[i]=Math.max(dp[i],dp[j]+1)
        //1,3,6,7,9,4,10,5,6
        int max = 1;
        int[] dp = new int[nums.length];
        for (int i = 0;i<nums.length;i++){
            dp[i] = 1;
            if (i<1){
                continue;
            }

            for (int j = 0;j<i;j++){
                if (nums[i]>nums[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                    max = Math.max(max,dp[i]);
                }
            }
        }

        return max;
    }

    /**
     * 376. 摆动序列 dp解法
     * @param nums
     * @return
     */
    public static int wiggleMaxLength(int[] nums) {
        //需要构成摇摆，就需要上升和下降，当num[i]>num[i-1],即指针i是处于上升时
        //该指针之前最大的摆动序列是最大的下降序列+1，反之就是处于下降该指针之前的
        //最大摆动序列是最大的上升序列+1
        if (nums.length<2){
            return nums.length;
        }
        int up = 1;
        int down = 1;

        for (int i = 0;i<nums.length;i++){
            if (i == 0){
                continue;
            }
            if (nums[i]>nums[i-1]){
                up = down+1;
            }
            if (nums[i]<nums[i-1]){
                down = up+1;
            }
        }

        return Math.max(up,down);
    }

    /**
     * 青蛙跳台阶问题
     * @param n
     * @return
     */
    public static int numWays(int n) {
        if (n<2){
            return 1;
        }
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2;i<=n;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }
}
