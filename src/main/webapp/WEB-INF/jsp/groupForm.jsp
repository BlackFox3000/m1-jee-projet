<%@ include file="/WEB-INF/jsp/header.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">
    <h1>Editer un group </h1>

    <form:form method="POST" modelAttribute="person" action="/projet/editGroup?id=${person.id}">

        <form:errors path="*" cssClass="alert alert-danger" element="div" />

                <div class="form-group">
                    <label for="name">Website:</label>
                    <form:input type="date" class="form-control" path="birthdate" value="${person.birthdate}"/>
                    <form:errors path="birthdate" cssClass="alert alert-warning" element="div" />
                </div>

        <div class="form-group">
            <button type="submit" class="btn btn-info">Submit</button>
        </div>

    </form:form>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>