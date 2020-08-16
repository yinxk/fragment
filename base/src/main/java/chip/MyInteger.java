package chip;

public class MyInteger {


    final int[] data;


    public MyInteger(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException();
        }
        int x = 10;
        int length = 1;
        while (value >= x) {
            length++;
            x *= 10;
        }
        data = new int[length];

        for (int i = 0; i < length; i++) {
            data[i] = (value % 10);
            value = value / 10;
        }
    }

    private MyInteger(int[] data) {
        this.data = data;
    }

    public MyInteger multiply(MyInteger that) {
        int[] a = this.data;
        int[] b = that.data;
        int lenA = a.length;
        int lenB = b.length;
        int lenRes = lenA + lenB;
        int[] res = new int[lenRes];
        MyInteger resInt = new MyInteger(res);

        for (int i = 0; i < lenB; i++) {
            for (int j = 0; j < lenA; j++) {
                int cIndex = i + j;
                int resBit = res[cIndex] + a[j] * b[i];
                res[cIndex] = resBit % 10;
                res[cIndex + 1] += resBit / 10;
            }
        }
        return resInt;
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        int length = data.length;
        boolean firstNotZero = false;
        for (int i = length - 1; i >= 0; i--) {
            if (!firstNotZero && data[i] != 0) {
                firstNotZero = true;
            }
            if (firstNotZero) {
                strBuilder.append(data[i]);
            }
        }
        return strBuilder.toString();
    }
}
