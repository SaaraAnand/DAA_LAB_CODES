public class Mergesortcode {

    private static int functionCalls = 0;

    public static void main(String[] args) {
        int[] myArray = {3, 6, 8, 10, 1, 2, 1, 15, 4, 7};

        System.out.println("Original Array:");
        printArray(myArray);

        mergeSort(myArray, 0, myArray.length - 1);

        System.out.println("\nSorted Array:");
        printArray(myArray);

        System.out.println("\nNumber of function calls in Merge Sort: " + functionCalls);
        System.out.println("Depth of recursion tree: " + (int) (Math.log(functionCalls) / Math.log(2)));
    }

    static void mergeSort(int[] arr, int low, int high) {
        functionCalls++;
        if (low < high) {
            int mid = (low + high) / 2;

            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);

            merge(arr, low, mid, high);
        }
    }

    static void merge(int[] arr, int low, int mid, int high) {
        int n1 = mid - low + 1;
        int n2 = high - mid;

        int[] left = new int[n1];
        int[] right = new int[n2];

        for (int i = 0; i < n1; i++) {
            left[i] = arr[low + i];
        }
        for (int j = 0; j < n2; j++) {
            right[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0, k = low;
        while (i < n1 && j < n2) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = left[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }

    static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}

