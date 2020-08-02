学习笔记

DFS代码模板：

public class TreeNode{
	int val;
	TreeNode left;
	TreeNode right;
	
	TreeNode(int x) {
		val = x;
	}
}

//Java
public List<List<Integer>> levelOrder(TreeNode root) {
	List<List<Integer>> allResults = new ArrayList<>();
	if (root == null) {
		return allResults;
	}
	dfs(root, 0, allResults);
	return allResults;
}

private void dfs(TreeNode root, int level, List<List<Integer>> results) {
	if (results.size() == level) {
		results.add(new ArrayList<>());
	}
	results.get(level).add(root.val);
	if (root.left != null) {
		dfs(root.left, level + 1, results);
	}
	if (root.right != null) {
		dfs(root.right, level + 1, results);
	}
}

BFS代码模板：
//Java

public List<List<Integer>> levelOrder(TreeNode root) {
	List<List<Integer>> res = new ArrayList<>();
	if (root == null) return res;
	
	Queue<TreeNode> queue = new LinkedList<>();
	queue.add(root);
	while(!queue.isEmpty()) {
		int size = queue.size();
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < size; ++i) {
			TreeNode node = queue.poll();
			res.add(node.val);
			if (node.left != null){
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}
		}
		res.add(list);
	}
	results res;
}

二分查找代码模板:
//Java

public int binarySearch(int[] array, int target) {
	int left = 0, right = array.length - 1, mid;
	while (left <= right) {
		mid = left + (right - left) / 2;
		
		if (array[mid] == target) {
			return mid;
		} else if (array[mid] > target) {
			right = mid - 1;
		} else {
			left = mid + 1;
		}
	}
	
	return -1;
}


作业：
使用二分查找，寻找一个半有序数组 [4, 5, 6, 7, 0, 1, 2] 中间无序的地方：

寻找中间无序的地方，类似于寻找最小值的下标，代码如下:

public int find(int[] nums) {
	int left = 0, right = nums.length - 1;
	
	while (left < right) {
		int mid = left + (right - left) / 2;
		
		//如果中间值大于最右值，说明最小值在右半部分，这缩小左边界，否则缩小右边界
		if (nums[mid] > nums[right]) {
			left = mid + 1;
		} else {
			right = mid;
		}
	}
	//返回left或者right即为无序的地方
	return left;
}