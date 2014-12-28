$(function () {
    $("li.gNumbered[value=" + 1 + "]").addClass("active");
});

$(document).ready(function () {
    $('#Goods-block').on('click', '#gBack', function () {
        var page = $('#goodsPageNumber').val() - 1;
        var rows = $('#goodsrows').val();

        $('.gNumbered').removeClass("active");
        $("li.gNumbered[value=" + page + "]").addClass("active");
        $.get("ajaxChangeGoodsPage",
            {
                goodspage: page,
                goodsrows: rows
            },
            function (data) {
                $('#Goods').html(data);
            })
    });

    $('#Goods-block').on('click', '.gNumbered', function () {
        $('.gNumbered').removeClass("active");
        $(this).addClass("active");

        var page = $(this).attr('value');
        var rows = $('#goodsrows').val();

        $.get("ajaxChangeGoodsPage",
            {
                goodspage: page,
                goodsrows: rows
            },
            function (data) {
                $('#Goods').html(data);
            })
    });


    $('#Goods-block').on('click', '#gNext', function () {
        var page = $('#goodsPageNumber').val() - 1 + 2;
        var rows = $('#goodsrows').val();

        $('.gNumbered').removeClass("active");
        $("li.gNumbered[value=" + page + "]").addClass("active");
        $.get("ajaxChangeGoodsPage",
            {
                goodspage: page,
                goodsrows: rows
            },
            function (data) {
                $('#Goods').html(data);
            })
    });

    $("#Goods").on('click', '.adding-button', function() {

        $(this).parents('form').submit();
        return false;

        //$(this).prop('disabled', true);
        //var id = $(this).attr("value");
        //
        //$.post("addGoods",
        //    {
        //        goodsId:id
        //    },
        //    function (data) {
        //        $(".adding-button[value="+id+"]").html(data);
        //    })
    })
});