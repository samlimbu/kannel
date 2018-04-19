$(document).ready(function(){
     var DATA = [];
     getNumbers(function(data){
          console.log('async ajax call' + data);
          DATA = JSON.parse(data);
          console.log("DATA[] variable:" + DATA);
          $('#submit-all').removeAttr('hidden');
          $('#submit-all').click(function(){
               var message = $('#message').val();
               postAjax(message, DATA,function(messageData){
                    displayMessages(messageData);
               });

          });
     });

     getMessages(function(messageData){
          displayMessages(messageData);
     });
   
});
  
function postAjax(message, data, done) {
     
     console.log('DATA[] post: ' + data);
     $.ajax({
          headers: {  'Access-Control-Allow-Origin': '*',
                    'Access-Control-Allow-Headers': 'Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With',
                    'Access-Control-Allow-Methods': 'GET, PUT, POST'
     },
          crossDomain: true,
          type : "POST",
          contentType : "application/json",
          url : "http://localhost:8080/sms/api/post/"+message,
          data : JSON.stringify(data),
          dataType : 'json',
          timeout : 100000,
          success : function(messageData) {
               done(messageData);
          },
          error : function(e) {
               console.log("ERROR: ", e);
               display(e);
          },
          done : function(e) {
               console.log("DONE");
               
          }
     });

}
function getNumbers(done){
    
     $.ajax({
          headers: {  'Access-Control-Allow-Origin': '*',
                    'Access-Control-Allow-Headers': 'Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With',
                    'Access-Control-Allow-Methods': 'GET, PUT, POST'
     },
          crossDomain: true,
          type : "GET",
          url : "http://localhost:8080/sms/api",
          
          timeout : 100000,
          success : function(data){
                
                done(data);
                              
          },
          error : function(e) {
               console.log("ERROR: ", e);
               display(e);
          },
          done : function(e) {
               console.log("DONE");
               
          }
     });
     
}

function getMessages(done){
     $.ajax({
          headers: {  'Access-Control-Allow-Origin': '*',
                    'Access-Control-Allow-Headers': 'Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With',
                    'Access-Control-Allow-Methods': 'GET, PUT, POST'
     },
          crossDomain: true,
          type : "GET",
          url : "http://localhost:8080/sms/api/message",
          
          timeout : 100000,
          success : function(data){
               done(data);
                              
          },
          error : function(e) {
               console.log("ERROR: ", e);
               display(e);
          },
          done : function(e) {
               console.log("DONE");
               
          }
     });
}


function display(data) {
     var json = "<h4>Ajax Response</h4><pre>"
               + JSON.stringify(data, null, 4) + "</pre>";
     $('#feedback').html(json);
    
    var jsonData = JSON.parse(data);
          for (var i = 0; i < jsonData.length; i++) {
          var counter = jsonData[i];
          console.log(counter.id);
     }
     //console.log('DATA[] : ' + DATA);
}

function displayMessages(messageData){
     console.log(messageData);
     $('#feedback2').html("s");
     var dataList = JSON.parse(messageData);
    
 
     var columnHead = "<th>Number</th><th>Message</th><th>Status</th><th>Sid</th>";
     var td = "";
     for(var i = 0; i < dataList.length; i++){
          td += "<tr><td>" + dataList[i].numberId.number  +"<td>"+dataList[i].body+"</td>" + "<td>"+dataList[i].status+"</td>"+ "<td>"+dataList[i].sid+"</td>"+"</tr>";
     }
     
     $('#feedback2').html("<table>"+columnHead +  td + "</table>");
}