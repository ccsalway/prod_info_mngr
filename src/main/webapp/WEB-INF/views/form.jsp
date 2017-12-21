<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form method="post" action="<c:url value="/form">">
    <input type="text" name="name" value="${name}">
    <button type="submit">Save</button>
</form>
</body>
</html>
