<div class="list-group">
  <a href="#" class="list-group-item list-group-item-action"> Cras justo odi </a>
  <a href="#" class="list-group-item list-group-item-action">Dapibus ac facilisis in</a>
  <a href="#" class="list-group-item list-group-item-action">Morbi leo risus</a>
  <a href="#" class="list-group-item list-group-item-action">Porta ac consectetur ac</a>
  <a href="#" class="list-group-item list-group-item-action">Vestibulum at eros</a>
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
