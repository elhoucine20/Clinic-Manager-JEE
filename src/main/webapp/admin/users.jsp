<%--
  Created by IntelliJ IDEA.
  User: Youcode
  Date: 06/10/2026
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Utilisateurs</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="min-h-screen bg-slate-100 text-slate-800">

<div class="flex min-h-screen">

    <c:set var="activePage" value="users"/>
    <%@ include file="/WEB-INF/fragments/admin-sidebar.jspf" %>

    <div class="flex flex-1 flex-col">

        <header class="flex items-center justify-between bg-white px-6 py-4 shadow-sm">
            <h1 class="text-lg font-semibold">Utilisateurs</h1>
            <div class="flex items-center gap-4">
                <div class="text-right leading-tight">
                    <p class="text-sm font-medium"><c:out value="${fullName}"/></p>
                    <p class="text-xs text-slate-500"><c:out value="${sessionScope.role}"/></p>
                </div>
                <a href="${pageContext.request.contextPath}/logout"
                   class="rounded-lg bg-red-600 px-3 py-1.5 text-sm font-semibold text-white hover:bg-red-700">Déconnexion</a>
            </div>
        </header>

        <main class="flex-1 space-y-4 p-6">

            <c:if test="${not empty param.success}">
                <div class="rounded-lg bg-green-100 px-4 py-3 text-sm text-green-800">Modification enregistrée.</div>
            </c:if>
            <c:if test="${not empty error}">
                <div class="rounded-lg bg-red-100 px-4 py-3 text-sm text-red-700"><c:out value="${error}"/></div>
            </c:if>

            <div class="overflow-x-auto rounded-xl bg-white shadow-sm">
                <table class="min-w-full text-left text-sm">
                    <thead class="bg-slate-50 text-xs uppercase text-slate-500">
                    <tr>
                        <th class="px-4 py-3">Nom</th>
                        <th class="px-4 py-3">Contact</th>
                        <th class="px-4 py-3">Rôle</th>
                        <th class="px-4 py-3">Statut</th>
                        <th class="px-4 py-3 text-right">Action</th>
                    </tr>
                    </thead>
                    <tbody class="divide-y divide-slate-100">
                    <c:forEach items="${users}" var="u">
                        <tr>
                            <td class="px-4 py-3 font-medium"><c:out value="${u.fullName}"/></td>
                            <td class="px-4 py-3">
                                <p><c:out value="${u.email}"/></p>
                                <p class="text-xs text-slate-500"><c:out value="${u.telephone}"/></p>
                            </td>
                            <td class="px-4 py-3">
                                <span class="rounded bg-slate-100 px-2 py-0.5 text-xs font-medium text-slate-700"><c:out value="${u.role}"/></span>
                            </td>
                            <td class="px-4 py-3">
                                <c:choose>
                                    <c:when test="${u.active}">
                                        <span class="rounded-full bg-green-100 px-2 py-0.5 text-xs font-medium text-green-700">Actif</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="rounded-full bg-red-100 px-2 py-0.5 text-xs font-medium text-red-700">Désactivé</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="px-4 py-3 text-right">
                                <c:choose>
                                    <c:when test="${u.id == currentUserId}">
                                        <span class="text-xs text-slate-400">Vous</span>
                                    </c:when>
                                    <c:otherwise>
                                        <form action="${pageContext.request.contextPath}/admin/users" method="post" class="inline">
                                            <input type="hidden" name="userId" value="${u.id}">
                                            <input type="hidden" name="active" value="${!u.active}">
                                            <c:choose>
                                                <c:when test="${u.active}">
                                                    <button type="submit"
                                                            class="rounded-lg border border-red-300 px-3 py-1 text-xs font-semibold text-red-600 hover:bg-red-50">Désactiver</button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button type="submit"
                                                            class="rounded-lg border border-green-300 px-3 py-1 text-xs font-semibold text-green-700 hover:bg-green-50">Activer</button>
                                                </c:otherwise>
                                            </c:choose>
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty users}">
                        <tr><td colspan="5" class="px-4 py-6 text-center text-slate-400">Aucun utilisateur.</td></tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </main>
    </div>
</div>

</body>
</html>