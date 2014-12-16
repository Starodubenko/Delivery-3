<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <ul class="nav nav-tabs  nav-justified" role="tablist">
        <li class="active"><a href="#Today" role="tab" data-toggle="tab"><fmt:message
                key="client.information.today"/></a></li>
        <li><a href="#HistoryOrders" role="tab" data-toggle="tab"><fmt:message key="client.information.history"/></a>
        </li>
    </ul>
    <form action="${pageContext.request.contextPath}/do/cancelOrder" id="cancelOrderForm">
        <div class="tab-content">
            <div class="orderListHeight tab-pane active" id="Today" style="overflow: scroll">
                <table class="table table-hover">
                    <tr>
                        <th></th>
                        <th><fmt:message key="view.client.orders.client.table.id"/></th>
                        <th><fmt:message key="view.client.orders.client.table.order.date"/></th>
                        <th><fmt:message key="view.client.orders.client.table.goods.name"/></th>
                        <th><fmt:message key="view.client.orders.client.table.goods.count"/></th>
                        <th><fmt:message key="view.client.orders.client.table.order.cost"/></th>
                        <th><fmt:message key="view.client.orders.client.table.paid"/></th>
                        <th><fmt:message key="view.client.orders.client.table.delivery.date"/></th>
                        <th><fmt:message key="view.client.orders.client.table.delivery.time"/></th>
                        <th><fmt:message key="view.client.orders.client.table.additional.info"/></th>
                        <th><fmt:message key="view.client.orders.client.table.status"/></th>
                    </tr>
                    <c:forEach var="row" items="${todayOrders}">
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
                </table>
            </div>
            <div class="orderListHeight tab-pane" id="HistoryOrders" style="overflow: scroll">
                <table class="table table-hover">
                    <tr>
                        <th></th>
                        <th><fmt:message key="view.client.orders.client.table.id"/></th>
                        <th><fmt:message key="view.client.orders.client.table.order.date"/></th>
                        <th><fmt:message key="view.client.orders.client.table.goods.name"/></th>
                        <th><fmt:message key="view.client.orders.client.table.goods.count"/></th>
                        <th><fmt:message key="view.client.orders.client.table.order.cost"/></th>
                        <th><fmt:message key="view.client.orders.client.table.paid"/></th>
                        <th><fmt:message key="view.client.orders.client.table.delivery.date"/></th>
                        <th><fmt:message key="view.client.orders.client.table.delivery.time"/></th>
                        <th><fmt:message key="view.client.orders.client.table.additional.info"/></th>
                        <th><fmt:message key="view.client.orders.client.table.status"/></th>
                    </tr>
                    <c:forEach var="row" items="${pastOrders}">
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
                </table>
            </div>
        </div>
        <button type="button" class="createOrderButton btn btn-primary" data-toggle="modal"
                data-target="#myModal">
            <fmt:message key="view.client.button.order.create"/>
        </button>
        <input type="submit" class="cancelOrderButton btn btn-primary" data-toggle="modal"
               data-target="#cancelOrder" value="<fmt:message key="view.client.button.order.cansel"/>">
    </form>
</fmt:bundle>