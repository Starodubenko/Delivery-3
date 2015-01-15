$('.datepicker').datepicker({
    format: "dd.mm.yyyy",
//    daysOfWeekDisabled: "0,6",
//    todayHighlight: true,
    startDate: '+0d'
});

$('#create').click(function () {

    $.get("createFullOrder?"+$("#createForm").serialize(),
        function (data) {
            $('.final-message').html(data);
        });
});

$('.param').change(function () {

    var goodsname = $('.goodsname').val();
    var goodscount = $('.goodscount').val();

    $.get("calculate-order-cost",
        {
            goodsname: goodsname,
            goodscount: goodscount
        },
        function (data) {
            $('.order-cost').html(data.cost);
        });
})
;
