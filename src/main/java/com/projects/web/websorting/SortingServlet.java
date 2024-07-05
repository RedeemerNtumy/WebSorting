package com.projects.web.websorting;

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

            // Perform sorting based on the selected type
            int[] sortedNumbers = sortNumbers(numbers, sortType);

            // Set sorted results and sort type as request attributes
            req.setAttribute("sortType", sortType);
            req.setAttribute("sortedNumbers", Arrays.toString(sortedNumbers));

            // Generate HATEOAS links
            String baseUrl = req.getRequestURL().toString().replace(req.getRequestURI(), req.getContextPath());
            Map<String, String> links = new HashMap<>();
            links.put("self", baseUrl + "/api/sort");
            links.put("home", baseUrl + "/api/home");

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

    private String generateJsonResponse(int[] sortedNumbers, String sortType, Map<String, String> links) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        jsonBuilder.append("\"sortedNumbers\": \"").append(Arrays.toString(sortedNumbers)).append("\",");
        jsonBuilder.append("\"sortType\": \"").append(sortType).append("\",");
        jsonBuilder.append("\"_links\": {");
        for (Map.Entry<String, String> entry : links.entrySet()) {
            jsonBuilder.append("\"").append(entry.getKey()).append("\": { \"href\": \"").append(entry.getValue()).append("\" },");
        }
        jsonBuilder.deleteCharAt(jsonBuilder.length() - 1); // Remove the trailing comma
        jsonBuilder.append("}");
        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }
}
