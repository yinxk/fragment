package leetcode;

/**
 * https://leetcode-cn.com/problems/container-with-most-water/
 */
public class A11ContainerWithMostWater {

    public static void main(String[] args) {

        A11ContainerWithMostWater water = new A11ContainerWithMostWater();
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(water.maxArea3(height));
    }

    /*
    官方解法, 不做过多判断
     */
    public int maxArea3(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r]) {
                ++l;
            } else {
                --r;
            }
        }
        return ans;
    }

    /*
    没有想出来其他合适的解法, 看了解答, 其中双指针法的思想, 先自己写下
     */
    public int maxArea2(int[] height) {
        int left, right, leftVal, rightVal, lastMaxLeftVal = 0, lastMaxRightVal = 0, len = height.length;
        left = 0;
        right = len - 1;

        int maxArea = 0, area = 0;
        while (left < right) {
            leftVal = height[left];
            rightVal = height[right];
            if (leftVal >= rightVal) {
                if (rightVal > lastMaxRightVal) {
                    area = (right - left) * rightVal;
                    maxArea = Math.max(maxArea, area);
                }
                lastMaxRightVal = Math.max(lastMaxRightVal, rightVal);
                right--;
            } else {
                if (leftVal > lastMaxLeftVal) {
                    area = (right - left) * leftVal;
                    maxArea = Math.max(maxArea, area);
                }
                lastMaxLeftVal = Math.max(lastMaxLeftVal, leftVal);
                left++;
            }
        }

        return maxArea;
    }


    /*
     * 最容易考虑到暴力解法
     * 超出了时间限制
     */
    public int maxArea1(int[] height) {
        int maxArea = 0;
        int len = height.length;
        int a, b;
        for (int i = 0; i < len; i++) {
            a = height[i];
            for (int j = 1; j < len; j++) {
                b = height[j];
                maxArea = Math.max(maxArea, ((j - i) * Math.min(a, b)));
            }
        }
        return maxArea;
    }


}
