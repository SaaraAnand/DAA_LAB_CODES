import java.util.LinkedList;
import java.util.Queue;

public class MaxFlow {

    private static final int V = 6; // Number of vertices in graph

    // Returns tne maximum flow from s to t in the given graph
    private static int fordFulkerson(int[][] graph, int s, int t) {
        int u, v;
        int[][] rGraph = new int[V][V]; // Residual graph where rGraph[i][j] indicates residual capacity of edge from i to j (if there is an edge).
        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];

        int[] parent = new int[V]; // This array is filled by BFS and to store path

        int maxFlow = 0; // There is no flow initially

        // Augment the flow while tere is path from source to sink
        while (bfs(rGraph, s, t, parent)) {
            // Find minimum residual capacity of the edhes along the path filled by BFS. Or we can say find the maximum flow through the path found.
            int pathFlow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                pathFlow = Math.min(pathFlow, rGraph[u][v]);
            }

            // update residual capacities of the edges and reverse edges along the path
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= pathFlow;
                rGraph[v][u] += pathFlow;
            }

            // Add path flow to overall flow
            maxFlow += pathFlow;
        }

        // Return the overall flow
        return maxFlow;
    }

    private static boolean bfs(int[][] rGraph, int s, int t, int[] parent) {
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; ++i)
            visited[i] = false;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v = 0; v < V; v++) {
                if (!visited[v] && rGraph[u][v] > 0) {
                    if (v == t) {
                        parent[v] = u;
                        return true;
                    }
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws java.lang.Exception {
        // Let us create a graph shown in the above example
        int[][] graph = new int[][]{
                {0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 4, 0, 0, 14, 0},
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };
        System.out.println("The maximum possible flow is " + fordFulkerson(graph, 0, 5));
    }
}

