package leetcode.twopointer;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: siskin_zh
 * @Date: 2021 2021-02-02 13:39
 */
public class App {
    public static void main(String[] args){
        int[] array = new int[]{2,3,1,2,4,3};
        //minSubArrayLen(7,array);
        //lengthOfLongestSubstring("pwwkew");
        checkInclusion("ab","eidboaoo");
    }

    /**
     * 209. 长度最小的子数组
     * @param s
     * @param nums
     * @return
     */
    public static int minSubArrayLen(int s, int[] nums) {
        int k = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        int i = 0;
        while (i<nums.length){
            sum = sum + nums[i];
            while (sum>=s){
                res = Math.min(res,i-k+1);
                sum = sum - nums[k];
                k++;
            }
            i++;
        }
        return res == Integer.MAX_VALUE ? 0:res;
    }

    public static int lengthOfLongestSubstring(String s) {
        //abcabcbb
        //pwwkew
        Map<Character,Integer> map = new HashMap<>();
        int res = 0;
        int startIndex = 0;
        for (int i = 0;i<s.length();i++){
            if (map.containsKey(s.charAt(i))){
                startIndex = Math.max(startIndex,map.get(s.charAt(i))+1);
            }
            res = Math.max(res,i - startIndex+1);
            map.put(s.charAt(i),i);
        }

        return res;
    }

    public static boolean checkInclusion(String s1, String s2) {
        //s1中字符出现的次数，在s2相等长度的窗口中出现次数相等即可
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0;i<s1.length();i++){
            if (map.containsKey(s1.charAt(i))){
                int temp = map.get(s1.charAt(i));
                temp++;
                map.put(s1.charAt(i),temp);
            }else {
                map.put(s1.charAt(i),1);
            }
        }

        int k = 0;
        int j = 0;

        Map<Character,Integer> map2 = new HashMap<>();
        while (k<s2.length()){
            if (map2.containsKey(s2.charAt(k))){
                int temp = map2.get(s2.charAt(k));
                temp++;
                map2.put(s2.charAt(k),temp);
            }else {
                map2.put(s2.charAt(k),1);
            }
            if (k-j+1>=s1.length()){
                if (isMatch(map,map2)){
                    return true;
                }
                int temp = map2.get(s2.charAt(j));
                temp--;
                if (temp>0){
                    map2.put(s2.charAt(j),temp);
                }else {
                    map2.remove(s2.charAt(j));
                }
                j++;
            }
            k++;
        }
        return false;
    }

    public static boolean isMatch(Map<Character,Integer> map1,Map<Character,Integer> map2){
        for (Map.Entry<Character,Integer> entry:map1.entrySet()) {
            if (!entry.getValue().equals(map2.get(entry.getKey()))){
                return false;
            }
        }
        return true;
    }

    public int change(int amount, int[] coins) {
        //定义dp数组，背包容量组成数
        int[] dp = new int[amount+1];

        dp[0] = 1;
        for (int k = 0;k<coins.length;k++){
            for (int i = 1;i<=amount;i++) {
                if (i>=coins[k]){
                    dp[i] = dp[i] + dp[i-coins[k]];
                }
            }
        }
        return dp[amount];
    }
}
