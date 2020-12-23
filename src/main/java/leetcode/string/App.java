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
}
