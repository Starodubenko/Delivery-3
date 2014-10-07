<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:bundle basename="i18n.messages">
<html>

<head>
    <title>Welcome</title>
    <link rel='stylesheet' href='<c:url value="/webjars/bootstrap/3.2.0/css/bootstrap.min.css"/>'>
    <link rel='stylesheet' href='<c:url value="/webjars/bootstrap-datepicker/1.3.0/css/datepicker3.css"/>'>
    <link rel='stylesheet' href='<c:url value="/style/dispatcher.css"/>'>
    <link rel='stylesheet' href='<c:url value="/style/retractableWindow.css"/>'>
</head>
<body background="<c:url value="/style/img/background.jpg"/>" onload="">

<t:navigation></t:navigation>

<div class="main panel panel-default">
    <t:authentication></t:authentication>

    <c:if test="${empty user}">
        <c:redirect url="/do/welcome"/>
    </c:if>

    <div class="panel-group save-panel">
        <div class="panel panel-default">
            <div class="panel-heading edit-panel-heading">
                <h4 class="panel-title">
                    <div class="center">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseSearche">
                            <fmt:message key="view.dispatcher.message.serch"/>
                        </a>
                    </div>
                </h4>
            </div>
            <div id="collapseSearche" class="panel-collapse collapse">
                <div class="panel-body find">
                    <form id="findForm">

                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="orderList panel panel-default">

        <ul class="nav nav-tabs  nav-justified" role="tablist">
            <li id="t1" class="active table" value="Clients"><a href="#Clients" role="tab"
                                                                data-toggle="tab"><fmt:message
                    key="view.dispatcher.table.clients.header"/></a>
            </li>
            <li id="t2" class="table" value="Orders"><a href="#Orders" role="tab" data-toggle="tab"><fmt:message
                    key="view.dispatcher.table.orders.header"/></a></li>
        </ul>
        <div class="tab-content">
            <div class="orderListHeight tab-pane active" id="Clients">
                <div id="clients-block">
                    <ul id="changee" class="pagination">
                        <li id="cBack"><a href="#page">&laquo;</a></li>

                        <c:forEach var="i" begin="1" end="${clientsPaginatedList.getPageCount()}">
                            <li value="${i}" name="page${i}" class="cNumbered page"><a href="#page${i}">${i}</a>
                            </li>
                        </c:forEach>

                        <li id="cNext"><a href="#page">&raquo;</a></li>
                    </ul>

                    <t:rowsCount></t:rowsCount>

                    <div class="orderListHeight tab-pane" style="overflow-y: scroll">
                        <table class="table table-hover" ID="clientsTable">
                            <input type="hidden" id="clientsPageNumber"
                                   value="${clientsPaginatedList.getPageNumber()}"/>
                            <tr>
                                <th><fmt:message key="view.dispatcher.table.clients.message.ID"/></th>
                                <th><fmt:message key="view.dispatcher.table.clients.message.last.name"/></th>
                                <th><fmt:message key="view.dispatcher.table.clients.message.first.name"/></th>
                                <th><fmt:message key="view.dispatcher.table.clients.message.middle.name"/></th>
                                <th><fmt:message key="view.dispatcher.table.clients.message.address"/></th>
                                <th><fmt:message key="view.dispatcher.table.clients.message.telephone"/></th>
                                <th><fmt:message key="view.dispatcher.table.clients.message.mobilephone"/></th>
                                <th><fmt:message key="view.dispatcher.table.clients.button.order"/></th>
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
                                                key="view.dispatcher.table.clients.message.create.order"/>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>

            <div class="orderListHeight tab-pane" id="Orders">
                <div id="orders-block">
                    <ul id="change" class="pagination">
                        <li id="oBack"><a href="#page">&laquo;</a></li>

                        <c:forEach var="i" begin="1" end="${ordersPaginatedList.getPageCount()}">
                            <li value="${i}" name="page${i}" class="oNumbered page"><a href="#page${i}">${i}</a>
                            </li>
                        </c:forEach>

                        <li id="oNext"><a href="#page">&raquo;</a></li>
                    </ul>

                    <select class="form-control switcher floatRight" id="switchStatusOrser">
                        <option>Waiting</option>
                        <option>Active</option>
                        <option>Canceled</option>
                        <option>Executed</option>
                    </select>

                    <t:rowsCount></t:rowsCount>

                    <div class="orderListHeight tab-pane" style="overflow-y: scroll">
                        <table class="table table-hover" ID="ordersTable">
                            <input type="hidden" id="ordersPageNumber" value="${ordersPaginatedList.getPageNumber()}"/>
                            <tr>
                                <th>
                                    <p><fmt:message key="view.dispatcher.table.orders.message.select.all"/></p>

                                    <div class="checkbox">
                                        <label>
                                            <input id="maincheck" type="checkbox">
                                        </label>
                                    </div>
                                </th>
                                <th><fmt:message key="view.dispatcher.table.orders.message.id"/></th>
                                <th><fmt:message key="view.dispatcher.table.orders.message.order.date"/></th>
                                <th><fmt:message key="view.dispatcher.table.orders.message.goods.name"/></th>
                                <th><fmt:message key="view.dispatcher.table.orders.message.goods.count"/></th>
                                <th><fmt:message key="view.dispatcher.table.orders.message.order.cost"/></th>
                                <th><fmt:message key="view.dispatcher.table.orders.message.delivery.date"/></th>
                                <th><fmt:message key="view.dispatcher.table.orders.message.delivery.time"/></th>
                                <th><fmt:message key="view.dispatcher.table.orders.message.additional.info"/></th>
                                <th><fmt:message key="view.dispatcher.table.orders.message.status"/></th>
                            </tr>
                            <c:forEach var="row" items="${ordersPaginatedList}">
                                <tr data-toggle="collapse" data-parent="#accordion">
                                    <td>
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox" name="IdOrder" class="mc" value="${row.getId()}">
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
                    </div>
                </div>

                <input type="button" class="ordersButtons btn btn-primary" value="Cancel the Order" id="cancel">
                <input type="button" class="ordersButtons btn btn-primary" value="accept the order" id="accept">
                <input type="button" class="ordersButtons btn btn-primary" value="restore order" id="restore">

            </div>

            <div class="panel-group edit-panel" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading edit-panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                <fmt:message key="view.dispatcher.message.edit"/>
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse">
                        <div class="panel-body">
                            <form id="changeForm" action="${pageContext.request.contextPath}/do/saveOrderData">

                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>

