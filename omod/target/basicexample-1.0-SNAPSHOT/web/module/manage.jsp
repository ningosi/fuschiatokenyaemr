<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<table>
    <tr>
        <th>Patient names</th>
        <th>Gender</th>
    </tr>
    <c:forEach items="${allPatients}" var="items">
        <tr>
            <td>${ items.names}</td>
            <td>${ items.gender}</td>
        </tr>
    </c:forEach>
</table>

<%@ include file="/WEB-INF/template/footer.jsp"%>