//给定一个非空二维矩阵 matrix 和一个整数 k，找到这个矩阵内部不大于 k 的最大矩形和。 
//
// 示例: 
//
// 输入: matrix = [[1,0,1],[0,-2,3]], k = 2
//输出: 2 
//解释: 矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
// 
//
// 说明： 
//
// 
// 矩阵内的矩形区域面积必须大于 0。 
// 如果行数远大于列数，你将如何解答呢？ 
// 
// Related Topics 队列 二分查找 动态规划 


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int rows = matrix.length, cols = matrix[0].length, max = Integer.MIN_VALUE;
        //1.动态规划 + 暴力 + 状态压缩
//        for (int i = 1; i <= rows; i++) {
//            for (int j = 1; j <= cols; j++) {
//                int[][] dp = new int[rows + 1][cols + 1];
//                for (int l = i; l <= rows; l++) {
//                    for (int m = j; m <= cols; m++) {
//                        dp[l][m] = dp[l - 1][m] + dp[l][m - 1] - dp[l - 1][m - 1] + matrix[l - 1][m - 1];
//                        if (dp[l][m] <= k && dp[l][m] > max) max = dp[l][m];
//                    }
//                }
//            }
//        }
//        return max;

        //2.滚动数组 + 动态规划
        for (int i = 0; i < cols; i++) {
            int[] rowSum = new int[rows];
            for (int j = i; j < cols; j++) {
                for (int l = 0; l < rows; l++) {
                    rowSum[l] += matrix[l][j];
                }
                max = Math.max(max, dpmax(rowSum, k));
                if (max == k) return k;
            }
        }
        return max;
    }

    private int dpmax(int[] rowSum, int k) {
        int rollSum = rowSum[0], rollMax = rollSum;

        for (int i = 1; i < rowSum.length; i++) {
            if (rollSum > 0) rollSum += rowSum[i];
            else rollSum = rowSum[i];
            if (rollSum > rollMax) rollMax = rollSum;
        }

        if (rollMax <= k) return rollMax;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < rowSum.length; i++) {
            int sum = 0;
            for (int j = i; j < rowSum.length; j++) {
                sum += rowSum[j];
                if (sum > max && sum <= k) max = sum;
                if (max == k) return k;
            }
        }
        return max;
    }
}