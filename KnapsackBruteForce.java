public class KnapsackBruteForce {

    static class Item {
        int weight;
        int value;

        Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    public static int knapsack(Item[] items, int capacity) {
        int n = items.length;
        int maxProfit = 0;

        // Generate all possible combinations of items
        for (int i = 0; i < (1 << n); i++) {
            int totalWeight = 0;
            int totalValue = 0;

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    totalWeight += items[j].weight;
                    totalValue += items[j].value;
                }
            }

            // Check if the combination is feasible and maximizes the profit
            if (totalWeight <= capacity && totalValue > maxProfit) {
                maxProfit = totalValue;
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        Item[] items = {
                new Item(2, 12),
                new Item(1, 10),
                new Item(3, 20),
                new Item(2, 15)
        };
        int capacity = 5;

        int maxProfit = knapsack(items, capacity);
        System.out.println("Maximum profit: " + maxProfit);
    }
}

