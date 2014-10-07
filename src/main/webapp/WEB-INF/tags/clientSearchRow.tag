<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="i18n.messages">
    <form id="findForm">
        <div id="searcheFields">
            <div class="form-group edit">
                <div class="center"><label for="first-name" class="edit-field"><fmt:message
                        key="view.dispatcher.table.clients.message.first.name"/></label></div>
                <input type="text" class="form-control" name="first-name"
                       id="first-name" value="">
            </div>

            <div class="form-group edit">
                <div class="center"><label for="middle-name" class="edit-field"><fmt:message
                        key="view.dispatcher.table.clients.message.middle.name"/></label></div>
                <input type="text" class="form-control" name="middle-name"
                       id="middle-name" value="">
            </div>

            <div class="form-group edit">
                <div class="center"><label for="last-name" class="edit-field"><fmt:message
                        key="view.dispatcher.table.clients.message.last.name"/></label></div>
                <input type="text" class="form-control" name="last-name"
                       id="last-name" value="">
            </div>

            <div class="form-group edit">
                <div class="center"><label for="address" class="edit-field"><fmt:message
                        key="view.dispatcher.table.clients.message.address"/></label></div>
                <input type="text" class="form-control" name="address"
                       id="address" value="">
            </div>

            <div class="form-group edit">
                <div class="center"><label for="telephone" class="edit-field"><fmt:message
                        key="view.dispatcher.table.clients.message.telephone"/></label></div>
                <input type="text" class="form-control" name="telephone"
                       id="telephone" value="">
            </div>

            <div class="form-group edit">
                <div class="center"><label for="mobilephone" class="edit-field"><fmt:message
                        key="view.dispatcher.table.clients.message.mobilephone"/></label></div>
                <input type="text" class="form-control" name="mobilephone"
                       id="mobilephone" value="">
            </div>
            <br>
            <input type="hidden" id="entityName" value="Client">
            <input type="button" class="btn btn-primary" id="search" value="<fmt:message key="view.search"/>">
        </div>
    </form>
</fmt:bundle>