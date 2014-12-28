<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="goods" type="java.util.List" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <input type="hidden" id="goodsPageNumber" value="${goods.getPageNumber()}"/>
    <c:forEach var="good" items="${goods}">
        <form class="panel panel-default goods-block" action="<c:url value="/do/addGoods"/>" method="post">
            <img class="goods-image" src="/image/${good.getImage().getFilename()}"/>

            <div class="goods-describe">
                    ${good.getGoodsName()}
            </div>
            <div class="goods-price">
                    ${good.getPrice()}
            </div>
            <input type="hidden" name="id" value="${good.getId()}">
            <button class="adding-button" <c:if test="${good.inCart}">disabled</c:if>>
                <c:choose>
                    <c:when test="${good.inCart}">
                        <fmt:message key="button.in.cart"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="button.add.goods"/>
                    </c:otherwise>
                </c:choose>
            </button>
        </form>
    </c:forEach>
</fmt:bundle>