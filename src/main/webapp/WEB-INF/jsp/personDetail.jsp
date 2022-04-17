<%@ include file="/WEB-INF/jsp/header.jsp" %>

  <div class="container">
    <h1>Detail personne - Projet Spring Application</h1>

	<div class="container">
        <h1>Liste personne</h1>
        <table class="table">
            <tr>
                <td>
                      FirstName : <c:out value="${person.firstname}" />
                </td>
            </tr>
            <tr>
                <td>
                      LastName : <c:out value="${person.lastname}" />
                </td>
            </tr>
            <tr>
                <td>
                      Email : <c:out value="${person.email}" />
                </td>
            </tr>
            <tr>
                <td>
                      Website : <c:out value="${person.website}" />
                </td>
            </tr>
            <tr>
                <td>
                      Birthday : <c:out value="${person.birthdate}" />
                </td>
            </tr>
        </table>
    </div>
  </div>

        <p>
            <a class="btn btn-info" href="/projet/personForm/${person.id}">Moddifier les informations</a>
        </p>


<%@ include file="/WEB-INF/jsp/footer.jsp" %>