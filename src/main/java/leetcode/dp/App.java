package leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
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
        //int[] a = new int[]{1,3,6,7,9,4,10,5,6};
//        int[] a = new int[]{10,9,8,7};
//        int[] b = new int[]{5,6,7,8};
//        int[] c = new int[]{7,6,5,4,3,2,1};
//        int rs = countSubstrings("aaa");
        //int rs = findContentChildren(a,b);
        //int rs = lengthOfLIS(a);
        int rs = uniquePaths(3,7);
        System.out.println(rs);
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

    /**
     * 455. 分发饼干
     * 贪心算法，最大的饼干给胃口最大的孩子
     * @param g
     * @param s
     * @return
     */
    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int res = 0;
        int k = g.length-1;

        for (int i = s.length-1;i>=0;i--){
            while (k>=0){
                if (s[i]>=g[k]){
                    res++;
                    k--;
                    break;
                }
                k--;
            }
        }
        return res;
    }

    /**
     * 121. 买卖股票的最佳时机
     * 动态规划解法利用两个动态数组记录0-i的最大收益和0-i的最小值
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int res = 0;
        if (prices.length<2){
            return 0;
        }
        //存储0-i的最大收益 转换方程是 0-i最大收益 = 0-(i-1)最大收益 和 i-min(0-i)大的值
        int[] dp = new int[prices.length];
        //存储0-i的最小值 转换方程是 0-i的最小值 = 0-(i-1)最小值 和当前值小的值
        int[] mimDp = new int[prices.length];
        mimDp[0] = prices[0];
        for (int i = 1;i<prices.length;i++){
            //0-i最小值转换方程
            mimDp[i] = Math.min(mimDp[i-1],prices[i]);
            //0-i最大收益转换方程
            dp[i] = Math.max(dp[i-1],prices[i] - mimDp[i]);
            res = Math.max(res,dp[i]);
        }
        return res;
    }

    /**
     * 647. 回文子串
     * 动态规划解法
     * @param s
     * @return
     */
    public static int countSubstrings(String s) {
        int res = 0;
        //定义动态数组指针之间是否为回文子串
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0;i<s.length();i++){
            dp[i][i] = true;
            res++;
            for (int k = 0;k<i;k++){
                //字符串相当需要判断是否是回文子串，不相等直接不是回文子串
                if (s.charAt(k) == s.charAt(i)){
                    //动态转换方程，dp[k][i] = dp[k+1][i-1]
                    //但需要一个条件k和i之间需要有字符串，即i-k>1
                    if (i-k>1){
                        dp[k][i] = dp[k+1][i-1];
                    }else {
                        dp[k][i] = true;
                    }
                }else {
                    dp[k][i] = false;
                }
                //是回文子串+1
                if (dp[k][i]){
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 62. 不同路径
     * 动态规划解法
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        //边界只能有一条走法
        for (int i = 0; i < n; i++){
            dp[0][i] = 1;
        }
        //边界只能有一条走法
        for (int i = 0; i < m; i++){
            dp[i][0] = 1;
        }
        //转换方程打到下一个点只能走右或者下,所以获得所有走法有以下方程
        //f[i][j]=f[i-1][j]+f[i][j+1]
        //举例要到达f[3][3]点，所有走法 f[3][3]=f[2][3]+f[3][4]
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        //数组从0开始，所以传参-1位实际位置
        return dp[m - 1][n - 1];
    }

    /**
     * 64. 最小路径和
     * 动态规划解法
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        if (grid.length<1){
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        //定义dp数组，两维数组m,n最小的和
        int[][] dp = new int[m][n];
        //设置初始值
        dp[0][0] = grid[0][0];
        //从左上角到右下角，只能向下或者向右，所以左边界和上边界只能有一种走法
        //初始左边界和
        for (int i = 1;i<m;i++){
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        //初始上边界和
        for (int i = 1;i<n;i++){
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }

        //转换方程，dp[i][k] = Math.min(dp[i][k-1],dp[i-1][k]) + grid[i][k];
        //遍历所有元素，转换计算
        for (int i = 1;i<m;i++){
            for (int k = 1;k<n;k++){
                dp[i][k] = Math.min(dp[i][k-1],dp[i-1][k]) + grid[i][k];
            }
        }
        //返回结果
        return dp[m-1][n-1];
    }
}
