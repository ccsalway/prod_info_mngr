<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/functions.tld" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="fragments/header.jsp" %>
</head>
<body>
<%@ include file="fragments/menu.jsp" %>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${products}" var="product">
        <tr>
            <td>${product.id}</td>
            <td><a href="<c:url value="/view/${product.id}"/>">${fn:htmlEscape(product.name)}</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
