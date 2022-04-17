<%@ include file="/WEB-INF/jsp/header.jsp" %>

  <div class="container">
    <h1>Detail groupe - Projet Spring Application</h1>

	<div class="container">
        <h1>Liste personne</h1>
        <table class="table table-hover">
            <c:forEach items="${persons}" var="person">
                <tr>
                    <td><a href="/projet/personDetail/${person.id}">
                        <c:out value="${person.firstname} ${person.lastname}" />
                    </a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
  </div>




<%@ include file="/WEB-INF/jsp/footer.jsp" %>