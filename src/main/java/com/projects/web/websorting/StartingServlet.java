package com.projects.web.websorting;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        resp.setContentType("text/html");
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // In a RESTful design, POST might not be necessary here unless it's handling initial data creation,
        // which is not needed for simply serving a page.
    }
}
