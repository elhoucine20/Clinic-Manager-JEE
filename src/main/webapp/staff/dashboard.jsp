<%--
  Created by IntelliJ IDEA.
  User: Youcode
  Date: 29/09/2026
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard Staff</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="min-h-screen bg-gray-100">

<header class="bg-white shadow">
    <div class="max-w-5xl mx-auto px-4 py-4 flex items-center justify-between">
        <h1 class="text-xl font-bold text-gray-800">ClinicManager</h1>
        <div class="flex items-center gap-4">
            <span class="text-sm text-gray-600">${sessionScope.fullName} (${sessionScope.role})</span>
            <a href="${pageContext.request.contextPath}/logout"
               class="rounded-lg bg-red-600 px-3 py-1.5 text-sm font-semibold text-white hover:bg-red-700">Déconnexion</a>
        </div>
    </div>
</header>

<main class="max-w-5xl mx-auto px-4 py-8">
    <h2 class="text-2xl font-bold text-gray-800 mb-6">Dashboard Staff</h2>

    <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
        <div class="rounded-xl bg-white p-6 shadow-sm">
            <h3 class="font-semibold text-gray-800">Planning</h3>
            <p class="mt-1 text-sm text-gray-500">Voir le planning de la clinique</p>
            <span class="mt-3 inline-block rounded bg-gray-100 px-2 py-0.5 text-xs text-gray-500">Bientôt</span>
        </div>
        <div class="rounded-xl bg-white p-6 shadow-sm">
            <h3 class="font-semibold text-gray-800">Rendez-vous</h3>
            <p class="mt-1 text-sm text-gray-500">Gérer les rendez-vous</p>
            <span class="mt-3 inline-block rounded bg-gray-100 px-2 py-0.5 text-xs text-gray-500">Bientôt</span>
        </div>
        <div class="rounded-xl bg-white p-6 shadow-sm">
            <h3 class="font-semibold text-gray-800">Replanification</h3>
            <p class="mt-1 text-sm text-gray-500">Déplacer un rendez-vous</p>
            <span class="mt-3 inline-block rounded bg-gray-100 px-2 py-0.5 text-xs text-gray-500">Bientôt</span>
        </div>
        <div class="rounded-xl bg-white p-6 shadow-sm">
            <h3 class="font-semibold text-gray-800">Liste d'attente</h3>
            <p class="mt-1 text-sm text-gray-500">Patients en attente d'un créneau</p>
            <span class="mt-3 inline-block rounded bg-gray-100 px-2 py-0.5 text-xs text-gray-500">Bientôt</span>
        </div>
    </div>
</main>

</body>
</html>