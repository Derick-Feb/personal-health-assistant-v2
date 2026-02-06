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
    <title>Sign Up - Health Assistant</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 flex items-center justify-center h-screen">
    <div class="bg-white p-8 rounded-xl shadow-md w-96 border-t-4 border-green-500">
        <h2 class="text-2xl font-bold text-center text-gray-800 mb-6">Join Us</h2>
        <form action="auth" method="post" class="space-y-4">
            <input type="hidden" name="action" value="register"> <div>
                <label class="block text-sm font-medium text-gray-700">Choose Username</label>
                <input type="text" name="username" class="w-full mt-1 p-2 border border-gray-300 rounded-md shadow-sm" required>
            </div>
            <div>
                <label class="block text-sm font-medium text-gray-700">Password</label>
                <input type="password" name="password" class="w-full mt-1 p-2 border border-gray-300 rounded-md shadow-sm" required>
            </div>
            <button type="submit" class="w-full bg-green-600 text-white py-2 rounded-md font-bold hover:bg-green-700 shadow-lg">Sign Up</button>
        </form>
        <p class="mt-4 text-center text-sm text-gray-600">Got an account? <a href="login.jsp" class="text-green-600 hover:underline font-medium">Login</a></p>
    </div>
</body>
</html>