<%@ include file="/WEB-INF/jsp/header.jsp" %>

  <div class="container">
    <h1>Accueil - Projet Spring Application</h1>

	<div class="container">
        <div class="groupes">
            <h2>Groupes </h2>
                    <table class="table table-hover">
                        <c:forEach items="${groups}" var="group">
                            <tr>
                                <td><a href="groupDetail/${group.id}">
                                    <c:out value="${group.name}" />
                                </a></td>
                            </tr>
                        </c:forEach>
                    </table>
        </div>
        <p>
            <a class="btn btn-info" href="addgroup">Create new group</a>
        </p>
                <div class="persones">
                    <h2>Personnes</h2>
                            <table class="table table-hover">
                                <c:forEach items="${persons}" var="person">
                                    <tr>
                                        <td><a href="personDetail/${person.id}">
                                            <c:out value="${person.firstname} ${person.lastname}" />
                                        </a></td>
                                    </tr>
                                </c:forEach>
                            </table>
                </div>
        <p>
            <a class="btn btn-info" href="addperson">Create new person</a>
        </p>
    </div>

  </div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>