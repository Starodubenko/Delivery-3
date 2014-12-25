<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tClient" tagdir="/WEB-INF/tags/client" %>
<%@ taglib prefix="tOrder" tagdir="/WEB-INF/tags/order" %>

<c:if test="${entityName == 'Clients'}">
    <tClient:clientSearchRow/>
</c:if>
<c:if test="${entityName == 'Orders'}">
    <tOrder:orderSearchRow/>
</c:if>
