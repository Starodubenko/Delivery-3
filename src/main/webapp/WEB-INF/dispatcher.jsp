<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <html>
    <t:gHead>
        <link rel='stylesheet' href='<c:url value="/webjars/bootstrap-datepicker/1.3.0/css/datepicker3.css"/>'>
        <link rel='stylesheet' href='<c:url value="/style/dispatcher.css"/>'>
    </t:gHead>
    <t:gbody>
        <t:authentication/>

        <form class="navbar-form text-center" id="search-form" onsubmit="return false;">
            <div class="form-group" id="searchRow">
                <input type="text" class="form-control" name="searchString" id="search"
                       placeholder="<fmt:message key="message.search"/>">
            </div>
            <input type="button" class="btn btn-default" id="search-button" value="<fmt:message key="button.search"/>">

            <div id="table-name">
                <input type="hidden" name="entityName" id="entityName" value="Client">
            </div>
        </form>

        <input type="hidden" id="user" value="${user.getRole().getPositionName()}">

        <div class="orderList panel panel-default">

            <ul class="nav nav-tabs  nav-justified" role="tablist">
                <li id="t1" class="active table" value="Clients"><a href="#Clients" role="tab"
                                                                    data-toggle="tab"><fmt:message
                        key="clients.header"/></a>
                </li>
                <li id="t2" class="table" value="Orders"><a href="#Orders" role="tab" data-toggle="tab"><fmt:message
                        key="orders.header"/></a></li>
            </ul>
            <div class="tab-content">
                <div class="orderListHeight tab-pane active" id="Clients">
                    <div id="Clients-block">

                        <div class="above-table-row">
                            <ul id="changee" class="pagination above-table-row-content">
                                <li id="cBack"><a href="#page">&laquo;</a></li>

                                <c:forEach var="i" begin="1" end="${clientsPaginatedList.getPageCount()}">
                                    <li value="${i}" name="page${i}" class="cNumbered page"><a href="#page${i}">${i}</a>
                                    </li>
                                </c:forEach>

                                <li id="cNext"><a href="#page">&raquo;</a></li>
                            </ul>

                            <div class="above-table-row-content rows-count">
                                <t:rowsCount target="client"
                                             targetRowsCount="${clientsPaginatedList.getRowsPerPage()}"/>
                            </div>
                            <div class="clear"></div>
                        </div>

                        <div class="orderListHeight tab-pane" style="overflow-y: scroll">
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
                        </div>
                    </div>
                </div>

                <div class="orderListHeight tab-pane" id="Orders">
                    <div id="Orders-block">
                        <div class="above-table-row">
                            <ul id="change" class="pagination above-table-row-content">
                                <li id="oBack"><a href="#page">&laquo;</a></li>

                                <c:forEach var="i" begin="1" end="${ordersPaginatedList.getPageCount()}">
                                    <li value="${i}" name="page${i}" class="oNumbered page"><a href="#page${i}">${i}</a>
                                    </li>
                                </c:forEach>

                                <li id="oNext"><a href="#page">&raquo;</a></li>
                            </ul>

                            <div class="switcher above-table-row-content">
                                <select class="form-control" name="switchStatusOrder">
                                    <option value="waiting"><fmt:message key="message.waiting"/></option>
                                    <option value="active"><fmt:message key="message.active"/></option>
                                    <option value="canceled"><fmt:message key="message.canceled"/></option>
                                    <option value="executed"><fmt:message key="message.executed"/></option>
                                    <option value="all"><fmt:message key="message.allorders"/></option>
                                </select>
                            </div>

                            <div class="above-table-row-content rows-count">
                                <t:rowsCount target="order" targetRowsCount="${ordersPaginatedList.getRowsPerPage()}"/>
                            </div>
                            <div class="clear"></div>
                        </div>

                        <div class="orderListHeight tab-pane" style="overflow-y: scroll">
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
                        </div>
                    </div>

                    <div class="under-orders-table-row">
                        <input type="button" class="under-orders-table-row-content btn btn-primary"
                               value="<fmt:message key="button.cancel.order"/>" id="cancel">
                        <input type="button" class="under-orders-table-row-content btn btn-primary"
                               value="<fmt:message key="button.accept.order"/>" id="accept">
                        <input type="button" class="under-orders-table-row-content btn btn-primary"
                               value="<fmt:message key="button.restore.order"/>" id="restore">

                        <div class="under-orders-table-row-content" id="message"></div>
                    </div>
                </div>

                <div class="panel-group edit-panel" id="accordion">
                    <div class="panel panel-default">
                        <div class="panel-heading edit-panel-heading">
                            <h4 class="panel-title text-center">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                    <fmt:message key="message.edit"/>
                                </a>
                            </h4>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse">
                            <div class="panel-body">
                                <form id="editForm" method="post" onsubmit="return false;">

                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="clear"></div>
            </div>
        </div>
        <div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span
                                aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title text-center" id="confirmModalLabel"><fmt:message
                                key="message.save.confinm"/></h4>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal" form="editForm"
                                id="confirmSave">
                            <fmt:message key="message.yes"/></button>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message
                                key="message.no"/></button>
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

                    <form id="orderForm" onsubmit="return false;">
                        <div class="orderText form-group">
                            <div class="center"><label for="date"><fmt:message
                                    key="orders.message.order.date"/></label></div>
                            <input type="text" name="deliverydate" value="Date" class="form-control datepicker" id="date">
                        </div>
                        <div class="orderText form-group">
                            <div class="center"><label for="periodTime"><fmt:message
                                    key="orders.message.delivery.time"/></label></div>
                            <select class="form-control" name="deliverytime" value="Time" class="form-control" id="periodTime">
                                <c:forEach var="period" items="${periods}">
                                    <option>${period.period}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="orderText form-group">
                            <div class="center"><label for="goodsName"><fmt:message
                                    key="orders.message.goods.name"/></label></div>
                            <select class="form-control" name="goodsname" value="Goods name" class="form-control" id="goodsName">
                                <c:forEach var="goodss" items="${goods}">
                                    <option>${goodss.getGoodsName()}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="orderText form-group">
                            <div class="center"><label for="count"><fmt:message
                                    key="orders.message.goods.count"/></label>
                            </div>
                            <input type="text" name="goodscount" value="1" class="form-control" id="count">
                        </div>
                        <div class="orderInfo form-group">
                            <div class="center"><label for="additionalInformation"><fmt:message
                                    key="orders.message.additional.info"/></label></div>
                            <textarea name="additionalinformation" value="Count" class="form-control" id="additionalInformation">
                            </textarea>
                        </div>
                    </form>

                    <div class="center form-group">
                        <input type="hidden" name="PaymentType" value="cache" id="PaymentType">
                    </div>

                    <p class="message"></p>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="create"><fmt:message
                                key="button.create.order"/></button>
                    </div>
                </div>
            </div>
        </div>
    </t:gbody>

    <script src="<c:url value="/webjars/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"/>"></script>
    <script src="<c:url value="/script/dispatcher.js"/>"></script>
    <script src="<c:url value="/script/session-plugin.js"/>"></script>
    </html>
</fmt:bundle>


