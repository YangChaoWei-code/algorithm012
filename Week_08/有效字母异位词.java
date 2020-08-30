//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。 
//
// 示例 1: 
//
// 输入: s = "anagram", t = "nagaram"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s = "rat", t = "car"
//输出: false 
//
// 说明: 
//你可以假设字符串只包含小写字母。 
//
// 进阶: 
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ 
// Related Topics 排序 哈希表 


import java.util.Arrays;
import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isAnagram(String s, String t) {
        //1.数组比较
//        char[] s1 = s.toCharArray();
//        char[] t1 = t.toCharArray();
//
//        Arrays.sort(s1);
//        Arrays.sort(t1);
//        return Arrays.equals(s1, t1);

        //2.数组映射
//        if (s.length() != t.length()) return false;
//        int[] dp = new int[26];
//        for (int i = 0; i < s.length(); i++) {
//            dp[s.charAt(i) - 'a']++;
//            dp[t.charAt(i) - 'a']--;
//        }
//        for (int i = 0; i < dp.length; i++) {
//            if (dp[i] != 0) return false;
//        }
//        return true;

        //3.哈希表
        if(s.length() != t.length()) return false;
        Map<Character, Integer> map = new HashMap<>();
        for (Character s1 : s.toCharArray()
             ) {
            map.put(s1, map.getOrDefault(s1, 0) + 1);
        }

        for (Character t1 : t.toCharArray()
             ) {
            int count = map.getOrDefault(t1, 0);
            if (count > 0) {
                count--;
                if (count > 0) {
                    map.put(t1, count);
                } else {
                    map.remove(t1);
                }
            }
        }
        return map.isEmpty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)