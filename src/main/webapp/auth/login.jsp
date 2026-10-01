<%--
  Created by IntelliJ IDEA.
  User: Youcode
  Date: 29/09/2026
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="min-h-screen bg-gray-100 flex items-center justify-center">

<div class="w-full max-w-md bg-white rounded-xl shadow-md p-8">
    <h1 class="text-2xl font-bold text-gray-800 text-center mb-6">Connexion</h1>

    <% if (request.getAttribute("error") != null) { %>
    <p class="mb-4 rounded-lg bg-red-100 px-3 py-2 text-sm text-red-700">${error}</p>
    <% }%>

    <form action="${pageContext.request.contextPath}/login" method="post" class="space-y-4">

        <div>
            <label for="email" class="block text-sm font-medium text-gray-700 mb-1">Email</label>
            <input type="email" id="email" name="email" required
                   class="w-full rounded-lg border border-gray-300 px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>

        <div>
            <label for="password" class="block text-sm font-medium text-gray-700 mb-1">Mot de passe</label>
            <input type="password" id="password" name="password" required
                   class="w-full rounded-lg border border-gray-300 px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>

        <button type="submit"
                class="w-full bg-blue-600 hover:bg-blue-700 text-white font-semibold py-2 rounded-lg transition">
            Se connecter
        </button>
    </form>

    <!-- <p class="text-sm text-gray-600 text-center mt-4">Pas de compte ?<a href="register.jsp" class="text-blue-600 hover:underline">S'inscrire</a></p>-->
</div>

</body>
</html>