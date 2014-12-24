<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="i18n.messages">
    <table class="table table-hover" ID="clientsTable">
        <input type="hidden" id="clientsPageNumber"
               value="${clientsPaginatedList.getPageNumber()}"/>
        <tr>
            <th><fmt:message key="clients.message.ID"/></th>
            <th><fmt:message key="clients.message.last.name"/></th>
            <th><fmt:message key="clients.message.first.name"/></th>
            <th><fmt:message key="clients.message.middle.name"/></th>
            <th><fmt:message key="clients.message.address"/></th>
            <th><fmt:message key="clients.message.telephone"/></th>
            <th><fmt:message key="clients.message.mobilephone"/></th>
            <th><fmt:message key="button.order"/></th>
        </tr>
        <c:forEach var="row" items="${clientsPaginatedList}">
            <tr>
                <td class="id">${row.getId()}</td>
                <td>${row.getLastName()}</td>
                <td>${row.getFirstName()}</td>
                <td>${row.getMiddleName()}</td>
                <td>${row.getAddress()}</td>
                <td>${row.getTelephone()}</td>
                <td>${row.getMobilephone()}</td>
                <td class=" createOrder">
                    <button type="button" class="btn btn-primary" data-toggle="modal"
                            value="${row.getId()}"
                            data-target="#myModel"><fmt:message
                            key="clients.message.create.order"/>
                    </button>
                </td>
            </tr>
        </c:forEach>
    </table>
</fmt:bundle>
