<%@include file="./shared/header.jsp" %>
        Do you want to remove the selected subject for sure ?
        <table>
            <td>subjectId</td><td>${subject.subjectId}</td>
            <td>levelId</td><td>${subject.levelId.levelId}</td>
            <td>subjectName</td><td>${subject.subjectName}</td>
        </table>    
        <a href=" <c:url value="/subject/removeyes/${subject.subjectId}"/>  ">Yes</a>
        <a href="<c:url value="/subject"/> ">No</a>
                
<%@include file="./shared/footer.jsp" %>