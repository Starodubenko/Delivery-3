<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<link rel='stylesheet' href='<c:url value="/style/footer.css"/>'>

<div class="footer panel panel-default">
    <div class="center footer-block panel panel-default">
        <div class="contacts panel panel-default">
            <c:forEach var="contact" items="${contacts}">
                <label>${contact.owner}: ${contact.telephone}</label>
            </c:forEach>
        </div>

        <div class="center"><h1></h1></div>
    </div>
</div>