<%@tag description="StatusBar template" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="pageName"%>
<%@ attribute name="target"%>
<%@ attribute name="targetRowsCount"%>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <link rel="stylesheet" href="<c:url value="/style/rowsCount.css"/>">

    <div class="form-group rows-count-block">
        <label class="labelCount" for="${target}rows"><fmt:message key="message.rows.count"/></label>
        <form class="rows-count-form" action="${pageContext.request.contextPath}/do/${pageName}">
            <div class="input-group">
                <input type="text" name="${target}rows" id="${target}rows" value="${targetRowsCount}" class="form-control count-field">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="submit"><fmt:message key="button.apply"/></button>
                        </span>
            </div>
        </form>
    </div>
</fmt:bundle>