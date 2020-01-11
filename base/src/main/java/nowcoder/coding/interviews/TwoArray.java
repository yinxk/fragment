package nowcoder.coding.interviews;

/*
在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
每一列都按照从上到下递增的顺序排序。
请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数
 */
public class TwoArray {
    
    public static void main(String[] args) {
        
        int row = 1100;
        int col = 2200;
        int start = 1;
        int target = 6587;
        // int[][] array = new int[row][col];
        int rowStart = start;
        int colVal;
        // for (int i = 0; i < row; i++) {
        //     colVal = rowStart;
        //     for (int j = 0; j < col; j++) {
        //         array[i][j] = colVal++;
        //     }
        //     rowStart++;
        // }
        
        target = 7;
        int[][] array = new int[][]{
                {1,2,8,9},
                {2,4,9,12},
                {4,7,10,13},
                {6,8,11,15}
        };
        
        
        System.out.println(find(target, array));
        
        
    }
    
    public static boolean find(int target, int[][] array) {
        int rowMaxIndex = array.length - 1;
        int colMaxIndex = array[0].length - 1;
        if (target < array[0][0]) {
            return false;
        }
        if (target > array[rowMaxIndex][colMaxIndex]) {
            return false;
        }
        
        int left = 0;
        int right = rowMaxIndex;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (array[mid][0] > target) {
                right = mid - 1;
            } else if (array[mid][0] < target) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        if (right < rowMaxIndex) {
            return false;
        }
        left = 0;
        right = colMaxIndex;
        while (left <= right) {
            mid = (left + right) / 2;
            if (array[rowMaxIndex][mid] > target) {
                right = mid - 1;
            } else if (array[rowMaxIndex][mid] < target) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        
        return false;
    }
    
    
}
