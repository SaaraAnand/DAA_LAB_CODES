import java.util.Arrays;
import java.util.Comparator;

class Item {
    int value, weight;

    public Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}

public class FractionalKnapsack {
    public static double getMaxValue(int[] wt, int[] val, int capacity) {
        Item[] items = new Item[wt.length];
        for (int i = 0; i < wt.length; i++) {
            items[i] = new Item(val[i], wt[i]);
        }

        // Sort items by value per unit weight in descending order
        Arrays.sort(items, Comparator.comparingDouble((Item item) -> (double) item.value / item.weight).reversed());

        double totalValue = 0d;

        for (Item item : items) {
            int curWeight = item.weight;
            int curValue = item.value;

            if (capacity - curWeight >= 0) {
                capacity -= curWeight;
                totalValue += curValue;
            } else {
                double fraction = (double) capacity / curWeight;
                totalValue += curValue * fraction;
                break;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        int[] wt = {10, 40, 20, 30};
        int[] val = {60, 40, 100, 120};
        int capacity = 50;

        double maxValue = getMaxValue(wt, val, capacity);
        System.out.println("Maximum value we can obtain = " + maxValue);
    }
}

