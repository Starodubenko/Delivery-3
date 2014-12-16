<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:bundle basename="i18n.messages">
    <html>

    <head>
        <title><fmt:message key="farm.name"/></title>
        <link rel='stylesheet' href='<c:url value="/webjars/bootstrap/3.2.0/css/bootstrap.min.css"/>'>
        <link rel='stylesheet' href='<c:url value="/style/welcome.css"/>'>
        <link rel='stylesheet' href='<c:url value="/style/navigation.css"/>'>
    </head>
    <body background="<c:url value="/style/img/background.jpg"/>">

    <t:navigation>

    </t:navigation>

    <div class="main panel panel-default">
        <t:authentication></t:authentication>

        <div class="banner panel panel-default">
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                </ol>

                <div class="carousel-inner">
                    <div class="item active">
                        <img src="<c:url value="/style/img/water/water.jpg"/>" alt="...">

                        <div class="carousel-caption">
                        </div>
                    </div>
                    <div class="item">
                        <img src="<c:url value="/style/img/water/water1.jpg"/>" alt="...">

                        <div class="carousel-caption">
                        </div>
                    </div>
                    <div class="item">
                        <img src="<c:url value="/style/img/water/water2.jpg"/>" alt="...">

                        <div class="carousel-caption">
                        </div>
                    </div>
                </div>

                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                </a>
            </div>
        </div>

            <%--<div class="contactInformation panel panel-default" style="overflow-y: scroll">--%>
            <%--<c:forEach var="contact" items="${contacts}">--%>
            <%--<label>${contact.owner}: ${contact.telephone}</label>--%>
            <%--</c:forEach>--%>
            <%--</div>--%>

        <form action="<c:url value="/do/addingImage"/>" method="post" enctype="multipart/form-data">
            <input type="file" name="image">
            <button type="submit">OK</button>
        </form>


        <div class="clear"></div>

    </div>

    <t:footer/>

    <div class="modal fade" id="RegistrationForm" tabindex="-1" role="dialog" aria-labelledby="RegistrationFormLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span
                            aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="RegistrationFormLabel">Registration form</h4>
                </div>
                <div class="registration">
                    <form id="regForm">
                        <div class="form-group" id="loginDiv">
                            <label for="Login" class="name_surname">Login</label>
                            <input type="text" name="login" value="Ivanov99" class="form-control" id="Login">

                            <p for="Login" class="name_surname errorRegistationLabel" id="loginInput"></p>
                        </div>
                        <div class="form-group" id="passwordDiv">
                            <label for="Password" class="name_surname">Password</label>
                            <input type="text" name="password" value="Ivanov9" class="form-control" id="Password">

                            <p for="Login" class="name_surname errorRegistationLabel" id="passwordInput"></p>
                        </div>
                        <div class="form-group" id="firstnameDiv">
                            <label for="Firstname" class="name_surname">Firsname</label>
                            <input type="text" name="firstname" value="Ivan" class="form-control" id="Firstname">

                            <p for="Login" class="name_surname errorRegistationLabel" id="firstnameInput"></p>
                        </div>
                        <div class="form-group" id="lastnameDiv">
                            <label for="Lastname" class="name_surname">Lastname</label>
                            <input type="text" name="lastname" value="Ivanov" class="form-control" id="Lastname">

                            <p for="Login" class="name_surname errorRegistationLabel" id="lastnameInput"></p>
                        </div>
                        <div class="form-group" id="middlenameDiv">
                            <label for="Middlename" class="name_surname">Middlename</label>
                            <input type="text" name="middlename" value="Ivanovich" class="form-control" id="Middlename">

                            <p for="Login" class="name_surname errorRegistationLabel" id="middlenameInput"></p>
                        </div>
                        <div class="form-group" id="addressDiv">
                            <label for="Address" class="name_surname">Address</label>
                            <input type="text" name="address" value="Ivanova-32" class="form-control" id="Address">

                            <p for="Login" class="name_surname errorRegistationLabel" id="addressInput"></p>
                        </div>
                        <div class="form-group" id="telephoneDiv">
                            <label for="Telephone" class="name_surname">Telephone</label>
                            <input type="text" name="telephone" value="87212965896" class="form-control" id="Telephone">

                            <p for="Login" class="name_surname errorRegistationLabel" id="telephoneInput"></p>
                        </div>
                        <div class="form-group" id="mobilephoneDiv">
                            <label for="Mobilephone" class="name_surname">Mobilephone</label>
                            <input type="text" name="mobilephone" value="87007778958" class="form-control"
                                   id="Mobilephone">

                            <p for="Login" class="name_surname errorRegistationLabel" id="mobilephoneInput"></p>
                        </div>

                            <%--<p class="name_surname errorRegistationLabel" id="registrationSuccessful"></p>--%>
                    </form>
                </div>
                <div class="modal-footer reg">
                    <p class="name_surname errorRegistationLabel" id="registrationSuccessful"></p>
                    <button class="btn btn-default" data-dismiss="modal">Close registration form</button>
                    <button type="submit" form="regForm" class="btn btn-primary" id="goRegistration">Registration
                    </button>
                </div>

            </div>
        </div>
    </div>

    <script src="<c:url value="/webjars/jquery/1.11.1/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/3.2.0/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/script/welcome.js"/>"></script>
    </body>
    </html>
</fmt:bundle>
