<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tClient" tagdir="/WEB-INF/tags/client"%>

<fmt:bundle basename="i18n.messages">
    <tClient:clientsTable clientsPaginatedList="${clientsPaginatedList}"/>
</fmt:bundle>
