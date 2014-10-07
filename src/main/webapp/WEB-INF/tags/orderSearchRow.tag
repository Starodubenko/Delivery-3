<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="i18n.messages">
    <form id="findForm">
        <div id="searcheFields">
            <div class="form-group edit">
                <div class="center"><label for="order-id" class="edit-field"><fmt:message
                        key="view.dispatcher.table.orders.message.id"/></label>
                </div>
                <input type="text" class="form-control" name="order-id" id="order-id"
                       value="39">
            </div>
            <div class="form-group edit">
                <div class="center"><label for="order-date" class="edit-field"><fmt:message
                        key="view.dispatcher.table.orders.message.order.date"/></label>
                </div>
                <input type="text" class="form-control searchDate" name="order-date"
                       id="order-date"
                       value="2014-09-14">
            </div>
            <div class="form-group edit">
                <div class="center"><label for="order-goods-name"
                                           class="edit-field"><fmt:message
                        key="view.dispatcher.table.orders.message.goods.name"/></label></div>
                <input type="text" class="form-control" name="order-goods-name"
                       id="order-goods-name"
                       value="Water 20L">
            </div>
            <div class="form-group edit">
                <div class="center"><label for="delivery-time" class="edit-field"><fmt:message
                        key="view.dispatcher.table.orders.message.goods.count"/></label></div>
                <input type="text" class="form-control" name="order-goods-count"
                       id="order-goods-count"
                       value="">
            </div>
            <div class="form-group edit">
                <div class="center"><label for="delivery-time" class="edit-field"><fmt:message
                        key="view.dispatcher.table.orders.message.order.cost"/></label></div>
                <input type="text" class="form-control" name="order-cost" id="order-cost"
                       value="">
            </div>
            <div class="form-group edit">
                <div class="center"><label for="delivery-time" class="edit-field"><fmt:message
                        key="view.dispatcher.table.orders.message.delivery.date"/></label></div>
                <input type="text" class="form-control searchDate" name="delivery-date"
                       id="delivery-date"
                       value="">
            </div>
            <div class="form-group edit">
                <div class="center"><label for="delivery-time" class="edit-field"><fmt:message
                        key="view.dispatcher.table.orders.message.delivery.time"/></label></div>
                <input type="text" class="form-control" name="delivery-time"
                       id="delivery-time"
                       value="">
            </div>
            <div class="form-group edit">
                <div class="center"><label for="delivery-time" class="edit-field"><fmt:message
                        key="view.dispatcher.table.orders.message.additional.info"/></label></div>
                <input type="text" class="form-control" name="order-addInfo"
                       id="order-addInfo"
                       value="">
            </div>
            <div class="form-group edit">
                <div class="center"><label for="delivery-time"
                                           class="edit-field"><fmt:message
                        key="view.dispatcher.table.orders.message.status"/></label></div>
                <input type="text" class="form-control" name="order-status"
                       id="order-status"
                       value="">
            </div>
            <br>
            <input type="hidden" id="entityName" value="Order">
            <input type="button" class="btn btn-primary" id="search" value="<fmt:message key="view.search"/>">
        </div>
    </form>
</fmt:bundle>