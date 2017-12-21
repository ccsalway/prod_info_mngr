<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/functions.tld" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<table>
<c:forEach items="${products}" var="product">
    <tr>
        <td>${product.id}</td>
        <td>${fn:htmlEscape(product.name)}</td>
    </tr>
</c:forEach>
</table>
</body>
</html>
