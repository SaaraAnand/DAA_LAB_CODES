import java.util.Arrays;

public class AssignmentProblem {

    static int[][] costMatrix = {
            {9, 2, 7, 8},
            {6, 4, 3, 7},
            {5, 8, 1, 8},
            {7, 6, 9, 4}
    };

    public static void main(String[] args) {
        int n = costMatrix.length;

        int[] assignment = new int[n];
        for (int i = 0; i < n; i++) {
            assignment[i] = i + 1;
        }

        int[] bestAssignment = null;
        int minTotalCost = Integer.MAX_VALUE;

        // Generate all permutations
        do {
            int totalCost = calculateTotalCost(assignment);
            if (totalCost < minTotalCost) {
                minTotalCost = totalCost;
                bestAssignment = Arrays.copyOf(assignment, assignment.length);
            }
        } while (nextPermutation(assignment));

        // Output the result
        System.out.println("Best Assignment: " + Arrays.toString(bestAssignment));
        System.out.println("Minimum Total Cost: " + minTotalCost);
    }

    // Helper method to calculate the total cost for a given assignment
    private static int calculateTotalCost(int[] assignment) {
        int totalCost = 0;
        for (int i = 0; i < assignment.length; i++) {
            totalCost += costMatrix[i][assignment[i] - 1];
        }
        return totalCost;
    }

    // Helper method to generate the next permutation
    private static boolean nextPermutation(int[] array) {
        int i = array.length - 1;
        while (i > 0 && array[i - 1] >= array[i]) {
            i--;
        }

        if (i <= 0) {
            return false; // Last permutation reached
        }

        int j = array.length - 1;
        while (array[j] <= array[i - 1]) {
            j--;
        }

        // Swap i-1 and j
        int temp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = temp;

        // Reverse the suffix
        j = array.length - 1;
        while (i < j) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }

        return true;
    }
}
