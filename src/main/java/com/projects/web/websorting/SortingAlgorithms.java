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
            int i = (low - 1);
            for (int j = low; j < high; j++) {
                if (array[j] < pivot) {
                    i++;
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
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
                return;
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

            for (int i = n / 2 - 1; i >= 0; i--) {
                heapify(array, n, i);
            }

            for (int i = n - 1; i > 0; i--) {
                int temp = array[0];
                array[0] = array[i];
                array[i] = temp;

                heapify(array, i, 0);
            }
        }

        private static void heapify(int[] array, int n, int i) {
            int largest = i;
            int l = 2 * i + 1;
            int r = 2 * i + 2;

            if (l < n && array[l] > array[largest]) {
                largest = l;
            }

            if (r < n && array[r] > array[largest]) {
                largest = r;
            }

            if (largest != i) {
                int swap = array[i];
                array[i] = array[largest];
                array[largest] = swap;

                heapify(array, n, largest);
            }
        }
    }

    // Radix Sort implementation
    public static class RadixSort {
        public static void sort(int[] array) {
            // Implementation needed
        }
    }

    // Bucket Sort implementation
    public static class BucketSort {
        public static void sort(int[] array) {
            // Implementation needed
        }
    }
}
