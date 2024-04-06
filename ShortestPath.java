import java.util.*;

class ShortestPath {
    private static final int V = 7; // Number of nodes in the graph
    // A utility method to print the constructed distance array
    void printSolution(int dist[]) {
        System.out.println("Node \tDistance from Source");
        for (int i = 0; i < V; i++)
            System.out.println(i + " \t" + dist[i]);
    }
    // Function that implements Dijkstra's shortest path algorithm for a graph represented using adjacency matrix
    void dijkstra(int graph[][], int src) {
        int dist[] = new int[V]; // The output array dist[i] holds the shortest distance from src to i
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(v -> dist[v]));
        pq.add(src);
        while (!pq.isEmpty()) {
            int u = pq.poll();
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    pq.add(v);
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
