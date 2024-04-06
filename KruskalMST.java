import java.util.Arrays;

class ShortestPath {
    private static final int V = 7; // Number of nodes in the graph

    // A utility method to find the vertex with the minimum distance value,
    // which is not yet included in the shortest path tree
    int minDistance(int dist[], boolean sptSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++) {
            if (!sptSet[v] && dist[v] < min) {
                min = dist[v];
                min_index = v;
            }
        }

        return min_index;
    }

    // A utility method to print the constructed distance array
    void printSolution(int dist[]) {
        System.out.println("Node \tDistance from Source");
        for (int i = 0; i < V; i++)
            System.out.println(i + " \t" + dist[i]);
    }

    // Function that implements Dijkstra's shortest path algorithm for a graph represented using adjacency matrix
    void dijkstra(int graph[][], int src) {
        int dist[] = new int[V]; // The output array dist[i] holds the shortest distance from src to i
        boolean sptSet[] = new boolean[V]; // sptSet[i] will be true if vertex i is included in the shortest path tree

        // Initialize all distances as INFINITE and sptSet[] as false
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(sptSet, false);

        // Distance from source vertex to itself is always 0
        dist[src] = 0;

        // Find the shortest path for all vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices not yet processed.
            int u = minDistance(dist, sptSet);

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update the distance value of the adjacent vertices of the picked vertex
            for (int v = 0; v < V; v++) {
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        // Print the constructed distance array
        printSolution(dist);
    }

    public static void main(String[] args) {
        ShortestPath sp = new ShortestPath();

        int graph[][] = new int[][]{
                {0, 28, 0, 0, 0, 10, 0},
                {28, 0, 16, 0, 0, 0, 14},
                {0, 16, 0, 12, 0, 0, 0},
                {0, 0, 12, 0, 22, 0, 18},
                {0, 0, 0, 22, 0, 25, 24},
                {10, 0, 0, 0, 25, 0, 0},
                {0, 14, 0, 18, 24, 0, 0}
        };

        int source = 0; // Source node

        sp.dijkstra(graph, source);
    }
}
