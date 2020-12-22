package leetcode.string;

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
}
