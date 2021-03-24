package leetcode.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: siskin_zh
 * @Date: 2020 2020-12-22 14:55
 */
public class App {
    public static void main(String[] args){
        //int[] array = new int[]{1,7,4,9,2,5};
        int[] array = new int[]{1,2,4,2,5,7,2,4,9,0};
        //wiggleMaxLength(array);
        int[][] a = new int[][]{{10,16},{2,8},{1,6},{7,12}};
        //findMinArrowShots(a);
        int[] b = new int[]{5,5,5,10,20};

        int[] A = new int[]{2,7,11,15};
        int[] B = new int[]{1,10,4,11};
        advantageCount(A,B);
        //lemonadeChange(b);
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
     * 376. 摆动序列
     * @param nums
     * @return
     */
    public static int wiggleMaxLength1(int[] nums) {
        //需要构成摇摆，就需要上升和下降，当num[i]>num[i-1],即指针i是处于上升时
        //该指针之前最大的摆动序列是最大的下降序列+1，反之就是处于下降该指针之前的
        //最大摆动序列是最大的上升序列+1
        if (nums.length<2){
            return nums.length;
        }
        int up = 1;
        int down = 1;

        for (int i = 0;i<nums.length;i++){
            if (i == 0){
                continue;
            }
            //上升了，摆动周期为下降+1;
            if (nums[i]>nums[i-1]){
                up = down+1;
            }
            //下降了，摆动周期为上升+1
            if (nums[i]<nums[i-1]){
                down = up+1;
            }
        }

        return Math.max(up,down);
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
        //从左到右遍历，方法糖果
        for (int i= 1;i<ratings.length;i++){
            if (ratings[i]>ratings[i-1]){
                res[i] = res[i-1]+1;
            }else {
                res[i] = 1;
            }
        }

        //从右到左在进行一次发放糖果，题中要求是分数高的要比两侧都多，在此一次遍历过程中
        //右边都是会比左边多的，所以在右到左遍历过程中只需要比较左边比右边是否大就行。
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

    /**
     * 452. 用最少数量的箭引爆气球
     * @param points
     * @return
     */
    public static int findMinArrowShots(int[][] points) {
        if (points.length<1){
            return 0;
        }
        if (points.length<2){
            return 1;
        }
        //对数组进行排序，按左边界从小到大即可
        Arrays.sort(points,(a, b) -> a[0] > b[0] ? 1 : -1);
        int[] temp = new int[2];
        int[] next = new int[2];
        int m = 0;
        for (int i = 0;i<points.length;i++){
            if (points[i] == null){
                continue;
            }
            temp = points[i];
            points[i] = null;
            for (int j = i+1;j<points.length;j++){
                if (points[j] == null){
                    continue;
                }
                next = points[j];
                //计算该区间与箭区间的交集，如果交集为空说明没有交集，
                //有交集说明存在箭的区域，同时该位置置位null
                int[] sub = getSub(temp,next);
                if (sub == null){
                    continue;
                }
                temp = sub;
                points[j] = null;
            }
            m++;
        }
        return m;
    }

    /**
     * 计算每次交集
     * @param a
     * @param b
     * @return
     */
    public static int[] getSub(int[] a,int[] b){
        if (a[0]<=b[0] && a[1]>=b[1]){
            return b;
        }
        if (b[0]<=a[0] && b[1]>=a[1]){
            return a;
        }

        if (a[0]<=b[1] && a[1] >=b[1]){
            return new int[]{a[0],b[1]};
        }

        if (b[0]<=a[1] && b[1] >= a[1]){
            return new int[]{b[0],a[1]};
        }
        return null;
    }

    /**
     * 860. 柠檬水找零
     * @param bills
     * @return
     */
    public static boolean lemonadeChange(int[] bills) {
        int[] temp = new int[3];

        for (int i = 0;i<bills.length;i++){
            if (bills[i] == 5){
                temp[0]++;
            }
            if (bills[i] == 10){
                temp[1]++;
                temp[0]--;
            }
            //贪心 当有20时先减10再减5
            if (bills[i] == 20){
                temp[2]++;
                if (temp[1]>0){
                    temp[1]--;
                    temp[0]--;
                }else {
                    temp[0] = temp[0]-3;
                }
            }
            if (temp[0]<0 || temp[1]<0 || temp[2]<0){
                return false;
            }
        }
        return true;
    }

    /**
     * 134. 加油站
     * 贪心算法，局部解释当前加油站剩余大于0，整体剩余大于0
     * 一直纠结的地方是如果一个节点有剩余，后面节点消耗大于前面剩余，整体剩余大于等于0，
     * 这种情况起始是开始节点选错了，只要整体消耗大于等于0，就会有一个节点存在
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalSum = 0;

        int curSum = 0;
        int index = 0;

        for (int i = 0;i<gas.length;i++){
            totalSum = totalSum + gas[i] - cost[i];
            curSum = curSum + gas[i] - cost[i];
            //如果当前节点的消耗大于获取，重置当前汽油消耗
            if (curSum < 0){
                index = i+1;
                curSum = 0;
            }
        }
        if (totalSum < 0){
            return -1;
        }

        return index;
    }

    /**
     * 870. 优势洗牌
     * @param A
     * @param B
     * @return
     */
    public static int[] advantageCount(int[] A, int[] B) {
        //贪心算法，只要A数组的每个指针位比B指针位大的值最小的值即可
        Arrays.sort(A);
        List<Integer> list = new ArrayList<>();
        for (int i = 0;i<A.length;i++){
            list.add(A[i]);
        }
        int[] res = new int[A.length];
        for (int i = 0;i<B.length;i++){
            int k = 0;
            int temp = Integer.MIN_VALUE;
            while (k<list.size()){
                //只要计算出A中比B指针位值大且在A中是最小的值
                if (list.get(k)>B[i]){
                    temp = list.get(k);
                    break;
                }
                k++;
            }
            if (temp==Integer.MIN_VALUE){
                res[i] = list.get(0);
                list.remove(0);
            }else {
                res[i] = temp;
                list.remove(k);
            }
        }

        return res;
    }
}
