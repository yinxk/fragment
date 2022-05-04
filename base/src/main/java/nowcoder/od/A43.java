package nowcoder.od;

import java.util.LinkedList;
import java.util.Scanner;

public class A43 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] data = new int[m][n];
        for (int i = 0; i < m; i++) {
            data[i] = new int[n];
            for (int j = 0; j < n; j++) {
                data[i][j] = in.nextInt();
            }
        }
        boolean[][] visited = new boolean[m][n];

        LinkedList<String> path = new LinkedList<>();
        if (dfs(data, m, n, 0, 0, path, visited)) {
            for (String s : path) {
                System.out.println(s);
            }
        }
    }

    private static boolean dfs(int[][] data, int m, int n, int i, int j, LinkedList<String> path, boolean[][] visited) {
        if (i < 0 || i >= m || j < 0 || j >= n || data[i][j] != 0 || visited[i][j]) {
            return false;
        }
        int[] directions = new int[]{-1, 0, 1, 0, -1};
        path.add(String.format("(%s,%s)", i, j));
        visited[i][j] = true;
        if (i == m - 1 && j == n - 1) {
            return true;
        }
        for (int k = 0; k < 4; k++) {
            if (dfs(data, m, n, i + directions[k], j + directions[k + 1], path, visited)) {
                return true;
            }
        }

        path.removeLast();
        visited[i][j] = false;
        return false;
    }
}
