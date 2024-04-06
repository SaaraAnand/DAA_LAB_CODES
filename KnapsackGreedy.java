import java.util.Arrays;
import java.util.Comparator;

class Item {
    int weight, value;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}

public class KnapsackGreedy {
    public static double getMaxValue(int[] wt, int[] val, int capacity) {
        Item[] items = new Item[wt.length];
        for (int i = 0; i < wt.length; i++) {
            items[i] = new Item(wt[i], val[i]);
        }

        // Sort items by value per unit weight in descending order
        Arrays.sort(items, Comparator.comparingDouble(o -> (double) o.value / o.weight).reversed());

        double totalValue = 0d;
        int remainingCapacity = capacity;

        // Pick items greedily
        for (Item item : items) {
            if (remainingCapacity >= item.weight) {
                totalValue += item.value;
                remainingCapacity -= item.weight;
            } else {
                totalValue += (double) item.value / item.weight * remainingCapacity;
                break;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        int[] wt = {10, 20, 30};
        int[] val = {60, 100, 120};
        int capacity = 50;
        double maxValue = getMaxValue(wt, val, capacity);
        System.out.println("Maximum value we can obtain = " + maxValue);
    }
}
