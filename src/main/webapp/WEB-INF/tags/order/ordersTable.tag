<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tOrder" tagdir="/WEB-INF/tags/order" %>
<%@ attribute name="ordersPaginatedList" type="com.epam.star.dao.util.PaginatedList" %>
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
                <th><fmt:message key="orders.message.paid"/></th>
                <th><fmt:message key="orders.message.delivery.date"/></th>
                <th><fmt:message key="orders.message.delivery.time"/></th>
                <th><fmt:message key="orders.message.additional.info"/></th>
                <th><fmt:message key="orders.message.status"/></th>
            </tr>
            <tOrder:orderRow orders="${ordersPaginatedList}"/>
        </table>
    </form>
</fmt:bundle>