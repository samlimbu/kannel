//http://localhost:8080/sms/api/number
$(document).ready(function () {

  $('#submit').click(function () {
    var username = $('#username').val();
    var password = $('#password').val();
    login(username, password, function (data) {
      if (data != null && data.response == "true") { //redirect...
        window.location = "campaign.html";
      } else { 
        
        showMessage("Wrong username or password");
       }
    });
  });

function showMessage(text){
  $('#message').html(text); 
  setTimeout(function(){ 
    $('#message').html(""); 
  }, 3000);
}




  function login(username, password, done) {

    $.ajax({
      headers: {
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Headers': 'Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With',
        'Access-Control-Allow-Methods': 'GET, PUT, POST'
      },
      //crossDomain: true,
      type: "GET",
      url: "http://localhost:8080/sms/login?username=" + username + "&password=" + password,

      timeout: 100000,
      success: function (data) {
        console.log(data);
        done(data);

      },
      error: function (e) {
        console.log("ERROR: ", e);

      },
      done: function (e) {
        console.log("DONE");

      }
    });

  }

});