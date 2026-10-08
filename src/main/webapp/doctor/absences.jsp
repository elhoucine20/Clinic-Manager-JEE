<%--
  Created by IntelliJ IDEA.
  User: Youcode
  Date: 08/10/2026
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Mes absences</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="min-h-screen bg-slate-100 text-slate-800">

<div class="flex min-h-screen">

  <c:set var="activePage" value="absences"/>
  <%@ include file="/WEB-INF/fragments/doctor-sidebar.jspf" %>

  <div class="flex flex-1 flex-col">

    <header class="flex items-center justify-between bg-white px-6 py-4 shadow-sm">
      <h1 class="text-lg font-semibold">Mes absences</h1>
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
        <div class="rounded-lg bg-green-100 px-4 py-3 text-sm text-green-800">Modification enregistrée.</div>
      </c:if>
      <c:if test="${not empty error}">
        <div class="rounded-lg bg-red-100 px-4 py-3 text-sm text-red-700"><c:out value="${error}"/></div>
      </c:if>

      <section class="rounded-xl bg-white p-6 shadow-sm">
        <h2 class="text-base font-bold">Déclarer une absence</h2>
        <p class="mt-1 text-sm text-slate-500">
          Pendant une absence, aucun créneau n'est proposé aux patients. Les deux dates sont incluses.
        </p>

        <form action="${pageContext.request.contextPath}/doctor/absences" method="post"
              class="mt-4 grid grid-cols-1 gap-4 sm:grid-cols-2 lg:grid-cols-4">
          <input type="hidden" name="action" value="add">

          <div>
            <label for="startDate" class="mb-1 block text-sm font-medium text-slate-700">Du</label>
            <input type="date" id="startDate" name="startDate" required
                   class="w-full rounded-lg border border-slate-300 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500">
          </div>

          <div>
            <label for="endDate" class="mb-1 block text-sm font-medium text-slate-700">Au</label>
            <input type="date" id="endDate" name="endDate" required
                   class="w-full rounded-lg border border-slate-300 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500">
          </div>

          <div class="lg:col-span-2">
            <label for="reason" class="mb-1 block text-sm font-medium text-slate-700">Motif (facultatif)</label>
            <input type="text" id="reason" name="reason" maxlength="255" placeholder="Congé, formation..."
                   class="w-full rounded-lg border border-slate-300 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500">
          </div>

          <div class="sm:col-span-2 lg:col-span-4">
            <button type="submit"
                    class="rounded-lg bg-blue-600 px-4 py-2 text-sm font-semibold text-white hover:bg-blue-700">Ajouter</button>
          </div>
        </form>
      </section>

      <section class="overflow-x-auto rounded-xl bg-white shadow-sm">
        <table class="min-w-full text-left text-sm">
          <thead class="bg-slate-50 text-xs uppercase text-slate-500">
          <tr>
            <th class="px-4 py-3">Du</th>
            <th class="px-4 py-3">Au</th>
            <th class="px-4 py-3">Motif</th>
            <th class="px-4 py-3">Statut</th>
            <th class="px-4 py-3 text-right">Action</th>
          </tr>
          </thead>
          <tbody class="divide-y divide-slate-100">
          <c:forEach items="${absences}" var="a">
            <tr>
              <td class="px-4 py-3 font-medium"><c:out value="${a.startDate}"/></td>
              <td class="px-4 py-3 font-medium"><c:out value="${a.endDate}"/></td>
              <td class="px-4 py-3"><c:out value="${a.reason}"/></td>
              <td class="px-4 py-3">
                <c:choose>
                  <c:when test="${a.status == 'En cours'}">
                    <span class="rounded-full bg-amber-100 px-2 py-0.5 text-xs font-medium text-amber-700">En cours</span>
                  </c:when>
                  <c:when test="${a.status == 'A venir'}">
                    <span class="rounded-full bg-blue-100 px-2 py-0.5 text-xs font-medium text-blue-700">A venir</span>
                  </c:when>
                  <c:otherwise>
                    <span class="rounded-full bg-slate-100 px-2 py-0.5 text-xs font-medium text-slate-600">Passee</span>
                  </c:otherwise>
                </c:choose>
              </td>
              <td class="px-4 py-3 text-right">
                <form action="${pageContext.request.contextPath}/doctor/absences" method="post" class="inline">
                  <input type="hidden" name="action" value="delete">
                  <input type="hidden" name="id" value="${a.id}">
                  <button type="submit"
                          class="rounded-lg border border-red-300 px-3 py-1 text-xs font-semibold text-red-600 hover:bg-red-50">Supprimer</button>
                </form>
              </td>
            </tr>
          </c:forEach>
          <c:if test="${empty absences}">
            <tr><td colspan="5" class="px-4 py-6 text-center text-slate-400">Aucune absence déclarée.</td></tr>
          </c:if>
          </tbody>
        </table>
      </section>
    </main>
  </div>
</div>

</body>
</html>