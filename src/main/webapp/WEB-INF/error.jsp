<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <title><fmt:message key="farm.name"/></title>
    <link rel='stylesheet' href='<c:url value="/webjars/bootstrap/3.2.0/css/bootstrap.min.css"/>'>
    <link rel='stylesheet' href='<c:url value="/style/welcome.css"/>'>
    <link rel='stylesheet' href='<c:url value="/style/navigation.css"/>'>
</head>
<body>
<t:navigation></t:navigation>
<p>Status code ${statusCode}</p>

<p>Message ${message}</p>
<t:footer></t:footer>
</body>
</html>
