$('.datepicker').datepicker({
    format: "dd.mm.yyyy",
//    daysOfWeekDisabled: "0,6",
//    todayHighlight: true,
    startDate: '+0d'
});

$('#create').click(function () {

    $.get("fastCreateOrder", $("#createForm").serialize(),
        function (data) {
            $('.final-message').html(data);
        });
});
