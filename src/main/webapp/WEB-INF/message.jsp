<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <%--<p><fmt:message key="message.registration.successful"/> </p>--%>

    <p><fmt:message key="message.${message}"/></p>
</fmt:bundle>
