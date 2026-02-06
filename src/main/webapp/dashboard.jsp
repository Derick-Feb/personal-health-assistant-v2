<%@ page import="java.util.List, org.app.model.Activity" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Dashboard - Health Assistant</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50 min-h-screen">
    <nav class="bg-white shadow-sm px-8 py-4 flex justify-between items-center">
        <h1 class="text-xl font-bold text-green-600">Health Assistant</h1>
        <div class="flex items-center gap-4">
            <span class="text-gray-600">Welcome, <strong><%= session.getAttribute("username") %></strong></span>
            <form action="auth" method="post">
                <input type="hidden" name="action" value="logout">
                <button type="submit" class="bg-red-50 text-red-600 px-4 py-1 rounded-md text-sm border border-red-200 hover:bg-red-100">Logout</button>
            </form>
        </div>
    </nav>

    <main class="max-w-4xl mx-auto py-10 px-4">
        <div class="grid grid-cols-1 md:grid-cols-3 gap-8">

            <div class="bg-white p-6 rounded-xl shadow-sm border border-gray-100 h-fit">
                <h3 class="text-lg font-bold text-gray-800 mb-4">Add Record</h3>
                <form action="activities" method="post" class="space-y-4">
                    <select name="type" class="w-full p-2 bg-gray-50 border border-gray-200 rounded-md">
                        <option value="MEAL">Meal (Gain)</option>
                        <option value="EXERCISES">Exercise (Loss)</option>
                    </select>
                    <input type="text" name="name" placeholder="Item Name" class="w-full p-2 border border-gray-200 rounded-md" required>
                    <input type="number" step="0.1" name="calories" placeholder="Calories" class="w-full p-2 border border-gray-200 rounded-md" required>
                    <button type="submit" class="w-full bg-green-500 text-white py-2 rounded-md font-bold hover:bg-green-600">Add Entry</button>
                </form>
            </div>

            <div class="md:col-span-2 bg-white p-6 rounded-xl shadow-sm border border-gray-100">
                <div class="flex justify-between items-center mb-6">
                    <h3 class="text-lg font-bold text-gray-800">History</h3>
                    <form action="activities" method="get" class="flex gap-2">
                        <select name="typeFilter" class="p-1 text-sm border border-gray-200 rounded-md">
                            <option value="">All</option>
                            <option value="MEAL">Meals</option>
                            <option value="EXERCISES">Exercises</option>
                        </select>
                        <button type="submit" class="bg-gray-100 px-3 py-1 rounded-md text-xs font-bold hover:bg-gray-200 transition">Filter</button>
                    </form>
                </div>

                <div class="overflow-x-auto">
                    <table class="w-full text-left">
                        <thead>
                            <tr class="text-gray-400 text-xs uppercase border-b border-gray-100">
                                <th class="pb-3">Type</th>
                                <th class="pb-3">Name</th>
                                <th class="pb-3 text-right">Calories</th>
                            </tr>
                        </thead>
                        <tbody class="divide-y divide-gray-50">
                            <%
                                List<Activity> list = (List<Activity>) request.getAttribute("activities");
                                double total = 0;
                                if(list != null) {
                                    for(Activity a : list) {
                                        boolean isMeal = a.getType().equals("MEAL");
                                        total += (isMeal ? a.getCalories() : -a.getCalories());
                            %>
                            <tr class="text-sm text-gray-700">
                                <td class="py-4">
                                    <span class="<%= isMeal ? "text-blue-600 bg-blue-50" : "text-orange-600 bg-orange-50" %> px-2 py-1 rounded-full text-xs font-bold">
                                        <%= a.getType() %>
                                    </span>
                                </td>
                                <td class="py-4 font-medium"><%= a.getName() %></td>
                                <td class="py-4 text-right <%= isMeal ? "text-green-600" : "text-red-600" %>">
                                    <%= isMeal ? "+" : "-" %><%= a.getCalories() %>
                                </td>
                            </tr>
                            <% }} %>
                        </tbody>
                    </table>
                </div>

                <div class="mt-8 pt-6 border-t border-gray-100 flex justify-between items-center">
                    <span class="text-gray-500 font-medium">Net Caloric Balance:</span>
                    <span class="text-2xl font-black <%= total >= 0 ? "text-green-600" : "text-red-600" %>">
                        <%= String.format("%.1f", total) %>
                    </span>
                </div>
            </div>
        </div>
    </main>
</body>
</html>