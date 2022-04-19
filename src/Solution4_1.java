

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Solution4_1 {

    public static void main(String[] args) {
        System.out.println(isAvailablePair(1, 1) + ": false");
        System.out.println(isAvailablePair(1, 3) + ": false");
        System.out.println(isAvailablePair(1, 7) + ": false");
        System.out.println(isAvailablePair(1, 21) + ": true");
        System.out.println(isAvailablePair(3, 7) + ": true");
        System.out.println(isAvailablePair(1, Integer.MAX_VALUE / 2 - 1) + ": true");
        System.out.println(isAvailablePair((1 << 30) - 1, (1 << 30) - 2) + ": true");

        Match matching;
        matching = maximumCandidateMatching(new ArrayList<>() {
            {
                add(new HashSet<>(Arrays.asList(1, 4)));
                   add(new HashSet<>(Arrays.asList(0, 3)));
                add(new HashSet<>(Arrays.asList(3, 4, 5)));
                add(new HashSet<>(Arrays.asList(2)));
                add(new HashSet<>(Arrays.asList(0, 2)));
                add(new HashSet<>(Arrays.asList(2)));
            }
        });
        System.out.println(matching.nodeCounts() + " : 6");
        matching = maximumCandidateMatching(new ArrayList<>() {
            {
                add(new HashSet<>(Arrays.asList(1, 6)));
                add(new HashSet<>(Arrays.asList(0, 3)));
                add(new HashSet<>(Arrays.asList(3, 6)));
                add(new HashSet<>(Arrays.asList(2, 1, 5)));
                add(new HashSet<>(Arrays.asList(5, 6, 7)));
                add(new HashSet<>(Arrays.asList(4, 3)));
                add(new HashSet<>(Arrays.asList(0, 2, 4)));
                add(new HashSet<>(Arrays.asList(4)));
            }
        });
        System.out.println(matching.nodeCounts() + " : 8");
        matching = maximumCandidateMatching(new ArrayList<>() {
            {
                add(new HashSet<>(Arrays.asList(1, 4)));
                add(new HashSet<>(Arrays.asList(0, 3)));
                add(new HashSet<>(Arrays.asList(3, 4)));
                add(new HashSet<>(Arrays.asList(2, 5)));
                add(new HashSet<>(Arrays.asList(0, 2)));
                add(new HashSet<>(Arrays.asList(3)));
            }
        });
        System.out.println(matching.nodeCounts() + " : 6");
        matching = maximumCandidateMatching(new ArrayList<>() {
            {
                add(new HashSet<>(Arrays.asList(1, 4)));
                add(new HashSet<>(Arrays.asList(0, 2, 3)));
                add(new HashSet<>(Arrays.asList(1, 3)));
                add(new HashSet<>(Arrays.asList(1, 2)));
                add(new HashSet<>(Arrays.asList(0)));
            }
        });
        System.out.println(matching.nodeCounts() + " : 4");
        matching = maximumCandidateMatching(new ArrayList<>() {
            {
                add(new HashSet<>(Arrays.asList(3)));
                add(new HashSet<>(Arrays.asList(2)));
                add(new HashSet<>(Arrays.asList(1, 5, 9)));
                add(new HashSet<>(Arrays.asList(0, 4, 6)));
                add(new HashSet<>(Arrays.asList(3, 5, 8)));
                add(new HashSet<>(Arrays.asList(2, 4, 9)));
                add(new HashSet<>(Arrays.asList(3, 7)));
                add(new HashSet<>(Arrays.asList(6, 8)));
                add(new HashSet<>(Arrays.asList(4, 7, 9)));
                add(new HashSet<>(Arrays.asList(2, 5, 8)));
            }
        });
        System.out.println(matching.nodeCounts() + " : 10");
        matching = maximumCandidateMatching(new ArrayList<>() {
            {
                add(new HashSet<>(Arrays.asList(3)));
                add(new HashSet<>(Arrays.asList(2)));
                add(new HashSet<>(Arrays.asList(1, 5, 9)));
                add(new HashSet<>(Arrays.asList(0, 4, 6)));
                add(new HashSet<>(Arrays.asList(3, 5)));
                add(new HashSet<>(Arrays.asList(2, 4, 9)));
                add(new HashSet<>(Arrays.asList(3, 7)));
                add(new HashSet<>(Arrays.asList(6, 8)));
                add(new HashSet<>(Arrays.asList(7, 9)));
                add(new HashSet<>(Arrays.asList(2, 5, 8)));
            }
        });
        System.out.println(matching.nodeCounts() + " : 10");
        matching = maximumCandidateMatching(new ArrayList<>() {
            {
                for (int i = 0; i < 98; i += 2) {
                    add(new HashSet<>(Arrays.asList(i + 1)));
                    add(new HashSet<>(Arrays.asList(i)));
                }
            }
        });
        System.out.println(matching.nodeCounts() + " : 98");

        System.out.println(Solution4_1.solution(new int[] { 1, 1 }));
        System.out.println(Solution4_1.solution(new int[] { 1, 7, 3, 21, 13, 19 }));
        System.out.println(Solution4_1.solution(new int[] { 21, 1, 1, 21 }));
        System.out.println(Solution4_1.solution(new int[] { 1, 3, 3}));
    }

    static boolean isAvailablePair(int lhs, int rhs) {
        int small = Math.min(lhs, rhs);
        int big = Math.max(lhs, rhs);

        while ((small + big) % 2 == 0) {
            if (small == big) {
                return false;
            }

            if (small % 2 == 0) {
                small /= 2;
                big /= 2;
            }
            else {
                int newSmall = Math.min(small + small, big - small);
                int newBig = Math.max(small + small, big - small);
    
                small = newSmall;
                big = newBig;
            }
        }

        return true;
    }

    static class Match {
        HashMap<Integer, Integer> map = new HashMap<>();

        boolean containsNode(int index)
        {
            return map.containsKey(index);
        }

        int matchOf(int index)
        {
            return map.get(index);
        }

        int nodeCounts()
        {
            return map.size();
        }

        void augmentBy(int[] path)
        {
            for (int i = 0; i < path.length; i += 2) {
                map.put(path[i], path[i + 1]);
                map.put(path[i + 1], path[i]);
            }
        }
    }

    static class PathNode {
        PathNode(int index, PathNode parent) {
            this.index = index;
            this.parent = parent;
        }
        int index;
        PathNode parent;
    }

    static PathNode lca(int root, PathNode l, PathNode r, Match match, int length) {
        boolean[] visited = new boolean[length];
        Arrays.fill(visited, false);

        while (l.index != root) {
            visited[l.index] = true;
            l = l.parent.parent;
        }
        while (r.index != root) {
            if (visited[r.index]) {
                return r;
            }
            visited[r.index] = true;
            r = r.parent.parent;
        }

        return l;
    }

    static int[] findAugmentedPath(ArrayList<HashSet<Integer>> graph, Match match, final int exposed) {
        boolean[] visited = new boolean[graph.size()];
        boolean[] inTree = new boolean[graph.size()];
        boolean[] isEvenDepth = new boolean[graph.size()];
        PathNode[] outNode = new PathNode[graph.size()];
        Arrays.fill(visited, false);
        Arrays.fill(inTree, false);
        Arrays.fill(isEvenDepth, false);
        Arrays.fill(outNode, null);
        
        LinkedList<PathNode> nodeToCheck = new LinkedList<>();
        outNode[exposed] = new PathNode(exposed, null);
        inTree[exposed] = true;
        isEvenDepth[exposed] = true;
        nodeToCheck.add(outNode[exposed]);

        while (!nodeToCheck.isEmpty()) {
            final PathNode v = nodeToCheck.remove();
            if (visited[v.index]) {
                continue;
            }
            
            
            for (final int wIndex : graph.get(v.index)) {
                if (visited[wIndex]) {
                    continue;
                }
                if (!inTree[wIndex]) {
                    if (match.containsNode(wIndex)) {
                        // add (v, w)
                        PathNode w = new PathNode(wIndex, v);
                        inTree[wIndex] = true;
                        isEvenDepth[wIndex] = false;

                        // add (w, x)
                        final int xIndex = match.matchOf(wIndex);
                        PathNode x = new PathNode(xIndex, w);
                        inTree[xIndex] = true;
                        isEvenDepth[xIndex] = true;
                        
                        // add x
                        outNode[xIndex] = x;
                        nodeToCheck.add(x);
                    }
                    else {
                        ArrayList<Integer> backtrace = new ArrayList<>();
                        PathNode n = new PathNode(wIndex, v);
                        inTree[wIndex] = true;
                        isEvenDepth[wIndex] = false;
                        do {
                            backtrace.add(n.index);
                            n = n.parent;
                        } while (n.parent != null);
                        backtrace.add(n.index);
                        
                        return backtrace.stream().mapToInt(i -> i).toArray();
                    }
                }
                else if (isEvenDepth[wIndex]) {
                    final PathNode w = outNode[wIndex];
                    PathNode root = lca(exposed, v, w, match, graph.size());

                    PathNode tmp, tmp2;

                    tmp = v;
                    tmp2 = w;
                    while (tmp2 != root) {
                        PathNode n1 = new PathNode(tmp2.index, tmp);
                        PathNode n2 = new PathNode(tmp2.parent.index, n1);
                        outNode[n2.index] = n2;
                        nodeToCheck.add(n2);

                        tmp  = n2;
                        tmp2 = tmp2.parent.parent;
                    }

                    tmp = w;
                    tmp2 = v;
                    while (tmp2 != root) {
                        PathNode n1 = new PathNode(tmp2.index, tmp);
                        PathNode n2 = new PathNode(tmp2.parent.index, n1);
                        outNode[n2.index] = n2;
                        nodeToCheck.add(n2);

                        tmp  = n2;
                        tmp2 = tmp2.parent.parent;
                    }
                }
            }

            visited[v.index] = true;
        }

        return new int[0];
    }



    static Match maximumCandidateMatching(ArrayList<HashSet<Integer>> graph) {
        Match match = new Match();

        // blossom algorithm
        int prev = -1;
        while (prev != match.nodeCounts()) {
            prev = match.nodeCounts();
            for (int node = 0; node < graph.size(); ++node) {
                if (match.containsNode(node)) {
                    continue;
                }
    
                int[] augmentedPath = findAugmentedPath(graph, match, node);
                if (augmentedPath == null || augmentedPath.length == 0) {
                    continue;
                }

                match.augmentBy(augmentedPath);
            }
        }

        return match;
    }

    public static int solution(int[] bananas) {
        ArrayList<HashSet<Integer>> availables = new ArrayList<>(bananas.length);
        for (int i = 0; i < bananas.length; ++i) {
            availables.add(new HashSet<>());
        }

        for (int i = 0; i < bananas.length; ++i) {
            for (int j = i + 1; j < bananas.length; ++j) {
                if (isAvailablePair(bananas[i], bananas[j])) {
                    availables.get(i).add(j);
                    availables.get(j).add(i);
                }
            }
        }

        Match match = maximumCandidateMatching(availables);

        return bananas.length - match.nodeCounts();
    }
}
