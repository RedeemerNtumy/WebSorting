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

@WebServlet(name = "StartingServlet", urlPatterns = {"/api/home"})
public class StartingServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialization logic here
        getServletContext().log("StartingServlet initialized successfully");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        String baseUrl = req.getRequestURL().toString().replace(req.getRequestURI(), req.getContextPath());

        Map<String, Map<String, Object>> links = new HashMap<>();
        links.put("self", createLink(baseUrl + "/api/home", "GET"));
        links.put("sort", createLink(baseUrl + "/api/sort", "GET", "POST"));  // Assuming POST is allowed here

        String jsonResponse = generateJsonResponse(links);

        resp.getWriter().write(jsonResponse);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    private Map<String, Object> createLink(String url, String... methods) {
        Map<String, Object> linkDetails = new HashMap<>();
        linkDetails.put("href", url);
        linkDetails.put("method", Arrays.asList(methods));
        return linkDetails;
    }

    private String generateJsonResponse(Map<String, Map<String, Object>> links) {
        Map<String, Object> root = new HashMap<>();
        root.put("_links", links);
        return new Gson().toJson(root);
    }
}
