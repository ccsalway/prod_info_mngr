<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form method="post" action="<s:url value="/add"/>">
    <input type="text" name="name" value="${product.name}">
    <button type="submit">Save</button>
</form>
<c:if test="${not empty errors}">
    <p>${errors}</p>
</c:if>
</body>
</html>
