package com.projects.web.websorting;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class SortingService {

    public int[] sortNumbers(int[] numbers, String sortType) {
        switch (sortType) {
            case "quick":
                SortingAlgorithms.QuickSort.sort(numbers);
                break;
            case "merge":
                SortingAlgorithms.MergeSort.sort(numbers);
                break;
            case "heap":
                SortingAlgorithms.HeapSort.sort(numbers);
                break;
            case "radix":
                SortingAlgorithms.RadixSort.sort(numbers);
                break;
            case "bucket":
                SortingAlgorithms.BucketSort.sort(numbers);
                break;
            default:
                throw new IllegalArgumentException("Invalid sort type: " + sortType);
        }
        return numbers;
    }
}
