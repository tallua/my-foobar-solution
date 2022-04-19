import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution5_1 {

    static void calcGroups(int l, int m, Stack<Integer> list, List<List<Integer>> result) {
        if (l == 0) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = 1; i <= Math.min(l, m); ++i) {
            list.push(i);
            calcGroups(l - i, i, list, result);
            list.pop();
        }
    }

    static List<List<Integer>> calcGroups(int len) {
        List<List<Integer>> groups = new ArrayList<>();

        calcGroups(len, len, new Stack<>(), groups);

        return groups;
    }


    static List<BigInteger> calcCoefficents(int N) {
        var coef = new ArrayList<BigInteger>(N + 1);
        for (int i = 0; i <= N; ++i) {
            coef.add(BigInteger.ONE);
        }

        return coef;
    }

    static int gcd(int lhs, int rhs) {
        while (lhs != 0) {
            int tmp = lhs;
            lhs = rhs % lhs;
            rhs = tmp;
        }

        return rhs;
    }

    static BigInteger calcOccurance(int sum, List<Integer> group) {
        BigInteger result = BigInteger.ONE;

        for (int i = 1; i <= sum; ++i) {
            result = result.multiply(BigInteger.valueOf(i));
        }

        for (int i = 0; i < group.size(); ++i) {
            result = result.divide(BigInteger.valueOf(group.get(i)));
        }

        int prev = 0;
        int prevCount = 1;
        for (int i = 0; i < group.size(); ++i) {
            if (group.get(i) != prev) {
                for (int j = 1; j <= prevCount; ++j) {
                    result = result.divide(BigInteger.valueOf(j));
                }

                prevCount = 0;
            }

            prev = group.get(i);
            prevCount++;
        }

        for (int j = 1; j <= prevCount; ++j) {
            result = result.divide(BigInteger.valueOf(j));
        }

        return result;
    }

    public static String solution(final int w, final int h, int s) {
        var wGroups = calcGroups(w);
        var hGroups = calcGroups(h);

        BigInteger totalCount = BigInteger.ZERO;
        BigInteger totalGroup = BigInteger.ZERO;
        for (List<Integer> wGroup : wGroups) {
            for (List<Integer> hGroup : hGroups) {
                BigInteger occurance = calcOccurance(w, wGroup).multiply(calcOccurance(h, hGroup));
                int nonFixedPoint = 0;

                for (int wRot : wGroup) {
                    for (int hRot : hGroup) {
                        nonFixedPoint += gcd(wRot, hRot);
                    }
                }

                totalCount = totalCount.add(occurance.multiply(BigInteger.valueOf(s).pow(nonFixedPoint)));
                totalGroup = totalGroup.add(occurance);
            }
        }

        return totalCount.divide(totalGroup).toString();
    }

    public static void main(String[] args) {
        assert(solution(2, 2, 2).equals("7"));
        assert(solution(2, 3, 4).equals("430"));
        System.out.println(solution(12, 12, 20));
    }
}
