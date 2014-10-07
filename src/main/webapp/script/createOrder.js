//$('.datepicker').datepicker({
//    format: "dd.mm.yyyy",
////    daysOfWeekDisabled: "0,6",
////    todayHighlight: true,
//    startDate: '+0d'
//});

$('.datetimepicker').datetimepicker({
    pickDate: false
});

$('.timepicker').timepicker({
    minuteStep: 1,
    template: 'modal',
    appendWidgetTo: 'body',
    showSeconds: true,
    showMeridian: false,
    defaultTime: false
});

$('#create').click(function () {

    var deliverydate = $('#Date').val();
    var deliverytime = $('#PeriodTime').val();
    var goodsname = $('#GoodsName').val();
    var goodscount = $('#Count').val();
    var additionalinformation = $('#AdditionalInformation').val();
    var paymenttype = $(':radio[name="paymentType"]').val();

    alert(paymenttype);

    $.get("fastCreateOrder", {deliverydate: deliverydate, deliverytime: deliverytime,
            goodsname: goodsname, goodscount: goodscount, additionalinformation: additionalinformation, paymentType: paymenttype},
        function (data) {
            $('#errorCreatingOrder').html(data.errorMessage);
        });
});
