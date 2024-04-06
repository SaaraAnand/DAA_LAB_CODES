public class AllPairsShortestPath {
    
    // Function to implement Floyd's algorithm for all pairs shortest path
    public static void floyd(int[][] graph) {
        int V = graph.length;
        int[][] dist = new int[V][V];
        
        // Initialize dist[][] with the given graph
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
            }
        }
        
        // Updating dist[][] with intermediate vertex k
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE &&
                        dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        
        // Print the shortest distances
        printSolution(dist);
    }
    
    // Function to implement Warshall's algorithm for all pairs shortest path
    public static void warshall(int[][] graph) {
        int V = graph.length;
        int[][] dist = new int[V][V];
        
        // Initialize dist[][] with the given graph
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
            }
        }
        
        // Updating dist[][] with intermediate vertex k
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE &&
                        dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        
        // Print the shortest distances
        printSolution(dist);
    }
    
    // Function to print the solution
    public static void printSolution(int dist[][]) {
        int V = dist.length;
        System.out.println("Shortest distances between every pair of vertices:");
        for (int i = 0; i < V; ++i) {
            for (int j = 0; j < V; ++j) {
                if (dist[i][j] == Integer.MAX_VALUE)
                    System.out.print("INF\t");
                else
                    System.out.print(dist[i][j] + "\t");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        int graph[][] = { {0, 4, 11},
                          {6, 0, 2},
                          {3, Integer.MAX_VALUE, 0}};
        
        // Using Floyd's algorithm
        System.out.println("Using Floyd's algorithm:");
        floyd(graph);
        
        // Using Warshall's algorithm
        System.out.println("\nUsing Warshall's algorithm:");
        warshall(graph);
    }
}
