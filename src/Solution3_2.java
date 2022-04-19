

public class Solution3_2 {
    public static void main(String[] args) {
        System.out.println("1 0 3 0");
        System.out.println(Solution3_2.solution(new int[] { 1, 1, 1 }));
        System.out.println(Solution3_2.solution(new int[] { 2, 3, 5 }));
        System.out.println(Solution3_2.solution(new int[] { 1, 2, 3, 4, 5, 6 }));
        System.out.println(Solution3_2.solution(new int[] { 1, 2 }));
    }

    public static int solution(int[] numbers) {
        int[][] counts = new int[numbers.length][2];
        for (int i = 0; i < counts.length; ++i) {
            for (int j = 0; j < counts[i].length; ++j) {
                counts[i][j] = 0;
            }
        }

        //Arrays.sort(numbers);

        for (int i = 0; i < numbers.length; ++i) {
            for (int j = i + 1; j < numbers.length; ++j) {
                if (numbers[j] % numbers[i] != 0) {
                    continue;
                }

                counts[j][0] += 1;
                counts[j][1] += counts[i][0];
            }
        }

        int sum = 0;
        for (int i = 0; i < counts.length; ++i) {
            sum += counts[i][1];
        }

        return sum;
    }
}