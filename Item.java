import java.util.Arrays;
import java.util.Comparator;

class Item {
    int weight, value;

    Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}

class KnapsackGreedy {
    static double fractionalKnapsack(int W, Item[] items) {
        Arrays.sort(items, Comparator.comparingDouble(o -> (double) o.value / o.weight));

        double totalValue = 0d;
        int currentWeight = 0;

        for (int i = 0; i < items.length; i++) {
            if (currentWeight + items[i].weight <= W) {
                currentWeight += items[i].weight;
                totalValue += items[i].value;
            } else {
                int remainingWeight = W - currentWeight;
                totalValue += items[i].value * ((double) remainingWeight / items[i].weight);
                break;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        int W = 50; // Knapsack capacity
        Item[] items = {new Item(10, 60), new Item(20, 100), new Item(30, 120)};

        System.out.println("Maximum value we can obtain = " + fractionalKnapsack(W, items));
    }
}

