package com.projects.web.websorting;

import com.google.gson.Gson;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(name = "SortingServlet", urlPatterns = {"/api/sort"})
public class SortingServlet extends HttpServlet {

    @Inject
    private SortingService sortingService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String numbersStr = req.getParameter("numbers");
        String sortType = req.getParameter("type");
        int[] numbers = Arrays.stream(numbersStr.split(","))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] sortedNumbers = sortingService.sortNumbers(numbers, sortType);

        resp.setContentType("application/json");
        String jsonResponse = generateJsonResponse(sortedNumbers, sortType, createLinks(req));
        resp.getWriter().write(jsonResponse);
    }

    private Map<String, Map<String, Object>> createLinks(HttpServletRequest req) {
        String baseUrl = req.getRequestURL().toString().replace(req.getRequestURI(), req.getContextPath());
        Map<String, Map<String, Object>> links = new HashMap<>();
        links.put("self", createLink(baseUrl + "/api/sort", "POST"));
        links.put("home", createLink(baseUrl + "/api/home", "GET"));
        return links;
    }

    private Map<String, Object> createLink(String url, String... methods) {
        Map<String, Object> linkDetails = new HashMap<>();
        linkDetails.put("href", url);
        linkDetails.put("method", methods);
        return linkDetails;
    }

    private String generateJsonResponse(int[] sortedNumbers, String sortType, Map<String, Map<String, Object>> links) {
        StartingServlet.ConfigService configService = (StartingServlet.ConfigService) getServletContext().getAttribute("configService");
        if (configService == null) {
            System.out.println("ConfigService is null");
            throw new RuntimeException("ConfigService is not available in ServletContext.");
        }
        Map<String, Object> root = new LinkedHashMap<>();
        root.put("sortedNumbers", Arrays.toString(sortedNumbers));
        root.put("sortType", sortType);
        root.put("_links", links);
        root.put("configInfo", configService.getConfigInfo());
        return new Gson().toJson(root);
    }
}
