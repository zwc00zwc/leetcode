package leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: siskin_zh
 * @Date: 2020 2020-12-22 14:27
 */
public class App {
    public static void main(String[] args){
        //isIsomorphic("ab","aa");
        //int res = lengthOfLongestSubstring("abcabcbb");

        int res = characterReplacement("ABAB",2);
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

    /**
     * 567. 字符串的排列
     * 利用滑动窗口解题，s2 是否包含 s1 的排列，只要S2内S1长度的窗口内各个字符的数量与
     * S1相当即可表示S2存在子集包含S1的排列
     * @param s1
     * @param s2
     * @return
     */
    public static boolean checkInclusion(String s1, String s2) {
        //保存S1各个字符的数量
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

        //进行处理S2字符数量处理
        Map<Character,Integer> map2 = new HashMap<>();
        //定义两个指针
        int k = 0;
        int j = 0;
        while (k<s2.length()){
            //记录每个字符的数量
            if (map2.containsKey(s2.charAt(k))){
                int temp = map2.get(s2.charAt(k));
                temp++;
                map2.put(s2.charAt(k),temp);
            }else {
                map2.put(s2.charAt(k),1);
            }

            //计算窗口大小，如果窗口大于S1，则将左边界缩小，即移除最左边的字符，
            //操作是将该字符出现次数减1
            if (k-j+1>s1.length()){
                if (map2.containsKey(s2.charAt(j))){
                    int temp = map2.get(s2.charAt(j));
                    temp--;
                    map2.put(s2.charAt(j),temp);
                }
                j++;
            }
            //如果窗口大小和S1相当，进行比较，如果都匹配上直接返回true
            if (k-j+1 == s1.length()){
                if (isMatch(map,map2)){
                    return true;
                }
            }
            k++;
        }
        return false;
    }

    /**
     * 比较map个字符数量是否相当
     * @param map1
     * @param map2
     * @return
     */
    public static boolean isMatch(Map<Character,Integer> map1,Map<Character,Integer> map2){
        for (Map.Entry<Character, Integer> entry : map1.entrySet()) {
            //数量比较用equals，数量大于128==不行
            if (!entry.getValue().equals(map2.get(entry.getKey()))){
                return false;
            }
        }
        return true;
    }

    /**
     * 424. 替换后的最长重复字符
     * @param s
     * @param k
     * @return
     */
    public static int characterReplacement(String s, int k) {
        Map<Character,Integer> map = new HashMap<>();
        int i = 0;
        int j = 0;
        while (i<s.length()){
            if (map.containsKey(s.charAt(i))){
                int r = map.get(s.charAt(i));
                r++;
                map.put(s.charAt(i),r);
            }else {
                map.put(s.charAt(i),1);
            }
            //判断当前窗口是否需要扩大
            int maxTemp = maxChar(map);

            //窗口容量大于相同字符，当前窗口下的字符不符合条件，需要移动窗口
            if (i - j + 1 > maxTemp + k){
                int tv = map.get(s.charAt(j));
                tv--;
                if (tv <=0){
                    map.remove(s.charAt(j));
                }else {
                    map.put(s.charAt(j),tv);
                }
                j++;
            }
            i++;
        }
        //前指针会扩大，计算窗口大小直接相减即可
        return i - j;
    }

    public static int maxChar(Map<Character,Integer> map){
        int temp = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()){
            if (entry.getValue()>temp){
                temp = entry.getValue();
            }
        }

        return temp;
    }
}
