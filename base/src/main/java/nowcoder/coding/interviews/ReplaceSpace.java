package nowcoder.coding.interviews;

/**
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class ReplaceSpace {
    public static void main(String[] args) {
        String replaceSpace = replaceSpace(new StringBuffer("We Are Happy"));
        System.out.println(replaceSpace);
    }
    
    public static String replaceSpace(StringBuffer str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        StringBuilder newStr = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            if (c == ' ') {
                newStr.append('%');
                newStr.append('2');
                newStr.append('0');
                continue;
            }
            newStr.append(c);
        
        }
        return newStr.toString();
    }
}
