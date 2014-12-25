<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tClient" tagdir="/WEB-INF/tags/client" %>
<%@ attribute name="clientsPaginatedList" type="com.epam.star.dao.util.PaginatedList" %>

<fmt:bundle basename="i18n.messages">
    <table class="table table-hover" ID="clientsTable">
        <input type="hidden" id="clientsPageNumber" value="${clientsPaginatedList.getPageNumber()}"/>
        <tr>
            <th><fmt:message key="clients.message.ID"/></th>
            <th><fmt:message key="clients.message.last.name"/></th>
            <th><fmt:message key="clients.message.first.name"/></th>
            <th><fmt:message key="clients.message.middle.name"/></th>
            <th><fmt:message key="clients.message.address"/></th>
            <th><fmt:message key="clients.message.telephone"/></th>
            <th><fmt:message key="clients.message.mobilephone"/></th>
        </tr>
        <tClient:clientRow clients="${clientsPaginatedList}"/>
    </table>
</fmt:bundle>