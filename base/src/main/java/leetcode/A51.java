package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class A51 {

    public static void main(String[] args) {

        A51 obj = new A51();
        System.out.println(obj.solveNQueens(1));
        System.out.println(obj.solveNQueens(4));
    }

    public List<List<String>> solveNQueens(int n) {
        if (n <= 0) {
            return Collections.emptyList();
        }
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        boolean[] columns = new boolean[n];
        dfs(0, n, board, columns, res);
        return res;
    }

    private void dfs(int row, int n, char[][] board, boolean[] columns, List<List<String>> res) {
        if (row == n) {
            List<String> it = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                it.add(new String(board[i]));
            }
            res.add(it);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (!isValid(board, row, col, n, columns)) {
                continue;
            }
            columns[col] = true;
            board[row][col] = 'Q';
            dfs(row + 1, n, board, columns, res);
            board[row][col] = '.';
            columns[col] = false;
        }
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
