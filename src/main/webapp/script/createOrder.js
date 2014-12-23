$('.datepicker').datepicker({
    format: "dd.mm.yyyy",
//    daysOfWeekDisabled: "0,6",
//    todayHighlight: true,
    startDate: '+0d'
});

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

    $.get("fastCreateOrder", $("#createForm").serialize(),
        function (data) {
            $('.final-message').html(data);
        });
});
