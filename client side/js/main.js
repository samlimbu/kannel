$(document).ready(function () {
  $("#form").on('submit', function (e) {
    showMessage("Submitted Form");
    $("#campaign-form").trigger('reset');
    $("#campaign-form").css("visibility", "hidden");
    
 });
     $('#click-button').click(function () {
          $("#campaign-form").css("visibility", "visible");
          $('#cancel-button').click(function(){
               $("#campaign-form").css("visibility", "hidden");
               $('#campaign-form').trigger("reset");
               console.log('cancael');
          });
     });
     
     
     getCampaign();
     AutoReload();

     //1XMR797JZYPEn

     
});
var localhostUrl = 'http://localhost:8080/sms';

function AutoReload() {
     setInterval(function () {
         
          getCampaign();

     }, 5000);
} 
function showMessage(text){
  $('#message').html(text); 
  setTimeout(function(){ 
    $('#message').html(""); 
  }, 3000);
}


function getCampaign() {

     $.ajax({
          headers: {
            
               'Access-Control-Allow-Origin': '*',
               'Access-Control-Allow-Headers': 'Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With',
               'Access-Control-Allow-Methods': 'GET, PUT, POST',
          },
          crossDomain: true,
          type: "GET",
          dataType: "json",
          processData: "false",

          url: "http://localhost:8080/sms/api/campaign",
         
          timeout: 100000,
          success: function (data) {
               displayData(data);
          },
          error: function (e) {
               console.log("ERROR: ", e);
               document.getElementById("logs").innerHTML = e;
          },
          done: function (e) {
               console.log("DONE");

          }
     });

}

function displayData(data) {
     var dataList = data;

     var td = '<table class="campaign-table"><th>ID</th><th>Campaign Name</th><th>Created Date</th><th>Schedule Date</th><th>SMS Text</th><th>Status</th><th>Actions</th>';
     for (var i = 0; i < dataList.length; i++) {
          if(!dataList[i].deleted){
               var createdDate = timeConverter(dataList[i].createdDate);
               console.log(createdDate);
               td += "<tr><td>" + dataList[i].id + "</td>" + "<td>" + dataList[i].campaignName + "</td>" + "<td>" + createdDate + "</td>"+ "<td>" + dataList[i].scheduleDate + "</td>"+"<td id='f-td' style='width:30px'>" + dataList[i].sms + "</td>"+"<td>" + dataList[i].status + "</td>"+ "</td>" +
               
               "<td>"+"<a target='_blank' href="+localhostUrl+"/CsvtoSql?"+"campaignId="+dataList[i].id + 
               "&smsText="+dataList[i].sms+">"+"Start</a>"+"<a target='_blank' href="+localhostUrl+"/stopCampaign?"+"campaignId="+dataList[i].id+ ">" +"</a>"+"<a target='_blank' href="+localhostUrl+"/deleteCampaign?"+"campaignId="+dataList[i].id+">"+" Delete</a>"+"</td>"
               + "</tr>";
          }
     }
     document.getElementById("campaign-container").innerHTML = td + '</table>';
}

function timeConverter(UNIX_timestamp){
     var a = new Date(UNIX_timestamp * 1);
     var months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
     var year = a.getFullYear();
     var month = months[a.getMonth()];
     var date = a.getDate();
     var hour = a.getHours();
     var min = a.getMinutes();
     var sec = a.getSeconds();
     var time = date + ' '+ month + ' ' + year + ' ' + hour + ':' + min;// + ':' + sec ;
     return time;
   }