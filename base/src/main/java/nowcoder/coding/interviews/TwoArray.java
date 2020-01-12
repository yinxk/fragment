package nowcoder.coding.interviews;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/*
在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
每一列都按照从上到下递增的顺序排序。
请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数
 */
public class TwoArray {
    
    public static void main(String[] args) {
        int reRunTimes = 3;
        int rowBound = 500;
        int colBound = 500;
        int start = 1;
        int stepBound = 5;
        for (int i = 0; i < reRunTimes; i++) {
            rand(rowBound, colBound, start, stepBound);
        }
        
    }
    
    public static void rand(int rowBound, int colBound, int start, int stepBound) {
        Random random = new Random();
        
        int row = random.nextInt(rowBound);
        int col = random.nextInt(colBound);
        int colVal = start;
        int targetBound = row + col;
        int target = random.nextInt(targetBound);
        boolean hasTarget = false;
        
        int[][] array = new int[row][col];
        for (int i = 0; i < row; i++) {
            if (i != 0) {
                colVal = array[i - 1][0];
            }
            for (int j = 0; j < col; j++) {
                if (i > 0 || j > 0) {
                    colVal = colVal + random.nextInt(stepBound) + 1;
                }
                if (i > 0) {
                    colVal = Math.max(colVal, array[i - 1][j]) + random.nextInt(stepBound) + 1;
                }
                array[i][j] = colVal;
                
                if (colVal == target) {
                    hasTarget = true;
                }
                
            }
        }
        
        
        // hasTarget = true;
        // target = 7;
        // int[][] array = new int[][]{
        //         {1, 2, 8, 9},
        //         {2, 4, 9, 12},
        //         {4, 7, 10, 13},
        //         {6, 8, 11, 15}
        // };
        // target = 15;
        // int[][] array = new int[][]{
        //         {1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}
        // };
        
        // target = 7;
        // int[][] array = new int[][]{
        //         {1, 2, 8, 9}, {4, 7, 10, 13}
        // };
        // target = 254;
        // int[][] array = new int[][]{
        //         {1, 4, 7, 8, 9, 10, 12, 14, 17, 20, 21, 23, 25, 28, 29, 32, 35, 38, 39, 42, 45, 48, 50, 52, 55, 57, 58, 59, 60, 61, 62, 65, 68, 70, 71, 73, 75, 78, 80, 82, 83, 84, 85, 86, 88, 91, 94, 97, 99, 102, 103, 105, 106, 108, 109, 112, 113, 115, 118, 120, 123, 126, 128, 129, 130, 133, 135, 138, 140, 141}
        //         , {2, 7, 10, 12, 13, 15, 16, 19, 22, 25, 28, 31, 34, 35, 37, 39, 41, 44, 46, 49, 51, 53, 54, 57, 58, 60, 61, 62, 64, 65, 67, 69, 72, 75, 78, 79, 80, 81, 82, 85, 88, 91, 93, 94, 95, 97, 98, 99, 102, 105, 107, 109, 111, 114, 117, 119, 122, 124, 125, 126, 129, 132, 134, 137, 138, 141, 144, 146, 149, 152}
        //         , {4, 9, 12, 15, 18, 19, 22, 24, 26, 28, 29, 33, 36, 38, 40, 43, 46, 49, 51, 52, 53, 56, 59, 61, 64, 67, 69, 72, 73, 74, 76, 77, 79, 81, 83, 86, 87, 89, 90, 91, 93, 96, 98, 100, 102, 103, 106, 107, 109, 110, 111, 114, 117, 120, 122, 123, 126, 129, 130, 131, 132, 135, 137, 139, 141, 144, 146, 148, 152, 155}
        //         , {6, 12, 15, 18, 21, 22, 24, 26, 29, 31, 34, 36, 39, 41, 43, 46, 49, 50, 54, 56, 59, 62, 65, 66, 68, 70, 73, 75, 78, 81, 82, 83, 85, 87, 88, 90, 92, 93, 94, 95, 96, 99, 101, 103, 106, 108, 111, 113, 114, 115, 117, 118, 121, 124, 126, 128, 131, 132, 135, 137, 138, 139, 140, 142, 143, 146, 149, 152, 155, 158}
        //         , {7, 13, 16, 20, 22, 24, 25, 27, 32, 35, 37, 40, 42, 45, 47, 49, 51, 53, 57, 59, 62, 65, 67, 69, 71, 73, 74, 76, 79, 82, 85, 88, 90, 93, 94, 97, 100, 101, 102, 105, 106, 107, 110, 112, 113, 116, 119, 120, 123, 126, 128, 129, 131, 133, 135, 138, 141, 144, 146, 147, 150, 152, 154, 157, 158, 160, 163, 166, 168, 170}
        //         , {10, 15, 17, 21, 24, 27, 29, 31, 35, 37, 39, 42, 44, 48, 51, 52, 55, 56, 59, 62, 64, 68, 71, 74, 77, 80, 83, 85, 86, 87, 89, 92, 95, 98, 101, 104, 105, 107, 109, 111, 114, 116, 119, 122, 124, 126, 127, 129, 132, 135, 136, 139, 140, 143, 144, 146, 149, 151, 154, 155, 156, 157, 160, 163, 165, 168, 171, 172, 173, 176}
        //         , {12, 17, 20, 23, 27, 30, 32, 33, 38, 40, 41, 45, 48, 51, 54, 56, 58, 61, 64, 65, 68, 70, 73, 76, 79, 82, 84, 87, 90, 91, 92, 94, 97, 99, 104, 107, 109, 110, 113, 116, 117, 118, 121, 125, 126, 129, 132, 134, 136, 138, 139, 142, 145, 146, 149, 151, 152, 154, 157, 160, 163, 166, 168, 171, 174, 177, 178, 180, 182, 184}
        //         , {15, 18, 21, 25, 28, 32, 35, 37, 39, 43, 45, 47, 51, 52, 56, 59, 61, 64, 66, 68, 71, 73, 76, 79, 80, 84, 87, 89, 91, 94, 97, 98, 101, 104, 106, 110, 113, 115, 118, 119, 121, 124, 127, 128, 130, 132, 135, 136, 138, 139, 140, 145, 148, 150, 153, 154, 155, 157, 158, 163, 166, 169, 171, 174, 177, 180, 183, 185, 187, 190}
        //         , {18, 20, 23, 28, 29, 35, 38, 39, 41, 45, 47, 50, 52, 54, 57, 60, 64, 66, 68, 71, 73, 76, 78, 80, 83, 86, 89, 92, 95, 98, 100, 102, 103, 106, 109, 111, 114, 118, 120, 121, 123, 127, 130, 133, 134, 135, 138, 141, 143, 145, 148, 149, 151, 153, 155, 158, 161, 162, 165, 167, 168, 172, 173, 177, 178, 182, 185, 188, 190, 193}
        //         , {19, 22, 25, 31, 32, 36, 39, 42, 44, 46, 50, 52, 55, 56, 59, 63, 67, 69, 72, 75, 78, 79, 80, 83, 85, 88, 91, 94, 97, 99, 102, 104, 106, 108, 111, 114, 117, 119, 122, 124, 126, 130, 133, 136, 139, 140, 142, 145, 148, 151, 154, 156, 158, 159, 161, 163, 164, 166, 169, 172, 173, 175, 177, 179, 182, 185, 188, 190, 193, 196}
        //         , {20, 24, 27, 33, 35, 39, 41, 44, 46, 49, 51, 55, 58, 60, 63, 65, 69, 70, 75, 78, 80, 82, 85, 86, 88, 91, 94, 97, 100, 103, 106, 108, 111, 114, 117, 120, 121, 123, 125, 128, 131, 132, 135, 137, 140, 141, 144, 147, 149, 153, 156, 158, 160, 162, 163, 166, 168, 171, 174, 175, 178, 180, 182, 185, 187, 188, 191, 194, 196, 199}
        //         , {22, 27, 30, 34, 38, 42, 43, 46, 49, 51, 52, 57, 60, 63, 65, 68, 72, 73, 77, 80, 81, 84, 87, 90, 91, 93, 96, 99, 103, 105, 108, 111, 114, 115, 120, 122, 125, 127, 130, 132, 135, 136, 139, 141, 143, 144, 147, 148, 151, 154, 158, 159, 163, 165, 167, 169, 172, 174, 176, 178, 180, 183, 185, 187, 190, 192, 194, 197, 200, 202}
        //         , {25, 28, 33, 36, 39, 45, 46, 48, 52, 55, 57, 60, 63, 66, 69, 71, 74, 76, 80, 82, 84, 86, 90, 93, 96, 99, 101, 103, 105, 108, 110, 112, 115, 117, 121, 123, 126, 130, 132, 134, 137, 140, 143, 146, 149, 150, 153, 155, 158, 160, 162, 165, 168, 170, 172, 175, 177, 179, 180, 182, 183, 185, 186, 189, 192, 194, 197, 200, 203, 206}
        //         , {27, 31, 34, 38, 41, 46, 49, 51, 55, 58, 59, 61, 66, 68, 72, 75, 76, 79, 82, 85, 86, 89, 93, 95, 98, 101, 104, 107, 110, 113, 115, 117, 120, 121, 123, 124, 129, 131, 135, 138, 140, 143, 146, 148, 152, 154, 155, 157, 159, 161, 163, 166, 171, 174, 176, 177, 180, 183, 186, 188, 190, 193, 194, 195, 198, 200, 202, 203, 205, 209}
        //         , {29, 33, 35, 41, 43, 49, 51, 53, 57, 60, 62, 63, 68, 70, 75, 77, 80, 82, 84, 87, 88, 90, 95, 98, 100, 104, 107, 109, 112, 115, 118, 121, 124, 125, 126, 127, 130, 133, 138, 140, 142, 144, 148, 150, 155, 157, 160, 163, 164, 167, 170, 172, 174, 176, 179, 182, 183, 186, 189, 191, 192, 196, 198, 201, 203, 206, 207, 210, 212, 213}
        //         , {31, 34, 38, 43, 46, 51, 54, 57, 58, 61, 65, 66, 71, 72, 77, 79, 82, 85, 86, 90, 92, 95, 98, 101, 102, 106, 109, 112, 114, 118, 121, 124, 126, 128, 129, 131, 134, 136, 139, 143, 145, 148, 151, 152, 158, 160, 161, 164, 167, 170, 171, 174, 176, 179, 182, 185, 186, 189, 192, 195, 198, 200, 203, 205, 206, 209, 210, 213, 215, 216}
        //         , {32, 37, 41, 44, 47, 53, 56, 60, 61, 64, 67, 69, 72, 73, 80, 83, 85, 86, 88, 93, 95, 98, 101, 104, 106, 109, 112, 115, 116, 121, 123, 126, 128, 130, 132, 133, 136, 138, 141, 145, 147, 150, 154, 156, 160, 163, 166, 167, 169, 172, 174, 176, 179, 181, 185, 187, 190, 193, 195, 197, 201, 203, 206, 207, 210, 213, 214, 217, 219, 221}
        //         , {34, 39, 43, 47, 50, 55, 58, 62, 63, 67, 70, 72, 75, 77, 82, 84, 87, 88, 91, 95, 98, 100, 104, 106, 108, 111, 113, 116, 118, 124, 126, 129, 131, 133, 135, 138, 140, 142, 144, 147, 149, 153, 157, 160, 163, 164, 169, 170, 173, 176, 178, 179, 181, 184, 186, 190, 193, 195, 198, 200, 203, 206, 209, 210, 212, 214, 217, 220, 222, 224}
        //         , {37, 41, 45, 50, 53, 57, 61, 64, 67, 70, 71, 73, 77, 79, 83, 86, 90, 93, 94, 97, 99, 102, 107, 109, 112, 113, 115, 118, 121, 126, 129, 132, 135, 138, 141, 143, 144, 146, 149, 151, 152, 156, 160, 163, 166, 167, 170, 171, 176, 179, 181, 184, 187, 190, 192, 193, 195, 198, 200, 203, 205, 208, 212, 215, 218, 220, 221, 222, 224, 227}
        //         , {40, 43, 47, 53, 55, 59, 63, 66, 70, 72, 74, 76, 78, 80, 84, 89, 91, 94, 97, 100, 102, 103, 109, 111, 114, 117, 120, 121, 122, 128, 132, 135, 138, 140, 144, 146, 148, 149, 151, 154, 157, 158, 163, 164, 167, 170, 173, 175, 178, 180, 183, 185, 188, 191, 193, 195, 197, 201, 204, 206, 207, 209, 213, 216, 220, 223, 224, 227, 228, 231}
        //         , {42, 45, 50, 54, 57, 62, 66, 68, 72, 74, 77, 79, 82, 83, 86, 90, 94, 96, 99, 103, 105, 108, 110, 114, 116, 120, 123, 125, 126, 130, 133, 137, 140, 142, 146, 149, 151, 153, 156, 157, 159, 161, 164, 167, 168, 172, 175, 177, 181, 184, 187, 189, 192, 194, 197, 198, 200, 202, 206, 209, 211, 214, 215, 218, 223, 226, 229, 232, 235, 237}
        //         , {45, 46, 51, 57, 60, 65, 68, 71, 75, 76, 79, 82, 84, 86, 89, 92, 96, 99, 101, 106, 107, 111, 112, 116, 119, 122, 126, 128, 130, 132, 135, 140, 143, 145, 148, 151, 153, 155, 158, 161, 164, 166, 167, 169, 170, 175, 177, 180, 183, 186, 190, 192, 195, 197, 199, 200, 203, 205, 207, 211, 214, 217, 219, 222, 224, 228, 231, 234, 238, 240}
        //         , {47, 50, 53, 58, 63, 67, 71, 74, 76, 78, 81, 83, 86, 88, 91, 93, 97, 101, 104, 109, 112, 113, 116, 119, 121, 125, 128, 130, 132, 135, 137, 143, 146, 148, 151, 154, 155, 158, 159, 163, 165, 168, 170, 172, 173, 178, 181, 184, 186, 189, 191, 194, 196, 200, 202, 205, 206, 207, 210, 214, 217, 220, 223, 225, 227, 231, 233, 236, 240, 243}
        //         , {50, 53, 56, 59, 65, 69, 72, 75, 79, 80, 84, 85, 89, 91, 94, 96, 100, 102, 106, 110, 115, 118, 121, 124, 127, 130, 133, 135, 138, 139, 142, 145, 149, 151, 153, 156, 157, 161, 163, 166, 168, 171, 172, 174, 177, 179, 184, 185, 187, 190, 193, 196, 199, 203, 206, 208, 209, 211, 213, 217, 219, 221, 224, 227, 230, 233, 234, 239, 243, 246}
        //         , {51, 56, 59, 62, 66, 71, 74, 78, 81, 84, 87, 89, 92, 94, 95, 99, 102, 105, 108, 111, 117, 121, 122, 126, 130, 132, 136, 138, 141, 142, 145, 147, 151, 152, 155, 157, 159, 164, 166, 167, 169, 172, 173, 175, 180, 181, 185, 187, 189, 193, 195, 198, 202, 204, 207, 211, 213, 215, 217, 219, 222, 223, 226, 230, 232, 235, 236, 241, 246, 249}
        //         , {53, 59, 62, 64, 68, 73, 77, 79, 83, 87, 89, 92, 95, 98, 100, 101, 103, 108, 110, 114, 119, 122, 125, 129, 133, 135, 139, 142, 144, 146, 148, 150, 154, 156, 157, 159, 161, 165, 168, 170, 173, 174, 175, 178, 181, 184, 188, 191, 192, 195, 196, 200, 205, 208, 211, 214, 216, 219, 222, 224, 227, 228, 229, 233, 235, 238, 239, 242, 248, 252}
        //         , {55, 60, 65, 68, 70, 74, 80, 82, 86, 90, 92, 94, 97, 100, 101, 104, 107, 110, 113, 115, 121, 125, 128, 131, 135, 138, 141, 145, 147, 149, 151, 154, 157, 159, 160, 161, 163, 166, 171, 173, 175, 178, 181, 184, 186, 189, 192, 194, 195, 197, 199, 201, 206, 209, 213, 215, 217, 220, 223, 226, 229, 230, 232, 234, 238, 241, 243, 246, 251, 254}
        //         , {58, 61, 66, 69, 73, 75, 82, 83, 89, 92, 95, 97, 100, 102, 104, 106, 109, 111, 116, 118, 122, 128, 131, 134, 137, 139, 142, 148, 149, 152, 155, 158, 161, 164, 165, 168, 170, 171, 174, 176, 179, 182, 184, 186, 188, 190, 195, 196, 199, 202, 204, 206, 209, 211, 214, 216, 220, 223, 224, 227, 232, 234, 236, 237, 240, 242, 244, 247, 254, 256}
        //
        // };
        for (int[] ints : array) {
            for (int anInt : ints) {
                System.out.print(String.format("%-8s", anInt));
            }
            System.out.println();
        }
        boolean actual = find(target, array);
        String s = String.format("target:%s expect:%s actual:%s", target, hasTarget, actual);
        if (hasTarget == actual) {
            System.out.println(s);
        } else {
            System.err.println(s);
        }
        System.out.println("===================================================================");
    }
    
