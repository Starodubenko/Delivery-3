<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <html>
    <t:gHead>
        <link rel='stylesheet' href='<c:url value="/webjars/bootstrap-datepicker/1.3.0/css/datepicker3.css"/>'>
        <link rel='stylesheet' href='<c:url value="/style/admin.css"/>'>
    </t:gHead>
    <t:gbody>
    <div class="main panel panel-default">
            <t:authentication></t:authentication>

        <div class="table-name form-group">
            <select class="form-control" name="tableName" id="tableName" class="form-control">
                <option>Clients</option>
                <option>Employers</option>
                <option>Orders</option>
                <option>Goods</option>
                <option>Pay card</option>
                <option>Contacts</option>
                <option>Periods</option>
                <option>Positions</option>
                <option>User status</option>
                <option>Pay card status</option>
            </select>
        </div>

        <div class="input-group searchRow">
            <span class="searcheWidth input-group-btn">
                <select class="form-control" name="columnName">
                    <option>ID</option>
                    <option>First name</option>
                    <option>Middle name</option>
                    <option>Last name</option>
                    <option>Address</option>
                    <option>Telephone</option>
                    <option>Mobile phone</option>
                </select>
            </span>
            <input type="text" class="form-control" name="desiredValue">
            <span class="input-group-btn">
                <button class="btn btn-default" type="submit">Search</button>
            </span>
        </div>

        <div class="clear"></div>

        <div class="panel panel-default row_for_finding">
            <div class="panel-group" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                Edit
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in">
                        <div class="panel-body">
                            <div class="tab-pane" style="overflow-y: scroll">
                                <table class="table table-hover" ID="clientsTable">
                                    <input type="hidden" id="clientsPageNumber" value="${clientsPageNumber}"/>
                                    <tr>
                                        <th>ID</th>
                                        <th>Firs name</th>
                                        <th>Middle name</th>
                                        <th>Last name</th>
                                        <th>Address</th>
                                        <th>Telephone</th>
                                        <th>Mobilephone</th>
                                    </tr>
                                        <%--<c:forEach var="row" items="${clientsList}">--%>
                                    <tr>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" name="deliverydate" class="form-control">
                                            </div>
                                                <%--${row.getId()}--%>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" name="deliverydate" class="form-control">
                                            </div>
                                                <%--${row.getFirstName()}--%>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" name="deliverydate" class="form-control">
                                            </div>
                                                <%--${row.getMiddleName()}--%>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" name="deliverydate" class="form-control">
                                            </div>
                                                <%--${row.getLastName()}--%>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" name="deliverydate" class="form-control">
                                            </div>
                                                <%--${row.getAddress()}--%>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" name="deliverydate" class="form-control">
                                            </div>
                                                <%--${row.getTelephone()}--%>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" name="deliverydate" class="form-control">
                                            </div>
                                                <%--${row.getMobilephone()}--%>
                                        </td>
                                    </tr>
                                        <%--</c:forEach>--%>
                                </table>
                            </div>


                            <button class="btn btn-default buttons">Insert</button>
                            <button class="btn btn-default buttons">Save</button>
                            <button class="btn btn-default buttons">Delete</button>

                        </div>
                    </div>
                </div>
            </div>

            <div class="panel panel-default dataBase">
                <div class="panel panel-default table-data-base">

                </div>
            </div>

            <div class="panel panel-default log">
                <div class="center"><label>Log</label></div>
                <div class="log-report-content panel panel-default">

                </div>
                <div class="buttons input-daterange input-group" id="datepickerLog">
                    <input type="text" class="input-sm form-control" name="start"/>
                    <span class="input-group-addon">to</span>
                    <input type="text" class="input-sm form-control" name="end"/>
                </div>
                <button class="btn btn-default buttons">Searche</button>
            </div>
            <div class="panel panel-default report">
                <div class="center"><label>Report</label></div>
                <div class="log-report-content panel panel-default">

                </div>
                <div class="buttons input-daterange input-group" id="datepickerReport">
                    <input type="text" class="input-sm form-control" name="start"/>
                    <span class="input-group-addon">to</span>
                    <input type="text" class="input-sm form-control" name="end"/>
                </div>
                <button class="btn btn-default buttons">Searche</button>
            </div>


            <div class="clear"></div>
        </div>
        </t:gbody>
        <script src="<c:url value="/webjars/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"/>"></script>
        <script src="<c:url value="/script/admin.js"/>"></script>
    </html>
</fmt:bundle>
