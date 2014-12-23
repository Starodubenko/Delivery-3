<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <link rel='stylesheet' href='<c:url value="/style/welcome.css"/>'>
    <link rel="stylesheet" href="<c:url value="/style/registration.css"/> ">
    <head>
        <title><fmt:message key="farm.name"/></title>
    </head>
    <t:gbody>
        <div class="regex-describe panel panel-default">
            <dl class="dl-horizontal">
                <dt><fmt:message key="clients.message.login"/>: </dt>
                <dd><fmt:message key="message.login.describe"/></dd>
                <dt><fmt:message key="clients.message.password"/>: </dt>
                <dd><fmt:message key="message.password.describe"/></dd>
                <dt><fmt:message key="clients.message.last.name"/>: </dt>
                <dd><fmt:message key="message.lastname.describe"/></dd>
                <dt><fmt:message key="clients.message.first.name"/>: </dt>
                <dd><fmt:message key="message.firstname.describe"/></dd>
                <dt><fmt:message key="clients.message.middle.name"/>: </dt>
                <dd><fmt:message key="message.middlename.describe"/></dd>
                <dt><fmt:message key="clients.message.address"/>: </dt>
                <dd><fmt:message key="message.address.describe"/></dd>
                <dt><fmt:message key="clients.message.telephone"/>: </dt>
                <dd><fmt:message key="message.telephone.describe"/></dd>
                <dt><fmt:message key="clients.message.mobilephone"/>: </dt>
                <dd><fmt:message key="message.mobilephone.describe"/></dd>
            </dl>
        </div>
        <div class="border">
            <form id="regForm" class="registration" method="post" onsubmit="return false;">
                <div class="form-group text-center" id="loginDiv">
                    <label for="Login"><fmt:message key="clients.message.login"/></label>
                    <input type="text" name="login" value="Ivanov99" class="form-control" id="Login">

                    <p for="Login" class="text-center errorRegistationLabel" id="loginInput"></p>
                </div>
                <div class="form-group text-center" id="passwordDiv">
                    <label for="Password"><fmt:message key="clients.message.password"/></label>
                    <input type="text" name="password" value="Ivanov9" class="form-control" id="Password">

                    <p for="Login" class="text-center errorRegistationLabel" id="passwordInput"></p>
                </div>
                <div class="form-group text-center" id="lastnameDiv">
                    <label for="Lastname"><fmt:message key="clients.message.last.name"/></label>
                    <input type="text" name="lastname" value="Ivanov" class="form-control" id="Lastname">

                    <p for="Login" class="text-center errorRegistationLabel" id="lastnameInput"></p>
                </div>
                <div class="form-group text-center" id="firstnameDiv">
                    <label for="Firstname"><fmt:message key="clients.message.first.name"/></label>
                    <input type="text" name="firstname" value="Ivan" class="form-control" id="Firstname">

                    <p for="Login" class="text-center errorRegistationLabel" id="firstnameInput"></p>
                </div>
                <div class="form-group text-center" id="middlenameDiv">
                    <label for="Middlename"><fmt:message key="clients.message.middle.name"/></label>
                    <input type="text" name="middlename" value="Ivanovich" class="form-control" id="Middlename">

                    <p for="Login" class="text-center errorRegistationLabel" id="middlenameInput"></p>
                </div>
                <div class="form-group text-center" id="addressDiv">
                    <label for="Address"><fmt:message key="clients.message.address"/></label>
                    <input type="text" name="address" value="Ivanova-32" class="form-control" id="Address">

                    <p for="Login" class="text-center errorRegistationLabel" id="addressInput"></p>
                </div>
                <div class="form-group text-center" id="telephoneDiv">
                    <label for="Telephone"><fmt:message key="clients.message.telephone"/></label>
                    <input type="text" name="telephone" value="87212965896" class="form-control" id="Telephone">

                    <p for="Login" class="text-center errorRegistationLabel" id="telephoneInput"></p>
                </div>
                <div class="form-group text-center" id="mobilephoneDiv">
                    <label for="Mobilephone"><fmt:message key="clients.message.mobilephone"/></label>
                    <input type="text" name="mobilephone" value="87007778958" class="form-control"
                           id="Mobilephone">

                    <p for="Login" class="text-center errorRegistationLabel" id="mobilephoneInput"></p>
                </div>
            </form>
            <p class="text-center errorRegistationLabel" id="registrationSuccessful"></p>
            <button type="button" class="btn btn-primary" id="goRegistration"><fmt:message
                    key="message.sing.up"/>
            </button>
            <div id="message">

            </div>
        </div>
        <script src="<c:url value="/script/registration.js"/>"></script>
    </t:gbody>
</fmt:bundle>