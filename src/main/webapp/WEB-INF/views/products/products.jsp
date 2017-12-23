<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/functions.tld" %>
<!DOCTYPE html>
<html lang="en" class="has-navbar-fixed-top">
<head>
    <jsp:include page="../fragments/header.jsp"/>
</head>
<body>
<jsp:include page="../fragments/navbar.jsp"/>
<div class="section">
    <div class="container is-fluid">
        <div class="level">
            <div class="level-left">
                <div class="level-item">
                    <h1 class="title">Products</h1>
                </div>
            </div>
            <div class="level-right">
                <div class="level-item">
                    <a class="button is-info" href="<s:url value="/product/add"/>">
                        New
                    </a>
                </div>
            </div>
        </div>

        <div style="">
            <table id="dataTable" class="table is-hoverable is-striped is-fullwidth" style="cursor: pointer;">
                <thead>
                <tr>
                    <th>Name</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${page.content}" var="product">
                    <tr data-id="${product.id}">
                        <td>${fn:htmlEscape(product.name)}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <%@ include file="../fragments/navigation.jsp" %>
</div>
<%@ include file="../fragments/footer.jsp" %>

<script>
    $("#dataTable").find("> tbody > tr").on("click", function () {
        var id = $(this).attr("data-id");
        window.location.href = "<s:url value="/product/"/>" + id;
    });
</script>

</body>
</html>
