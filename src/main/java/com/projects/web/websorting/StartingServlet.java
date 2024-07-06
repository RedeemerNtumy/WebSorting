package com.projects.web.websorting;

import com.google.gson.Gson;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "StartingServlet", urlPatterns = {"/api/home"})
@RequestScoped
public class StartingServlet extends HttpServlet {
    @Inject
    private LoggingService loggingService;

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("StartingServlet: Initializing");
        ConfigService configService = new ConfigService();
        getServletContext().setAttribute("configService", configService);
        System.out.println("StartingServlet: ConfigService has been set in ServletContext");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        String baseUrl = req.getRequestURL().toString().replace(req.getRequestURI(), req.getContextPath());

        Map<String, Map<String, Object>> links = new HashMap<>();
        links.put("self", createLink(baseUrl + "/api/home", "GET"));
        links.put("sort", createLink(baseUrl + "/api/sort", "GET", "POST"));

        ConfigService configService = (ConfigService) getServletContext().getAttribute("configService");
        String jsonResponse = loggingService.generateJsonResponse(links, configService);

        resp.getWriter().write(jsonResponse);
    }

    private Map<String, Object> createLink(String url, String... methods) {
        Map<String, Object> linkDetails = new HashMap<>();
        linkDetails.put("href", url);
        linkDetails.put("method", methods);
        return linkDetails;
    }

    @RequestScoped
    public static class LoggingService {
        private Gson gson = new Gson();

        public String generateJsonResponse(Map<String, Map<String, Object>> links, ConfigService configService) {
            Map<String, Object> root = new HashMap<>();
            root.put("_links", links);
            root.put("configInfo", configService.getConfigInfo());  // Example usage of ConfigService
            return gson.toJson(root);
        }
    }

    // Example service class for configuration management
    public static class ConfigService {
        public String getConfigInfo() {
            return "Configuration details";
        }
    }
}
