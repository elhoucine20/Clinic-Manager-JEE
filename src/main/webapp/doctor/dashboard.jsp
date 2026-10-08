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
    <title>Dashboard Médecin</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="min-h-screen bg-slate-100 text-slate-800">

<div class="flex min-h-screen">

    <c:set var="activePage" value="dashboard"/>
    <%@ include file="/WEB-INF/fragments/doctor-sidebar.jspf" %>

    <div class="flex flex-1 flex-col">

        <header class="flex items-center justify-between bg-white px-6 py-4 shadow-sm">
            <h1 class="text-lg font-semibold">Dashboard Médecin</h1>
            <div class="flex items-center gap-4">
                <div class="text-right leading-tight">
                    <p class="text-sm font-medium"><c:out value="${sessionScope.fullName}"/></p>
                    <p class="text-xs text-slate-500"><c:out value="${sessionScope.role}"/></p>
                </div>
                <a href="${pageContext.request.contextPath}/logout"
                   class="rounded-lg bg-red-600 px-3 py-1.5 text-sm font-semibold text-white hover:bg-red-700">Déconnexion</a>
            </div>
        </header>

        <main class="flex-1 space-y-6 p-6">

            <!-- Bienvenue -->
            <section class="rounded-2xl bg-gradient-to-r from-blue-600 to-indigo-600 p-8 text-white shadow-sm">
                <p class="text-sm text-blue-100">Bon retour</p>
                <h2 class="mt-1 text-2xl font-bold"><c:out value="${sessionScope.fullName}"/></h2>
                <p class="mt-2 text-sm text-blue-100">Retrouvez ici votre agenda, vos disponibilités et vos notes médicales.</p>
            </section>

            <!-- Chiffres -->
            <section class="grid grid-cols-1 gap-4 sm:grid-cols-3">
                <div class="rounded-xl bg-white p-5 shadow-sm">
                    <p class="text-xs uppercase text-slate-500">Rendez-vous aujourd'hui</p>
                    <p class="mt-2 text-3xl font-bold text-slate-300">—</p>
                </div>
                <div class="rounded-xl bg-white p-5 shadow-sm">
                    <p class="text-xs uppercase text-slate-500">Prochain rendez-vous</p>
                    <p class="mt-2 text-3xl font-bold text-slate-300">—</p>
                </div>
                <div class="rounded-xl bg-white p-5 shadow-sm">
                    <p class="text-xs uppercase text-slate-500">Notes à rédiger</p>
                    <p class="mt-2 text-3xl font-bold text-slate-300">—</p>
                </div>
            </section>

            <!-- Accès rapides -->
            <section class="grid grid-cols-1 gap-4 sm:grid-cols-2 lg:grid-cols-4">

                <div class="rounded-xl bg-white p-6 shadow-sm">
                    <div class="mb-4 flex h-11 w-11 items-center justify-center rounded-lg bg-blue-100 text-blue-600">
                        <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M8 7V3m8 4V3M5 11h14M5 5h14a2 2 0 012 2v12a2 2 0 01-2 2H5a2 2 0 01-2-2V7a2 2 0 012-2z"/>
                        </svg>
                    </div>
                    <h3 class="font-semibold">Mon agenda</h3>
                    <p class="mt-1 text-sm text-slate-500">Consulter mon planning</p>
                    <span class="mt-3 inline-block rounded bg-slate-100 px-2 py-0.5 text-xs text-slate-500">Bientôt</span>
                </div>

                <div class="rounded-xl bg-white p-6 shadow-sm">
                    <div class="mb-4 flex h-11 w-11 items-center justify-center rounded-lg bg-emerald-100 text-emerald-600">
                        <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z"/>
                        </svg>
                    </div>
                    <h3 class="font-semibold">Mes disponibilités</h3>
                    <p class="mt-1 text-sm text-slate-500">Gérer mes horaires et absences</p>
                    <span class="mt-3 inline-block rounded bg-slate-100 px-2 py-0.5 text-xs text-slate-500">Bientôt</span>
                </div>

                <div class="rounded-xl bg-white p-6 shadow-sm">
                    <div class="mb-4 flex h-11 w-11 items-center justify-center rounded-lg bg-amber-100 text-amber-600">
                        <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2"/>
                        </svg>
                    </div>
                    <h3 class="font-semibold">Mes rendez-vous</h3>
                    <p class="mt-1 text-sm text-slate-500">Voir les rendez-vous de mes patients</p>
                    <span class="mt-3 inline-block rounded bg-slate-100 px-2 py-0.5 text-xs text-slate-500">Bientôt</span>
                </div>

                <div class="rounded-xl bg-white p-6 shadow-sm">
                    <div class="mb-4 flex h-11 w-11 items-center justify-center rounded-lg bg-rose-100 text-rose-600">
                        <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z"/>
                        </svg>
                    </div>
                    <h3 class="font-semibold">Notes médicales</h3>
                    <p class="mt-1 text-sm text-slate-500">Créer et consulter les notes</p>
                    <span class="mt-3 inline-block rounded bg-slate-100 px-2 py-0.5 text-xs text-slate-500">Bientôt</span>
                </div>
            </section>
        </main>
    </div>
</div>

</body>
</html>