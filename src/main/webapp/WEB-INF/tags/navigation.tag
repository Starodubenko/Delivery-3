<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

            <select class="form-control language" id="switchLanguage">
                <option>en</option>
                <option>ru</option>
            </select>
        </nav>
    </div>
</fmt:bundle>
