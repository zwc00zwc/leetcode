package leetcode.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: siskin_zh
 * @Date: 2020 2020-12-22 14:27
 */
public class App {
    public static void main(String[] args){
        //isIsomorphic("ab","aa");
        int res = lengthOfLongestSubstring("abcabcbb");
        System.out.println(res);
    }

    /**
     * 344. 反转字符串
     * @param s
     */
    public void reverseString(char[] s) {
        //对称交换位置
        int k = s.length%2>0?s.length/2:s.length/2-1;
        for (int i = 0;i<=k;i++){
            char temp = s[i];
            s[i] = s[s.length-i-1];
            s[s.length-i-1] = temp;
        }
    }

    /**
     * 387. 字符串中的第一个唯一字符
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        Map<Character,int[]> map = new HashMap<>();
        for(int i = 0;i<s.length();i++){
            if (map.containsKey(s.charAt(i))){
                int[] temp = map.get(s.charAt(i));
                temp[1]++;
                map.put(s.charAt(i),temp);
            }else {
                int[] temp = new int[2];
                temp[0] = i;
                temp[1] = 1;
                map.put(s.charAt(i),temp);
            }
        }
        int res = -1;
        for (Character c:map.keySet()) {
            int[] temp = map.get(c);
            if (temp[1] == 1 ){
                if (res < 0){
                    res = temp[0];
                }else {
                    res = Math.min(res,temp[0]);
                }
            }
        }
        return res;
    }

    /**
     * 205. 同构字符串
     * @param s
     * @param t
     * @return
     */
    public static boolean isIsomorphic(String s, String t) {
        Map<Character,Character> smap = new HashMap<>();
        Map<Character,Character> tmap = new HashMap<>();
        for (int i = 0;i<s.length();i++){
            if (smap.containsKey(s.charAt(i)) != tmap.containsKey(t.charAt(i))){
                return false;
            }
            if (!smap.containsKey(s.charAt(i)) && !tmap.containsKey(t.charAt(i))){
                smap.put(s.charAt((i)),t.charAt(i));
                tmap.put(t.charAt((i)),s.charAt(i));
                continue;
            }
            char sc = smap.get(s.charAt(i));
            char tc = tmap.get(t.charAt(i));
            if (sc != t.charAt(i) || tc != s.charAt(i)){
                return false;
            }
        }
        return true;
    }

    /**
     * 3. 无重复字符的最长子串
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
            max = Math.max(max,i - k + 1);
            map.put(v,i);
            i++;
        }
        return max;
    }

    /**
     * 3. 无重复字符的最长子串
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstringfor(String s) {
        if (s.length()<1){
            return 0;
        }
        int res = 0;
        int startIndex = 0;
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0;i<s.length();i++){
            //当有重复字符出现，初始指针指向上一次字符的下一个指针
            if (map.containsKey(s.charAt(i))){
                startIndex = Math.max(startIndex,map.get(s.charAt(i))+1);
            }
            map.put(s.charAt(i),i);
            res = Math.max(res,i-startIndex+1);
        }
        return res;
    }
}
