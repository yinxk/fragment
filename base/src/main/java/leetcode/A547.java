package leetcode;

public class A547 {

    public static void main(String[] args) {
        A547 obj = new A547();
        System.out.println(obj.findCircleNum(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));

    }

    public int findCircleNum(int[][] isConnected) {
        int len = isConnected.length;
        boolean[] visited = new boolean[len];
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (!visited[i]) {
                count++;
                dfs(isConnected, i, visited);
            }
        }
        return count;
    }

    private void dfs(int[][] isConnected, int i, boolean[] visited) {
        visited[i] = true;
        int len = isConnected.length;
        for (int j = 0; j < len; j++) {
            if (!visited[j] && isConnected[i][j] == 1) {
                dfs(isConnected, j, visited);
            }
        }
    }
}
