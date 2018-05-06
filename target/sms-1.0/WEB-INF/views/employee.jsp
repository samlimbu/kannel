<%@include file="./shared/header.jsp"%>
<div id="table-container">        
        <table>
            <th>empid</th>
             <th>empname</th>
              <th>mgr id</th>
               <th>department</th>
                <th></th>
                 <th></th>
                <c:forEach items="${employees}" var="item">
                    <tr>
                        <td>${item.employeeId}</td>
                        <td>${item.employeeName}</td>
                        <td>${item.managerId.managerId}</td>
                        ${item.managerId.managerDetailList[0].department}
                        <c:forEach var="mdetail" items="${item.managerId.managerDetailList}">
                          
                            <td>${mdetail.department}</td>
                        </c:forEach>
                       
                    <tr>
                </c:forEach>   
        </table>    
</div>
<%@include file="./shared/footer.jsp"%>