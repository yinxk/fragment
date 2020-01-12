package nowcoder.coding.interviews;

/**
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class ReplaceSpace {
    public static void main(String[] args) {
        String replaceSpace = replaceSpace(new StringBuffer("We Are Happy"));
        System.out.println(replaceSpace);
    }
    
    /*
        问题1：替换字符串，是在原来的字符串上做替换，还是新开辟一个字符串做替换！
        问题2：在当前字符串替换，怎么替换才更有效率（不考虑java里现有的replace方法）。
              从前往后替换，后面的字符要不断往后移动，要多次移动，所以效率低下
              从后往前，先计算需要多少空间，然后从后往前移动，则每个字符只为移动一次，这样效率更高一点。
    */
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
    
    public static String replaceSpace2(StringBuffer str) {
        if (str == null) {
            return null;
        }
        int spaceNum = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ')
                spaceNum++;
        }
        int indexOld = str.length() - 1;
        int newLength = str.length() + spaceNum * 2;
        int indexNew = newLength - 1;
        str.setLength(newLength);
        for (; indexOld >= 0 && indexOld < newLength; --indexOld) {
            if (str.charAt(indexOld) == ' ') {  //
                str.setCharAt(indexNew--, '0');
                str.setCharAt(indexNew--, '2');
                str.setCharAt(indexNew--, '%');
            } else {
                str.setCharAt(indexNew--, str.charAt(indexOld));
            }
        }
        return str.toString();
    }
}
