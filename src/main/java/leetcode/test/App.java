package leetcode.test;

/**
 * @Author: siskin_zh
 * @Date: 2021 2021-02-20 14:52
 */
public class App {
    public static void main(String[] args){
        int[] array =new int[]{1, 1, 1, 1, 1};

//        findTargetSumWays(array,3);
    }

    public static int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int i = 0;i<nums.length;i++){
            sum = sum + nums[i];
        }

        int x = (S + sum) / 2;

        //解题变为从num数组中求子集和为(S+sum)/2的子集数量

        int[] dp = new int[(S+sum)/2+1];

        dp[0] = 1;

        //转换方程为dp[i] = dp[i] + dp[x-i]

        //0，1背包问题，物品只能用一次，二维数组需要进行倒叙遍历背包容量
        for (int i = 0;i<nums.length;i++){
            for (int k = (S+sum)/2;k>=nums[i];k--){
                dp[k] = dp[k] + dp[k-nums[i]];
            }
        }

        return dp[(S+sum)/2];
    }
}
