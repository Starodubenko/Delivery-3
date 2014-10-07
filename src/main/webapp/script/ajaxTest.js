$(document).ready(function(){
    $('.page').click(function () {
        alert('ok');
        $('#ordersTable').load('ajaxTable');
    });
});