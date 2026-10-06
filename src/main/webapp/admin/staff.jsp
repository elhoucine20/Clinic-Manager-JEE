<%--
  Created by IntelliJ IDEA.
  User: Youcode
  Date: 05/10/2026
  Time: 13:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Staff</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="min-h-screen bg-slate-100 text-slate-800">

<div class="flex min-h-screen">

    <c:set var="activePage" value="staff"/>
    <%@ include file="/WEB-INF/fragments/admin-sidebar.jspf" %>

    <div class="flex flex-1 flex-col">

        <header class="flex items-center justify-between bg-white px-6 py-4 shadow-sm">
            <h1 class="text-lg font-semibold">Staff</h1>
            <div class="flex items-center gap-4">
                <div class="text-right leading-tight">
                    <p class="text-sm font-medium"><c:out value="${sessionScope.fullName}"/></p>
                    <p class="text-xs text-slate-500"><c:out value="${sessionScope.role}"/></p>
                </div>
                <a href="${pageContext.request.contextPath}/logout"
                   class="rounded-lg bg-red-600 px-3 py-1.5 text-sm font-semibold text-white hover:bg-red-700">Déconnexion</a>
            </div>
        </header>

        <main class="flex-1 p-6">
            <div class="overflow-x-auto rounded-xl bg-white shadow-sm">
                <table class="min-w-full text-left text-sm">
                    <thead class="bg-slate-50 text-xs uppercase text-slate-500">
                    <tr>
                        <th class="px-4 py-3">Nom</th>
                        <th class="px-4 py-3">Contact</th>
                        <th class="px-4 py-3">Statut</th>
                    </tr>
                    </thead>
                    <tbody class="divide-y divide-slate-100">
                    <c:forEach items="${staffList}" var="s">
                        <tr>
                            <td class="px-4 py-3 font-medium"><c:out value="${s.fullName}"/></td>
                            <td class="px-4 py-3">
                                <p><c:out value="${s.email}"/></p>
                                <p class="text-xs text-slate-500"><c:out value="${s.telephone}"/></p>
                            </td>
                            <td class="px-4 py-3">
                                <c:choose>
                                    <c:when test="${s.active}">
                                        <span class="rounded-full bg-green-100 px-2 py-0.5 text-xs font-medium text-green-700">Actif</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="rounded-full bg-red-100 px-2 py-0.5 text-xs font-medium text-red-700">Désactivé</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty staffList}">
                        <tr><td colspan="3" class="px-4 py-6 text-center text-slate-400">Aucun membre du staff pour l'instant.</td></tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </main>
    </div>
</div>

</body>
</html>