    public static boolean find(int target, int[][] array) {
        // for (int i = 0; i < array.length; i++) {
        //     for (int j = 0; j < array[i].length; j++) {
        //         if (array[i][j] == target) {
        //             return true;
        //         }
        //     }
        // }
        // return false;
        if (array == null || array.length < 1 || array[0].length < 1) {
            return false;
        }
        AtomicLong runTimes = new AtomicLong(0);
        int find = find0(target, array, 0, 0, array.length - 1, array[0].length - 1, runTimes);
        System.out.println("probably runTimes:" + runTimes.get());
        return find > 0;
    }
    
    public static int find0(int target, int[][] array, int row1, int col1, int row2, int col2, AtomicLong runTimes) {
        runTimes.getAndIncrement();
        if (target < array[row1][col1]) {
            return 0;
        }
        if (target > array[row2][col2]) {
            return 0;
        }
        if (row2 - row1 <= 1 && col2 - col1 <= 1) {
            for (int i = row1; i <= row2; i++) {
                for (int j = col1; j <= col2; j++) {
                    runTimes.incrementAndGet();
                    if (array[i][j] == target) {
                        return 1;
                    }
                }
            }
            
        }
        int rowStart = row1;
        int colStart = col1;
        int rowEnd = row2;
        int colEnd = col2;
        int rowMid;
        int colMid;
        int midVal;
        boolean rowChange;
        boolean colChange;
        int rowColAllNotChangeTime = 0;
        boolean isCeil = false;
        while (true) {
            runTimes.incrementAndGet();
            rowMid = (rowStart + rowEnd) / 2;
            colMid = (colStart + colEnd) / 2;
            if (isCeil) {
                if (rowMid < rowEnd) {
                    rowMid += 1;
                }
                if (colMid < colEnd) {
                    colMid += 1;
                }
                isCeil = false;
            }
            rowChange = false;
            colChange = false;
            midVal = array[rowMid][colMid];
            if (midVal > target) {
                if (rowStart <= rowMid && rowMid != rowEnd) {
                    rowEnd = rowMid;
                    rowChange = true;
                }
                if (colStart <= colMid && colMid != colEnd) {
                    colEnd = colMid;
                    colChange = true;
                }
            } else if (midVal < target) {
                if (rowMid <= rowEnd && rowMid != rowStart) {
                    rowStart = rowMid;
                    rowChange = true;
                }
                if (colMid <= colEnd && colMid != colStart) {
                    colStart = colMid;
                    colChange = true;
                }
            } else {
                return 1;
            }
            if (!rowChange && !colChange) {
                if (++rowColAllNotChangeTime < 2) {
                    isCeil = true;
                    continue;
                }
                break;
            }
        }
        if (row1 == row2 || col1 == col2) {
            return 0;
        }
        System.out.println("rowStart:" + rowStart + " colStart:" + colStart);
        System.out.println("rowEnd:" + rowEnd + " colEnd:" + colEnd);
        if (rowEnd - row1 - 1 >= 0) {
            int count = find0(target, array, row1, colEnd, rowEnd - 1, col2, runTimes);
            if (count > 0) {
                return count;
            }
        }
        if (row2 - rowStart - 1 >= 0) {
            return find0(target, array, rowStart + 1, col1, row2, colStart, runTimes);
        }
        return 0;
    }
    
    
}
