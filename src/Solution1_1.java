public class Solution1_1 {

    public static String solution(String str) {
        return new String(str.chars().map((ch) -> {
            if ('a' <= ch && ch <= 'z') {
                return 'a' + 'z' - ch;
            }
            return ch;
        }).toArray(), 0, str.length());
    }
}
