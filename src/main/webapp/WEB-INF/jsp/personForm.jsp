<%@ include file="/WEB-INF/jsp/header.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">
    <h1>Editer une personne </h1>

    <form:form method="POST" modelAttribute="person" action="/projet/edit?id=${person.id}">

        <form:errors path="*" cssClass="alert alert-danger" element="div" />

        <div class="form-group">
            <label for="firstname">Firstname:</label>
            <form:input class="form-control" path="firstname" value="${person.firstname}"/>
            <form:errors path="firstname" cssClass="alert alert-warning" element="div" />
        </div>

        <div class="form-group">
            <label for="lastname">Lastname:</label>
            <form:input class="form-control" path="lastname" value="${person.lastname}"/>
            <form:errors path="lastname" cssClass="alert alert-warning" element="div" />
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <form:input class="form-control" path="email" value="${person.email}"/>
            <form:errors path="email" cssClass="alert alert-warning" element="div" />
        </div>

        <div class="form-group">
            <label for="website">Website:</label>
            <form:input class="form-control" path="website" value="${person.website}"/>
            <form:errors path="website" cssClass="alert alert-warning" element="div" />
        </div>

        <div class="form-group">
            <label for="birthdate">Website:</label>
            <form:input type="date" class="form-control" path="birthdate" value="${person.birthdate}"/>
            <form:errors path="birthdate" cssClass="alert alert-warning" element="div" />
        </div>

        <div class="form-group">
		    <label for="group">Type:</label>
		    <form:select path="group" multiple="false" class="form-control">
		        <form:option value="" label="--- Select ---" />
		        <c:forEach items="${groups}" var="group">
		            <option value="${group.id}">${group.name}<option/>
		        </c:forEach>
		    </form:select>
		    <form:errors path="group" cssClass="alert alert-warning"
		        element="div" />
		</div>

        <div class="form-group">
            <button type="submit" class="btn btn-info">Submit</button>
        </div>

    </form:form>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>