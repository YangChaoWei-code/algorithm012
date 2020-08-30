//给你两个数组，arr1 和 arr2， 
//
// 
// arr2 中的元素各不相同 
// arr2 中的每个元素都出现在 arr1 中 
// 
//
// 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末
//尾。 
//
// 
//
// 示例： 
//
// 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
//输出：[2,2,2,1,4,3,3,9,6,7,19]
// 
//
// 
//
// 提示： 
//
// 
// arr1.length, arr2.length <= 1000 
// 0 <= arr1[i], arr2[i] <= 1000 
// arr2 中的元素 arr2[i] 各不相同 
// arr2 中的每个元素 arr2[i] 都出现在 arr1 中 
// 


import java.util.Arrays;
import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    Map<Integer, Integer> record;
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        //1.计数排序
//        int[] arr = new int[1001];
//        for (int num1: arr1
//             ) {
//            arr[num1]++;
//        }
//        int i = 0;
//        for (int num2 : arr2
//             ) {
//            while (arr[num2] > 0) {
//                arr1[i++] = num2;
//                arr[num2]--;
//            }
//        }
//        for (int j = 0; j < arr.length; j++) {
//            while (arr[j] > 0) {
//                arr1[i++] = j;
//                arr[j]--;
//            }
//        }
//        return arr1;
        record = new HashMap<>(arr2.length);
        for (int i = 0; i < arr2.length; i++) {
            record.put(arr2[i], i);
        }
        //2.快排
        //quickSort(arr1, 0, arr1.length - 1);
        //3.归并
        mergeSort(arr1, 0, arr1.length - 1);
        return arr1;
    }
    public void mergeSort(int[] arr, int lo, int hi){
        if (lo >= hi) return;
        int mid = (lo + hi) >> 1;
        mergeSort(arr, lo, mid);
        mergeSort(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);
    }

    public void merge(int[] arr, int lo, int mid, int hi){
        int[] temp = new int[hi - lo + 1];
        int i = lo;
        int j = mid + 1;
        for (int k = 0; k < temp.length; k++) {
            if (i > mid){
                temp[k] = arr[j++];
            } else if (j > hi) {
                temp[k] = arr[i++];
            } else if (less(arr[i], arr[j])){
                temp[k] = arr[i++];
            } else {
                temp[k] = arr[j++];
            }
        }
        for (int k = 0; k < temp.length; k++) {
            arr[lo + k] = temp[k];
        }
    }

    public void quickSort(int[] arr, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(arr, lo, hi);
        quickSort(arr, lo, j - 1);
        quickSort(arr, j + 1, hi);
    }

    public int partition(int[] arr, int lo, int hi){
        int temp = arr[hi];
        int j = lo;
        for (int i = lo; i < hi; i++) {
            if (less(arr[i], temp)){
                swap(arr, i, j++);
            }
        }
        swap(arr, j, hi);
        return j;
    }

    public void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public boolean less(int num1, int num2){
        if (record.containsKey(num1) && record.containsKey(num2)) {
            return record.get(num1) < record.get(num2);
        } else if (record.containsKey(num1)) {
            return true;
        } else if (record.containsKey(num2)) {
            return false;
        } else {
            return num1 < num2;
        }
    }
}