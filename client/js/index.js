$(document).ready(function() {
    const linkRestaurant = "http://localhost:8080/restaurant"
    const token = localStorage.getItem("token")
    console.log(token)
    $.ajax({
        method: "GET",
        url: linkRestaurant,
        dataType: 'json',
        contentType: "application/json",
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', `Bearer ${token}`);
        }
        })
        .done(function( msg ) {
            if(msg.isSuccess){
                console.log(msg.data)
                $.each(msg.data, function(index, item){
                    console.log(item)
                    var html =`
                    <li>
                        <img src="${linkRestaurant}/files/${item.image}" alt="">
                        <div class="restaurant-details">
                                <h2 class="restaurant-name">${item.title}</h2>
                                <p class="restaurant-subtitle">${item.subtitle}</p>
                                <p class="restaurant-distance">0.3km</p>
                                <span>${item.rating.toFixed(2)} rating</span>
                                <h3>free ship</h3>
                        </div>
                    </li>
                    `
                    $("#feature_restaurant").append(html);
                })
                
            }else{
                alert("failed")
            }
        });
})