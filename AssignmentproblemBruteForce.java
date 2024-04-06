import java.util.Arrays;

public class AssignmentproblemBruteForce {

    private static int[][] costs = {
            {3, 2, 7, 6},
            {5, 3, 2, 1},
            {8, 7, 4, 2},
            {4, 6, 5, 2}
    };
    private static int n = costs.length;
    private static int[] assignment = new int[n];
    private static boolean[] assigned = new boolean[n];
    private static int minCost = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Arrays.fill(assignment, -1);
        assignTask(0, 0);
        System.out.println("Minimum cost: " + minCost);
        System.out.println("Assignment: " + Arrays.toString(assignment));
    }

    private static void assignTask(int currentWorker, int currentCost) {
        if (currentWorker == n) {
            if (currentCost < minCost) {
                minCost = currentCost;
                System.arraycopy(assignment, 0, assignment, 0, n);
            }
            return;
        }

        for (int task = 0; task < n; task++) {
            if (!assigned[task]) {
                assigned[task] = true;
                assignment[currentWorker] = task;
                assignTask(currentWorker + 1, currentCost + costs[currentWorker][task]);
                assigned[task] = false;
                assignment[currentWorker] = -1;
            }
        }
    }
}

