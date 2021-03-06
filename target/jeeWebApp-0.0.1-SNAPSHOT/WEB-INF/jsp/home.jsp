<jsp:root
   version="2.0"
   xmlns:form="http://www.springframework.org/tags/form"
   xmlns:jsp="http://java.sun.com/JSP/Page"
   xmlns:c="http://java.sun.com/jsp/jstl/core"
   xmlns:spring="http://www.springframework.org/tags"
   xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
   xmlns="http://www.w3.org/1999/xhtml">

<jsp:output
    omit-xml-declaration="false"
    doctype-root-element="html"
    doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN"
    doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"/>

	<html>
	<jsp:directive.page contentType="text/html" />
	<head>
		<c:url var="list" value="/actions/group/list" />
		<c:url var="searchPersons" value="/actions/person/searchPersons" />
		<c:url var="searchGroups" value="/actions/group/searchGroups" />

		<jsp:include page="/WEB-INF/jspx/head-bootstrap.jspx"/>
		<title><spring:message code="title.home"/></title>
	</head>
	<body>
        <%@ include file="/WEB-INF/jsp/header.jsp"%>

	    <div class="container">
	        <h2><spring:message code="home.find.a.person"/></h2>

	        <form action="${searchPersons}" method="GET">

	            <div class="form-group">
	                <label for="personName"><spring:message code="home.person.name"/> :</label>
	                <input type="text" name="personName" class="form-control"/>
	                <c:if test = "${errorPersonName == true}">
				    	<div class="alert alert-warning"><spring:message code="home.warn.person.not.found"/></div>
				    </c:if>
	            </div>

	            <div class="form-group">
	                <button type="submit" class="btn btn-info"><spring:message code="home.find"/></button>
	            </div>
	        </form>
	    </div>

	        <div class="container">
	        <h2><spring:message code="home.find.persons.by.group"/></h2>

	        <form action="${searchGroups}" method="GET">

	            <div class="form-group">
	                <label for="groupName"><spring:message code="home.group.name"/> :</label>
	                <input type="text" name="groupName" class="form-control"/>
	                <c:if test = "${errorGroupName == true}">
				    	<div class="alert alert-warning"><spring:message code="home.warn.group.not.found"/></div>
				    </c:if>
	            </div>

	            <div class="form-group">
	                <button type="submit" class="btn btn-info"><spring:message code="home.find"/></button>
	            </div>
	        </form>
	    </div>

	</body>
	</html>
</jsp:root>