<%@tag description="StatusBar template" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="target"%>
<%@ attribute name="targetRowsCount"%>

<fmt:bundle basename="i18n.messages">
    <div class="form-group rows-count floatRight">
        <label class="labelCount" for="${target}srows"><fmt:message key="message.rows.count"/></label>

        <form class="rowsCountForm" action="${pageContext.request.contextPath}/do/dispatcher">
            <div class="input-group rows-count-height">
                <input type="text" name="${target}srows" id="${target}srows" value="${targetRowsCount}"
                       class="form-control textCount">
                        <span class="input-group-btn">
                            <button class="btn btn-default rows-count-height" type="submit"><fmt:message
                                    key="button.apply"/></button>
                        </span>
            </div>
        </form>
    </div>
</fmt:bundle>