<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<link rel='stylesheet' href='<c:url value="/style/navigation.css"/>'>

<fmt:bundle basename="i18n.messages">
    <div class="background-navigation">
        <nav class="center">
            <ul class="fancyNav">
                <li id="home"><a href="welcome" class="homeIcon"><fmt:message key="navigation.home"/></a></li>
                <li id="news"><a href="#news"><fmt:message key="navigation.news"/></a></li>
                <li id="about"><a href="qwe.html"><fmt:message key="navigation.aboutus"/></a></li>
                <li id="services"><a href="#services"><fmt:message key="navigation.services"/></a></li>
                <li id="createOrder"><a href="createOrder"><fmt:message key="navigation.create.order"/></a></li>
                <li id="personalCabinet"><a href="personal-cabinet"><fmt:message key="navigation.personal.cabinet"/></a>
                </li>
            </ul>

            <form method="post" action="<c:url value="/do/changeLocale"/>">
                <select class="form-control language" id="switchLanguage" onchange="submit()" name="locale">
                        <%--<select class="form-control language" id="switchLanguage" name="locale">--%>
                    <option class="language-icon"
                            <c:if test="${locale == 'ru'}">selected</c:if> value="ru"><fmt:message key="navigation.rus"/></option>
                    <option class="language-icon"
                            <c:if test="${locale == 'en'}">selected</c:if> value="en"><fmt:message key="navigation.eng"/>
                    </option>
                </select>
            </form>
        </nav>
    </div>

    <script src="<c:url value="/webjars/jquery/1.11.1/jquery.min.js"/>"></script>
    <script src="<c:url value="/script/navigation.js"/>"></script>
</fmt:bundle>
