$(function () {
    $("li.cNumbered[value=" + 1 + "]").addClass("active");
    $("li.oNumbered[value=" + 1 + "]").addClass("active");
    backNext(1,'c');
    backNext(1,'o');
});

function backNext (page, target) {
    if (page < $('.'+target+'Numbered').size() && page > 1){
        $('#'+target+'Back').removeClass("disabledd");
        $('#'+target+'Next').removeClass("disabledd");
    } else
    if (page < 2){
        $('#'+target+'Back').addClass("disabledd");
        $('#'+target+'Next').removeClass("disabledd");
    } else
    if (page > $('.'+target+'Numbered').size()-1){
        $('#'+target+'Next').addClass("disabledd");
        $('#'+target+'Back').removeClass("disabledd");
    }
}

$(document).ready(function () {

    $('#search-form').on('click', '#search-button', function () {
        var entityName = $('#entityName').val();
        var orderStatus = $('#switchStatusOrder').val();

        $.post("find" + entityName, $('#search-form').serialize() + "&"
                                  + $(".rowsCountForm").serialize()
                                  + "&orderStatus="+orderStatus,
            function (data) {
                $("#" + entityName + "s-block").html(data);
            })
    });

    $('#search-form').keydown(function () {
        var entityName = $('#entityName').val();
        var orderStatus = $('#switchStatusOrder').val();

        if (event.keyCode == 13) {
            $.post("find" + entityName, $('#search-form').serialize() + "&"
                                      + $(".rowsCountForm").serialize()
                                      + "&orderStatus="+orderStatus,
                function (data) {
                    $("#" + entityName + "s-block").html(data);
                })
        }
    });

    $('#Clients').on('click', '#cBack', function () {
        var page = $('#clientsPageNumber').val() - 1;
        var rows = $('#clientsrows').val();

        backNext(page,'c');

        $('.cNumbered').removeClass("active");
        $("li.cNumbered[value=" + page + "]").addClass("active");
        $.get("ajaxChangeClientsPage",
            {
                clientspage: page,
                clientsrows: rows
            },
            function (data) {
                $('#clientsTable').html(data);
            })
    });

    $('#Clients').on('click', '.cNumbered', function () {
        $('.cNumbered').removeClass("active");
        $(this).addClass("active");

        var page = $(this).attr('value');
        var rows = $('#clientsrows').val();

        backNext(page,'c');

        $.get("ajaxChangeClientsPage",
            {
                clientspage: page,
                clientsrows: rows
            },
            function (data) {
                $('#clientsTable').html(data);
            })
    });


    $('#Clients').on('click', '#cNext', function () {
        var page = $('#clientsPageNumber').val() - 1 + 2;
        var rows = $('#clientsrows').val();

        backNext(page,'c');

        $('.cNumbered').removeClass("active");
        $("li.cNumbered[value=" + page + "]").addClass("active");
        $.get("ajaxChangeClientsPage",
            {
                clientspage: page,
                clientsrows: rows
            },
            function (data) {
                $('#clientsTable').html(data);
            })
    });

    $('#Orders').on('click', '#oBack', function () {
        var page = $('#ordersPageNumber').val() - 1;
        var rows = $('#ordersrows').val();

        backNext(page,'o');

        $('.oNumbered').removeClass("active");
        $("li.oNumbered[value=" + page + "]").addClass("active");
        $.get("ajaxChangeOrdersPage",
            {
                orderspage: page,
                ordersrows: rows
            },
            function (data) {
                $('#ordersTable').html(data);
            })
    });

    $('#Orders').on('click', '.oNumbered', function () {
        $('.oNumbered').removeClass("active");
        $(this).addClass("active");

        var page = $(this).attr('value');
        var rows = $('#ordersrows').val();

        backNext(page,'o');

        $.get("ajaxChangeOrdersPage",
            {
                orderspage: page,
                ordersrows: rows
            },
            function (data) {
                $('#ordersTable').html(data);
            })
    });

    $('#Orders').on('click', '#oNext', function () {
        var page = $('#ordersPageNumber').val() - 1 + 2;
        var rows = $('#ordersrows').val();

        backNext(page,'o');

        $('.oNumbered').removeClass("active");
        $("li.oNumbered[value=" + page + "]").addClass("active");
        $.get("ajaxChangeOrdersPage",
            {
                orderspage: page,
                ordersrows: rows
            },
            function (data) {
                $('#ordersTable').html(data);
            })
    });

    $("#maincheck").click(function () {
        if ($('#maincheck').prop("checked")) {
            $('.mc').attr('checked', true);
        } else {
            $('.mc').attr('checked', false);
        }
    });

    var ID;
    $('table#clientsTable').on('click', 'button', function () {
        var rowIndex = $(this).parents('tr').get(0).rowIndex;
        ID = $("table:eq(0) tr:eq(" + rowIndex + ") td:eq(0)").html();
    });


    $('#create').click(function () {

        $.get("fastCreateOrder?id="+ID+"&"+$('#orderForm').serialize(),
            function (data) {
                $('.message').html(data);
            });
    });

    $('#cancel').click(function () {

        $.get("orderOperation?operation=cancel&ajax=ajax&"+$("#ordersForm").serialize(),
            function (data) {
                $('#message').html(data);
            });
    });

    $('#accept').click(function () {

        $.get("orderOperation?operation=accept&ajax=ajax&"+$("#ordersForm").serialize(),
            function (data) {
                $('#message').html(data);
            });
    });

    $('#restore').click(function () {

        $.get("orderOperation?operation=restore&ajax=ajax&"+$("#ordersForm").serialize(),
            function (data) {
                $('#message').html(data);
            });
    });

//    $('#switchStatusOrser').change(function () {
//        var e = document.getElementById("switchStatusOrser");
//        var status = e.options[e.selectedIndex].value;
//        console.log(status);
//
//        $.get("selectStatus", {status: status},
//            function (data) {
//                $('#Orders').html(data);
//            });
//    });

    $('#Orders-block').on('click', 'tr', function () {
        $('tr').removeClass('info');
        $(this).addClass('info');

        ID = $(this).children().eq(1).text();
        $.get("SetOrderToEditFields", {id: ID},
            function (data) {
                $('#editForm').html(data);
            });
    });

    $('#Clients-block').on('click', 'tr', function () {
        $('tr').removeClass('info');
        $(this).addClass('info');

        ID = $(this).children().eq(0).text();
        $.get("SetClientToEditFields", {id: ID},
            function (data) {
                $('#editForm').html(data);
            });
    });

    $('li.table').click(function () {
        var tab = $(this).attr('value');
        $.get('checkTable' + '?' + "entityName=" + tab,
            function (data) {
                $('#table-name').html(data);
            })
    });

    $('#confirmModal').on('click', '#confirmSave', function () {

        var table = $('#entityName').val();

        $.post("save" + table + "Data", $('#editForm').serialize(),
            function (data) {
                $('#saveMessage').html(data.errorMessage);
            })
    });

    $(function () {
        $('body').on('click', '.datepicker', function () {
            $(this).datepicker(
                {
                    format: "dd.mm.yyyy",
                    startDate: '+0d'
                }).focus();
        });
    });
});

