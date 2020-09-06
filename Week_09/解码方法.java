//一条包含字母 A-Z 的消息通过以下方式进行了编码： 
//
// 'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
// 
//
// 给定一个只包含数字的非空字符串，请计算解码方法的总数。 
//
// 示例 1: 
//
// 输入: "12"
//输出: 2
//解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
// 
//
// 示例 2: 
//
// 输入: "226"
//输出: 3
//解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
// 
// Related Topics 字符串 动态规划 
// 👍 489 👎 0


import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numDecodings(String s) {
        //1.动态规划
//        if (s == null || s.length() == 0) return 0;
//        int n = s.length();
//        int[] dp = new int[n + 1];
//        dp[0] = 1;
//        dp[1] = s.charAt(0) != '0' ? 1 : 0;
//        for (int i = 2; i <= n; i++) {
//            int first = Integer.valueOf(s.substring(i - 1, i));
//            int second = Integer.valueOf(s.substring(i - 2, i));
//
//            if (first >= 1 && first <= 9) {
//                dp[i] += dp[i - 1];
//            }
//            if (second >= 10 && second <= 26) {
//                dp[i] += dp[i - 2];
//            }
//        }
//        return dp[n];
        //2.动态规划 空间压缩
//        int len = s.length();
//        int end = 1;
//        int cur = 0;
//        if (s.charAt(len - 1) != '0') {
//            cur = 1;
//        }
//        for (int i = len - 2; i >= 0; i--) {
//            if (s.charAt(i) == '0') {
//                end = cur;
//                cur = 0;
//                continue;
//            }
//            int ans1 = cur;
//            int ans2 = 0;
//            int ten = (s.charAt(i) - '0') * 10;
//            int one = s.charAt(i + 1) - '0';
//            if (ten + one <= 26){
//                ans2 = end;
//            }
//            end = cur;
//            cur = ans1 + ans2;
//        }
//        return cur;

        //3.dp
//        int n = s.length();
//        if (n == 0) return 0;
//
//        int[] dp = new int[n + 1];
//        dp[n] = 1;
//        dp[n - 1] = s.charAt(n - 1) != '0' ? 1 : 0;
//        for (int i = n - 2; i >= 0; i--) {
//            if (s.charAt(i) == '0')continue;
//            else dp[i] = (Integer.parseInt(s.substring(i, i + 2)) <= 26) ? dp[i + 1] + dp[i + 2] : dp[i + 1];
//        }
//        return dp[0];

        //4.递归
        Map<Integer, Integer> map = new HashMap<>();
        return getAns(s, 0, map);
    }

    private int getAns(String s, int start, Map<Integer, Integer> map) {
        if (start == s.length()) {
            return 1;
        }

        if (s.charAt(start) == '0') return 0;

        int m = map.getOrDefault(start, -1);
        if (m != -1){
            return map.get(start);
        }

        int ans1 = getAns(s, start + 1, map);
        int ans2 = 0;
        if (start < s.length() - 1){
            int ten = (s.charAt(start) - '0') * 10;
            int one = s.charAt(start + 1) - '0';
            if (ten + one <= 26) {
                ans2 = getAns(s, start + 2, map);
            }
        }
        map.put(start, ans1 + ans2);
        return ans1 + ans2;
    }
}