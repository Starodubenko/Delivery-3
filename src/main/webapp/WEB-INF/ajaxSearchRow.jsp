<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<c:if test="${entityName == 'Clients'}">
    <t:clientSearchRow>

    </t:clientSearchRow>
</c:if>
<c:if test="${entityName == 'Orders'}">
    <t:orderSearchRow>

    </t:orderSearchRow>
</c:if>
