$(document).ready(function () {

    $('.goods-list').on('change', '.GoodsCount', function () {

        var currentRowForm = $(this).parents(".good").serialize();

        $.get("set-goods-count?"+ currentRowForm,
            function (data) {
                $(".GoodsCount").parents(".good").find(".goods-price#"+data.id+"").html(data.cost);
                $("#total").html(data.total);
            });
    });

    $('.goods-list').on('click', '.del', function () {
        $(this).parents('form').submit();
        return false;
        //var currentRowForm = $(this).parents(".good").serialize();
        //
        //$.get("delete-goods?"+ currentRowForm,
        //    function (data) {
        //        $(".goods-list").html(data);
        //    });
    });

    $('.goods-list').on('click', '.button-continue-order', function(){
        location.href = "completion-order";
    });
});