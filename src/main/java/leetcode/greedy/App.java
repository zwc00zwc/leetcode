package leetcode.greedy;

/**
 * @Author: siskin_zh
 * @Date: 2020 2020-12-22 14:55
 */
public class App {
    public static void main(String[] args){
        //int[] array = new int[]{1,7,4,9,2,5};
        int[] array = new int[]{1,0,2};
        //wiggleMaxLength(array);
        candy(array);
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

    /**
     * 135. 分发糖果
     * 贪心算法，通过局部最优解推算出全局最优解，该题局部最优解释顺序走大的比小的多
     * 先从左到右走一遍，在从右到左走一遍，得到全局最优解
     * @param ratings
     * @return
     */
    public static int candy(int[] ratings) {
        if (ratings.length<2){
            return 1;
        }

        int[] res = new int[ratings.length];
        res[0]=1;
        for (int i= 1;i<ratings.length;i++){
            if (ratings[i]>ratings[i-1]){
                res[i] = res[i-1]+1;
            }else {
                res[i] = 1;
            }
        }

        for (int k= ratings.length-2;k>=0;k--){
            if (ratings[k]>ratings[k+1]){
                res[k] = Math.max(res[k],res[k+1]+1);
            }
        }

        int r = 0;
        for (int i = 0;i<res.length;i++){
            r = r+res[i];
        }

        return r;
    }
}
