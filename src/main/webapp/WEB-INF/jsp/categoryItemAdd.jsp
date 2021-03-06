<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <link href="<c:url value="/bootstrap/css/bootstrap.min.css"/>"
        rel="stylesheet">
  <title></title>
</head>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<div class="container">
  <div class="row">

    <div class="input-group">
      <form action="/servlet/category/add" method="POST">
        <c:if test="${ErrorMessage != null &&ErrorMessage != ''}">
          <div class="alert alert-danger" role="alert"><c:out value="${ErrorMessage}"/></div>
        </c:if>
        <input type="text" name="categoryName" class="form-control"
               placeholder="Category name" aria-describedby="basic-addon1">
        <input class="btn btn-default" type="submit" value="Submit">
      </form>
    </div>
  </div>
</div>

</body>
</html>


