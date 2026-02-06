<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
    HttpSession loginSession = request.getSession(false);
    if (loginSession != null && loginSession.getAttribute("userId") != null) {
        response.sendRedirect("activities");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Health Assistant</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 flex items-center justify-center h-screen">
    <div class="bg-white p-8 rounded-xl shadow-md w-96">
        <h2 class="text-2xl font-bold text-center text-gray-800 mb-6">Login</h2>
        <form action="auth" method="post" class="space-y-4">
            <input type="hidden" name="action" value="login">
            <div>
                <label class="block text-sm font-medium text-gray-700">Username</label>
                <input type="text" name="username" class="w-full mt-1 p-2 border border-gray-300 rounded-md focus:ring-green-500 focus:border-green-500" required>
            </div>
            <div>
                <label class="block text-sm font-medium text-gray-700">Password</label>
                <input type="password" name="password" class="w-full mt-1 p-2 border border-gray-300 rounded-md focus:ring-green-500 focus:border-green-500" required>
            </div>
            <button type="submit" class="w-full bg-green-500 text-white py-2 rounded-md font-bold hover:bg-green-600 transition">Enter</button>
        </form>
        <p class="mt-4 text-center text-sm text-gray-600">New here? <a href="signup.jsp" class="text-green-600 hover:underline">Create account</a></p>
    </div>
</body>
</html>