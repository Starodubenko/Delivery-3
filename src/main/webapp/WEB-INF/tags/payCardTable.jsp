<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="i18n.messages">
    <table class="table table-hover" ID="clientsTable">
        <input type="hidden" id="clientsPageNumber" value="${payCardsPaginatedList.getPageNumber()}"/>
        <tr>
            <th><fmt:message key="view.admin.table.message.ID"/></th>
            <th><fmt:message key="view.admin.table.pay.card.message.serial.number"/></th>
            <th><fmt:message key="view.admin.table.pay.card.message.secret.number"/></th>
            <th><fmt:message key="view.admin.table.pay.card.message.balance"/></th>
            <th><fmt:message key="view.admin.table.message.status.name"/></th>
        </tr>
        <c:forEach var="row" items="${clientsPaginatedList}">
            <tr>
                <td class="id">${row.getId()}</td>
                <td>${row.getSerialNumber()}</td>
                <td>${row.getSecretNumber()}</td>
                <td>${row.getBalance()}</td>
                <td>${row.getStatusPayCard().getStatusName()}</td>
            </tr>
        </c:forEach>
    </table>
</fmt:bundle>
