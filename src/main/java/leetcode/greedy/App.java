package leetcode.greedy;

/**
 * @Author: siskin_zh
 * @Date: 2020 2020-12-22 14:55
 */
public class App {
    public static void main(String[] args){
        int[] array = new int[]{1,7,4,9,2,5};
        wiggleMaxLength(array);
    }

    /**
     * 122 股票买卖最佳时机
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length<1){
            return 0;
        }
        int temp = prices[0];
        int res = 0;
        for (int i = 0;i<prices.length;i++){
            if (prices[i]>temp){
                res = res + prices[i] - temp;
            }
            temp = prices[i];
        }
        return res;
    }

    /**
     * 376. 摆动序列 贪心解法
     * @param nums
     * @return
     */
    public static int wiggleMaxLength(int[] nums) {

        if (nums.length<2){
            return nums.length;
        }

        int res = 1;
        int prediff = 0;
        int currdiff = 0;
        for (int i = 1;i<nums.length;i++){
            currdiff = nums[i] - nums[i-1];
            //通过指针上升与下降的比较确定峰值或者低估
            if ((currdiff > 0 && prediff <=0) || (currdiff < 0 && prediff >= 0)){
                res++;
                prediff = currdiff;
            }
        }
        return res;
    }
}
