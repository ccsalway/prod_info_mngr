<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/functions.tld" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
${product.id} ${fn:htmlEscape(product.name)}
</body>
</html>
