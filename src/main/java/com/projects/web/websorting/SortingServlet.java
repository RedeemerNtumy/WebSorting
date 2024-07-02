package com.projects.web.websorting;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.Arrays;

public class SortingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String numbersStr = req.getParameter("numbers");
        String sortType = req.getParameter("type");
        int[] numbers = Arrays.stream(numbersStr.split(","))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();

        // Perform sorting based on the selected type
        int[] sortedNumbers = sortNumbers(numbers, sortType);

        // Set sorted results and sort type as request attributes
        req.setAttribute("sortType", sortType);
        req.setAttribute("sortedNumbers", Arrays.toString(sortedNumbers));
        resp.setContentType("text/html");

        // Forward to results page
        req.getRequestDispatcher("/results.jsp").forward(req, resp);
    }

    private int[] sortNumbers(int[] numbers, String sortType) {
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