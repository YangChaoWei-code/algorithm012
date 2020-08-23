//编写一个程序，通过已填充的空格来解决数独问题。 
//
// 一个数独的解法需遵循如下规则： 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。 
// 
//
// 空白格用 '.' 表示。 
//
// 
//
// 一个数独。 
//
// 
//
// 答案被标成红色。 
//
// Note: 
//
// 
// 给定的数独序列只包含数字 1-9 和字符 '.' 。 
// 你可以假设给定的数独只有唯一解。 
// 给定数独永远是 9x9 形式的。 
// 
// Related Topics 哈希表 回溯算法 


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    boolean[][] row = new boolean[10][10];
    boolean[][] col = new boolean[10][10];
    boolean[][] box = new boolean[10][10];
    boolean flag = false;
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) return;
        //1.回溯
        //backtrack(board, 0, 0);
        //2.
        //slove(board);
        //3.
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(board[i][j] == '.') continue;
                int temp = 3*(i / 3) + j / 3;
                int num = board[i][j] - '0';
                col[j][num] = row[i][num] = box[temp][num] = true;
            }
        }
        dfs(board, 0, 0);
    }

    private void dfs(char[][] board, int i, int j) {
        if (flag == true) return;
        if (i >= 9){
            flag = true;
            return;
        }
        if (board[i][j] != '.') {
            if (j < 8) dfs(board, i, j + 1);
            else dfs(board, i + 1, 0);
            if (flag) return;
        } else {
            int temp = 3 * (i / 3) + j / 3;
            for (char n = '1'; n <= '9'; n++) {
                int num = n - '0';
                if (!col[j][num] && !row[i][num] && !box[temp][num]) {
                    board[i][j] = n;
                    col[j][num] = row[i][num] = box[temp][num] = true;
                    if (j < 8) dfs(board, i, j + 1);
                    else dfs(board, i + 1, 0);
                    col[j][num] = row[i][num] = box[temp][num] = false;
                    if (flag) return;
                }
            }
            board[i][j] = '.';
        }
    }

    private boolean slove(char[][] board){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;

                            if (slove(board))
                                return true;
                            else
                                board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean backtrack(char[][] board, int i, int j) {
        int m = 9, n = 9;
        if (j == n) {
            //穷举到最后一列的话就换到下一行重新开始
            return backtrack(board, i + 1, 0);
        }
        if (i == m) {
            // 找到一个可行解，触发base case
            return true;
        }
        // 如果该位置是预设的数字，不需要穷举
        if (board[i][j] != '.'){
            return backtrack(board, i, j + 1);
        }
        for (char ch = '1'; ch <= '9'; ch++) {
            //如果遇到不合法的数字就跳过
            if (!isValid(board, i, j, ch)) continue;

            board[i][j] = ch;
            if (backtrack(board, i, j + 1)) {
                return true;
            }
            board[i][j] = '.';
        }
        return false;
    }
    // 判断board[i][j]是否可以填入n
    private boolean isValid(char[][] board, int r, int c, char n) {
        for (int i = 0; i < 9; i++) {
            //判断行是否存在重复
            if (board[r][i] == n) return false;
            //判断列是否存在重复
            if (board[i][c] == n) return false;
            //判断3 * 3方框是否存在重复
            if (board[(r / 3) * 3 + i / 3][(c / 3) * 3 + i % 3] == n) return false;
        }
        return true;
    }
}