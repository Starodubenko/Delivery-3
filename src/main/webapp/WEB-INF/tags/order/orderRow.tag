<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="orders" type="java.util.List" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <c:forEach var="row" items="${orders}">
        <tr>
            <td>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" name="IdOrder" value="${row.getId()}">
                    </label>
                </div>
            </td>
            <td>${row.getId()}</td>
            <td>${row.getOrderDate()}</td>
            <td>${row.getGoods().getGoodsName()}</td>
            <td>${row.getCount()}</td>
            <td>${row.getOrderCost()}</td>
            <td>${row.getPaid()}</td>
            <td>${row.getDeliveryDate()}</td>
            <td>${row.getPeriod().getPeriod()}</td>
            <td>${row.getAdditionalInfo()}</td>
            <td>${row.getStatus().getStatusName()}</td>
        </tr>
    </c:forEach>
</fmt:bundle>