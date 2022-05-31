package leetcode;

public class A36 {

    public static void main(String[] args) {

        A36 obj = new A36();

    }

    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] columns = new int[9][9];
        int[][][] subboxes = new int[3][3][9];
        int len = board.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int pos = board[i][j] - '0' - 1;
                rows[i][pos]++;
                columns[pos][j]++;
                subboxes[i / 3][j / 3][pos]++;
                if (rows[i][pos] > 1 || columns[pos][j] > 1 || subboxes[i / 3][j / 3][pos] > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidSudoku2(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] columns = new int[9][9];
        int[][][] subboxes = new int[3][3][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int index = c - '0' - 1;
                    rows[i][index]++;
                    columns[j][index]++;
                    subboxes[i / 3][j / 3][index]++;
                    if (rows[i][index] > 1 || columns[j][index] > 1 || subboxes[i / 3][j / 3][index] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
