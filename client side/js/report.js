//http://localhost:8080/sms/api/number
$(document).ready(function () {
     var index = -1;
     $('#click-button').click(function () {
     });
     getNumbers();
     getCampaign(function (dataList) {



          var select = "Select Campaign<select> <option disabled selected value> -- select campaign -- </option>"

          for (var i = 0; i < dataList.length; i++) {
               select += " <option value=" + dataList[i].id +
                    ">" + dataList[i].id + " " + dataList[i].campaignName + "</option>";
               //console.log(dataList[i].mobileNumber);
          }

          document.getElementById("campaign-drop-menu").innerHTML = select + '</select>';

          $('select').on('change', function () {

               index = (this.value);
               getNumbers(index);
          })
     });



     AutoReload();

     //1XMR797JZYPEn




     function AutoReload() {
          setInterval(function () {
              if(index>=0){
                    getNumbers(index);
              }  
              
          }, 8000);
     }



     function checkFile() {
          var x = document.getElementById("myFile").value;
          document.getElementById("logs").innerHTML = x;
          console.log(x);
     }


     function getNumbers(index) {

          $.ajax({
               headers: {
                    'Access-Control-Allow-Origin': '*',
                    'Access-Control-Allow-Headers': 'Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With',
                    'Access-Control-Allow-Methods': 'GET, PUT, POST'
               },
               crossDomain: true,
               type: "GET",
               url: "http://localhost:8080/sms/api/number",

               timeout: 100000,
               success: function (data) {
                //    var dataList = JSON.parse(data);
                    //console.log("getNumbers"+ data[0].campaignId.id);

                    displayData(data, index);


               },
               error: function (e) {
                    console.log("ERROR: ", e);

               },
               done: function (e) {
                    console.log("DONE");

               }
          });

     }
     function getCampaign(done) {

          $.ajax({
               headers: {
                    'Access-Control-Allow-Origin': '*',
                    'Access-Control-Allow-Headers': 'Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With',
                    'Access-Control-Allow-Methods': 'GET, PUT, POST'
               },
               crossDomain: true,
               type: "GET",
               url: "http://localhost:8080/sms/api/campaign",

               timeout: 100000,
               success: function (dataList) {
                    //var dataList = JSON.parse(data);
                    done(dataList);
               },
               error: function (e) {
                    console.log("ERROR: ", e);
                    display(e);
               },
               done: function (e) {
                    console.log("DONE");

               }
          });

     }
     function displayData(dataList, index) {
            console.log('cur index' + index);
          //console.log(dataList[1].campaignId.id);
          //dataList[0].campaignId.id
          document.getElementById("report-details").innerHTML = '';
          var td = '<table class="campaign-table"><th>SNo.</th><th>Mobile Number</th><th>SMS Text</th><th>SentDateTime</th><th>Send Status</th><th>Delivery DateTime</th><th>Delivery Response</th>';


          for (var i = 0; i < dataList.length; i++) {
              if (dataList[i].campaignId.id==index) {
                    var sentDateTime = timeConverter(dataList[i].sentDateTime);
                    var deliveryDateTime = timeConverter(dataList[i].deliveryDatetime);

                    td += "<tr><td>" + dataList[i].id + "</td>" + "<td>" + dataList[i].mobileNumber + "</td><td id='f-td' style='width:30px'>" + dataList[i].smsText + "</td><td>" + sentDateTime + "</td><td>" + dataList[i].sendstatus + "</td><td>" + deliveryDateTime + "</td><td>" + dataList[i].deliveryResponse + "</td>" +  "</tr>";
              }
               //console.log(dataList[i].mobileNumber);
          }

          document.getElementById("report-details").innerHTML = td + '</table>';
     }

});

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