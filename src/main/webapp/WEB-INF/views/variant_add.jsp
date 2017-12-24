<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/functions.tld" %>
<!DOCTYPE html>
<html lang="en" class="has-navbar-fixed-top">
<head>
    <jsp:include page="fragments/header.jsp"/>
    <title>Variant</title>
</head>
<body>

<!-- navbar -->
<jsp:include page="fragments/navbar.jsp"/>

<!-- content -->
<div class="section">
    <div class="container is-fluid">

        <!-- title -->
        <h1 class="title">Variant</h1>

        <!-- breadcrumbs -->
        <nav class="breadcrumb" aria-label="breadcrumbs">
            <ul>
                <li><a href="<s:url value="/variants"/>">Products</a></li>
                <li><a href="<s:url value="/variants/${product.id}"/>">${fn:htmlEscape(product.name)}</a></li>
                <li class="is-active"><a href="#" aria-current="page">New</a></li>
            </ul>
        </nav>

        <hr>

        <!-- form -->
        <form method="post" action="<s:url value="/variant/add"/>" autocomplete="off">



        </form>

    </div> <!-- /container -->
</div> <!-- /section -->

<!-- footer -->
<jsp:include page="fragments/footer.jsp"/>

</body>
</html>
