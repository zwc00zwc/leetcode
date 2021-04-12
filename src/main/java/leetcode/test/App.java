package leetcode.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: siskin_zh
 * @Date: 2021 2021-02-20 14:52
 */
public class App {
    public static void main(String[] args){
//        int[] array =new int[]{1, 1, 1, 1, 1};
//        Integer a = new Integer(2);
//        Integer b= 2;
//        Integer c = 2;
//        Integer d = a;
//
//        Integer e = 127;
//        Integer f = 127;
//        Integer g = new Integer(127);
//        System.out.println(a.equals(b));
//        int a = 3;
//        int b = 4;
//        float r = (float) a/(float) b;
//        System.out.println(r);
//        int[] array = new int[]{3, 1, 4, 2, 5};
//        lengthOfLongestSubstring("dvdf");
//        quickSort(array,0,4);
//        for (int i = 0;i<array.length;i++){
//            System.out.println(array[i]);
//        }

        int[] nums = new int[]{2,3,1,2,4,3};
        threeSum(nums);
//        System.out.println(a == b);
//        System.out.println(b == c);
//        System.out.println(d == c);
//        System.out.println(e == f);
//        System.out.println(f == g);
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
        Map map = new HashMap();
        return dp[(S+sum)/2];
    }

    public static void insertSort(int[] array){
        for (int i = 1;i<array.length;i++){
            while (i>0 && array[i]<array[i-1]){
                int temp = array[i];
                array[i] = array[i-1];
                array[i-1] = temp;
                i--;
            }
        }
    }

    public int[] sortArray(int[] nums) {
        for (int i = 1;i<nums.length;i++){
            int j = i;
            if (nums[j]>nums[j-1]){
                continue;
            }
            while (j>0 && nums[j]<nums[j-1]){
                int temp = nums[j];
                nums[j] = nums[j-1];
                nums[j-1] = temp;
                j--;
            }
        }
        return nums;
    }




    public static void quickSort(int[] array,int start,int end){
        if (start>=end){
            return;
        }
        int q = quickP(array,start,end);

        quickSort(array,start,q-1);
        quickSort(array,q+1,end);
    }
    /**
     * 2 1 4 3
     * @param array
     * @param start
     * @param end
     */
    public static int quickP(int[] array,int start,int end){
        int index = start;
        int temp = array[index];
        int left = start;
        int right = end;

        while (left != right){
            while (left < right && array[right]>temp){
                right--;
            }
            array[index] = array[right];
            index = right;

            //1143
            while (left < right && array[left]<temp){
                left++;
            }

            array[index] = array[left];
            index = left;
        }
        array[index] = temp;
        return index;
    }

    //abcabcbb
    // aabcd
    public static int lengthOfLongestSubstring(String s) {
        int res = 0;
        int index = 0;
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0;i<s.length();i++){
            if (map.containsKey(s.charAt(i))){
                index = Math.max(index,map.get(s.charAt(i))+1);
            }
            map.put(s.charAt(i),i);
            res = Math.max(res,i-index+1);
        }
        return res;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> res = null;
        Arrays.sort(nums);

        for (int i = 0;i<nums.length;i++){

            if (nums[i]>0){
                break;
            }

            if (i>0 && nums[i] == nums[i-1]){
                continue;
            }

            if (i < nums.length-1 && nums[i] + nums[i+1]>0){
                break;
            }

            int left = i+1;
            int right = nums.length-1;

            while (left < right){

                if (left > i+1 && nums[left] == nums[left-1]){
                    left++;
                    continue;
                }
                //排除相同的值
                if (right < nums.length -1 && nums[right]==nums[right+1]){
                    right--;
                    continue;
                }
                //数组是排序过的，大于0了那就所有都大于0了
                if (nums[i]+nums[left]>0){
                    break;
                }
                if (nums[i] + nums[left] + nums[right] > 0){
                    right--;
                    continue;
                }
                if (nums[i] + nums[left] + nums[right] < 0){
                    left++;
                    continue;
                }
                if (nums[i] + nums[left] + nums[right] == 0){
                    res = new ArrayList<>();
                    res.add(nums[i]);
                    res.add(nums[left]);
                    res.add(nums[right]);
                    list.add(res);
                    left++;
                    right--;
                    continue;
                }
            }
        }
        return list;
    }

    public static int minSubArrayLen(int target, int[] nums) {
        int res = Integer.MAX_VALUE;
        int sum = 0;
        int left = 0;
        for (int i = 0;i<nums.length;i++){
            sum = sum + nums[i];
            while (sum>=target){
                if (sum >= target){
                    res = Math.min(res,i-left+1);
                }
                sum = sum - nums[left];
                left++;
            }
        }
        return res == Integer.MAX_VALUE ? 0:res;
    }

    public int findMaxForm(String[] strs, int m, int n) {
        //定义dp数组，下标为dp[m][n]含义为m个1和n个0最大的字符数量
        int[][] dp =new int[m+1][n+1];
        //dp推算方程为dp[m][n] = Math.max(dp[m][n],dp[m-x][n-x]+1);

        //数组中的字符只有两种状态，要么选择要么不选择
        //01背包问题，遍历物品在外
        for (int i= 0;i<strs.length;i++){
            int[] res = getFormRes(strs[i]);
            //物品只能放一次，倒叙遍历背包容量
            for (int j = m;j>0;j--){
                for (int k = n;k>0;k--){
                    //当前背包不能够放下物品
                    if (j<res[0] || k<res[1]){
                        continue;
                    }
                    dp[j][k] = Math.max(dp[j][k],dp[j-res[0]][k-res[1]]+1);
                }
            }
        }
        return dp[m][n];
    }

    private int[] getFormRes(String str){
        int[] res = new int[2];
        for (int i = 0;i<str.length();i++){
            if (str.charAt(i) == 0){
                res[0]++;
            }else {
                res[1]++;
            }
        }
        return res;
    }

    public int wiggleMaxLength(int[] nums) {
        if (nums.length<2){
            return nums.length;
        }
        //上一个的差值
        int preDiff = 0;
        //当前的差值
        int curDiff = 0;
        int res = 0;
        for (int i = 1;i<nums.length;i++){
            curDiff = nums[i] - nums[i-1];
            if ((curDiff>0 && preDiff<=0) || (curDiff<0 && preDiff>=0)){
                res++;
                preDiff = curDiff;
            }
        }

        return res;
    }
}
