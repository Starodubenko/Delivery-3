<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="i18n.messages">
    <table class="table table-hover" ID="clientsTable">
        <input type="hidden" id="clientsPageNumber" value="${goodsPaginatedList.getPageNumber()}"/>
        <tr>
            <th><fmt:message key="view.admin.table.message.ID"/></th>
            <th><fmt:message key="view.admin.table.goods.message.goods.name"/></th>
            <th><fmt:message key="view.admin.table.goods.message.price"/></th>
        </tr>
        <c:forEach var="row" items="${clientsPaginatedList}">
            <tr>
                <td class="id">${row.getId()}</td>
                <td>${row.getGoodsName()}</td>
                <td>${row.getPrice()}</td>
            </tr>
        </c:forEach>
    </table>
</fmt:bundle>