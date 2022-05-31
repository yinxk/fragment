package leetcode;

public class A37 {

    public static void main(String[] args) {
        A37 obj = new A37();
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " \t");
            }
            System.out.println();
        }
        System.out.println(obj.solveSudoku(board));
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " \t");
            }
            System.out.println();
        }

    }

    public boolean solveSudoku(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] columns = new int[9][9];
        int[][][] subboxes = new int[3][3][9];
        boolean[][] visited = new boolean[9][9];
        int len = board.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (!dfs(board, visited, i, j, rows, columns, subboxes)) {
                    return false;
                }
            }
        }
        return true;
    }


    public boolean dfs(char[][] board, boolean[][] visited, int i, int j, int[][] rows, int[][] columns, int[][][] subboxes) {
        if (i < 0 || j < 0 || i >= 9 || j >= 9 ) {
            return false;
        }
        if (visited[i][j]) {
            return true;
        }
        visited[i][j] = true;
        int pos;
        if (board[i][j] != '.') {
            pos = board[i][j] - '0' - 1;
            rows[i][pos]++;
            columns[pos][j]++;
            subboxes[i / 3][j / 3][pos]++;
        } else {
            boolean flag = false;
            for (pos = 0; pos < 9; pos++) {
                if (rows[i][pos] > 0 || columns[pos][j] > 0 || subboxes[i / 3][j / 3][pos] > 0) {
                    continue;
                }
                rows[i][pos]++;
                columns[pos][j]++;
                subboxes[i / 3][j / 3][pos]++;
                board[i][j] = (char) (pos + '0' + 1);
                int[] directions = new int[]{-1, 0, 1, 0, -1};
                boolean res = false;
                for (int l = 0; l < 4; l++) {
                    res |= dfs(board, visited, i + directions[l], j + directions[l + 1], rows, columns, subboxes);
                }
                flag |= res;
                if (!res) {
                    board[i][j] = '.';
                    rows[i][pos]--;
                    columns[pos][j]--;
                    subboxes[i / 3][j / 3][pos]--;
                    visited[i][j] = false;
                }
            }
            return flag;
        }
        return true;
    }

}
