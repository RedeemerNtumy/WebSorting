package com.projects.web.websorting;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
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

        Map<String, String> links = new HashMap<>();
        links.put("self", baseUrl + "/api/home");
        links.put("sort", baseUrl + "/api/sort");

        String jsonResponse = generateJsonResponse(links);

        resp.getWriter().write(jsonResponse);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // In a RESTful design, POST might not be necessary here unless it's handling initial data creation,
        // which is not needed for simply serving a page.
    }

    private String generateJsonResponse(Map<String, String> links) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{ \"_links\": {");
        for (Map.Entry<String, String> entry : links.entrySet()) {
            jsonBuilder.append("\"").append(entry.getKey()).append("\": { \"href\": \"").append(entry.getValue()).append("\" },");
        }
        jsonBuilder.deleteCharAt(jsonBuilder.length() - 1); // Remove the trailing comma
        jsonBuilder.append("} }");
        return jsonBuilder.toString();
    }
}
