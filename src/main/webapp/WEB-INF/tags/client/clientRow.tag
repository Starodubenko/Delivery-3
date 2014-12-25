<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="clients" type="java.util.List" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <c:forEach var="row" items="${clients}">
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
</fmt:bundle>