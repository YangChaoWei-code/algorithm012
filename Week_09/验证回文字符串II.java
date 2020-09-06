//给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。 
//
// 示例 1: 
//
// 
//输入: "aba"
//输出: True
// 
//
// 示例 2: 
//
// 
//输入: "abca"
//输出: True
//解释: 你可以删除c字符。
// 
//
// 注意: 
//
// 
// 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。 
// 
// Related Topics 字符串 
// 👍 255 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean validPalindrome(String s) {
        //1.贪心
//        char[] s1 = s.toCharArray();
//        int low = 0, high = s1.length - 1;
//        while (low < high){
//            if (s1[low] == s1[high]){
//                low++;
//                high--;
//            } else {
//                boolean flag1 = true, flag2 = true;
//                for (int i = low, j = high - 1; i < j; i++, j--) {
//                    if (s1[i] != s1[j]){
//                        flag1 = false;
//                        break;
//                    }
//                }
//                for (int i = low + 1, j = high; i < j; i++, j--) {
//                    if (s1[i] != s1[j]) {
//                        flag2 = false;
//                        break;
//                    }
//                }
//                return flag1 || flag2;
//            }
//        }
//        return true;

        //2.双指针
        char[] s1 = s.toCharArray();
        for (int i = 0, j = s1.length - 1; i < j; i++, j--) {
            if (s1[i] != s1[j]){
                return isPalindrome(s1, i + 1, j) || isPalindrome(s1, i, j - 1);
            }
        }
        return true;
    }

    private boolean isPalindrome(char[] s1, int i, int j) {
        while (i < j) {
            if (s1[i++] != s1[j--]) {
                return false;
            }
        }
        return true;
    }
}