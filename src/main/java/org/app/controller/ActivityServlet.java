package org.app.controller;

import org.app.dao.ActivityDAO;
import org.app.model.Activity;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/activities")
public class ActivityServlet extends HttpServlet {
    private final ActivityDAO activityDAO = new ActivityDAO();

    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Activity activity = new Activity();
        activity.setUserId((int) session.getAttribute("userId"));
        activity.setType(request.getParameter("type"));
        activity.setName(request.getParameter("name"));
        activity.setCalories(Double.parseDouble(request.getParameter("calories")));

        try {
            activityDAO.addActivity(activity);

            // This sends to the servlet itself but on the GET method
            response.sendRedirect("activities");
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");
        String typeFilter = request.getParameter("typeFilter");

        if (typeFilter == null || typeFilter.trim().isEmpty()) {
            typeFilter = null;
        }

        try {
            var list = activityDAO.getFilteredActivities(userId, typeFilter);
            request.setAttribute("activities", list);

            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
