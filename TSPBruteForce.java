import java.util.ArrayList;
import java.util.Arrays;

public class TSPBruteForce {

    private static int[][] distances = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
    };
    private static int numberOfCities = distances.length;

    private static int[] visited;
    private static int minCost = Integer.MAX_VALUE;

    public static void main(String[] args) {
        visited = new int[numberOfCities];
        Arrays.fill(visited, 0);

        ArrayList<Integer> path = new ArrayList<>();
        path.add(0); // Starting from city 0

        tsp(0, path, 0);

        System.out.println("Minimum cost: " + minCost);
    }

    private static void tsp(int currentCity, ArrayList<Integer> path, int cost) {
        if (path.size() == numberOfCities) {
            cost += distances[currentCity][0]; // Add the cost to return to the starting city
            if (cost < minCost) {
                minCost = cost;
                System.out.println("Path: " + path + ", Cost: " + cost);
            }
            return;
        }

        for (int nextCity = 0; nextCity < numberOfCities; nextCity++) {
            if (visited[nextCity] == 0) {
                visited[nextCity] = 1;
                path.add(nextCity);
                tsp(nextCity, path, cost + distances[currentCity][nextCity]);
                visited[nextCity] = 0;
                path.remove(path.size() - 1);
            }
        }
    }
}

