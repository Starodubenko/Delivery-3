<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <html>
    <head>
        <title><fmt:message key="farm.name"/></title>
        <link rel='stylesheet' href='<c:url value="/webjars/bootstrap/3.2.0/css/bootstrap.min.css"/>'>
        <link rel='stylesheet' href='<c:url value="/style/error.css"/>'>
        <link href='http://fonts.googleapis.com/css?family=Lobster&subset=latin,cyrillic' rel='stylesheet'
              type='text/css'>
    </head>
    <t:gbody>
        <div class="panel panel-default error-block">
            <div class="code-error">
                <p>${statusCode}</p>
            </div>

            <div class="text-error">
                <p><fmt:message key="${statusCode}"/></p>
            </div>
        </div>
    </t:gbody>
    </html>
</fmt:bundle>