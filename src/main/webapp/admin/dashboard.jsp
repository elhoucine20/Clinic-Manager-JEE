<%--
  Created by IntelliJ IDEA.
  User: Youcode
  Date: 29/09/2026
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard Admin</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="min-h-screen bg-slate-100 text-slate-800">

<div class="flex min-h-screen">

    <!-- Barre latérale -->
    <aside class="hidden md:flex w-64 flex-col bg-slate-900 text-slate-300">
        <div class="px-6 py-5 text-xl font-bold text-white border-b border-slate-800">ClinicManager</div>
        <nav class="flex-1 px-3 py-4 space-y-1 text-sm">
            <a href="${pageContext.request.contextPath}/WEB-INF/views/admin/dashboard"
               class="flex items-center rounded-lg bg-slate-800 px-3 py-2 font-medium text-white">Dashboard</a>
            <span class="flex items-center justify-between rounded-lg px-3 py-2 text-slate-500">Médecins <em class="text-xs not-italic">Bientôt</em></span>
            <span class="flex items-center justify-between rounded-lg px-3 py-2 text-slate-500">Staff <em class="text-xs not-italic">Bientôt</em></span>
           <span class="flex items-center justify-between rounded-lg px-3 py-2 text-slate-500">Patients <em class="text-xs not-italic">Bientôt</em></span>
            <!-- <span class="flex items-center justify-between rounded-lg px-3 py-2 text-slate-500">Spécialités <em class="text-xs not-italic">Bientôt</em></span>
                        <span class="flex items-center justify-between rounded-lg px-3 py-2 text-slate-500">Départements <em class="text-xs not-italic">Bientôt</em></span>
            -->
            <a href="${pageContext.request.contextPath}/admin/catalog"
               class="flex items-center rounded-lg px-3 py-2 hover:bg-slate-800 hover:text-white">Départements et spécialités</a>

            <span class="flex items-center justify-between rounded-lg px-3 py-2 text-slate-500">Utilisateurs <em class="text-xs not-italic">Bientôt</em></span>
        </nav>
    </aside>

    <!-- Contenu -->
    <div class="flex flex-1 flex-col">

        <header class="flex items-center justify-between bg-white px-6 py-4 shadow-sm">
            <h1 class="text-lg font-semibold">Dashboard Admin</h1>
            <div class="flex items-center gap-4">
                <div class="text-right leading-tight">
                    <p class="text-sm font-medium">${sessionScope.fullName}</p>
                    <p class="text-xs text-slate-500">${sessionScope.role}</p>
                </div>
                <a href="${pageContext.request.contextPath}/logout"
                   class="rounded-lg bg-red-600 px-3 py-1.5 text-sm font-semibold text-white hover:bg-red-700">Déconnexion</a>
            </div>
        </header>

        <main class="flex flex-1 flex-col items-center justify-center gap-4 p-6">

            <% if (request.getParameter("success") != null) { %>
            <div class="w-full max-w-md rounded-lg bg-green-100 px-4 py-3 text-sm text-green-800">
                Compte créé avec succès.
            </div>
            <% } %>

            <div class="w-full max-w-md rounded-2xl bg-white p-10 text-center shadow-sm">
                <div class="mx-auto mb-4 flex h-14 w-14 items-center justify-center rounded-full bg-blue-100 text-blue-600">
                    <svg xmlns="http://www.w3.org/2000/svg" class="h-7 w-7" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
                        <path stroke-linecap="round" stroke-linejoin="round" d="M12 4v16m8-8H4"/>
                    </svg>
                </div>
                <h2 class="text-xl font-bold">Gestion du personnel</h2>
                <p class="mt-2 text-sm text-slate-500">Créez un compte pour un médecin ou un membre du staff.</p>
                <button id="openModal" type="button"
                        class="mt-6 rounded-lg bg-blue-600 px-5 py-2.5 text-sm font-semibold text-white hover:bg-blue-700">
                    Ajouter un utilisateur
                </button>
            </div>
        </main>
    </div>
</div>

