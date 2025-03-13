package Kahn;

import java.util.Arrays;
import java.util.List;

public class KahnTest {

    public static void main(String[] args) {
        // Exemplo de uso: Grafo com 6 nós (0 a 5)
        int vertices = 6;
        List<int[]> edges = Arrays.asList(
                new int[]{5, 2},
                new int[]{5, 0},
                new int[]{4, 0},
                new int[]{4, 1},
                new int[]{2, 3},
                new int[]{3, 1},
                new int[]{1,1}
        );

        List<Integer> result = Kahn.topologicalSort(vertices, edges);
        System.out.println("Topological Order: " + result);
    }
}
