<%@include file="./shared/header.jsp"%>
<c:url var="submitAction" value="/user"/>
<form:form modelAttribute="linklist" method="post" action="${submitAction}">
    add<form:input path="links[0].addr" />
    tel<form:input path="links[0].tel" />
    add<form:input path="links[1].addr" />
   tel <form:input path="links[1].tel" />
    <input type="submit">Submit</input>
</form:form>

<%@include file="./shared/footer.jsp"%>
        