<%@include file="./shared/header.jsp"%>
<script>
function change(){
                
                var e = document.getElementById("drop");
                var strUser = e.options[e.selectedIndex].value;
                
                
                document.getElementById("selected-level").value=strUser;
           
                console.log(strUser);
    }
    function change2(){
                
                var e = document.getElementById("drop2");
                var strUser = e.options[e.selectedIndex].value;
                
                
                document.getElementById("selected-level").value=strUser;
           
                console.log(strUser);
    }
</script>
<h1><a href=" <c:url value="/subject"/>  ">Subject</a></h1>

<c:if test = "${empty form.subjectId}">
    <c:url value="/subject/add" var="addAction"/>
    <form:form action="${addAction}" modelAttribute="form">

        subject name <form:input path="subjectName"/>
        level <form:hidden id="selected-level" path="levelId.levelId" />
        <select id="drop" onchange="change();" required>
            <option selected hidden disabled value="">Select Level</option>
            <c:forEach items="${levels}" var="item">
                
                <option value="${item.levelId}">${item.levelId}</option>
            </c:forEach>        
        </select> 
        <form:button>Add</form:button>
    </form:form>    
</c:if>
<c:if test = "${!empty form.subjectId}">
    <c:url value="/subject/update" var="updateAction"/>
    <form:form action="${updateAction}" modelAttribute="form">
        subject id <input name="subjectId" value="${form.subjectId}" readonly="true" class="disable"></input>
        subject name <form:input path="subjectName"/>
        level <form:hidden id="selected-level" path="levelId.levelId" />
        <select id="drop2" onchange="change2();" required>
            <option selected hidden>${form.levelId.levelId}</option>
            <c:forEach items="${levels}" var="item">
                
                <option value="${item.levelId}">${item.levelId}</option>
            </c:forEach>        
        </select> 
        <form:button>Update</form:button>
    </form:form>    
</c:if>
        
    
    <table>
        <th>subjectId</th>
        <th>Level</th>
        <th>subjectName</th>
        <c:forEach items="${subjects}" var="item">
        <tr>
            <td>${item.subjectId}</td>
            <td>${item.levelId.levelId}</td>
            <td>${item.subjectName}</td>
            <td>
                <a href=" <c:url value="/subject/edit/${item.subjectId}"/>  ">Edit</a>
                <a href=" <c:url value="/subject/remove/${item.subjectId}"/>  ">Remove</a>
            </td>    
                
        </tr>
        </c:forEach>
    </table>    
<%@include file="./shared/footer.jsp"%>