package leetcode.binarySearch;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        int[] array = new int[]{2,3,1,2,4,3};
        int res = minSubArrayLen(7,array);
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

    /**
     * 数组最小范围和 双指针窗口
     * @param s
     * @param nums
     * @return
     */
    public static int minSubArrayLen(int s, int[] nums) {
        int firstIndex = 0;
        int secondIndex = 0;
        int sum = 0;
        int count = 0;
        while (true){
            if (sum >= s){
                if (count < 1){
                    count = firstIndex - secondIndex;
                }
                if (firstIndex - secondIndex<count){
                    count = firstIndex - secondIndex;
                }
                if (secondIndex>=nums.length){
                    break;
                }
                sum = sum - nums[secondIndex];
                secondIndex++;
            }else {
                if (firstIndex>=nums.length){
                    break;
                }
                sum = sum + nums[firstIndex];
                firstIndex++;
            }
        }
        return count;
    }
}
