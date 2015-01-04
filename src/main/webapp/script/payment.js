$('#Payment').on('click', '#Pay', function () {

    $.post("payment",$('#payment-form').serialize(),
        function (data) {
            $('.payment-message').html(data);
        })
});