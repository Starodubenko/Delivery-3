<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="tClients" tagdir="/WEB-INF/tags/client" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <tClients:clientsBlock clientsPaginatedList="${clientsPaginatedList}"/>
</fmt:bundle>
