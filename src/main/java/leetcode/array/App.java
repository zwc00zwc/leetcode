package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        int[] array = new int[]{3,1,6,4,7,4,5};
        //kuickSort(array,0,array.length - 1);
        //insertSort(array);

//        int[][] r = new int[][]{{1,2,3},{4,5,6}};
//        transpose(r);


        int[] a1 = new int[]{3,3,3,1,2,1,1,2,3,3,4};
        int res = totalFruit(a1);
//        int[] a2 = new int[]{2,5,6};
        //merge(a1,3,a2,3);

//        int[] a = new int[]{-2,1};

        //int res = maxSubArray(a);

//        int[] d = new int[]{2,3,1,2,4,3};
//        int res = minSubArrayLen(7,d);
        System.out.println(res);
        //List<List<Integer>> res = threeSum(a);
//        mergeSort(array,0,6);
//        for (int i = 0;i<array.length;i++){
//            System.out.println(array[i]);
//        }

//        mergeSort(array,0,6);
//        int[] arr1 = new int[]{1,3,5,7};
//        int[] arr2 = new int[]{2,4,6,8};
//        int[] res = merge(arr1,arr2);
//        for (int i = 0;i<res.length;i++){
//            System.out.println(res[i]);
//        }
    }

    /**
     * 快速排序，选中基准位置，从两边往中间走，左边比基准大的和右边比基准小的调换位置，
     * 当左右指针到一个位置，该位置就是基准的值
     * @param array
     * @param left
     * @param right
     */
    public static void kuickSort(int[] array,int left,int right){
        if (left>=right){
            return;
        }
        int index = partitionPoint(array,left,right);
        kuickSort(array,left,index-1);
        kuickSort(array,index+1,right);
    }

    /**
     * 快速排序坑位法
     * @param array
     * @param first
     * @param end
     * @return
     */
    private static int partition(int[] array, int first, int end){
        int temp = array[first];
        int index = first;
        int left = first;
        int right = end;
        while (left<=right){
            while (left <= right){
                if (array[right]<temp){
                    array[index] = array[right];
                    index = right;
                    break;
                }
                right--;
            }
            while (left <= right){
                if (array[left]>temp){
                    array[index] = array[left];
                    index = left;
                    break;
                }
                left++;
            }
        }
        array[index] = temp;
        return index;
    }

    /**
     * 快速排序坑位法
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static int positionKuick(int[] array,int start,int end){
        int left = start;
        int right = end;
        //设置坑位指针，和初始值通一个位置
        int index = start;
        int temp = array[start];
        while (left!=right){
            //如果右指针比基准值大，指针往左移
            while (left < right && array[right] > temp){
                right--;
            }
            //右指针比基准值小，坑位指针设置为右指针，坑位存放右指针值
            array[index] = array[right];
            index = right;

            //如果左指针比基准值小，指针往右移
            while (left < right && array[left] <= temp){
                left++;
            }
            //左指针比基准值大，坑位指针设置为左指针，坑位存放左指针值
            array[index] = array[left];
            index = left;
        }
        //此时的指针就是基准值该待的位置
        array[index] = temp;
        return index;
    }

    /**
     * 插入排序
     * 思路，遍历指针，指针之前的数组是有序数组，如果当前指针位置的值小于指针前一位的值
     * 需要将该指针插入到之前有序数组的正确位置
     * @param nums
     * @return
     */
    public static int[] insertSort(int[] nums) {
        //遍历指针
        for (int i = 1;i<nums.length;i++){
            int j = i;
            int temp = nums[j];
            //如果当前指针值小于前一位值，说明需要将该指针值插入到前面有序数组正确位置
            if (nums[j]<nums[j-1]){
                //遍历有序数组寻找正确位置
                while (j>0){
                    //如果当前位置的值大于前面的值，前面的值往后移一位，疼一个位置出来
                    if (temp<nums[j-1]){
                        nums[j] = nums[j-1];
                        j--;
                    }else {
                        //因为是有序数组，所以当有不大于发生就可以break,已经找到指针的正确位置
                        break;
                    }
                }
                //正确位置赋值
                nums[j] = temp;
            }
        }
        return nums;
    }

    /**
     * 快速排序指针法，左右两边指针往中间走，交换左右指针值，当指针走到重叠和基准值进行交换
     * 右边指针必须先走
     * @param array
     * @param first
     * @param end
     * @return
     */
    private static int partitionPoint(int[] array, int first, int end){
        int temp = 0;
        int index = array[first];
        int left = first;
        int right = end;
        while (left!=right){
            //指针法必须右边先进行
            while (left<right && array[right]>index){
                right--;
            }
            while (left<right && array[left]<=index){
                left++;
            }

            if (left<right){
                temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }
        }
        temp = array[left];
        array[left] = array[first];
        array[first] = temp;
        return left;
    }


    private static void mergeSort(int[] array,int start,int end){
        if (start>=end){
            return;
        }
        int mid = (end + start)/2;
        mergeSort(array,start,mid);
        mergeSort(array,mid+1,end);
        merge(array,start,mid,end);
    }

    /**
     *
     * @param array
     * @param start
     * @param mid
     * @param end
     * @return
     */
    private static void merge(int[] array,int start,int mid,int end){

        int[] tempArray = new int[array.length];
        int m = start;
        int n = mid+1;
        int i = 0;
        while (m<=mid && n<=end){
            if (array[m]<array[n]){
                tempArray[i++] = array[m++];
            }else {
                tempArray[i++] = array[n++];
            }
        }
        while (m<=mid){
            tempArray[i++] = array[m++];
        }
        while (n<=end){
            tempArray[i++] = array[n++];
        }
        i =0;
        while (start<=end){
            array[start++] = tempArray[i++];
        }
    }

    /**
     * 合并两个有序数组
     * @param arr1
     * @param arr2
     * @return
     */
    private static int[] merge(int[] arr1,int[] arr2){
        int[] tempArray = new int[arr1.length+arr2.length];
        int m = 0;
        int n = 0;
        int i = 0;
        while (m<arr1.length && n<arr2.length){
            if (arr1[m]<arr2[n]){
                tempArray[i] = arr1[m];
                m++;
            }else {
                tempArray[i] = arr2[n];
                n++;
            }
            i++;
        }
        while (m<arr1.length){
            tempArray[i++] = arr1[m++];
        }
        while (n<arr2.length){
            tempArray[i++] = arr2[n++];
        }
        return tempArray;
    }

    /**
     * 矩阵转置
     * @param A
     * @return
     */
    public static int[][] transpose(int[][] A) {
        int[][] res = new int[A[0].length][A.length];
        for (int i=0;i<A.length;i++){
            for (int k = 0;k<A[0].length;k++){
                res[k][i] = A[i][k];
            }
        }
        return res;
    }

    /**
     * 面试题 10.01. 合并排序的数组
     * @param A
     * @param m
     * @param B
     * @param n
     */
    public static void merge(int[] A, int m, int[] B, int n) {
        int i = 0;
        int j = 0;
        int k = 0;
        int[] array = new int[m+n];
        while (j<m && k<n){
            if (A[j]<B[k]){
                array[i] = A[j];
                j++;
            }else {
                array[i] = B[k];
                k++;
            }
            i++;
        }

        while (j<m){
            array[i++] = A[j++];
        }
        while (k<n){
            array[i++] = B[k++];
        }

        for (int c = 0;c<array.length;c++){
            A[c] = array[c];
        }
    }

    /**
     * 15. 三数之和
     * 利用双指针向中间靠拢的方法进行解法
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        //对数组进行排序
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = null;
        for (int i = 0;i<nums.length;i++){
            //数组已经是排序过的，如果指针位置值已经大于0，说明后面所有的和都大于0了，直接break
            if (nums[i]>0){
                break;
            }
            //判断值是否相同
            if (i>0 && nums[i] == nums[i-1]){
                continue;
            }
            //定义左右指针
            int j = i+1;
            int k = nums.length -1;
            //左指针必须小于右指针
            while (j<k){
                list = new ArrayList<>();
                //排除相同的值
                if (j>i+1 && nums[j]==nums[j-1]){
                    j++;
                    continue;
                }
                //排除相同的值
                if (k < nums.length -1 && nums[k]==nums[k+1]){
                    k--;
                    continue;
                }
                //数组是排序过的，大于0了那就所有都大于0了
                if (nums[i]+nums[j]>0){
                    break;
                }
                //和大于0，右指针太大，往中间靠，减小一点
                if (nums[i]+nums[j]+nums[k] > 0){
                    k--;
                    continue;
                }
                //和大于0，左指针太小，往中间靠，增大一点
                if (nums[i]+nums[j]+nums[k] < 0){
                    j++;
                    continue;
                }
                list.add(nums[i]);
                list.add(nums[j]);
                list.add(nums[k]);
                res.add(list);
                k--;
                j++;
            }
        }
        return res;
    }

    /**
     * 1046. 最后一块石头的重量
     * @param stones
     * @return
     */
    public int lastStoneWeight(int[] stones) {
        //递归中止条件
        if (stones.length < 1){
            return 0;
        }
        if (stones.length == 1){
            return stones[0];
        }
        //排序
        Arrays.sort(stones);
        //计算最大的两个石头碰撞结果
        int temp = stones[stones.length-1]-stones[stones.length-2];
        //进行新数组组装
        if (temp == 0){
            int[] tempstones = new int[stones.length-2];
            for (int i = 0;i<stones.length-2;i++){
                tempstones[i] = stones[i];
            }
            stones = tempstones;
        }else {
            int[] tempstones = new int[stones.length-1];
            for (int i = 0;i<stones.length-2;i++){
                tempstones[i] = stones[i];
            }
            tempstones[tempstones.length-1] = temp;
            stones = tempstones;
        }
        //递归
        return lastStoneWeight(stones);
    }

    /**
     * 53. 最大子序和
     * 贪心算法
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        if (nums.length < 1){
            return 0;
        }
        if (nums.length < 2){
            return nums[0];
        }

        int res = nums[0];
        int temp = 0;
        for (int i = 0;i<nums.length;i++){
            temp = temp + nums[i];
            res = Math.max(res,temp);
            //当和未负数，说明当前指针位置的值不是增益，需要重该指针后面开始计算
            if (temp <= 0){
                temp = 0;
            }
        }
        return res;
    }

    /**
     * 209. 长度最小的子数组
     * 双指针滑动窗口解题
     * @param s
     * @param nums
     * @return
     */
    public static int minSubArrayLen(int s, int[] nums) {
        int sum = 0;
        //定义右指针，左指针
        int rightIndex = 0;
        int leftIndex = 0;
        int res = Integer.MAX_VALUE;
        //右指针先走
        while (rightIndex < nums.length) {
            sum = sum + nums[rightIndex];
            rightIndex++;
            //如果值大于设定值，缩小窗口，即左指针右移
            while (sum >= s) {
                res = Math.min(res, rightIndex - leftIndex);
                sum = sum - nums[leftIndex];
                leftIndex++;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    /**
     * 904. 水果成篮
     * 题解本意是求解只包含两种元素的最长连续子序列
     * 利用滑动窗口解题
     * @param tree
     * @return
     */
    public static int totalFruit(int[] tree) {
        //记录每个数字出现的次数
        Map<Integer,Integer> map = new HashMap<>();
        int res = 0;
        int k = 0;
        for (int i=0;i<tree.length;i++){
            //数字往窗口放
            if (map.containsKey(tree[i])){
                int temp = map.get(tree[i]);
                temp++;
                map.put(tree[i],temp);
            }else {
                map.put(tree[i],1);
            }
            //当map大于2说明窗口内有超过2中数字
            //缩小窗口剔除左边
            while (map.size()>2){
                int temp = map.get(tree[k]);
                temp--;
                if (temp<1){
                    map.remove(tree[k]);
                }else {
                    map.put(tree[k],temp);
                }
                k++;
            }
            //计算符合条件的窗口大小就是结果
            res = Math.max(res,i-k+1);
        }
        return res;
    }
}
