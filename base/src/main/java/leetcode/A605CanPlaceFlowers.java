package leetcode;

/**
 * https://leetcode-cn.com/problems/can-place-flowers/
 */
public class A605CanPlaceFlowers {


    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if ((i <= 0 || flowerbed[i - 1] == 0) && flowerbed[i] == 0 &&
                    (i + 1 >= flowerbed.length || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                count++;
            }
        }
        return count >= n;
    }

    public static void main(String[] args) {

        A605CanPlaceFlowers a605CanPlaceFlowers = new A605CanPlaceFlowers();
        System.out.println(a605CanPlaceFlowers.canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 1));
        System.out.println(a605CanPlaceFlowers.canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 2));

    }
}
