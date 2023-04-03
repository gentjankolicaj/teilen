<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>

  <body>

    <h2 th:text="${welcome}">Welcome</h2>
    <div class="row">
        <div class="col-md-12">
          <img class="img-responsive" src="${pageContext.request.contextPath}/resources/static/images/pets.png"/>
        </div>
    </div>

  </body>

</html>