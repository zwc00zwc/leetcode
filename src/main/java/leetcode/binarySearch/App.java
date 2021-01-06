package leetcode.binarySearch;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        int[] array = new int[]{2,3,1,2,4,3};
    }

    /**
     * 剑指 Offer 53 - II. 0～n-1中缺失的数字
     * @param nums
     * @return
     */
    public static int missingNumber(int[] nums) {
        return missing(nums,0,nums.length-1);
    }

    /**
     * 二分处理0～n-1中缺失的数字
     * @param nums
     * @param startIndex
     * @param endIndex
     * @return
     */
    public static int missing(int[] nums,int startIndex,int endIndex){
        //只需判断开始指针是否大于结束指针，如果大于直接返回开始指针
        if (startIndex > endIndex){
            return startIndex;
        }

        int mid = (startIndex + endIndex) / 2;

        if (nums[mid] == mid){
            return missing(nums,mid+1,endIndex);
        }

        if (nums[mid] > mid){
            return missing(nums,startIndex,mid-1);
        }

        return missing(nums,mid + 1,endIndex);
    }

    /**
     * 二分查找target
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {
        return search(nums,target,0,nums.length-1);
    }

    public static int search(int[] nums, int target, int startIndex, int endIndex){
        if (startIndex > endIndex){
            return startIndex;
        }
        int mid = (startIndex + endIndex) / 2;
        if (nums[mid] > target){
            return search(nums,target,startIndex,mid-1);
        }
        if (nums[mid] == target){
            return mid;
        }
        return search(nums,target,mid+1,endIndex);
    }

    /**
     * 删除数组的值
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val) {
        int i = 0;
        int k = 0;

        while (i<nums.length){
            int temp = nums[i];
            if (temp == val){
                i++;
                continue;
            }
            nums[k] = nums[i];
            i++;
            k++;
        }
        return k;
    }
}
