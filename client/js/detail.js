$(document).ready(function() {
    const linkRestaurant = "http://localhost:8080/restaurant"
    let searchParams = new URLSearchParams(window.location.search)
    const token = localStorage.getItem("token")
    var param
    if(searchParams.has('id')){
        param = searchParams.get('id')
    }
    console.log(token)
    $.ajax({
        method: "GET",
        url: `${linkRestaurant}/detail?id=${param}`,
        dataType: 'json',
        contentType: "application/json",
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', `Bearer ${token}`);
        }
        })
        .done(function( msg ) {
            if(msg.isSuccess){
                console.log(msg.data)  
            }else{
                alert("failed")
            }
        });
})