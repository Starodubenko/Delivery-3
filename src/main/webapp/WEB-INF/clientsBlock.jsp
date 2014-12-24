<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <c:choose>
        <c:when test="${clientsPaginatedList.getTotalRowsCount() > 0}">
            <div id="clients-block">
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
                        <t:rowsCount target="client"
                                     targetRowsCount="${clientsPaginatedList.getRowsPerPage()}"/>
                    </div>
                    <div class="clear"></div>
                </div>

                <div class="orderListHeight tab-pane" style="overflow-y: scroll">
                    <table class="table table-hover" ID="clientsTable">
                        <input type="hidden" id="clientsPageNumber" value="${clientsPaginatedList.getPageNumber()}"/>
                        <tr>
                            <th class="text-center"><fmt:message key="clients.message.ID"/></th>
                            <th class="text-center"><fmt:message key="clients.message.last.name"/></th>
                            <th class="text-center"><fmt:message key="clients.message.first.name"/></th>
                            <th class="text-center"><fmt:message key="clients.message.middle.name"/></th>
                            <th class="text-center"><fmt:message key="clients.message.address"/></th>
                            <th class="text-center"><fmt:message key="clients.message.telephone"/></th>
                            <th class="text-center"><fmt:message key="clients.message.mobilephone"/></th>
                            <th class="text-center"><fmt:message key="message.action"/></th>
                        </tr>
                        <c:forEach var="row" items="${clientsPaginatedList}">
                            <tr>
                                <td class="id">${row.getId()}</td>
                                <td>${row.getLastName()}</td>
                                <td>${row.getFirstName()}</td>
                                <td>${row.getMiddleName()}</td>
                                <td>${row.getAddress()}</td>
                                <td>${row.getTelephone()}</td>
                                <td>${row.getMobilephone()}</td>
                                <td class=" createOrder">
                                    <button type="button" class="btn btn-primary" data-toggle="modal"
                                            value="${row.getId()}"
                                            data-target="#myModel">Order
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <t:recordsNotFound/>
        </c:otherwise>
    </c:choose>
</fmt:bundle>
