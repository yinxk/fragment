package leetcode;

public class A52 {
    public static void main(String[] args) {
        A52 obj = new A52();
        for (int i = 0; i < 10; i++) {
            System.out.println(obj.totalNQueens(i));
        }
    }

    public int totalNQueens(int n) {
        if (n <= 0) {
            return 0;
        }
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        boolean[] columns = new boolean[n];
        return dfs(0, n, board, columns);
    }



    private int dfs(int row, int n, char[][] board, boolean[] columns) {
        if (row == n) {
            return 1;
        }
        int res = 0;
        for (int col = 0; col < n; col++) {
            if (!isValid(board, row, col, n, columns)) {
                continue;
            }
            columns[col] = true;
            board[row][col] = 'Q';
            res += dfs(row + 1, n, board, columns);
            board[row][col] = '.';
            columns[col] = false;
        }
        return res;
    }

    private boolean isValid(char[][] board, int row, int col, int n, boolean[] columns) {
        if (columns[col]) {
            return false;
        }
        int r = row - 1;
        int c = col - 1;
        while (r >= 0 && c >= 0) {
            if (board[r][c] == 'Q') {
                return false;
            }
            r--;
            c--;
        }
        r = row - 1;
        c = col + 1;
        while (r >= 0 && c < n) {
            if (board[r][c] == 'Q') {
                return false;
            }
            r--;
            c++;
        }
        return true;
    }
}
