import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Solution2_1 {

    static List<Integer> reverseSub(List<Integer> list, int base) {
        ArrayList<Integer> ascending = new ArrayList<Integer>(list);
        ascending.sort((lhs, rhs) -> lhs - rhs);

        ArrayList<Integer> result = new ArrayList<>(list.size());
        for (int i = 0; i < list.size(); ++i) {
            result.add(0);
        }

        int carry = 0;
        for (int i = 0; i < ascending.size(); ++i) {
            int diff = ascending.get(i) - ascending.get(ascending.size() - 1 - i) - carry;
            if (diff < 0) {
                result.set(ascending.size() - 1 - i, base + diff);
                carry = 1;
            } else {
                result.set(ascending.size() - 1 - i, diff);
                carry = 0;
            }
            
        }

        return result;
    }

    public static int solution(String str, int b) {

        HashMap<List<Integer>, Integer> visited = new HashMap<>(); 

        List<Integer> current = str.chars().map((ch) -> ch - '0').boxed().collect(Collectors.toList());
        int counters = 0;

        while (!visited.containsKey(current)) {
            visited.put(current, counters);

            current = reverseSub(current, b);
            counters += 1;
            assert(counters < Integer.MAX_VALUE);
        }

        return counters - visited.get(current);
    }
}
