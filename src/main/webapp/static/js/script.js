var cartButton = document.getElementById("cart-button");
var value = 0;
cartButton.innerHTML=`Cart (${value})`;

var button = document.getElementsByName("button");
button.forEach(function (element) {
    element.addEventListener("click", function () {
            var id = element.getAttribute("id");
            var data = {"id": id};
            var post = new XMLHttpRequest();
            post.open("POST", "/cart");
            post.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
            post.send(JSON.stringify(data));
            post.onreadystatechange = function (ev) {
                console.log(ev);
                if (post.readyState === 4) {
                    callback(post.response);
                }
            }
        }
    )
});
function callback(response) {
    value = JSON.parse(response).cartSize;
    cartButton.innerHTML=`Cart (${value})`;
    console.log(response)
}
