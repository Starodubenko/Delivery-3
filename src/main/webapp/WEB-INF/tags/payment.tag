<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel='stylesheet' href='<c:url value="/style/payment.css"/>'>

<fmt:bundle basename="i18n.messages">
    <div class="eWalet panel panel-default">
        <label class="balance"> <fmt:message key="payment.message.your.balance"/>: </label>
        <label> ${user.getVirtualBalance()} </label>
        <br>
        <button class="logoutbtn btn btn-primary" data-toggle="modal" data-target="#Payment">
            <fmt:message key="payment.button.topup"/>
        </button>
    </div>

    <div class="modal fade" id="Payment" tabindex="-1" role="dialog" aria-labelledby="PaymentLabel"
         aria-hidden="true">
        <form action="${pageContext.request.contextPath}/do/payment" method="post">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span
                                aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title" id="PaymentLabel"><fmt:message key="payment.message.header"/></h4>
                    </div>
                    <div class="paymentform">
                        <div class="form-group">
                            <div class="center">
                                <p for="SecretCode"><fmt:message key="payment.message.explanation"/></p>
                            </div>
                            <div class="center">
                                <input type="text" name="SecretCode"
                                       placeholder="<fmt:message key="payment.message.input"/>"
                                       class=" center input-field form-control" id="SecretCode">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary"><fmt:message key="payment.button.pay"/></button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</fmt:bundle>
