

import java.math.BigInteger;
import java.util.HashMap;

public class Solution3_3 {

    public static int solution(String x) {
        return solution(new BigInteger(x));
    }

    static HashMap<BigInteger, Integer> solution_cache = new HashMap<>();

    public static int solution(BigInteger x) {
        if (x.compareTo(BigInteger.TWO) <= 0) {
            return x.intValue() / 2;
        }
        if (solution_cache.containsKey(x)) {
            return solution_cache.get(x);
        }

        BigInteger[] div2 = x.divideAndRemainder(BigInteger.TWO);

        int result = Math.min(
            solution(div2[0].add(BigInteger.ONE)) + 2, 
            solution(div2[0]) + 1 + (div2[1].equals(BigInteger.ZERO) ? 0 : 1));
        
        solution_cache.put(x, result);

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Solution3_3.solution("4"));
        System.out.println(Solution3_3.solution("15"));
        System.out.println(Solution3_3.solution("16"));
        System.out.println(Solution3_3.solution("32"));
        System.out.println(Solution3_3.solution("64"));
        System.out.println(Solution3_3.solution("128"));
    }

    public static int tmp_solution(int val) {
        if (val <= 2) {
            return val / 2;
        }

        int lhs = tmp_solution(val / 2 + 1) + 2;
        int rhs = tmp_solution(val / 2) + 1 + (val % 2 == 0 ? 0 : 1);
        return Math.min(lhs, rhs);
    }
}
