<div class="panel-body">
    <form id="changeForm" action="${pageContext.request.contextPath}/do/saveOrderData">
        <input type="hidden" name="id" value="${order.getId()}">

        <div>
            <div class="form-group edit">
                <div class="center"><label for="goods-name" class="edit-field">Goods name</label></div>
                <input type="text" class="form-control" name="goods-name" id="goods-name"
                       value="${order.getGoods().getGoodsName()}">
            </div>
            <div class="form-group edit">
                <div class="center"><label for="goods-count" class="edit-field">Goods count</label></div>
                <input type="text" class="form-control" name="goods-count" id="goods-count" value="${order.getCount()}">
            </div>
            <div class="form-group edit">
                <div class="center"><label for="delivery-date" class="edit-field">Delivery date</label></div>
                <input type="text" class="form-control" name="delivery-date" id="delivery-date"
                       value="${order.getDeliveryDate()}">
            </div>
            <div class="form-group edit">
                <div class="center"><label for="delivery-time" class="edit-field">Delivery time</label></div>
                <input type="text" class="form-control" name="delivery-time" id="delivery-time"
                       value="${order.getPeriod().getPeriod()}">
            </div>
            <div class="form-group  edit">
                <div class="center"><label for="additional-info" class="edit-field">Additional info</label></div>
                <textarea class="form-control" name="additional-info" id="additional-info">
                    ${order.getAdditionalInfo()}
                </textarea>
            </div>
        </div>
        <br><br><br><br><br>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#confirmModal">
            Save
        </button>
    </form>
</div>