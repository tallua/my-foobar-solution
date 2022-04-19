

import java.util.LinkedList;
import java.util.Queue;

public class Solution3_1 {

    public static void main(String[] args) {
        System.out.println(
                Solution3_1.solution(new int[][] { { 0, 1, 1, 0 }, { 0, 0, 0, 1 }, { 1, 1, 0, 0 }, { 1, 1, 1, 0 } })
                        + " = 7");
        System.out.println(Solution3_1.solution(new int[][] { { 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1, 0 },
                { 0, 0, 0, 0, 0, 0 }, { 0, 1, 1, 1, 1, 1 }, { 0, 1, 1, 1, 1, 1 }, { 0, 0, 0, 0, 0, 0 } }) + " = 11");
        System.out.println(
                Solution3_1.solution(new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } })
                        + " = 7");
        System.out.println(
                Solution3_1.solution(new int[][] { { 0, 1, 0, 0 }, { 1, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } })
                        + " = 7");
        System.out.println(Solution3_1.solution(new int[][] { { 0, 1, 0, 0, 0 }, { 0, 1, 0, 1, 0 }, { 0, 1, 0, 1, 0 },
                { 0, 1, 0, 1, 0 }, { 0, 0, 0, 1, 0 } }) + " = 9");
        System.out.println(
                Solution3_1.solution(new int[][] { { 0, 1, 1, 0 }, { 0, 1, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } })
                        + " = 7");
        System.out.println(Solution3_1.solution(new int[][] { { 0, 0 }, { 0, 0 } }) + " = 3");
        System.out.println(Solution3_1.solution(new int[][] { { 0, 1 }, { 1, 0 } }) + " = 3");
        System.out
                .println(Solution3_1
                        .solution(new int[][] { { 0, 1, 1, 0, 0, 0, 0 }, { 0, 1, 1, 0, 1, 1, 0 },
                                { 0, 1, 1, 0, 1, 1, 0 }, { 0, 1, 1, 0, 1, 1, 0 }, { 0, 1, 0, 0, 1, 1, 0 }, })
                        + " = 19");
        System.out
                .println(Solution3_1
                        .solution(new int[][] { { 0, 1, 1, 0, 0, 0, 0 }, { 0, 1, 1, 0, 1, 1, 0 },
                                { 0, 1, 1, 0, 1, 1, 0 }, { 0, 1, 1, 0, 1, 1, 0 }, { 0, 0, 0, 0, 1, 1, 0 }, })
                        + " = 19");
        System.out.println(Solution3_1.solution(new int[][] { { 0, 0, 0 }, { 0, 0, 0 } }) + " = 4");
        System.out.println(Solution3_1.solution(new int[][] { { 0, 0, 0, 1 }, { 0, 1, 0, 1 }, { 0, 1, 1, 0 } }) + " = 6");
        System.out.println(Solution3_1.solution(new int[][] { { 0 }, { 1 }, { 0 } }) + " = 3");
        System.out.println(Solution3_1.solution(new int[][] { { 0, 0 }, { 1, 1 }, { 0, 0 } }) + " = 4");
    }

    static class Task {
        Task(int w, int h, int dist, int remain) {
            this.w = w;
            this.h = h;
            this.dist = dist;
            this.remain = remain;
        }

        int w, h, dist, remain;
    }

    final static int DIST_NONE = 1000;
    final static int REMOVE_COUNT = 1;

    public static int solution(int[][] map) {
        final int width = map.length;
        final int height = map[0].length;

        int[][][] distance = new int[width][height][REMOVE_COUNT + 1];
        for (int w = 0; w < width; ++w) {
            for (int h = 0; h < height; ++h) {
                for (int i = 0; i <= REMOVE_COUNT; ++i) {
                    distance[w][h][i] = DIST_NONE;
                }
            }
        }

        Queue<Task> queue = new LinkedList<>();
        distance[width - 1][height - 1][REMOVE_COUNT] = 1;
        queue.add(new Task(width - 1, height - 1, 1, REMOVE_COUNT));

        while (!queue.isEmpty()) {
            Task current = queue.remove();

            //System.out.println(" - " + current.w + ", " + current.h);

            final int remain = current.remain;
            final int dist = distance[current.w][current.h][remain];

            if (0 < current.w) {
                if (map[current.w - 1][current.h] == 0 && dist + 1 < distance[current.w - 1][current.h][remain]) {
                    distance[current.w - 1][current.h][remain] = Math.min(distance[current.w - 1][current.h][remain], dist + 1);
                    queue.add(new Task(current.w - 1, current.h, dist + 1, remain));
                }
                else if (0 < remain && dist + 1 < distance[current.w - 1][current.h][remain - 1]) {
                    distance[current.w - 1][current.h][remain - 1] = Math.min(distance[current.w - 1][current.h][remain - 1], dist + 1);
                    queue.add(new Task(current.w - 1, current.h, dist + 1, remain - 1));
                }
            }
            if (0 < current.h) {
                if (map[current.w][current.h - 1] == 0 && dist + 1 < distance[current.w][current.h - 1][remain]) {
                    distance[current.w][current.h - 1][remain] = Math.min(distance[current.w][current.h - 1][remain], dist + 1);
                    queue.add(new Task(current.w, current.h - 1, dist + 1, remain));
                }
                else if (0 < remain && dist + 1 < distance[current.w][current.h - 1][remain - 1]) {
                    distance[current.w][current.h - 1][remain - 1] = Math.min(distance[current.w][current.h - 1][remain - 1], dist + 1);
                    queue.add(new Task(current.w, current.h - 1, dist + 1, remain - 1));
                }
            }
            if (current.w < width - 1) {
                if (map[current.w + 1][current.h] == 0 && dist + 1 < distance[current.w + 1][current.h][remain]) {
                    distance[current.w + 1][current.h][remain] = Math.min(distance[current.w + 1][current.h][remain], dist + 1);
                    queue.add(new Task(current.w + 1, current.h, dist + 1, remain));
                }
                else if (0 < remain && dist + 1 < distance[current.w + 1][current.h][remain - 1]) {
                    distance[current.w + 1][current.h][remain - 1] = Math.min(distance[current.w + 1][current.h][remain - 1], dist + 1);
                    queue.add(new Task(current.w + 1, current.h, dist + 1, remain - 1));
                }
            }
            if (current.h < height - 1) {
                if (map[current.w][current.h + 1] == 0 && dist + 1 < distance[current.w][current.h + 1][remain]) {
                    distance[current.w][current.h + 1][remain] = Math.min(distance[current.w][current.h + 1][remain], dist + 1);
                    queue.add(new Task(current.w, current.h + 1, dist + 1, remain));
                }
                else if (0 < remain && dist + 1 < distance[current.w][current.h + 1][remain - 1]) {
                    distance[current.w][current.h + 1][remain - 1] = Math.min(distance[current.w][current.h + 1][remain - 1], dist + 1);
                    queue.add(new Task(current.w, current.h + 1, dist + 1, remain - 1));
                }
            }
        }

        int answer = DIST_NONE;
        for (int i = 0; i <= REMOVE_COUNT; ++i) {
            answer = Math.min(distance[0][0][i], answer);
        }
        return answer;
    }
}