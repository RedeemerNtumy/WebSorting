package com.projects.web.websorting;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "StartingServlet", urlPatterns = {"/api/sort"})
public class StartingServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialization logic here
        getServletContext().log("StartingServlet initialized successfully");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Handle any initial POST requests before the sorting action
        // For instance, you can preprocess data or redirect based on initial parameters
        resp.setContentType("text/html");
        resp.getWriter().write("Processing data before sorting...");
        // Optional: redirect or forward to the sorting servlet or another page
    }
}