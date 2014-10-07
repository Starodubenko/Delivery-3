<div class="panel-body">
    <form id="changeForm" action="${pageContext.request.contextPath}/do/saveClientData">
        <input type="hidden" name="id" value="${client.getId()}">

        <div>
            <div class="form-group edit">
                <div class="center"><label for="first-name" class="edit-field">First name</label></div>
                <input type="text" class="form-control" name="first-name"
                       id="first-name" value="${client.getFirstName()}">
            </div>

            <div class="form-group edit">
                <div class="center"><label for="middle-name" class="edit-field">Middle name</label></div>
                <input type="text" class="form-control" name="middle-name"
                       id="middle-name" value="${client.getMiddleName()}">
            </div>

            <div class="form-group edit">
                <div class="center"><label for="last-name" class="edit-field">Last name</label></div>
                <input type="text" class="form-control" name="last-name"
                       id="last-name" value="${client.getLastName()}">
            </div>

            <div class="form-group edit">
                <div class="center"><label for="address" class="edit-field">Address</label></div>
                <input type="text" class="form-control" name="address"
                       id="address" value="${client.getAddress()}">
            </div>

            <div class="form-group edit">
                <div class="center"><label for="telephone" class="edit-field">Telephone</label></div>
                <input type="text" class="form-control" name="telephone"
                       id="telephone" value="${client.getTelephone()}">
            </div>

            <div class="form-group edit">
                <div class="center"><label for="mobilephone" class="edit-field">Mobilephone</label></div>
                <input type="text" class="form-control" name="mobilephone"
                       id="mobilephone" value="${client.getMobilephone()}">
            </div>
        </div>
        <br><br><br><br><br>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#confirmModal">
            Save
        </button>
    </form>
</div>