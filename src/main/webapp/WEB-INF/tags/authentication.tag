<%@tag description="authentication template" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>

<link rel='stylesheet' href='<c:url value="/style/authentication.css"/>'>

<fmt:bundle basename="i18n.messages">
    <div class="authentication">
        <c:if test="${not empty user}">
            <div id="autorized">
                <form action="<c:url value="/do/logout"/>">
                    <p align="center"><fmt:message key="authentication.welcome"/></p>

                    <p align="center" class="name_surname">${user.getFirstName()}</p>

                    <p align="center" class="name_surname">${user.getLastName()}</p>
                    <br>
                    <input type="submit" class="logoutbtn btn btn-primary"
                           value="<fmt:message key="button.logout"/>">
                </form>
            </div>
        </c:if>
        <c:if test="${empty user}">
            <div id="unAutorized">
                <form id="loginform" method="post">
                    <div class="center"><label for="inputLogin"><fmt:message key="authentication.login"/></label></div>
                    <input type="text" name="authenticationLogin" class="form-control" id="inputLogin" value="Elena">

                    <div class="center"><label for="inputPassword"><fmt:message key="authentication.password"/></label>
                    </div>
                    <input type="password" name="authenticationPassword" class="form-control" id="inputPassword"
                           value="EleEle">

                    <input type="button" class="logoutbtn btn btn-primary" value="<fmt:message key="button.login"/>" id="goLogin">
                    <br>
                    <a href="<c:url value="/do/registration"/>"><fmt:message key="button.registration"/></a>
                </form>
                <p class="name_surname errorRegistationLabel" id="errorLogin"></p>
            </div>
        </c:if>
    </div>

<script src="<c:url value="/webjars/jquery/1.11.1/jquery.min.js"/>"></script>
<script src="<c:url value="/script/authentication.js"/>"></script>
</fmt:bundle>