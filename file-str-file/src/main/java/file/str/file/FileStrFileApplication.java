package file.str.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@SuppressWarnings("RedundantStringFormatCall")
public class FileStrFileApplication {

    private static final String CONVERT_PATH = "D:/convert/";
    private static final String TO_CONVERT_PATH = "D:/to_convert/";
    private static final String SPE = ";";


    public static void main(String[] args) throws IOException {
        convert(CONVERT_PATH, TO_CONVERT_PATH, true);
//        convert(TO_CONVERT_PATH, CONVERT_PATH, false);
    }

    public static void convert(String source, String dest, boolean convert) throws IOException {
        File context = new File(source);
        if (!context.isDirectory()) {
            System.out.println(String.format("约定路径 [%s] 不是一个目录", CONVERT_PATH));
            return;
        }
        File[] files = context.listFiles();
        if (files == null || files.length == 0) {
            System.out.println(String.format("约定路径 [%s] 中没有文件", CONVERT_PATH));
            return;
        }
        for (File file : files) {
            if (convert) {
                aFile2Str(file, dest);
            } else {
                aStr2File(file, dest);
            }
        }
    }

    public static void aFile2Str(File file, String destPath) throws IOException {
        List<String> encodeList = new ArrayList<>();
        String name;
        if (file.isFile()) {
            name = file.getName();
            encodeList.add(name);
        } else {
            return;
        }
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        int temp;
        byte[] buff = new byte[1024 * 1024];
        while ((temp = bis.read(buff)) != -1) {
            byte[] toEncode;
            if (temp == buff.length) {
                toEncode = buff;
            } else {
                toEncode = new byte[temp];
                System.arraycopy(buff, 0, toEncode, 0, temp);
            }
            encodeList.add(Base64.getEncoder().encodeToString(toEncode));
        }
        bis.close();
        fis.close();

        int size = encodeList.size();
        FileOutputStream fos = new FileOutputStream(destPath + name + ".txt");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(bos);
        for (int i = 0; i < size; i++) {
            String s = encodeList.get(i);
            if (i != 0) {
                outputStreamWriter.write(SPE);
            }
            outputStreamWriter.write(s);
        }
        outputStreamWriter.flush();
        outputStreamWriter.close();
        bos.close();
        fos.close();


        System.out.println(String.format("文件 %s 转换成功", name));
    }

    public static void aStr2File(File file, String destPath) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        InputStreamReader isr = new InputStreamReader(bis);
        int temp;
        StringBuilder sb = new StringBuilder();
        char[] buff = new char[1024];
        while ((temp = isr.read(buff)) != -1) {
            char[] read;
            if (temp == buff.length) {
                read = buff;
            } else {
                read = new char[temp];
                System.arraycopy(buff, 0, read, 0, temp);
            }
            sb.append(read);
        }
        isr.close();
        bis.close();
        fis.close();

        String encodeStr = sb.toString();
        String[] split = encodeStr.split(SPE);
        if (split.length < 2) {
            return;
        }
        String fileName = split[0];

        FileOutputStream fos = new FileOutputStream(destPath + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        boolean first = true;
        for (String s : split) {
            if (first) {
                first = false;
                continue;
            }
            byte[] decode = Base64.getDecoder().decode(s);
            bos.write(decode);
        }
        bos.flush();
        bos.close();
        fos.close();
        System.out.println(String.format("文件 %s 写出成功", fileName));
    }
}
