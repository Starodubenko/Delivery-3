<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div id="orders-block">
    <ul id="change" class="pagination">
        <li id="oBack"><a href="#page">&laquo;</a></li>

        <c:forEach var="i" begin="1" end="${ordersPaginatedList.getPageCount()}">
            <li value="${i}" name="page${i}"
                class="oNumbered page"><a href="#page${i}">${i}</a>
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
                    <p> Check all</p>

                    <div class="checkbox">
                        <label>
                            <input id="maincheck" type="checkbox">
                        </label>
                    </div>
                </th>
                <th>ID</th>
                <th>Order date</th>
                <th>Goods name</th>
                <th>Goods count</th>
                <th>Order cost</th>
                <th>Delivery date</th>
                <th>Delivery time</th>
                <th>Additional info</th>
                <th>Status</th>
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