$('#datepickerLog').datepicker({

});
$('#datepickerReport').datepicker({

});

$('#tableName').change(function () {
    var e = document.getElementById("tableName");
    var tableName = e.options[e.selectedIndex].value;
    console.log(tableName);

    $.get("changeTableAction", {tableName: tableName},
        function (data) {
            $('#Orders').html(data);
        });
});