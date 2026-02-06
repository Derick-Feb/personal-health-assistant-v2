<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Health Assistant</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50 h-screen flex flex-col items-center justify-center">
    <div class="text-center p-8 bg-white shadow-xl rounded-2xl max-w-md">
        <h1 class="text-4xl font-bold text-green-600 mb-4">Health Assistant</h1>
        <p class="text-gray-600 mb-8">Track your meals and exercises with ease. Stay healthy, stay fit.</p>

        <%
            HttpSession currentSession = request.getSession(false);
            if (currentSession != null && currentSession.getAttribute("userId") != null) {
        %>
            <div class="space-y-4">
                <p class="text-lg">Welcome back, <span class="font-semibold text-gray-800"><%= currentSession.getAttribute("username") %></span>!</p>
                <div class="flex flex-col gap-3">
                    <a href="activities" class="bg-green-500 hover:bg-green-600 text-white font-bold py-2 px-6 rounded-lg transition">Go to My Dashboard</a>
                    <form action="auth" method="post">
                        <input type="hidden" name="action" value="logout">
                        <button type="submit" class="text-gray-500 hover:text-red-500 underline text-sm">Logout</button>
                    </form>
                </div>
            </div>
        <% } else { %>
            <div class="flex gap-4 justify-center">
                <a href="login.jsp" class="bg-green-500 hover:bg-green-600 text-white font-bold py-2 px-8 rounded-lg transition">Login</a>
                <a href="signup.jsp" class="bg-white border-2 border-green-500 text-green-500 hover:bg-green-50 font-bold py-2 px-8 rounded-lg transition">Sign Up</a>
            </div>
        <% } %>
    </div>
</body>
</html>