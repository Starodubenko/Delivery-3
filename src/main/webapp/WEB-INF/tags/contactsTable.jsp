<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="i18n.messages">
    <table class="table table-hover" ID="clientsTable">
        <input type="hidden" id="clientsPageNumber" value="${contactsPaginatedList.getPageNumber()}"/>
        <tr>
            <th><fmt:message key="view.admin.table.message.ID"/></th>
            <th><fmt:message key="view.admin.table.contacts.message.telephone"/></th>
            <th><fmt:message key="view.admin.table.contacts.message.owner"/></th>
        </tr>
        <c:forEach var="row" items="${clientsPaginatedList}">
            <tr>
                <td class="id">${row.getId()}</td>
                <td>${row.getTelephone()}</td>
                <td>${row.getOwner}</td>
            </tr>
        </c:forEach>
    </table>
</fmt:bundle>
