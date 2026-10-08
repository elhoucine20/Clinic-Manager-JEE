<%--
  Created by IntelliJ IDEA.
  User: Youcode
  Date: 07/10/2026
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mes disponibilités</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="min-h-screen bg-slate-100 text-slate-800">

<div class="flex min-h-screen">

    <c:set var="activePage" value="availabilities"/>
    <%@ include file="/WEB-INF/fragments/doctor-sidebar.jspf" %>

    <div class="flex flex-1 flex-col">

        <header class="flex items-center justify-between bg-white px-6 py-4 shadow-sm">
            <h1 class="text-lg font-semibold">Mes disponibilités</h1>
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

            <c:if test="${not empty success}">
                <div class="rounded-lg bg-green-100 px-4 py-3 text-sm text-green-800">Modification enregistrée.</div>
            </c:if>

            <c:if test="${not empty error}">
                <div class="rounded-lg bg-red-100 px-4 py-3 text-sm text-red-700"><c:out value="${error}"/></div>
            </c:if>

            <!-- Formulaire d'ajout -->
            <section class="rounded-xl bg-white p-6 shadow-sm">
                <h2 class="text-base font-bold">Ajouter un horaire</h2>
                <p class="mt-1 text-sm text-slate-500">
                    L'horaire se répète chaque semaine. Pour deux plages le même jour (matin et après-midi), ajoutez deux horaires.
                </p>

                <form action="${pageContext.request.contextPath}/doctor/availabilities" method="post"
                      class="mt-4 grid grid-cols-1 gap-4 sm:grid-cols-2 lg:grid-cols-5">
                    <input type="hidden" name="action" value="add">

                    <div>
                        <label for="day" class="mb-1 block text-sm font-medium text-slate-700">Jour</label>
                        <select id="day" name="day" required
                                class="w-full rounded-lg border border-slate-300 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500">
                            <option value="MONDAY">Lundi</option>
                            <option value="TUESDAY">Mardi</option>
                            <option value="WEDNESDAY">Mercredi</option>
                            <option value="THURSDAY">Jeudi</option>
                            <option value="FRIDAY">Vendredi</option>
                            <option value="SATURDAY">Samedi</option>
                        </select>
                    </div>

                    <div>
                        <label for="startTime" class="mb-1 block text-sm font-medium text-slate-700">Début</label>
                        <input type="time" id="startTime" name="startTime" required
                               class="w-full rounded-lg border border-slate-300 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500">
                    </div>

                    <div>
                        <label for="endTime" class="mb-1 block text-sm font-medium text-slate-700">Fin</label>
                        <input type="time" id="endTime" name="endTime" required
                               class="w-full rounded-lg border border-slate-300 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500">
                    </div>

                    <div>
                        <label for="validFrom" class="mb-1 block text-sm font-medium text-slate-700">Valable à partir du</label>
                        <input type="date" id="validFrom" name="validFrom" required
                               class="w-full rounded-lg border border-slate-300 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500">
                    </div>

                    <div>
                        <label for="validTo" class="mb-1 block text-sm font-medium text-slate-700">Jusqu'au (facultatif)</label>
                        <input type="date" id="validTo" name="validTo"
                               class="w-full rounded-lg border border-slate-300 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500">
                    </div>

                    <div class="sm:col-span-2 lg:col-span-5">
                        <button type="submit"
                                class="rounded-lg bg-blue-600 px-4 py-2 text-sm font-semibold text-white hover:bg-blue-700">Ajouter</button>
                    </div>
                </form>
            </section>

            <!-- Liste -->
            <section class="overflow-x-auto rounded-xl bg-white shadow-sm">
                <table class="min-w-full text-left text-sm">
                    <thead class="bg-slate-50 text-xs uppercase text-slate-500">
                    <tr>
                        <th class="px-4 py-3">Jour</th>
                        <th class="px-4 py-3">Horaire</th>
                        <th class="px-4 py-3">Début de validité</th>
                        <th class="px-4 py-3">Fin de validité</th>
                        <th class="px-4 py-3">Statut</th>
                        <th class="px-4 py-3 text-right">Action</th>
                    </tr>
                    </thead>
                    <tbody class="divide-y divide-slate-100">
                    <c:forEach items="${availabilities}" var="a">
                        <tr>
                            <td class="px-4 py-3 font-medium"><c:out value="${a.dayLabel}"/></td>
                            <td class="px-4 py-3"><c:out value="${a.startTime}"/> – <c:out value="${a.endTime}"/></td>
                            <td class="px-4 py-3"><c:out value="${a.validFrom}"/></td>
                            <td class="px-4 py-3"><c:out value="${a.validTo}"/></td>
                            <td class="px-4 py-3">
                                <c:choose>
                                    <c:when test="${a.active}">
                                        <span class="rounded-full bg-green-100 px-2 py-0.5 text-xs font-medium text-green-700">Actif</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="rounded-full bg-slate-100 px-2 py-0.5 text-xs font-medium text-slate-600">Inactif</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="px-4 py-3 text-right">
                                <form action="${pageContext.request.contextPath}/doctor/availabilities" method="post" class="inline">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="id" value="${a.id}">
                                    <button type="submit"
                                            class="rounded-lg border border-red-300 px-3 py-1 text-xs font-semibold text-red-600 hover:bg-red-50">Supprimer</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty availabilities}">
                        <tr><td colspan="6" class="px-4 py-6 text-center text-slate-400">Aucun horaire pour l'instant.</td></tr>
                    </c:if>
                    </tbody>
                </table>
            </section>
        </main>
    </div>
</div>

</body>
</html>