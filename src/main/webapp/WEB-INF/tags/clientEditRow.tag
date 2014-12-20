<div class="panel-body">
    <form id="changeForm" action="${pageContext.request.contextPath}/do/saveClientData">
        <input type="hidden" name="id" value="${client.getId()}">

        <%--<div>--%>
            <%--<div class="form-group edit">--%>
                <%--<div class="center"><label for="first-name" class="edit-field">First name</label></div>--%>
                <%--<input type="text" class="form-control" name="first-name"--%>
                       <%--id="first-name" value="${client.getFirstName()}">--%>
            <%--</div>--%>

            <%--<div class="form-group edit">--%>
                <%--<div class="center"><label for="middle-name" class="edit-field">Middle name</label></div>--%>
                <%--<input type="text" class="form-control" name="middle-name"--%>
                       <%--id="middle-name" value="${client.getMiddleName()}">--%>
            <%--</div>--%>

            <%--<div class="form-group edit">--%>
                <%--<div class="center"><label for="last-name" class="edit-field">Last name</label></div>--%>
                <%--<input type="text" class="form-control" name="last-name"--%>
                       <%--id="last-name" value="${client.getLastName()}">--%>
            <%--</div>--%>

            <%--<div class="form-group edit">--%>
                <%--<div class="center"><label for="address" class="edit-field">Address</label></div>--%>
                <%--<input type="text" class="form-control" name="address"--%>
                       <%--id="address" value="${client.getAddress()}">--%>
            <%--</div>--%>

            <%--<div class="form-group edit">--%>
                <%--<div class="center"><label for="telephone" class="edit-field">Telephone</label></div>--%>
                <%--<input type="text" class="form-control" name="telephone"--%>
                       <%--id="telephone" value="${client.getTelephone()}">--%>
            <%--</div>--%>

            <%--<div class="form-group edit">--%>
                <%--<div class="center"><label for="mobilephone" class="edit-field">Mobilephone</label></div>--%>
                <%--<input type="text" class="form-control" name="mobilephone"--%>
                       <%--id="mobilephone" value="${client.getMobilephone()}">--%>
            <%--</div>--%>
        <%--</div>--%>

        <div class="tab-pane" style="overflow-y: scroll">
            <table class="table table-hover" ID="clientsTable">
                <tr>
                    <th>ID</th>
                    <th>Firs name</th>
                    <th>Middle name</th>
                    <th>Last name</th>
                    <th>Address</th>
                    <th>Telephone</th>
                    <th>Mobilephone</th>
                </tr>
                <tr>
                    <td>
                        <div class="form-group">
                            <input type="text" name="deliverydate" class="form-control" value="${client.getFirstName()}">
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <input type="text" name="deliverydate" class="form-control" value="${client.getMiddleName()}">
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <input type="text" name="deliverydate" class="form-control" value="${client.getLastName()}">
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <input type="text" name="deliverydate" class="form-control" value="${client.getAddress()}">
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <input type="text" name="deliverydate" class="form-control" value="${client.getTelephone()}">
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <input type="text" name="deliverydate" class="form-control" value="${client.getTelephone()}">
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <input type="text" name="deliverydate" class="form-control" value="${client.getMobilephone()}">
                        </div>
                    </td>
                </tr>
            </table>
        </div>

        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#confirmModal">
            Save
        </button>
    </form>
</div>