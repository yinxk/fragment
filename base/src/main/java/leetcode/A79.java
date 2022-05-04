package leetcode;

public class A79 {

    public static void main(String[] args) {
        A79 obj = new A79();
        System.out.println(obj.exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}}, "ABCCED"));
    }
    // 还存在一些问题, 暂时先跳过了
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null || word.length() == 0) {
            return false;
        }
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (backTrack(board, m, n, visited, word, 0, i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean backTrack(char[][] board, int m, int n, boolean[][] visited, String word, int index, int x, int y) {
        if (x < 0 || x == m || y < 0 || y >= n) {
            return false;
        }
        if (index == word.length() - 1) {
            return true;
        }
        if (visited[x][y] || board[x][y] != word.charAt(index)) {
            return false;
        }
        visited[x][y] = true;
        int[] directions = new int[]{-1, 0, 1, 0, -1};
        boolean res = false;
        if (word.charAt(index) == board[x][y]) {
            for (int i = 0; i < directions.length - 1; i++) {
                res |= backTrack(board, m, n, visited, word, index + 1, x + directions[i], y + directions[i + 1]);
            }
        }
        visited[x][y] = false;
        return res;
    }
}
