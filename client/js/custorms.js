$(document).ready(function() {
    $("#btn-signIn").click(function(){
        var username = $("#email").val();
        var password = $("#password").val();
        $.ajax({
            method: "POST",
            url: "http://localhost:8080/login/signIn",
            data: { 
                username,
                password
             }
          })
            .done(function( msg ) {
                if(msg.isSuccess){
                    localStorage.setItem("token",msg.data)
                    window.location.href = './index.html'
                }else{
                    alert("failed")
                }
            });
    })
})