//你的赛车起始停留在位置 0，速度为 +1，正行驶在一个无限长的数轴上。（车也可以向负数方向行驶。） 
//
// 你的车会根据一系列由 A（加速）和 R（倒车）组成的指令进行自动驾驶 。 
//
// 当车得到指令 "A" 时, 将会做出以下操作： position += speed, speed *= 2。 
//
// 当车得到指令 "R" 时, 将会做出以下操作：如果当前速度是正数，则将车速调整为 speed = -1 ；否则将车速调整为 speed = 1。 (当前所
//处位置不变。) 
//
// 例如，当得到一系列指令 "AAR" 后, 你的车将会走过位置 0->1->3->3，并且速度变化为 1->2->4->-1。 
//
// 现在给定一个目标位置，请给出能够到达目标位置的最短指令列表的长度。 
//
// 示例 1:
//输入: 
//target = 3
//输出: 2
//解释: 
//最短指令列表为 "AA"
//位置变化为 0->1->3
// 
//
// 示例 2:
//输入: 
//target = 6
//输出: 5
//解释: 
//最短指令列表为 "AAARA"
//位置变化为 0->1->3->7->7->6
// 
//
// 说明: 
//
// 
// 1 <= target（目标位置） <= 10000。 
// 
// Related Topics 堆 动态规划 
// 👍 70 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int[] dp = new int[10001];
    public int racecar(int target) {
        //1.动态规划Time O(TlogT) Space O(T)
//        if (dp[target] > 0) return dp[target];
//        int n = (int)(Math.log(target) / Math.log(2)) + 1;
//        if (1 << n == target + 1) {
//            dp[target] = n;
//        } else {
//            dp[target] = racecar((1 << n) - 1 - target) + n + 1;
//            for (int m = 0; m < n - 1; m++) {
//                dp[target] = Math.min(dp[target], racecar(target - (1 << (n - 1)) + (1 << m)) + n + m + 1);
//            }
//        }
//        return dp[target];

        //2.动态规划
        int[] dp = new int[target + 1];
        for (int i = 1; i <= target; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int forword = 1; (1 << forword) - 1 < 2 * i; forword++) {
                int j = (1 << forword) - 1;
                if (j == i) {
                    dp[i] = forword;
                } else if (j > i) {
                    dp[i] = Math.min(dp[i], forword + 1 + dp[j - i]);
                } else {
                    for (int back = 0; back < forword; back++) {
                        int k = (1 << back) - 1;
                        dp[i] = Math.min(dp[i], forword + 1 + back + 1 + dp[i - j + k]);
                    }
                }
            }
        }
        return dp[target];
    }
}
//leetcode submit region end(Prohibit modification and deletion)