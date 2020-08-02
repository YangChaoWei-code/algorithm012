//给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
// 
//
// 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回 0。 
// 所有单词具有相同的长度。 
// 所有单词只由小写字母组成。 
// 字典中不存在重复的单词。 
// 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。 
// 
//
// 示例 1: 
//
// 输入:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//输出: 5
//
//解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//     返回它的长度 5。
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: 0
//
//解释: endWord "cog" 不在字典中，所以无法进行转换。 
// Related Topics 广度优先搜索 


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //1.双向bfs
        if (wordList == null || beginWord.equals(endWord) || !wordList.contains(endWord)) return 0;
        wordList.add(beginWord);
        Set<String> newSet = new HashSet<>(wordList);

        Queue<String> bqueue = new LinkedList<>();
        Set<String> bVisited = new HashSet<>();
        bqueue.offer(beginWord);
        bVisited.add(beginWord);

        Queue<String> equeue = new LinkedList<>();
        Set<String> eVisited = new HashSet<>();
        equeue.offer(endWord);
        eVisited.add(endWord);

        int depth = 0;
        while (!bqueue.isEmpty() && !equeue.isEmpty()) {
            if (bqueue.size() > equeue.size()) {
                Queue<String> temp = bqueue;
                bqueue = equeue;
                equeue = temp;
                Set<String> set = bVisited;
                bVisited = eVisited;
                eVisited = set;
            }
            depth++;
            int size = bqueue.size();
            while (size-- > 0) {
                String curr = bqueue.poll();
                char[] charArray = curr.toCharArray();
                for (int i = 0; i < charArray.length; i++) {
                    char oldChar = charArray[i];
                    for (char j = 'a'; j <= 'z'; j++) {
                        if (charArray[i] == j) continue;
                        charArray[i] = j;
                        String nextWord = new String(charArray);
                        if (bVisited.contains(nextWord)) continue;

                        if (eVisited.contains(nextWord)) return depth + 1;

                        if (newSet.contains(nextWord)) {
                            bqueue.offer(nextWord);
                            bVisited.add(nextWord);
                        }
                    }
                    charArray[i] = oldChar;
                }
            }
        }
        return 0;
        //2.单向bfs
//        if (beginWord.equals(endWord) || !wordList.contains(endWord)) return 0;
//
//        Set<String> newSet = new HashSet<>(wordList);
//
//        Queue<String> queue = new LinkedList<>();
//        Set<String> visited = new HashSet<>();
//        queue.offer(beginWord);
//        visited.add(beginWord);
//
//        int level = 0;
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            for (int i = 0; i < size; i++) {
//                String currWord = queue.poll();
//                if (currWord.equals(endWord)) return level + 1;
//                char[] charArray = currWord.toCharArray();
//                for (int j = 0; j < charArray.length; j++) {
//                    char oldChar = charArray[j];
//                    for (char k = 'a'; k <= 'z'; k++) {
//                        if (charArray[j] == k) continue;
//                        charArray[j] = k;
//                        String nextWord = new String(charArray);
//                        if (!visited.contains(nextWord) && newSet.contains(nextWord)) {
//                            visited.add(nextWord);
//                            queue.offer(nextWord);
//                        }
//                    }
//                    charArray[j] = oldChar;
//                }
//            }
//            level++;
//        }
//        return 0;
    }
}