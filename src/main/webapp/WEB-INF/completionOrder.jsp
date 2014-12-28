<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <html>
    <t:gHead>
        <link rel='stylesheet' href='<c:url value="/webjars/bootstrap-datepicker/1.3.0/css/datepicker3.css"/>'>
        <link rel='stylesheet' href='<c:url value="/style/createOrder.css"/>'>
    </t:gHead>
    <t:gbody>
        <div class="client-info">
            <t:authentication/>

            <t:payment/>
        </div>

        <c:if test="${not empty user}">
            <form class="panel panel-default create-order-panel" id="createForm" onsubmit="return false;">
                <div class="center"><h1><fmt:message key="order.checkout.message.header"/></h1></div>
                <div>
                    <div class="orderText form-group">
                        <div class="center"><label for="Date"><fmt:message key="orders.message.delivery.date"/></label>
                        </div>
                        <input type="text" name="deliverydate" placeholder="Date" class="form-control datepicker" id="Date">
                    </div>
                    <div class="orderText form-group">
                        <div class="center"><label for="PeriodTime"><fmt:message
                                key="orders.message.delivery.time"/></label></div>
                        <select class="form-control" name="deliverytime" value="Time" class="form-control"
                                id="PeriodTime">
                            <c:forEach var="period" items="${periods}">
                                <option>${period.period}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="orderInfo form-group">
                        <div class="center"><label for="Additional Information"><fmt:message
                                key="orders.message.additional.info"/></label></div>
                        <textarea name="additionalinformation" value="Count" class="form-control"
                                  id="Additional Information">
                        </textarea>
                    </div>
                </div>
                <div class="total-sum">
                    <label>
                        <fmt:message key="message.orders.sum"/>: ${shoppingCart.getTotalSum()}
                    </label>
                </div>
                <div class="payment-option form-group">
                    <div class="radio">
                        <label>
                            <input type="radio" name="paymentType" value="online" checked>
                            <fmt:message key="message.online"/>
                        </label>
                    </div>
                    <div class="radio">
                        <label>
                            <input type="radio" name="paymentType" value="cache">
                            <fmt:message key="message.cache"/>
                        </label>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="create"><fmt:message
                            key="button.create.order"/></button>
                </div>
            </form>
        </c:if>

        <div class="final-message text-center">

        </div>
        <div class="clear"></div>
    </t:gbody>

    <script src="<c:url value="/webjars/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"/>"></script>
    <script src="<c:url value="/script/createOrder.js"/>"></script>
    </html>
</fmt:bundle>
