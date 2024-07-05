package com.projects.web.websorting;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "SortingServlet", urlPatterns = {"/api/sort"})
public class SortingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String numbersStr = req.getParameter("numbers");
        String sortType = req.getParameter("type");
        int[] numbers = Arrays.stream(numbersStr.split(","))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] sortedNumbers = sortNumbers(numbers, sortType);

        String baseUrl = req.getRequestURL().toString().replace(req.getRequestURI(), req.getContextPath());
        Map<String, Map<String, Object>> links = new HashMap<>();
        links.put("self", createLink(baseUrl + "/api/sort", "POST"));
        links.put("home", createLink(baseUrl + "/api/home", "GET"));

        resp.setContentType("application/json");

        String jsonResponse = generateJsonResponse(sortedNumbers, sortType, links);

        resp.getWriter().write(jsonResponse);
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

    private Map<String, Object> createLink(String url, String... methods) {
        Map<String, Object> linkDetails = new HashMap<>();
        linkDetails.put("href", url);
        linkDetails.put("method", methods);
        return linkDetails;
    }

    private String generateJsonResponse(int[] sortedNumbers, String sortType, Map<String, Map<String, Object>> links) {
        Map<String, Object> root = new HashMap<>();
        root.put("sortType", sortType);
        root.put("_links", links);
        root.put("sortedNumbers", Arrays.toString(sortedNumbers));
        return new Gson().toJson(root);
    }
}
