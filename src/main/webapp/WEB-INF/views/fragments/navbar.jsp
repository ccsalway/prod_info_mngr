<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<nav class="navbar is-fixed-top" role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
        <a class="navbar-item" href="https://bulma.io">
            <img src="https://bulma.io/images/bulma-logo.png" width="112" height="28">
        </a>
    </div>
    <div class="navbar-menu is-active">
        <div class="navbar-start">
            <a class="navbar-item" href="<s:url value="/products/"/>">
                Products
            </a>
        </div>
        <div class="navbar-end">
            <div class="navbar-item">
                <a class="button is-info" href="<s:url value="/product/add"/>">
                    Add
                </a>
            </div>
        </div>
    </div>
</nav>