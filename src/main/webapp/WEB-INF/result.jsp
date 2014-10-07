<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:bundle basename="i18n.messages">
    <html>
    <head>
        <title>OpenDataase</title>
        <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
        <link rel='stylesheet' href='style/result.css'>

        <script language="JavaScript" type="text/javascript">
            ns4 = (document.layers)? true:false
            ie4 = (document.all)? true:false

            function init() {
                if (ns4) {document.captureEvents(Event.MOUSEMOVE);}
                document.onmousemove=mousemove;
            }
            function mousemove(event) {

                var mouse_x = y = 0;
                if (document.attachEvent != null) {
                    mouse_x = window.event.clientX;
                    mouse_y = window.event.clientY;
                } else if (!document.attachEvent && document.addEventListener) {
                    mouse_x = event.clientX;
                    mouse_y = event.clientY;
                }
                status="x = " + mouse_x + ", y = " + mouse_y;
                document.getElementById('xy').innerHTML = "x = " + mouse_x + ", y = " + mouse_y;
            }
            init()
        </script>

    </head>
    <body class="body">

    <span id="xy"></span> <br />

    <div class="mainn">
        <div id="mainn" class="panel panel-default">
            <div id="topPart" class="panel panel-default">
                <form id="btn-group" method="post">
                    <div class="btn-group">
                        <button type="submit" name="TableName" value="Clients" class="btn btn-default">Clients</button>
                        <button type="submit" name="TableName" value="Employees" class="btn btn-default">Employees</button>
                        <button type="submit" name="TableName" value="Goods" class="btn btn-default">Goods</button>
                        <button type="submit" name="TableName" value="Orders" class="btn btn-default">Orders</button>
                        <button type="submit" name="TableName" value="Positions" class="btn btn-default">Positions</button>
                        <button type="submit" name="TableName" value="Gorod" class="btn btn-default">Gorod</button>
                        <button type="submit" name="TableName" value="Maikuduk" class="btn btn-default">Maikuduk</button>
                        <button type="submit" name="TableName" value="Mihailovka" class="btn btn-default">Mihailovka</button>
                        <button type="submit" name="TableName" value="Prishahtinsk" class="btn btn-default">Prishahtinsk</button>
                        <button type="submit" name="TableName" value="Ugo_Vostok" class="btn btn-default">Ugo_Vostok</button>
                        <input type="hidden" name="actionName" value="getdatafromdbaction">
                    </div>

                    <div class="btn-group" data-toggle="buttons">
                        <c:forEach var="name" items="${namesOfTables}">
                        <label class="btn btn-primary">
                            <input type="radio" name="options" id="option1" checked>${name.getElement()}
                        </label>
                        </c:forEach>
                    </div>
                </form>
            </div>

            <div id="leftPart" class="panel panel-default" style="overflow-y: scroll;">
                <form method="post">
                <c:forEach var="title" items="${titlesOfColumns}">
                    <div class="form-group">
                        <label for="exampleInputEmail1" class="name_surname">${title.getElement()}</label>
                        <input type="text" class="form-control" id="exampleInputEmail1">
                    </div>
                </c:forEach>
                    <input type="submit" value="добавить запись в базу" class="btn btn-default btn-lg btn-block">
                </form>
            </div>

            <div id="rightPart" class="panel panel-default">
                <div class="panel panel-default tablePanel" style="overflow-y: scroll;">
                    <%--<div class="panel-heading">Текущая таблица</div>--%>
                    <table class="table table-hover">
                        <tr>
                            <c:forEach var="title" items="${titlesOfColumns}">
                            <th>${title.getElement()}</th>
                            </c:forEach>
                        </tr>
                        <c:forEach var="row" items="${result}">
                            <tr>
                                <c:forEach var="element" items="${row}">
                                <td><b> ${element.getElement()}</b>
                                </td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </table>
                </div>

                <table class="table">
                    <tr>
                        <th>1</th>
                        <th>2</th>
                        <th>3</th>
                    </tr>
                    <tr>
                        <td>q</td>
                        <td>w</td>
                        <td>e</td>
                    </tr>
                </table>
            </div>
            <div class="clear"></div>
        </div>
    </div>

    <input type="text" value="weqrwer[oijgiudshfgouh">

    <script src="webjars/jquery/1.11.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    </body>
    </html>
</fmt:bundle>