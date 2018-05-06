<%@include file="./shared/header.jsp" %>

<h2><a href=" <c:url value="/classes"/>  ">Classes</a></h2>

<c:if test = "${empty form.classId}">
    <c:url value="/classes/add" var="addAction"/>
    <form:form action="${addAction}" modelAttribute="form">

        sectionid <form:input path="sectionId.sectionId"/>
        levelid name <form:input path="levelId.levelId"/>
        facultyid name <form:input path="facultyId.facultyId"/>
        
        <form:button>Add</form:button>
    </form:form>    
</c:if>


<table>
    <th>classId</th>
    <th>sectionId</th>
    <th>levelId</th>
    <th>facultyId</th>
    <c:forEach items="${classes}" var="item">
    <tr>
        <td>${item.classId}</td>
        <td>${item.sectionId.sectionId}</td>
        <td>${item.levelId.levelId}</td>
        <td>${item.facultyId.facultyId}</td>
        <td>
            <a href=" <c:url value="/classes/edit/${item.classId}"/> ">Edit</a>
            <a href=" <c:url value="/classes/remove/${item.classId}"/> ">Remove</a>
        </td>    
    </tr>
    </c:forEach>
          
</table>

<%@include file="./shared/footer.jsp" %>