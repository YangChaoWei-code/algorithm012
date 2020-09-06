//给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。 
//
// 示例 1: 
//
// 输入: "(()"
//输出: 2
//解释: 最长有效括号子串为 "()"
// 
//
// 示例 2: 
//
// 输入: ")()())"
//输出: 4
//解释: 最长有效括号子串为 "()()"
// 
// Related Topics 字符串 动态规划 
// 👍 946 👎 0


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestValidParentheses(String s) {
        //1. 栈
//        if (s == null || s.length() == 0) return 0;
//        Stack<Integer> stack = new Stack<>();
//        stack.push(-1);
//        int max = 0;
//        for (int i = 0; i < s.length(); i++) {
//            if (s.charAt(i) == '(') {
//                stack.push(i);
//            } else {
//                stack.pop();
//                if (stack.isEmpty()){
//                    stack.push(i);
//                } else {
//                    max = Math.max(max, i - stack.peek());
//                }
//            }
//        }
//        return max;
        //2.正逆向结合
//        int left = 0, right = 0, maxLength = 0;
//        for (int i = 0; i < s.length(); i++) {
//            if (s.charAt(i) == '(') {
//                left++;
//            } else {
//                right++;
//            }
//            if (left == right) {
//                maxLength = Math.max(maxLength, right * 2);
//            } else if (right > left) {
//                left = right = 0;
//            }
//        }
//        left = right = 0;
//        for (int i = s.length() - 1; i >= 0; i--) {
//            if (s.charAt(i) == '(') {
//                left++;
//            } else {
//                right++;
//            }
//
//            if (left == right) {
//                maxLength = Math.max(maxLength, left * 2);
//            } else if (left > right) {
//                left = right = 0;
//            }
//        }
//        return maxLength;

        //3.动态规划
        int max = 0;
        int[] dp = new int[s.length()];

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                max = Math.max(dp[i], max);
            }
        }
        return max;
    }
}