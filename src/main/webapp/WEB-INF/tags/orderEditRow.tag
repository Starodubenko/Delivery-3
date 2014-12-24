<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <div class="tab-pane">
        <table class="table table-hover" id="editOrder">
            <tr>
                <th class="text-center"><fmt:message
                        key="orders.message.goods.name"/></th>
                <th class="text-center"><fmt:message
                        key="orders.message.goods.count"/></th>
                <th class="text-center"><fmt:message
                        key="orders.message.delivery.date"/></th>
                <th class="text-center"><fmt:message
                        key="orders.message.delivery.time"/></th>
                <th class="text-center"><fmt:message
                        key="orders.message.additional.info"/></th>
                <th class="text-center"><fmt:message key="message.action"/></th>
            </tr>
            <tr>
                <td>
                    <select class="form-control" name="goods-name" class="form-control">
                        <c:forEach var="goodss" items="${goods}">
                            <option
                                    <c:if test="${order.getGoods().getGoodsName() eq goodss.getGoodsName()}">selected</c:if>>${goodss.getGoodsName()}
                            </option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <div class="form-group">
                        <input type="text" name="goods-count" class="form-control" value="${order.getCount()}">
                    </div>
                </td>
                <td>
                    <div class="form-group">
                        <input type="text" name="delivery-date" class="form-control datepicker" value="${order.getDeliveryDate()}">
                    </div>
                </td>
                <td>
                    <select class="form-control text-center" name="delivery-time" class="form-control">
                        <c:forEach var="period" items="${periods}">
                            <option
                                    <c:if test="${order.getPeriod().getPeriod() eq period.period}">selected</c:if>>${period.period}
                            </option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <div class="form-group">
                        <input type="text" name="additional-info" class="form-control" value="${order.getAdditionalInfo()}">
                    </div>
                </td>
                <td>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#confirmModal">
                        <fmt:message key="button.save"/>
                    </button>
                </td>
            </tr>
        </table>
    </div>
    <h4 class="text-center" id="saveMessage">${saveMessage}</h4>
</fmt:bundle>