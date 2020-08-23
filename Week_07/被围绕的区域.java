//给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。 
//
// 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。 
//
// 示例: 
//
// X X X X
//X O O X
//X X O X
//X O X X
// 
//
// 运行你的函数后，矩阵变为： 
//
// X X X X
//X X X X
//X X X X
//X O X X
// 
//
// 解释: 
//
// 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被
//填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。 
// Related Topics 深度优先搜索 广度优先搜索 并查集 


import java.util.LinkedList;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public class Pos{
        int i;
        int j;
        Pos(int i, int j){
            this.i = i;
            this.j = j;
        }
    }

    //并查集
    class UnionFind{
        int[] parents;

        public UnionFind(int totalNodes) {
            parents = new int[totalNodes];
            for (int i = 0; i < totalNodes; i++) {
                parents[i] = i;
            }
        }

        //合并连通区域
        void union(int node1, int node2) {
            int root1 = find(node1);
            int root2 = find(node2);
            if (root1 != root2) {
                parents[root2] = root1;
            }
        }

        int find(int node) {
            while (parents[node] != node) {
                // 当前节点的父节点指向父节点的父节点。
                // 保证一个连通区域最终的parents只有一个
                parents[node] = parents[parents[node]];
                node = parents[node];
            }
            return node;
        }

        boolean isConnected(int node1, int node2){
            return find(node1) == find(node2);
        }
    }
    private int n;
    public void solve(char[][] board) {
        //1.dfs
        if (board == null || board.length == 0) return;
        int m = board.length;
        n = board[0].length;

        UnionFind uf = new UnionFind(m * n + 1);
        int dummpNode = m * n;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O'){
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1){
                        uf.union(node(i, j), dummpNode);
                    } else {
                        // 和上下左右合并成一个连通区域
                        if (i > 0 && board[i - 1][j] == 'O') {
                            uf.union(node(i, j), node(i - 1, j));
                        }
                        if (i < m - 1 && board[i + 1][j] == 'O') {
                            uf.union(node(i, j), node(i + 1, j));
                        }
                        if (j > 0 && board[i][j - 1] == 'O') {
                            uf.union(node(i, j), node(i, j - 1));
                        }
                        if (j < n - 1 && board[i][j + 1] == 'O') {
                            uf.union(node(i, j), node(i, j + 1));
                        }
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (uf.isConnected(node(i, j), dummpNode)){
                    board[i][j] = 'O';
                }else {
                    board[i][j] = 'X';
                }
            }
        }

        //dfs和bfs
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                boolean isEdge = i == 0 || j == 0 || i == m - 1 || j == n - 1;
//                if (isEdge && board[i][j] == 'O') {
//                    bfs(board, i, j);
//                }
//            }
//        }

//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (board[i][j] == 'O'){
//                    board[i][j] = 'X';
//                }
//                if (board[i][j] == '#') {
//                    board[i][j] = 'O';
//                }
//            }
//        }
    }
    int node(int i, int j){
        return i * n + j;
    }
    //1.递归
    private void dfs(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == 'X' || board[i][j] == '#') {
            return;
        }
        board[i][j] = '#';
        dfs(board, i - 1, j);
        dfs(board, i + 1, j);
        dfs(board, i, j - 1);
        dfs(board, i, j + 1);
    }
    //2.非递归
    private void dfs2(char[][] board, int i, int j) {
        Stack<Pos> stack = new Stack<>();
        stack.push(new Pos(i, j));
        board[i][j] = '#';
        while (!stack.isEmpty()) {
            Pos current = stack.peek();
            //上
            if (current.i - 1 >= 0 && board[current.i - 1][current.j] == 'O') {
                stack.push(new Pos(current.i - 1, current.j));
                board[current.i - 1][current.j] = '#';
                continue;
            }
            //下
            if (current.i + 1 < board.length - 1 && board[current.i + 1][current.j] == 'O') {
                stack.push(new Pos(current.i + 1, current.j));
                board[current.i + 1][current.j] = '#';
                continue;
            }
            //左
            if (current.j - 1 >= 0 && board[current.i][current.j - 1] == 'O') {
                stack.push(new Pos(current.i, current.j - 1));
                board[current.i][current.j - 1] = '#';
                continue;
            }
            //右
            if (current.j + 1 < board[0].length && board[current.i][current.j + 1] == 'O') {
                stack.push(new Pos(current.i, current.j + 1));
                board[current.i][current.j + 1] = '#';
                continue;
            }
            stack.pop();
        }
    }

    private void bfs(char[][] board, int i, int j) {
        Queue<Pos> queue = new LinkedList<>();
        queue.offer(new Pos(i, j));
        board[i][j] = '#';
        while (!queue.isEmpty()){
            Pos current = queue.poll();
            //上
            if (current.i - 1 >= 0 && board[current.i - 1][current.j] == 'O') {
                queue.offer(new Pos(current.i - 1, current.j));
                board[current.i - 1][current.j] = '#';
            }
            //下
            if (current.i + 1 < board.length && board[current.i + 1][current.j] == 'O') {
                queue.offer(new Pos(current.i + 1, current.j));
                board[current.i + 1][current.j] = '#';
            }
            //左
            if (current.j - 1 >= 0 && board[current.i][current.j - 1] == 'O') {
                queue.offer(new Pos(current.i, current.j - 1));
                board[current.i][current.j - 1] = '#';
            }
            //右
            if (current.j + 1 < board[0].length && board[current.i][current.j + 1] == 'O') {
                queue.offer(new Pos(current.i, current.j + 1));
                board[current.i][current.j + 1] = '#';
            }
        }
    }
}