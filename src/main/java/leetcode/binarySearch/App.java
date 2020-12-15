package leetcode.binarySearch;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        int[] array = new int[]{0};
        int res = missingNumber(array);
        System.out.println(res);
    }

    /**
     * 二分查找0-(n-1)确实的数字
     * @param nums
     * @return
     */
    public static int missingNumber(int[] nums) {
        return missing(nums,0,nums.length-1);
    }

    public static int missing(int[] nums,int startIndex,int endIndex){
        if (startIndex > endIndex){
            return startIndex;
        }

        int mid = (startIndex + endIndex) / 2;
        if (nums[mid] == mid){
            return missing(nums,mid+1,endIndex);
        }
        if (mid == 0){
            return 0;
        }
        if (nums[mid-1] == mid-1){
            return mid;
        }
        return missing(nums,startIndex,mid-1);
    }
}