<t:footer></t:footer>

<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span
                        aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="confirmModalLabel">Do you confirm to change the data</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                <button type="submit" class="btn btn-primary" form="changeForm">Yes</button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="myModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span
                        aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel"><fmt:message key="order.create.message.header"/></h4>
            </div>
                <%--<form action="${pageContext.request.contextPath}/do/fastCreateOrder">--%>
            <div>
                <div class="orderText form-group">
                    <div class="center"><label for="Date"><fmt:message
                            key="order.create.message.delivery.date"/></label></div>
                    <input type="text" name="deliverydate" value="Date" class="form-control datepicker" id="Date">
                </div>
                <div class="orderText form-group">
                    <div class="center"><label for="PeriodTime"><fmt:message
                            key="order.create.message.delivery.time"/></label></div>
                    <select class="form-control" name="deliverytime" value="Time" class="form-control"
                            id="PeriodTime">
                        <c:forEach var="period" items="${periods}">
                            <option>${period.period}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="orderText form-group">
                    <div class="center"><label for="GoodsName"><fmt:message
                            key="order.create.message.goods.name"/></label></div>
                    <select class="form-control" name="goodsname" value="Goods name" class="form-control"
                            id="GoodsName">
                        <c:forEach var="goodss" items="${goods}">
                            <option>${goodss.getGoodsName()}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="orderText form-group">
                    <div class="center"><label for="Count"><fmt:message key="order.create.message.goods.count"/></label>
                    </div>
                    <input type="text" name="goodscount" value="1" class="form-control" id="Count">
                </div>
                <div class="orderInfo form-group">
                    <div class="center"><label for="AdditionalInformation"><fmt:message
                            key="order.create.message.additional.info"/></label></div>
                    <textarea name="additionalinformation" value="Count" class="form-control"
                              id="AdditionalInformation">
                    </textarea>
                </div>
            </div>

            <div class="center form-group">
                <input type="hidden" name="PaymentType" value="cache" id="PaymentType">
            </div>

            <p id="errorCreatingOrder"></p>

            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="create"><fmt:message
                        key="order.create.button.create.order"/></button>
            </div>

                <%--</form>--%>
        </div>
    </div>
</div>

<script src="<c:url value="/webjars/jquery/1.11.1/jquery.min.js"/>"></script>
<script src="<c:url value="/webjars/bootstrap/3.2.0/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/webjars/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"/>"></script>
<script src="<c:url value="/script/dispatcher.js"/>"></script>
</body>
</html>
</fmt:bundle>


