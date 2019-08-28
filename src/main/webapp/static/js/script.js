var cartButton = document.getElementById("cart-button");
var value = 0;
cartButton.innerHTML=`Cart (${value})`;

var buttons = document.getElementsByName("button");
buttons.forEach(function (button) {
    button.addEventListener("click", function () {
            var id = button.getAttribute("id");
            var data = {"id": id};
            var request = new XMLHttpRequest();
            request.open("POST", "/cart");
            request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
            request.send(JSON.stringify(data)); // elküldi a product id-ját a cartcontrollernek
            request.onreadystatechange = function (ev) { //Use XMLHttpRequest (XHR) objects to interact with servers. You can retrieve data from a URL without having to do a full page refresh. This enables a Web page to update just part of a page without disrupting what the user is doing.
                console.log(ev);
                if (request.readyState === 4) {
                    callback(request.response);
                }
            }
        }
    )
});
function callback(response) {
    value = JSON.parse(response).sumOfAllProductsInCart; // miért nem működik get-tel?
    cartButton.innerHTML=`Cart (${value})`;
    console.log(response)
}
