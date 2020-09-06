//给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。 
//
// 
//
// 示例： 
//
// s = "leetcode"
//返回 0
//
//s = "loveleetcode"
//返回 2
// 
//
// 
//
// 提示：你可以假定该字符串只包含小写字母。 
// Related Topics 哈希表 字符串 
// 👍 257 👎 0


import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int firstUniqChar(String s) {
        if (s.length() == 1) return 0;
        //1.哈希表
//        Map<Character, Integer> map = new HashMap<>();
//        for (Character c: s.toCharArray()
//             ) {
//            map.put(c, map.getOrDefault(c, 0) + 1);
//        }
//
//        for (int i = 0; i < s.length(); i++) {
//            if (map.get(s.charAt(i)) == 1) return i;
//        }
//        return -1;

        //2.数组
        int[] arr = new int[26];

        for (Character c : s.toCharArray()
             ) {
            arr[c - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (arr[s.charAt(i) - 'a'] == 1) return i;
        }
        return -1;
    }
}