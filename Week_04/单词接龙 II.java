//给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换
//需遵循如下规则： 
//
// 
// 每次转换只能改变一个字母。 
// 转换后得到的单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回一个空列表。 
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
//输出:
//[
//  ["hit","hot","dot","dog","cog"],
//  ["hit","hot","lot","log","cog"]
//]
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: []
//
//解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。 
// Related Topics 广度优先搜索 数组 字符串 回溯算法 

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        //bfs
        Set<String> wordSet = new HashSet<>(wordList);
        List<List<String>> res = new ArrayList<>();
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) return res;

        Map<String, Set<String>> successors = new HashMap<>();
        // 使用广度优先搜索得到后继结点列表
        //boolean found = bfs(beginWord, endWord, wordSet, successors);
        // 使用双向广度优先搜素得到后继节点列表
        boolean found = twobfs(beginWord, endWord, wordSet, successors);
        if (!found) return res;

        //使用回溯算法得到所有的最短路径列表
        Deque<String> path = new ArrayDeque<>();
        path.addLast(beginWord);
        dfs(beginWord, endWord, successors, path, res);
        return res;
    }

    private boolean twobfs(String beginWord, String endWord, Set<String> worSet, Map<String, Set<String>> successors){
        //记录访问过的单词
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        visited.add(endWord);

        Set<String> beginVisited = new HashSet<>();
        beginVisited.add(beginWord);

        Set<String> endVisited = new HashSet<>();
        endVisited.add(endWord);


        boolean forward = true;
        boolean found = false;
        while (!beginVisited.isEmpty() && !endVisited.isEmpty()) {
            if (beginVisited.size() > endVisited.size()) {
                Set<String> temp = beginVisited;
                beginVisited = endVisited;
                endVisited = temp;
                forward = !forward;
            }
            Set<String> nextLevelVisited = new HashSet<>();
            for (String currentWord : beginVisited) {
                char[] charArray = currentWord.toCharArray();
                for (int i = 0; i < charArray.length; ++i) {
                    char oldChar = charArray[i];
                    for (char j = 'a'; j <= 'z'; ++j) {
                        if (charArray[i] == j) continue;
                        charArray[i] = j;
                        String nextWord = new String(charArray);
                        if (worSet.contains(nextWord)) {
                            if (endVisited.contains(nextWord)) {
                                found = true;
                                addToSuccessors(successors, forward, currentWord, nextWord);
                            }
                            if (!visited.contains(nextWord)) {
                                nextLevelVisited.add(nextWord);
                                addToSuccessors(successors, forward, currentWord, nextWord);
                            }
                        }
                    }
                    charArray[i] = oldChar;
                }
            }
            beginVisited = nextLevelVisited;
            visited.addAll(nextLevelVisited);
            if (found) break;
        }
        return found;
    }

    private void addToSuccessors(Map<String, Set<String>> successors, boolean forward, String currentWord, String nextWord) {
        if (!forward) {
            String temp = currentWord;
            currentWord = nextWord;
            nextWord = temp;
        }

        if (successors.containsKey(currentWord)) {
            successors.get(currentWord).add(nextWord);
        } else {
            Set<String> set = new HashSet<>();
            set.add(nextWord);
            successors.put(currentWord, set);
        }
    }

    private boolean bfs(String beginWord, String endWord, Set<String> worSet, Map<String, Set<String>> successors){
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        //记录访问过的单词
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        boolean found = false;
        Set<String> nextLevelVisited = new HashSet<>();
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                String currWord = queue.poll();
                char[] charArray = currWord.toCharArray();
                for(int j = 0; j < charArray.length; ++j) {
                    char oldChar = charArray[j];
                    for (char k = 'a'; k <= 'z'; ++k) {
                        if (charArray[j] == k) continue;
                        charArray[j] = k;
                        String nextWord = new String(charArray);
                        if (worSet.contains(nextWord)){
                            if (!visited.contains(nextWord)) {
                                if (nextWord.equals(endWord)) {
                                    found = true;
                                }
                                if (!nextLevelVisited.contains(nextWord)) {
                                    queue.offer(nextWord);
                                    nextLevelVisited.add(nextWord);
                                }

                                if (successors.containsKey(currWord)) {
                                    successors.get(currWord).add(nextWord);
                                } else {
                                    Set<String> set = new HashSet<>();
                                    set.add(nextWord);
                                    successors.put(currWord, set);
                                }
                            }
                        }
                    }
                    charArray[j] = oldChar;
                }
            }
            if (found) break;
            visited.addAll(nextLevelVisited);
            nextLevelVisited.clear();
        }
        return found;
    }

    private void dfs(String beginWord, String endWord, Map<String, Set<String>> successors, Deque<String> path, List<List<String>> res) {
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (!successors.containsKey(beginWord)) return;

        Set<String> words = successors.get(beginWord);
        for (String word : words){
            path.addLast(word);
            dfs(word, endWord, successors, path, res);
            path.removeLast();
        }
    }
}