<%@include file="./shared/header.jsp"%>
<style>
    .dynamic-input{
        color: aqua;
    }
    #p11{
        color: blue;
    }
</style>    

<script type="text/javascript">
    
     
     var arr = [];
     <c:forEach var="item" items="${form.subjectList}">
     arr.push("<c:out value="${item.subjectId}"></c:out>");
     </c:forEach>
     console.log(arr.length);
     var count = arr.length;
     var init_count = count;
     
     function clear1() {
         count--;
         var element = document.getElementById("dynamic-input-"+count+"");
         var container = document.getElementById("myspan");
       
         
         container.removeChild(element);
        console.log(count);
         
         
     }
     
      function clearAll11() {
         
        count--;
          var container = document.getElementById("myspan");
         for(i = count;i >=0;i--){
        
            var element = document.getElementById("dynamic-input-"+i+"");
            container.removeChild(element);
                  console.log(i);
        }
        count = 0;
      
      
     }
     
    function add() {
       
        var element = document.createElement("input");

        element.setAttribute("name", "subjectList["+count+"].subjectId");
        element.setAttribute("placeholder", "subjec:" + "["+count+"]");
        element.setAttribute("id", "dynamic-input-"+count+"");
        element.setAttribute("required", "true");
        
        count++;

        var spanvar = document.getElementById("myspan");
        
        spanvar.appendChild(element);
        console.log(count);
     }
</script>

<h2><a href=" <c:url value="/faculty"/> ">Faculty</a></h2>
    <br>
    <br>
    <br>
    
<div id="form-container">


    <c:if test="${empty form.facultyId}">    
        <c:url var="actionForm" value="/faculty/add"/>
        <form:form action="${actionForm}" modelAttribute="form" name="add-form">
            <div class="form-inputs">
                
            tname<form:input path="facultyName"/>
            </div>
            <div class="form-inputs">
                <div id="formlist-container-input" class="form-inputs">  
                    <span id="myspan"></span>  
                </div>
            </div>
            <input type="submit" id="add-form-button" class="hide"/>

        </form:form>  
    </c:if>   


    <c:if test="${!empty form.facultyId}"> 
        <c:url var="actionFormupdate" value="/faculty/update"/>
        <form:form action="${actionFormupdate}" modelAttribute="form" name="update-form">
            <form:input path="facultyId" readonly="true" id="disable"/>
            
            
             <div class="form-inputs">
            facultyname<form:input path="facultyName"/>
                 <input type="submit" id="update-form-button" class="hide"/>
              </div>   
                 
           <div class="form-inputs">
                <div id="formlist-container-input" class="form-inputs">  
                    <span id="myspan">
                        
                        <c:forEach var="item" items="${form.subjectList}" varStatus="i" begin="0">
                            <form:input id="dynamic-input-${i.index}" path="subjectList[${i.index}].subjectId" />
                          
                        </c:forEach>  
                       
                                                    
                        
                    </span>  
                        <label class="button-label-2" for="remove-subject-button" tabindex="0">RemoveSubject</label>
                </div>
            </div>   
    
               
        </div>
        
    </form:form>    
</c:if>    
<div id="buttons-container">
    <p class="setting">
        <button onclick="add();">Add Subject</button>
        <button onclick="clear1();" id="remove-subject-button" class="hide">RemoveSubject</button>
        <button onclick="clearAll11();">Clear All Subject</button>
        <br>
        <div id="submit-button-container">
        
        <c:if test="${empty form.facultyId}">  
          <label class="button-label" for="add-form-button" tabindex="0">Add Faculty</label>
        </c:if>
         <c:if test="${!empty form.facultyId}">  
          <label class="button-label" for="update-form-button" tabindex="1">Update Faculty</label>
         </c:if>
        </div>
    </p>
</div>



<div id="table-container">
    <table>    
        <th>iinex</th>
        <th>facultyId</th>
        <th>facultyName</th>
        <th>Edit</th>
        <th>Remove</th>
        <th>subjectList.subjectName</th>
        <th>class teacher</th>
            <c:forEach var="item" items="${facultylist}" varStatus="i" begin="0">
            <tr>

                <td>${i.index}</td>
                <td>${item.facultyId}</td>    
                <td>${item.facultyName}</td>
                <td><a href="<c:url value="/faculty/edit/${item.facultyId}"/>">Edit</a></td>
                <td><a href="<c:url value="/faculty/remove/${item.facultyId}"/>">Remove</a></td>
                <td>
                    <table class="tbinsdie">
                        <c:forEach var="subitem" items="${item.subjectList}">
                            <tr>

                                <td>${subitem.subjectId}</td>
                                <td>${subitem.subjectName}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
                <td>
                    
                    <c:if test="${!empty item.classes.levelId}">
                         <table class="tbinsdie">    
                       
                            <tr>
                                
                                <td>
                                    sec:<c:out value="${item.classes.sectionId.sectionId}"></c:out> </td>
                            </tr>
                            <tr>
                                <td>lvl: ${item.classes.levelId.levelId}</td>
                            </tr>
                            
                         
                            
                           </table>
                        </c:if>
                    
                </td>   

            </tr>
        </c:forEach>
</div>

<%@include file="./shared/footer.jsp"%>