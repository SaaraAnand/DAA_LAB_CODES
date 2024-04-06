import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    // Comparator function used for sorting edges based on their weight
    public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
    }
}

class Graph {
    private final int V, E; // V-> no. of vertices & E->no.of edges
    final Edge[] edges; // collection of all edges

    // Creates a graph with V vertices and E edges
    public Graph(int v, int e) {
        V = v;
        E = e;
        edges = new Edge[E];
        for (int i = 0; i < e; ++i)
            edges[i] = new Edge(0, 0, 0);
    }

    // A utility function to find the subset of an element i
    private int find(int[] parent, int i) {
        if (parent[i] == -1)
            return i;
        return find(parent, parent[i]);
    }

    // A utility function to do union of two subsets
    private void Union(int[] parent, int x, int y) {
        int xset = find(parent, x);
        int yset = find(parent, y);
        parent[xset] = yset;
    }

    // The main function to construct MST using Kruskal's algorithm
    public void KruskalMST() {
        Edge[] result = new Edge[V]; // Tnis will store the resultant MST
        int e = 0; // An index variable, used for result[]
        int i = 0; // An index variable, used for sorted edges
        for (i = 0; i < V; ++i)
            result[i] = new Edge(0, 0, 0);

        // Step 1: Sort all the edges in non-decreasing order of their weight. If we are not allowed to change the
        // given graph, we can create a copy of the array of edges
        Arrays.sort(edges);

        // Allocate memory for creating V subsets
        int[] parent = new int[V];

        // Initialize all subsets as single element sets
        Arrays.fill(parent, -1);

        i = 0; // Index used to pick next edge

        // Number of edges to be taken is equal to V-1
        while (e < V - 1 && i < E) {
            // Step 2: Pick the smallest edge. And increment the index for next iteration
            Edge next_edge = edges[i++];

            int x = find(parent, next_edge.src);
            int y = find(parent, next_edge.dest);

            // If including this edge does't cause cycle, include it in result and increment the index of result for next edge
            if (x != y) {
                result[e++] = next_edge;
                Union(parent, x, y);
            }
            // Else discard the next_edge
        }

        // Print the contents of result[] to display the built MST
        System.out.println("Following are the edges in the constructed MST");
        int minimumCost = 0;
        for (i = 0; i < e; ++i) {
            System.out.println(result[i].src + " -- " + result[i].dest + " == " + result[i].weight);
            minimumCost += result[i].weight;
        }
        System.out.println("Minimum Cost Spanning Tree " + minimumCost);
    }
}

public class KruskalMinimumSpanningTree {
    public static void main(String[] args) {
        int V = 4; // Number of vertices in graph
        int E = 5; // Number of edges in graph
        Graph graph = new Graph(V, E);

        // add edge 0-1
        graph.edges[0] = new Edge(0, 1, 10);
        // add edge 0-2
        graph.edges[1] = new Edge(0, 2, 6);
        // add edge 0-3
        graph.edges[2] = new Edge(0, 3, 5);
        // add edge 1-3
        graph.edges[3] = new Edge(1, 3, 15);
        // add edge 2-3
        graph.edges[4] = new Edge(2, 3, 4);

        graph.KruskalMST();
    }
}


