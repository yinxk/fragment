package fragment.read.pattern.decorator;

import java.io.*;

public class InputTest {
    public static void main(String[] args) {

        int c;
        try {
            InputStream in = new LowerCaseInputStream(
                    new BufferedInputStream(
                            new FileInputStream(
                                    new File(InputTest.class.getClassLoader().getResource("").getPath())
                                            + File.separator +
                                            "test.txt")));
            while ((c = in.read()) >= 0) {
                System.out.print((char) c);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
