$('.datepicker').datepicker({
    format: 'mm.dd.yyyy',
    startDate: '-3d'
});

$('#datetimepicker').datetimepicker({
    pickDate: false
});

//change class the clicked row (active or warning)
$(document).ready(function () {
    $('#Today').children('input[type="checkbox"]').mousedown(function () {
        //ind = $(this).parents('tr').get(0).rowIndex;
        $("tr:eq(" + ind + ")").addClass('bg-success');
    }).mouseup(function () {
        $("tr:eq(" + ind + ")").removeClass('bg-success');
        $("tr:eq(" + ind + ")").toggleClass('bg-info');
    });
});

$('#browse-orders').click(function () {

    if (!$('#collapseOne').hasClass('in')) {
        $.get("browseOrders",
            function (data) {
                $('.orderList').html(data);
            });
    }
});

$('form input[type=file]').change(function () {
    var form = document.getElementById('image');
    var formData = new FormData(form);

    formData.append("image", $("#image")[0].files[0]);

    $.ajax({
            url: "ajaxShowCurrentAvatar",
            type: "POST",
            data: formData,
            mimeType: "multipart/form-data",
            contentType: false,
            cache: false,
            processData: false,
            success: function (data) {
                $('.image-block').html(data);
            }
        });
});

$('').on('click','',function(){


});

