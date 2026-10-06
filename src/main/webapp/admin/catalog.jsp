<%--
  Created by IntelliJ IDEA.
  User: Youcode
  Date: 03/10/2026
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Départements et spécialités</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="min-h-screen bg-slate-100 text-slate-800">

<div class="flex min-h-screen">

  <c:set var="activePage" value="catalog"/>
  <%@ include file="/WEB-INF/fragments/admin-sidebar.jspf" %>

  <div class="flex flex-1 flex-col">

    <header class="flex items-center justify-between bg-white px-6 py-4 shadow-sm">
      <h1 class="text-lg font-semibold">Départements et spécialités</h1>
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

      <c:if test="${not empty param.success}">
        <div class="rounded-lg bg-green-100 px-4 py-3 text-sm text-green-800">Enregistré avec succès.</div>
      </c:if>
      <c:if test="${not empty error}">
        <div class="rounded-lg bg-red-100 px-4 py-3 text-sm text-red-700"><c:out value="${error}"/></div>
      </c:if>

      <div class="grid grid-cols-1 gap-6 lg:grid-cols-2">

        <!-- Départements -->
        <section class="rounded-xl bg-white p-6 shadow-sm">
          <h2 class="text-base font-bold">Départements</h2>

          <form action="${pageContext.request.contextPath}/admin/catalog" method="post" class="mt-4 flex gap-3">
            <input type="hidden" name="action" value="department">
            <input type="text" name="name" placeholder="Ex : Médecine" required
                   class="flex-1 rounded-lg border border-slate-300 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500">
            <button type="submit"
                    class="rounded-lg bg-blue-600 px-4 py-2 text-sm font-semibold text-white hover:bg-blue-700">Ajouter</button>
          </form>

          <ul class="mt-4 divide-y divide-slate-100 text-sm">
            <c:forEach items="${departments}" var="d">
              <li class="py-2"><c:out value="${d.name}"/></li>
            </c:forEach>
            <c:if test="${empty departments}">
              <li class="py-2 text-slate-400">Aucun département pour l'instant.</li>
            </c:if>
          </ul>
        </section>

        <!-- Spécialités -->
        <section class="rounded-xl bg-white p-6 shadow-sm">
          <h2 class="text-base font-bold">Spécialités</h2>

          <c:choose>
            <c:when test="${empty departments}">
              <p class="mt-4 rounded-lg bg-amber-50 px-3 py-2 text-sm text-amber-800">
                Créez d'abord un département pour pouvoir ajouter une spécialité.
              </p>
            </c:when>
            <c:otherwise>
              <form action="${pageContext.request.contextPath}/admin/catalog" method="post" class="mt-4 space-y-3">
                <input type="hidden" name="action" value="specialty">
                <select name="departmentId" required
                        class="w-full rounded-lg border border-slate-300 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500">
                  <option value="">Choisir un département</option>
                  <c:forEach items="${departments}" var="d">
                    <option value="${d.id}"><c:out value="${d.name}"/></option>
                  </c:forEach>
                </select>
                <div class="flex gap-3">
                  <input type="text" name="name" placeholder="Ex : Cardiologie" required
                         class="flex-1 rounded-lg border border-slate-300 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500">
                  <button type="submit"
                          class="rounded-lg bg-blue-600 px-4 py-2 text-sm font-semibold text-white hover:bg-blue-700">Ajouter</button>
                </div>
              </form>
            </c:otherwise>
          </c:choose>

          <ul class="mt-4 divide-y divide-slate-100 text-sm">
            <c:forEach items="${specialties}" var="s">
              <li class="flex items-center justify-between py-2">
                <span><c:out value="${s.name}"/></span>
                <span class="text-xs text-slate-500"><c:out value="${s.departmentName}"/></span>
              </li>
            </c:forEach>
            <c:if test="${empty specialties}">
              <li class="py-2 text-slate-400">Aucune spécialité pour l'instant.</li>
            </c:if>
          </ul>
        </section>
      </div>
    </main>
  </div>
</div>

</body>
</html>