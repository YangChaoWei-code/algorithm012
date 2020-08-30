学习笔记
//1.冒泡排序
private void bubbleSort(int[] arr){

    int n = arr.length;
    if (n <= 1) return;
	for (int i = 0; i < n; ++i){
		// 提前退出标志位
		boolean falg = false;
		for (int j = 0; j < n - i - 1; ++j) {
			if (arr[j] > arr[j + 1]) {
				int temp = arr[j + 1];
				arr[j + 1] = arr[j];
				arr[j] = temp;
				// 此次冒泡有数据交换
				falg = true;
			}
		}
		if (!flag) break; // 没有数据交换，提前退出
	}
}

 /**
     * 冒泡排序改进:在每一轮排序后记录最后一次元素交换的位置,作为下次比较的边界,
     * 对于边界外的元素在下次循环中无需比较.
 */
 
 private void bubbleSort(int[] arr){

    int n = arr.length;
    if (n <= 1) return;
	
	// 最后一次交换的位置
	int lastExchange = 0;
	// 无序数据的边界，每次只需要比较这里即可退出
	int sotBorder = n - 1;
	for (int i = 0; i < n; ++i){
		// 提前退出标志位
		boolean falg = false;
		for (int j = 0; j < sotBorder; ++j) {
			if (arr[j] > arr[j + 1]) {
				int temp = arr[j + 1];
				arr[j + 1] = arr[j];
				arr[j] = temp;
				// 此次冒泡有数据交换
				falg = true;
				// 更新最后一次交换的位置
				lastExchange = j;
			}
		}
		sotBorder = lastExchange;
		if (!flag) break; // 没有数据交换，提前退出
	}
}


// 插入排序
 private void insertionSort(int[] arr){

    int n = arr.length;
    if (n <= 1) return;
	
	for (int i = 1; i < n; i++) {
		int value = arr[i];
		int j = i - 1;
		// 查找要插入的位置并移动
		for(; j >= 0; --j) {
			if (arr[j] > value) {
				arr[j + 1] = arr[j];
			} else {
				break;
			}
		}
		arr[j + 1] = value;
	}
}


// 选择排序
public static void selectionSort(int[] arr){
    int n = arr.length;
    if (n <= 1) return;
	
	for (int i = 0; i < n - 1; i++){
		// 查找最小值
		int minIndex = i;
		for (int j = i + 1; j < n; ++j) {
			if (arr[j] < arr[minIndex]){
				minIndex = j;
			}
		}
		// 交换
		int temp = arr[i];
		arr[i] = arr[minIndex];
		arr[minIndex] = temp;
	}
     
}

快排代码示例：
// Java
public static void quickSort(int[] array, int begin, int end){
	if (end <= begin) return;
	int pivot = partition(array, begin, end);
	quickSort(array, begin, pivot - 1);
	quickSort(array, pivot + 1, end);
}

static int partition(int[] a, int begin, int end) {
	// pivot: 标杆位置， counter: 小于pivot的元素个数
	int pivot = end, counter = begin;
	for (int i = begin; i < end; ++i){
		if (a[i] < a[pivot]) {
			int temp = a[counter]; a[counter] = a[i]; a[i] = temp;
			counter++;
		}
	}
	int temp = a[pivot]; a[pivot] = a[counter]; a[counter] = temp;
	return counter;
}

归并排序代码示例:
// Java
public static void mergeSort(int[] array, int left, int right) {
    if (right <= left) return;
    int mid = (left + right) >> 1; // (left + right) / 2

    mergeSort(array, left, mid);
    mergeSort(array, mid + 1, right);
    merge(array, left, mid, right);
}

public static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1]; // 中间数组
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }

        while (i <= mid)   temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (int p = 0; p < temp.length; p++) {
            arr[left + p] = temp[p];
        }
        // 也可以用 System.arraycopy(a, start1, b, start2, length)
}

堆排序代码示例:
//Java
static void heapify(int[] array, int length, int i) {
    int left = 2 * i + 1, right = 2 * i + 2；
    int largest = i;
    if (left < length && array[left] > array[largest]) {
        largest = left;
    }
    if (right < length && array[right] > array[largest]) {
        largest = right;
    }
    if (largest != i) {
        int temp = array[i]; array[i] = array[largest]; array[largest] = temp;
        heapify(array, length, largest);
    }
}
public static void heapSort(int[] array) {
    if (array.length == 0) return;
    int length = array.length;
    for (int i = length / 2-1; i >= 0; i-) 
        heapify(array, length, i);
    for (int i = length - 1; i >= 0; i--) {
        int temp = array[0]; array[0] = array[i]; array[i] = temp;
        heapify(array, i, 0);
    }
}
