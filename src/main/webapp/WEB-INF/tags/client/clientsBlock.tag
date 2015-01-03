<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="tClient" tagdir="/WEB-INF/tags/client" %>
<%@ attribute name="clientsPaginatedList" type="com.epam.star.dao.util.PaginatedList" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <div id="Clients-block">
        <div class="above-table-row">
            <ul id="changee" class="pagination above-table-row-content">
                <li id="cBack"><a href="#page">&laquo;</a></li>

                <c:forEach var="i" begin="1" end="${clientsPaginatedList.getPageCount()}">
                    <li value="${i}" name="page${i}" class="cNumbered page"><a href="#page${i}">${i}</a>
                    </li>
                </c:forEach>

                <li id="cNext"><a href="#page">&raquo;</a></li>
            </ul>

            <div class="above-table-row-content rows-count">
                <t:rowsCount pageName="dispatcher" target="clients"
                             targetRowsCount="${clientsPaginatedList.getRowsPerPage()}"/>
            </div>
            <div class="clear"></div>
        </div>

        <div class="orderListHeight tab-pane" style="overflow-y: scroll">
            <c:choose>
                <c:when test="${clientsPaginatedList.getTotalRowsCount() > 0}">
                    <tClient:clientsTable clientsPaginatedList="${clientsPaginatedList}"/>
                </c:when>
                <c:otherwise>
                    <t:recordsNotFound/>
                </c:otherwise>
            </c:choose>
        </div>

        <script>
            $(function () {
                $("li.cNumbered[value=" + 1 + "]").addClass("active");
            });
        </script>
    </div>
</fmt:bundle>