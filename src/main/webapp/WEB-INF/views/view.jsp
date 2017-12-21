<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/functions.tld" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="fragments/header.jsp" %>
</head>
<body>
<%@ include file="fragments/menu.jsp" %>
${product.id} ${fn:htmlEscape(product.name)}
</body>
</html>
