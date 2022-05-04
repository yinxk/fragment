package nowcoder.od;

import java.util.LinkedList;
import java.util.Scanner;

// visited 可以不用
public class A43_2 {
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

        LinkedList<String> path = new LinkedList<>();
        if (dfs(data, m, n, 0, 0, path)) {
            for (String s : path) {
                System.out.println(s);
            }
        }
    }

    private static boolean dfs(int[][] data, int m, int n, int i, int j, LinkedList<String> path) {
        if (i < 0 || i >= m || j < 0 || j >= n || data[i][j] != 0) {
            return false;
        }
        int[] directions = new int[]{-1, 0, 1, 0, -1};
        path.add(String.format("(%s,%s)", i, j));
        data[i][j] = 1;
        if (i == m - 1 && j == n - 1) {
            return true;
        }
        for (int k = 0; k < 4; k++) {
            if (dfs(data, m, n, i + directions[k], j + directions[k + 1], path)) {
                return true;
            }
        }
        path.removeLast();
        data[i][j] = 0;
        return false;
    }
}
