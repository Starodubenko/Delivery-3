<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <form id="ordersForm" onsubmit="return false;">
        <table class="table table-hover" ID="ordersTable">
            <input type="hidden" id="ordersPageNumber"
                   value="${ordersPaginatedList.getPageNumber()}"/>
            <tr>
                <th>
                    <p><fmt:message key="orders.message.select.all"/></p>

                    <div class="checkbox">
                        <label>
                            <input id="maincheck" type="checkbox">
                        </label>
                    </div>
                </th>
                <th><fmt:message key="orders.message.id"/></th>
                <th><fmt:message key="orders.message.order.date"/></th>
                <th><fmt:message key="orders.message.goods.name"/></th>
                <th><fmt:message key="orders.message.goods.count"/></th>
                <th><fmt:message key="orders.message.order.cost"/></th>
                <th><fmt:message key="orders.message.delivery.date"/></th>
                <th><fmt:message key="orders.message.delivery.time"/></th>
                <th><fmt:message key="orders.message.additional.info"/></th>
                <th><fmt:message key="orders.message.status"/></th>
            </tr>

            <c:forEach var="row" items="${ordersPaginatedList}">
                <tr>
                    <td>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="IdOrder" class="mc"
                                       value="${row.getId()}">
                            </label>
                        </div>
                    </td>
                    <td>${row.getId()}</td>
                    <td>${row.getOrderDate()}</td>
                    <td>${row.getGoods().getGoodsName()}</td>
                    <td>${row.getCount()}</td>
                    <td>${row.getOrderCost()}</td>
                    <td>${row.getDeliveryDate()}</td>
                    <td>${row.getPeriod().getPeriod()}</td>
                    <td>${row.getAdditionalInfo()}</td>
                    <td>${row.getStatus().getStatusName()}</td>
                </tr>
            </c:forEach>
        </table>
    </form>
</fmt:bundle>