<!-- Modale -->
<dialog id="addUserDialog" class="w-full max-w-lg rounded-xl p-0 shadow-xl backdrop:bg-black/50">
    <div class="p-6">
        <div class="mb-4 flex items-center justify-between">
            <h3 class="text-lg font-bold">Ajouter un utilisateur</h3>
            <button id="closeModal" type="button" class="text-2xl leading-none text-slate-400 hover:text-slate-600">&times;</button>
        </div>

        <% if (request.getAttribute("error") != null) { %>
        <p class="mb-4 rounded-lg bg-red-100 px-3 py-2 text-sm text-red-700">${error}</p>
        <% } %>

        <form action="${pageContext.request.contextPath}/admin/dashboard" method="post" class="space-y-4">

            <div>
                <label for="role" class="mb-1 block text-sm font-medium text-slate-700">Rôle</label>
                <select id="role" name="role"
                        class="w-full rounded-lg border border-slate-300 px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500">
                    <option value="DOCTOR">Médecin</option>
                    <option value="STAFF">Staff</option>
                </select>
            </div>

            <div>
                <label for="fullname" class="mb-1 block text-sm font-medium text-slate-700">Nom complet</label>
                <input type="text" id="fullname" name="fullname" required
                       class="w-full rounded-lg border border-slate-300 px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500">
            </div>

            <div class="grid grid-cols-1 gap-4 sm:grid-cols-2">
                <div>
                    <label for="email" class="mb-1 block text-sm font-medium text-slate-700">Email</label>
                    <input type="email" id="email" name="email" required
                           class="w-full rounded-lg border border-slate-300 px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500">
                </div>
                <div>
                    <label for="telephone" class="mb-1 block text-sm font-medium text-slate-700">Téléphone</label>
                    <input type="tel" id="telephone" name="telephone" required
                           class="w-full rounded-lg border border-slate-300 px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500">
                </div>
            </div>

            <div>
                <label for="password" class="mb-1 block text-sm font-medium text-slate-700">Mot de passe (8 caractères minimum)</label>
                <input type="password" id="password" name="password" required minlength="8"
                       class="w-full rounded-lg border border-slate-300 px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500">
            </div>

            <div id="doctorFields" class="grid grid-cols-1 gap-4 sm:grid-cols-2">
                <div>
                    <label for="matricule" class="mb-1 block text-sm font-medium text-slate-700">Matricule</label>
                    <input type="text" id="matricule" name="matricule" required
                           class="w-full rounded-lg border border-slate-300 px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500">
                </div>
                <div>
                    <label for="titre" class="mb-1 block text-sm font-medium text-slate-700">Titre</label>
                    <input type="text" id="titre" name="titre" placeholder="Dr, Pr..." required
                           class="w-full rounded-lg border border-slate-300 px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500">
                </div>

                <div class="sm:col-span-2">
                    <label for="specialtyId" class="mb-1 block text-sm font-medium text-slate-700">Spécialité</label>
                    <c:choose>
                        <c:when test="${empty specialties}">
                            <p class="rounded-lg bg-amber-50 px-3 py-2 text-sm text-amber-800">
                                Aucune spécialité n'existe.
                                <a href="${pageContext.request.contextPath}/admin/catalog" class="font-semibold underline">Créez-en une d'abord</a>.
                            </p>
                        </c:when>
                        <c:otherwise>
                            <select id="specialtyId" name="specialtyId" required
                                    class="w-full rounded-lg border border-slate-300 px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500">
                                <option value="">Choisir une spécialité</option>
                                <c:forEach items="${specialties}" var="s">
                                    <option value="${s.id}"><c:out value="${s.name}"/> (<c:out value="${s.departmentName}"/>)</option>
                                </c:forEach>
                            </select>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <div class="flex justify-end gap-3 pt-2">
                <button id="cancelModal" type="button"
                        class="rounded-lg border border-slate-300 px-4 py-2 text-sm font-medium text-slate-700 hover:bg-slate-50">Annuler</button>
                <button type="submit"
                        class="rounded-lg bg-blue-600 px-4 py-2 text-sm font-semibold text-white hover:bg-blue-700">Créer le compte</button>
            </div>
        </form>
    </div>
</dialog>

<script>
    const dialog = document.getElementById('addUserDialog');
    const roleSelect = document.getElementById('role');
    const doctorFields = document.getElementById('doctorFields');
    const doctorInputs = doctorFields.querySelectorAll('input, select');

    function toggleDoctorFields() {
        const isDoctor = roleSelect.value === 'DOCTOR';
        doctorFields.classList.toggle('hidden', !isDoctor);
        doctorInputs.forEach(function (input) { input.required = isDoctor; });
    }

    document.getElementById('openModal').addEventListener('click', function () { dialog.showModal(); });
    document.getElementById('closeModal').addEventListener('click', function () { dialog.close(); });
    document.getElementById('cancelModal').addEventListener('click', function () { dialog.close(); });
    dialog.addEventListener('click', function (e) { if (e.target === dialog) dialog.close(); });
    roleSelect.addEventListener('change', toggleDoctorFields);

    toggleDoctorFields();
    <% if (request.getAttribute("error") != null) { %>
    dialog.showModal();
    <% } %>
</script>

</body>
</html>