package com.projects.web.websorting;

public class SortingAlgorithms {

    // Quick Sort implementation
    public static class QuickSort {
        public static void sort(int[] array) {
            quickSort(array, 0, array.length - 1);
        }

        private static void quickSort(int[] array, int low, int high) {
            if (low < high) {
                int pi = partition(array, low, high);
                quickSort(array, low, pi - 1);
                quickSort(array, pi + 1, high);
            }
        }

        private static int partition(int[] array, int low, int high) {
            int pivot = array[high];
            int i = (low - 1); // Index of smaller element
            for (int j = low; j < high; j++) {
                if (array[j] < pivot) {
                    i++;
                    // Swap arr[i] and arr[j]
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
            // Swap arr[i+1] and arr[high] (or pivot)
            int temp = array[i + 1];
            array[i + 1] = array[high];
            array[high] = temp;
            return i + 1;
        }
    }

    // Merge Sort implementation
    public static class MergeSort {
        public static void sort(int[] array) {
            if (array.length < 2) {
                return; // Base case for recursion
            }
            int mid = array.length / 2;
            int[] left = new int[mid];
            int[] right = new int[array.length - mid];

            for (int i = 0; i < mid; i++) {
                left[i] = array[i];
            }
            for (int i = mid; i < array.length; i++) {
                right[i - mid] = array[i];
            }

            sort(left);
            sort(right);

            merge(array, left, right);
        }

        private static void merge(int[] array, int[] left, int[] right) {
            int i = 0, j = 0, k = 0;
            while (i < left.length && j < right.length) {
                if (left[i] <= right[j]) {
                    array[k++] = left[i++];
                } else {
                    array[k++] = right[j++];
                }
            }
            while (i < left.length) {
                array[k++] = left[i++];
            }
            while (j < right.length) {
                array[k++] = right[j++];
            }
        }
    }

    // Heap Sort implementation
    public static class HeapSort {
        public static void sort(int[] array) {
            int n = array.length;

            // Build heap (rearrange array)
            for (int i = n / 2 - 1; i >= 0; i--) {
                heapify(array, n, i);
            }

            // One by one extract an element from heap
            for (int i = n - 1; i > 0; i--) {
                // Move current root to end
                int temp = array[0];
                array[0] = array[i];
                array[i] = temp;

                // Call max heapify on the reduced heap
                heapify(array, i, 0);
            }
        }

        private static void heapify(int[] array, int n, int i) {
            int largest = i; // Initialize largest as root
            int l = 2 * i + 1; // left = 2*i + 1
            int r = 2 * i + 2; // right = 2*i + 2

            // If left child is larger than root
            if (l < n && array[l] > array[largest]) {
                largest = l;
            }

            // If right child is larger than largest so far
            if (r < n && array[r] > array[largest]) {
                largest = r;
            }

            // If largest is not root
            if (largest != i) {
                int swap = array[i];
                array[i] = array[largest];
                array[largest] = swap;

                // Recursively heapify the affected sub-tree
                heapify(array, n, largest);
            }
        }
    }

    // Radix Sort implementation
    public static class RadixSort {
        public static void sort(int[] array) {
            // Find the maximum number to know the number of digits
            int m = getMax(array);

            // Do counting sort for every digit. Note that instead
            // of passing digit number, exp is passed. exp is 10^i
            // where i is current digit number
            for (int exp = 1; m / exp > 0; exp *= 10) {
                countSort(array, exp);
            }
        }

        private static int getMax(int[] array) {
            int mx = array[0];
            for (int i = 1; i < array.length; i++)
                if (array[i] > mx)
                    mx = array[i];
            return mx;
        }

        private static void countSort(int[] array, int exp) {
            int[] output = new int[array.length]; // Output array
            int i;
            int[] count = new int[10];

            // Store count of occurrences in count[]
            for (i = 0; i < array.length; i++) {
                count[(array[i] / exp) % 10]++;
            }

            // Change count[i] so that count[i] now contains
            // actual position of this digit in output[]
            for (i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }

            // Build the output array
            for (i = array.length - 1; i >= 0; i--) {
                output[count[(array[i] / exp) % 10] - 1] = array[i];
                count[(array[i] / exp) % 10]--;
            }

            // Copy the output array to array[], so that array[] now
            // contains sorted numbers according to current digit
            for (i = 0; i < array.length; i++)
                array[i] = output[i];
        }
    }

    // Bucket Sort implementation
    public static class BucketSort {
        public static void sort(int[] array) {
            int maxVal = getMax(array);
            int[] bucket = new int[maxVal + 1];

            for (int i = 0; i < array.length; i++) {
                bucket[array[i]]++;
            }

            int outPos = 0;
            for (int i = 0; i < bucket.length; i++) {
                for (int j = 0; j < bucket[i]; j++) {
                    array[outPos++] = i;
                }
            }
        }

        private static int getMax(int[] array) {
            int max = array[0];
            for (int i : array) {
                if (i > max) {
                    max = i;
                }
            }
            return max;
        }
    }
}
