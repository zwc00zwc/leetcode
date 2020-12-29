package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        int[] array = new int[]{3,1,6,4,7,4,5};
        //kuickSort(array,0,array.length - 1);

//        int[][] r = new int[][]{{1,2,3},{4,5,6}};
//        transpose(r);

        int[] a1 = new int[]{1,2,3,0,0,0};
        int[] a2 = new int[]{2,5,6};
        //merge(a1,3,a2,3);

        int[] a = new int[]{0,0,0};
        List<List<Integer>> res = threeSum(a);
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
        if (start<end){
            int mid = (end + start)/2;
            mergeSort(array,start,mid);
            mergeSort(array,mid+1,end);
            merge(array,start,mid,end);
        }
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
}
