//给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。 
//
// 示例: 
//
// 输入:
//[
//  ["1","0","1","0","0"],
//  ["1","0","1","1","1"],
//  ["1","1","1","1","1"],
//  ["1","0","0","1","0"]
//]
//输出: 6 
// Related Topics 栈 数组 哈希表 动态规划 
// 👍 576 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maximalRectangle(char[][] matrix) {
        //1.动态规划
//        if (matrix.length == 0) return 0;
//        int maxarea = 0;
//        int[][] dp = new int[matrix.length][matrix[0].length];
//
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[0].length; j++) {
//                if (matrix[i][j] == '1'){
//                    dp[i][j] = j == 0 ? 1 : dp[i][j - 1] + 1;
//                    int width = dp[i][j];
//
//                    for (int k = i; k >= 0; k--) {
//                        width = Math.min(width, dp[k][j]);
//                        maxarea = Math.max(maxarea, width * (i - k + 1));
//                    }
//                }
//            }
//        }
//        return maxarea;
        //2.使用柱状图
//        if (matrix.length == 0) return 0;
//        int maxarea = 0;
//        int[] dp = new int[matrix[0].length];
//
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[0].length; j++) {
//                dp[j] = matrix[i][j] == '1' ? dp[j] + 1 : 0;
//            }
//            maxarea = Math.max(maxarea, maxRectangle(dp));
//        }
//        return maxarea;
        //3.动态规划2
        if (matrix.length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;

        int[] left = new int[n];
        int[] right = new int[n];
        int[] height = new int[n];

        Arrays.fill(right, n);

        int maxarea = 0;
        for (int i = 0; i < m; i++) {
            int cur_left = 0, cur_right = n;
            //update height
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') height[j]++;
                else height[j] = 0;
            }

            //update left
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') left[j] = Math.max(left[j], cur_left);
                else {
                    left[j] = 0;
                    cur_left = j + 1;
                }
            }

            //update right
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') right[j] = Math.min(right[j], cur_right);
                else {
                    right[j] = n;
                    cur_right = j;
                }
            }

            //update area
            for (int j = 0; j < n; j++) {
                maxarea = Math.max(maxarea, (right[j] - left[j]) * height[j]);
            }
        }
        return maxarea;
    }

    private int maxRectangle(int[] heights){
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxarea = 0;
        for (int i = 0; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[i] < heights[stack.peek()]){
                maxarea = Math.max(maxarea, heights[stack.pop()] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        while (stack.peek() != -1){
            maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() - 1));
        }
        return maxarea;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
