import java.util.*;

public class StableMarriage {

    public static void main(String[] args) {
        int[][] menPrefs = {
                {1, 2, 3, 4},
                {2, 3, 1, 4},
                {3, 1, 2, 4},
                {1, 2, 3, 4}
        };

        int[][] womenPrefs = {
                {2, 3, 1, 4},
                {3, 1, 2, 4},
                {1, 2, 3, 4},
                {2, 1, 3, 4}
        };

        int n = menPrefs.length;
        int[] matches = stableMarriage(menPrefs, womenPrefs, n);

        for (int i = 0; i < n; i++) {
            System.out.println("Man " + i + " is matched with Woman " + matches[i]);
        }
    }

    public static int[] stableMarriage(int[][] menPrefs, int[][] womenPrefs, int n) {
        int[] matches = new int[n];
        Arrays.fill(matches, -1);

        Queue<Integer> freeMen = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            freeMen.add(i);
        }

        while (!freeMen.isEmpty()) {
            int man = freeMen.poll();
            for (int i = 0; i < n && matches[man] == -1; i++) {
                int woman = menPrefs[man][i] - 1;
                if (matches[woman] == -1) {
                    matches[woman] = man;
                } else {
                    int currentMan = matches[woman];
                    if (isWomanBetter(womenPrefs[woman], currentMan, man)) {
                        matches[woman] = man;
                        freeMen.add(currentMan);
                    } else {
                        freeMen.add(man);
                    }
                }
            }
        }

        return matches;
    }

    private static boolean isWomanBetter(int[] womenPrefs, int currentMan, int newMan) {
        for (int i = 0; i < womenPrefs.length; i++) {
            int pref = womenPrefs[i];
            if (pref == newMan + 1) {
                return true;
            }
            if (pref == currentMan + 1) {
                return false;
            }
        }
        return false;
    }
}
