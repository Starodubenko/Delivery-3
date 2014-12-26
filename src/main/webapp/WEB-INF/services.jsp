<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="tGoods" tagdir="/WEB-INF/tags/goods" %>

<html>
<t:gHead>
    <link rel="stylesheet" href="<c:url value="/style/services.css"/>">
</t:gHead>
<t:gbody>
    <h1 class="services-header">Товары</h1>

    <div class="services-content">
        Выберите товар, который после обработки поступит курьеру для последующей доставки.
    </div>

    <form action="<c:url value="/do/addingImage"/>" method="post" enctype="multipart/form-data">
        <input type="file" name="image">
        <button type="submit">OK</button>
    </form>

    <div class="panel panel-default goods">
        <tGoods:goodsShowcase goodsPaginatedList="${goodsPaginatedList}"/>
    </div>
</t:gbody>
<script src="<c:url value="/script/services.js"/>"></script>
</html>
