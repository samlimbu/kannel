<%@include file="./shared/header.jsp"%>
<script>
            function change(){
                
                var e = document.getElementById("drop");
                var strUser = e.options[e.selectedIndex].value;
                document.getElementById("aid").value=strUser;
                console.log(strUser);
            }
        </script>  
        
        
  
<div id="table-container">        
        <table>
            <th>studentId</th>
             <th>studentName</th>
              <th>classId</th>
               <th>className</th>
                <th></th>
                 <th></th>
                <c:forEach items="${studentlist}" var="item">
                    <tr>
                        <td>${item.studentId}</td>
                        <td>${item.studentName}</td>
                        <td>${item.classId.classId}</td>
                        <td>${item.classId.className}</td>
                        <td>${item.sectionId.sectionName}</td>
         
                            
                        
                        <td><a href="<c:url value="/student/remove/${item.studentId}"/>">Remove</a></td>
                        <td><a href="<c:url value="/student/edit/${item.studentId}"/>">Edit</a></td>
                    <tr>
                </c:forEach>   
        </table>    
</div>
<div id="form-container">
    <c:if test="${empty form.studentId}">
        <c:url var="addAction" value="/student/add"/>
        <form:form action="${addAction}" modelAttribute="form">
            studentName<form:input path="studentName"/>
            <br>
            classId<form:hidden id="aid" path="classId.classId"/>
            <select id= "drop" name="profileId" onclick="change()">
                <option hidden="true">-- Select ID from List --</option>
                    <c:forEach var="item" items="${classeslist}">
                        <option value= '<c:out value="${item.classId}"  />'>

                                    <c:out value="${item.classId} - ${item.className}" />
                        </option>
                    </c:forEach>
            </select>
            <form:button name="Submit">Add</form:button>
        </form:form>    
    </c:if>

    <c:if test="${!empty form.studentId}">
        <c:url var="addAction" value="/student/update"/>
        <form:form action="${addAction}" modelAttribute="form">
            sID<form:input path="studentId"/>
            <br>
            studentName<form:input path="studentName"/>
            <br>
            class<form:input path="classId.className" disabled="true"/>
            <br>
            <select id= "drop" name="profileId" onclick="change()">
                <option hidden="true" selected= '<c:out value="${form.classId.classId}"  />'>

                                    <c:out value="${form.classId.classId} - ${form.classId.className}" />
                        </option>
                <option hidden="true">-- Select ID from List --</option>
                    <c:forEach var="item" items="${classeslist}">
                        <option value= '<c:out value="${item.classId}"  />'>

                                    <c:out value="${item.classId} - ${item.className}" />
                        </option>
                    </c:forEach>
            </select>
            <form:hidden id="aid" path="classId.classId"/>


            <form:button name="Submit">Update</form:button>
        </form:form>    
    </c:if>

            <form:form modelAttribute="form" action="result" method="get" >

            </form:form>
</div>         
        
<%@include file="./shared/footer.jsp"%>