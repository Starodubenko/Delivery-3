<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tOrder" tagdir="/WEB-INF/tags/order" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <ul class="nav nav-tabs  nav-justified" role="tablist">
        <li class="active"><a href="#Today" role="tab" data-toggle="tab"><fmt:message
                key="client.information.today"/></a></li>
        <li><a href="#HistoryOrders" role="tab" data-toggle="tab"><fmt:message key="client.information.history"/></a>
        </li>
    </ul>
    <form action="${pageContext.request.contextPath}/do/orderOperation" id="cancelOrderForm">
        <div class="tab-content">
            <div class="orderListHeight tab-pane active" id="Today" style="overflow: scroll">
                <table class="table table-hover">
                    <tr>
                        <th></th>
                        <th><fmt:message key="orders.message.id"/></th>
                        <th><fmt:message key="orders.message.order.date"/></th>
                        <th><fmt:message key="orders.message.goods.name"/></th>
                        <th><fmt:message key="orders.message.goods.count"/></th>
                        <th><fmt:message key="orders.message.order.cost"/></th>
                        <th><fmt:message key="orders.message.paid"/></th>
                        <th><fmt:message key="orders.message.delivery.date"/></th>
                        <th><fmt:message key="orders.message.delivery.time"/></th>
                        <th><fmt:message key="orders.message.additional.info"/></th>
                        <th><fmt:message key="orders.message.status"/></th>
                    </tr>
                    <tOrder:orderRow orders="${todayOrders}"/>
                </table>
            </div>
            <div class="orderListHeight tab-pane" id="HistoryOrders" style="overflow: scroll">
                <table class="table table-hover">
                    <tr>
                        <th></th>
                        <th><fmt:message key="orders.message.id"/></th>
                        <th><fmt:message key="orders.message.order.date"/></th>
                        <th><fmt:message key="orders.message.goods.name"/></th>
                        <th><fmt:message key="orders.message.goods.count"/></th>
                        <th><fmt:message key="orders.message.order.cost"/></th>
                        <th><fmt:message key="orders.message.paid"/></th>
                        <th><fmt:message key="orders.message.delivery.date"/></th>
                        <th><fmt:message key="orders.message.delivery.time"/></th>
                        <th><fmt:message key="orders.message.additional.info"/></th>
                        <th><fmt:message key="orders.message.status"/></th>
                    </tr>
                    <tOrder:orderRow orders="${pastOrders}"/>
                </table>
            </div>
        </div>
        <%--<button type="button" class="createOrderButton btn btn-primary" data-toggle="modal"--%>
                <%--data-target="#myModal">--%>
            <%--<fmt:message key="button.create.order"/>--%>
        <%--</button>--%>
        <input type="submit" class="cancelOrderButton btn btn-primary" data-toggle="modal"
               data-target="#cancelOrder" value="<fmt:message key="button.cancel.order"/>">
    </form>
</fmt:bundle>