package leetcode;

import java.util.ArrayList;
import java.util.List;

public class A54 {

    public static void main(String[] args) {
        A54 obj = new A54();
        System.out.println(obj.spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}));
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        dfs(matrix, res, visited, 0, 0, 1, 0);
        return res;
    }

    private void dfs(int[][] matrix, List<Integer> res, boolean[][] visited, int x, int y, int directionX,
                     int directionY) {
        res.add(matrix[y][x]);
        visited[y][x] = true;
        if (res.size() == matrix.length * matrix[0].length) {
            return;
        }
        matrix[y][x] = -1;
        x = x + directionX;
        y = y + directionY;
        if (y < matrix.length && x < matrix[0].length && x >= 0 && y >= 0 && !visited[y][x]) {
            dfs(matrix, res, visited, x, y, directionX, directionY);
        } else {
            int[] point = nextDirection(directionX, directionY);
            x -= directionX;
            y -= directionY;
            directionX = point[0];
            directionY = point[1];
            x += directionX;
            y += directionY;
            dfs(matrix, res, visited, x, y, directionX, directionY);
        }
    }

    public int[] nextDirection(int x, int y) {
        if (x == 1 && y == 0) {
            return new int[]{0, 1};
        } else if (x == 0 && y == 1) {
            return new int[]{-1, 0};
        } else if (x == -1 && y == 0) {
            return new int[]{0, -1};
        } else if (x == 0 && y == -1) {
            return new int[]{1, 0};
        }
        return null;
    }


}
