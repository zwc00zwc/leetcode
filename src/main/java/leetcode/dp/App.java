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
        int[] array = new int[]{1,17,5,10,13,15,10,5,16,8};
        //int rs = wiggleMaxLength(array);
        int rs = lengthOfLongestSubstring("abba");
        System.out.println(rs);
    }

    /**
     * 动态规划摇摆数组
     * @param nums
     * @return
     */
    public static int wiggleMaxLength(int[] nums) {
        if (nums.length <= 2){
            return nums.length;
        }
        int maxLength = 1;
        boolean[][] pool = new boolean[nums.length][nums.length];
        for (int i = 1;i<nums.length;i++){
            for (int j = 0;j<=i;j++){
                if (i - j <= 1){
                    pool[j][i] = true;
                }else {
                    if (nums[i]>nums[i-1] && nums[j+1]>nums[j]){
                        pool[j][i] = pool[j+1][i-1];
                    }
                    if (nums[i]<nums[i-1] && nums[j+1]<nums[j]){
                        pool[j][i] = pool[j+1][i-1];
                    }
                }
                if (pool[j][i]){
                    if (i - j +1 > maxLength){
                        maxLength = i - j +1;
                    }
                }
            }
        }
        return maxLength;
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

}
