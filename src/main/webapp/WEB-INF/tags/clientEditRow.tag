<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="i18n.messages">
    <div class="tab-pane">
        <table class="table table-hover" ID="clientsTable">
            <tr>
                <th class="text-center"><fmt:message key="clients.message.last.name"/></th>
                <th class="text-center"><fmt:message key="clients.message.first.name"/></th>
                <th class="text-center"><fmt:message key="clients.message.middle.name"/></th>
                <th class="text-center"><fmt:message key="clients.message.address"/></th>
                <th class="text-center"><fmt:message key="clients.message.telephone"/></th>
                <th class="text-center"><fmt:message key="clients.message.mobilephone"/></th>
                <th class="text-center"><fmt:message key="message.action"/></th>
            </tr>
            <tr>
                <td>
                    <div class="form-group">
                        <input type="text" name="last-name" class="form-control"
                               value="${client.getLastName()}">
                    </div>
                </td>
                <td>
                    <div class="form-group">
                        <input type="text" name="first-name" class="form-control"
                               value="${client.getFirstName()}">
                    </div>
                </td>
                <td>
                    <div class="form-group">
                        <input type="text" name="middle-name" class="form-control"
                               value="${client.getMiddleName()}">
                    </div>
                </td>
                <td>
                    <div class="form-group">
                        <input type="text" name="address" class="form-control"
                               value="${client.getAddress()}">
                    </div>
                </td>
                <td>
                    <div class="form-group">
                        <input type="text" name="telephone" class="form-control"
                               value="${client.getTelephone()}">
                    </div>
                </td>
                <td>
                    <div class="form-group">
                        <input type="text" name="mobilephone" class="form-control"
                               value="${client.getMobilephone()}">
                    </div>
                </td>
                <td>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#confirmModal">
                        <fmt:message key="button.save"/>
                    </button>
                </td>
            </tr>
        </table>
    </div>
    <h4 class="text-center" id="saveMessage">${saveMessage}</h4>
</fmt:bundle>