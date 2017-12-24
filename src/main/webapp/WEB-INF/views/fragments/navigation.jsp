<div class="content">
    <p class="has-text-centered">${page.totalElements} results</p>
</div>
<nav class="pagination is-centered" role="navigation">
    <ul class="pagination-list">
        <li>
            <c:choose>
                <c:when test="${page.hasPrevious()}">
                    <a class="pagination-link" href="${requestUri}?page=0">1</a>
                </c:when>
                <c:otherwise>
                    <a class="pagination-link" disabled>1</a>
                </c:otherwise>
            </c:choose>
        </li>
        <li class="is-hidden-mobile"><span class="pagination-ellipsis">&hellip;</span></li>
        <li>
            <c:choose>
                <c:when test="${page.hasPrevious()}">
                    <a class="pagination-link" href="${requestUri}?page=${page.number - 1}">&lt;&lt;</a>
                </c:when>
                <c:otherwise>
                    <a class="pagination-link" disabled>&lt;&lt;</a>
                </c:otherwise>
            </c:choose>
        </li>
        <li>
            <a class="pagination-link is-current">${page.number + 1}</a>
        </li>
        <li>
            <c:choose>
                <c:when test="${page.hasNext()}">
                    <a class="pagination-link" href="${requestUri}?page=${page.number + 1}">&gt;&gt;</a>
                </c:when>
                <c:otherwise>
                    <a class="pagination-link" disabled>&gt;&gt;</a>
                </c:otherwise>
            </c:choose>
        </li>
        <li class="is-hidden-mobile"><span class="pagination-ellipsis">&hellip;</span></li>
        <li>
            <c:choose>
                <c:when test="${page.hasNext()}">
                    <a class="pagination-link" href="${requestUri}?page=${page.totalPages - 1}">${page.totalPages}</a>
                </c:when>
                <c:otherwise>
                    <a class="pagination-link" disabled>${page.totalPages}</a>
                </c:otherwise>
            </c:choose>
        </li>
    </ul>
</nav>