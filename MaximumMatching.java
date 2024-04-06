import java.util.*;

public class MaximumMatching {

    static class Edge {
        int u, v, residualCapacity;

        Edge(int u, int v, int residualCapacity) {
            this.u = u;
            this.v = v;
            this.residualCapacity = residualCapacity;
        }
    }

    static class BipartiteGraph {
        int[][] graph;
        int[] matchU, matchV;
        int[] visited;

        BipartiteGraph(int u, int v) {
            graph = new int[u][v];
            matchU = new int[u];
            matchV = new int[v];
            visited = new int[u];
        }

        void addEdge(int u, int v) {
            graph[u][v] = 1;
        }

        boolean dfs(int u) {
            visited[u] = 1;
            for (int v = 0; v < graph[u].length; v++) {
                if (graph[u][v] == 1) {
                    if (matchV[v] == -1 || (visited[matchV[v]] == 0 && dfs(matchV[v]))) {
                        matchU[u] = v;
                        matchV[v] = u;
                        return true;
                    }
                }
            }
            return false;
        }

        int maximumMatching() {
            Arrays.fill(matchU, -1);
            Arrays.fill(matchV, -1);
            int matching = 0;
            for (int u = 0; u < graph.length; u++) {
                Arrays.fill(visited, 0);
                if (dfs(u)) {
                    matching++;
                }
            }
            return matching;
        }
    }

    public static void main(String[] args) {
        BipartiteGraph graph = new BipartiteGraph(4, 4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        int maxMatching = graph.maximumMatching();
        System.out.println("Maximum Matching: " + maxMatching);
    }
}
