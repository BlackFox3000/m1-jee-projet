<%@ include file="/WEB-INF/jsp/header.jsp" %>

  <div class="container">
    <h1>Accueil - Projet Spring Application</h1>
	<%@ include file="/WEB-INF/jsp/groupList.jsp" %>
  </div>


<div class="container">
    <h1>Products (bootstrap)</h1>
    <table class="table table-hover">
        <c:forEach items="${groups}" var="group">
            <tr>
                <td><a href="${edit}?id=${group.id}">
                    <c:out value="${group.name}" />
                </a></td>
            </tr>
        </c:forEach>
    </table>
    <p>
        <a class="btn btn-info" href="${edit}">Create new product</a>
    </p>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>