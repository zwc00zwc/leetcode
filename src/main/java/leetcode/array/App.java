package leetcode.array;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        int[] array = new int[]{3,1,6,4,7,4,5};
        kuickSort(array,0,array.length - 1);
        for (int i = 0;i<array.length;i++){
            System.out.println(array[i]);
        }
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
}
