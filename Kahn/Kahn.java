package Kahn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Kahn {

    public static List<Integer> topologicalSort(int vertices, List<int[]> edges) {
        List<List<Integer>> adjacencyList = new ArrayList<>();

        int[] inDegree = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int from = edge[0], to = edge[1];

            adjacencyList.get(from).add(to);

            inDegree[to]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < vertices; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> topologicalOrder = new ArrayList<>();

        while (!queue.isEmpty()) {
            int node = queue.poll();

            topologicalOrder.add(node);

            for (int neighbor : adjacencyList.get(node)) {
                inDegree[neighbor]--;

                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        if (topologicalOrder.size() == vertices) {
            return topologicalOrder;
        } else {
            throw new RuntimeException("Cyclical Graph - Cannot be ordered topologically");
        }
    }
}
