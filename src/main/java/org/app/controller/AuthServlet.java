package org.app.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.app.dao.UserDAO;
import org.app.model.User;

import java.io.IOException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {

        String action = request.getParameter("action");
        String userParam = request.getParameter("username");
        String passParam = request.getParameter("password");

        switch (action) {
            case "register" -> {
                User newUser = new User();
                newUser.setUsername(userParam);
                newUser.setPassword(passParam);

                boolean success = false;
                try {
                    success = userDAO.register(newUser);
                } catch (Exception e) {
                    throw new ServletException(e);
                }

                if (success)
                    response.sendRedirect("login.jsp?msg=signup_success");
                else
                    response.sendRedirect("signup.jsp?error=signup_failed");
            }

            case "login" -> {
                User authenticatedUser = null;
                try {
                    authenticatedUser = userDAO.validateUser(userParam, passParam);
                } catch (Exception e) {
                    throw new ServletException(e);
                }

                if (authenticatedUser != null) {
                    // SETTING THE SESSION HERE
                    HttpSession session = request.getSession();
                    session.setAttribute("user", authenticatedUser);
                    session.setAttribute("userId", authenticatedUser.getId());
                    session.setAttribute("username", authenticatedUser.getUsername());

                    response.sendRedirect("activities");
                } else {
                    response.sendRedirect("login.jsp?error=invalid_credentials");
                }
            }

            case "logout" -> {
                HttpSession session = request.getSession(false);
                if (session != null) session.invalidate();
                response.sendRedirect("index.jsp");
            }
        }
    }
}
