import java.util.Arrays;

public class SimplexMethod {

    public static void main(String[] args) {
        double[][] A = {
                {2, 1, 1, 0, 0, 0, 0},
                {1, 3, 0, 1, 0, 0, 0},
                {1, 1, 0, 0, 1, 0, 0},
                {-4, -5, 0, 0, 0, 1, 0},
                {-1, 0, 0, 0, 0, 0, 1}
        };
        double[] c = {3, 4, 0, 0, 0, 0, 0};
        double[] b = {4, 5, 3, 0, 0};

        int[] basis = {2, 3, 4, 5, 6};

        double[] x = simplex(A, b, c, basis);

        System.out.println("Optimal solution:");
        for (int i = 0; i < x.length; i++) {
            System.out.printf("x%d = %.2f\n", i + 1, x[i]);
        }
        System.out.printf("Optimal value: %.2f\n", calculateValue(c, x));
    }

    public static double[] simplex(double[][] A, double[] b, double[] c, int[] basis) {
        int m = A.length;
        int n = A[0].length;
        double[][] tableau = new double[m + 1][n + m + 1];

        for (int i = 0; i < m; i++) {
            System.arraycopy(A[i], 0, tableau[i], 0, n);
            tableau[i][n + i] = 1;
            tableau[i][n + m] = b[i];
        }

        System.arraycopy(c, 0, tableau[m], 0, n);

        while (true) {
            int q = 0;
            for (int j = 1; j < n + m; j++) {
                if (tableau[m][j] < tableau[m][q]) {
                    q = j;
                }
            }

            if (tableau[m][q] >= 0) {
                break;
            }

            int p = -1;
            for (int i = 0; i < m; i++) {
                if (tableau[i][q] > 0) {
                    if (p == -1) {
                        p = i;
                    } else {
                        double ratio1 = tableau[p][n + m] / tableau[p][q];
                        double ratio2 = tableau[i][n + m] / tableau[i][q];
                        if (ratio2 < ratio1) {
                            p = i;
                        }
                    }
                }
            }

            if (p == -1) {
                throw new ArithmeticException("Linear program is unbounded");
            }

            for (int i = 0; i < m + 1; i++) {
                if (i != p) {
                    double alpha = tableau[i][q] / tableau[p][q];
                    for (int j = 0; j < n + m + 1; j++) {
                        tableau[i][j] -= alpha * tableau[p][j];
                    }
                }
            }

            for (int j = 0; j < n + m + 1; j++) {
                tableau[p][j] /= tableau[p][q];
            }
        }

        double[] x = new double[n];
        Arrays.fill(x, 0);
        for (int i = 0; i < m; i++) {
            if (basis[i] < n) {
                x[basis[i]] = tableau[i][n + m];
            }
        }

        return x;
    }

    public static double calculateValue(double[] c, double[] x) {
        double value = 0;
        for (int i = 0; i < c.length; i++) {
            value += c[i] * x[i];
        }
        return value;
    }
}

