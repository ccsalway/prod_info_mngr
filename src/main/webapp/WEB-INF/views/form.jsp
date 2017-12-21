<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="fragments/header.jsp" %>
</head>
<body>
<%@ include file="fragments/menu.jsp" %>
<form method="post" action="<c:url value="/add" />">
    <input type="text" name="name" value="${product.name}">
    <button type="submit">Save</button>
</form>
<c:if test="${not empty errors}">
    <p>${errors}</p>
</c:if>
</body>
</html>
