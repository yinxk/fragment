package leetcode;

public class A69 {

    public static void main(String[] args) {

        A69 obj = new A69();
        System.out.println(obj.mySqrt(4));
        System.out.println(obj.mySqrt(8));
    }


    public int mySqrt(int x) {
        if (x == 0) {
            return x;
        }
        int left = 1;
        int right = x;
        int mid = 0;
        int sqrt;
        while (left <= right) {
            mid = left + (right - left) / 2;
            sqrt = x / mid;
            if (sqrt == mid) {
                return mid;
            } else if (mid > sqrt) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    public int mySqrt1(int a) {
        if (a == 0) return a;
        int l = 1, r = a, mid, sqrt;
        while (l <= r) {
            mid = l + (r - l) / 2;
            sqrt = a / mid;
            if (sqrt == mid) {
                return mid;
            } else if (mid > sqrt) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return r;
    }
}
