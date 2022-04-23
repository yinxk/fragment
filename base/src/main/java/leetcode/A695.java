package leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class A695 {

    public static void main(String[] args) {
        int[][] grid = {{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
        A695 obj = new A695();
        System.out.println(obj.maxAreaOfIsland(grid));

    }

    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    // maxArea = Math.max(maxArea, dfsRecursion(grid, i, j));
                    // maxArea = Math.max(maxArea, dfsLoop(grid, i, j));
                    maxArea = Math.max(maxArea, bfs(grid, i, j));
                }
            }
        }
        return maxArea;
    }

    public int dfsRecursion(int[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x == grid.length || y == grid[0].length || grid[x][y] == 0) {
            return 0;
        }
        grid[x][y] = 0;
        // 四个方向, 多一个值为了方便
        int[] directions = new int[]{-1, 0, 1, 0, -1};
        int area = 1;
        // 这里4次迭代, 产生(-1,0), (0,1), (1,0), (0,-1)
        for (int i = 0; i < 4; i++) {
            area += dfsRecursion(grid, x + directions[i], y + directions[i + 1]);
        }
        return area;
        //
        // return 1 + dfsRecursion(grid, x - 1, y) + dfsRecursion(grid, x + 1, y)
        //         + dfsRecursion(grid, x, y - 1) + dfsRecursion(grid, x, y + 1);
    }

    public int dfsLoop(int[][] grid, int x, int y) {
        int area = 0;
        int[] directions = new int[]{-1, 0, 1, 0, -1};
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{x, y});
        while (!stack.isEmpty()) {
            int[] point = stack.pop();
            int a = point[0];
            int b = point[1];
            if (a < 0 || b < 0 || a == grid.length || b == grid[0].length ||
                    grid[a][b] == 0) {
                continue;
            }
            area++;
            grid[a][b] = 0;
            for (int i = 0; i < 4; i++) {
                stack.push(new int[]{a + directions[i], b + directions[i + 1]});
            }
            // stack.push(new int[]{a - 1, b});
            // stack.push(new int[]{a + 1, b});
            // stack.push(new int[]{a, b - 1});
            // stack.push(new int[]{a, b + 1});
        }
        return area;
    }
    public int bfs(int[][] grid, int x, int y) {
        int area = 0;
        int[] directions = new int[]{-1, 0, 1, 0, -1};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int a = point[0];
            int b = point[1];
            if (a < 0 || b < 0 || a == grid.length || b == grid[0].length ||
                    grid[a][b] == 0) {
                continue;
            }
            area++;
            grid[a][b] = 0;
            for (int i = 0; i < 4; i++) {
                queue.add(new int[]{a + directions[i], b + directions[i + 1]});
            }
            // queue.add(new int[]{a - 1, b});
            // queue.add(new int[]{a + 1, b});
            // queue.add(new int[]{a, b - 1});
            // queue.add(new int[]{a, b + 1});
        }
        return area;
    }

}
