

public class Solution2_2 {

    public static int solution(String s) {
        int count = 0;
        
        int right_way = 0;
        for(int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '>') {
                right_way += 1;
            }
            else if (s.charAt(i) == '<') {
                count += 2 * right_way;
            }
        }

        return count;
    }
}