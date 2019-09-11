let cartButton = document.getElementById("cart-button");
let value = 0;
cartButton.innerHTML=`Cart (${value})`;

let loginButton = document.getElementById("login-button");
loginButton.innerHTML = "Login";

let loginSubmit = document.getElementById("login");
loginSubmit.addEventListener("click", function () {
    let name = document.getElementById("username").value;
    console.log(name);
    let password = document.getElementById("password").value;
    console.log(password);
    let data = {"username": name, "password": password};
    post("/login", data, function (response) {
        console.log(response)
        $('#login-modal').modal('hide');
    });
});

let registrationSubmit = document.getElementById("register");
registrationSubmit.addEventListener("click", function () {
    let name = document.getElementById("uname").value;
    let password = document.getElementById("passwd").value;
    let email = document.getElementById("email").value;
    let phone = document.getElementById("phone").value;
    let badress = document.getElementById("badress").value;
    let sadress = document.getElementById("sadress").value;

    let data = {
        "username": name,
        "password": password,
        "email": email,
        "phone_number": phone,
        "billing_address": badress,
        "shipping_address": sadress
    };
    post("/registration", data, function (response) {
        console.log(response)
    })
});


let registrationButton = document.getElementById("reg-button");
registrationButton.innerHTML = "Register";

let buttons = document.getElementsByName("button");
buttons.forEach(function (button) {
    button.addEventListener("click", function () {
        let id = button.getAttribute("id");
        let data = {"id": id};
        /*let request = new XMLHttpRequest();
            request.open("POST", "/cart");
            request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
            request.send(JSON.stringify(data)); // elküldi a product id-ját a cartcontrollernek
            request.onreadystatechange = function (ev) { //Use XMLHttpRequest (XHR) objects to interact with servers. You can retrieve data from a URL without having to do a full page refresh. This enables a Web page to update just part of a page without disrupting what the user is doing.
                console.log(ev);
                if (request.readyState === 4) {
                    callback(request.response);
                }
            }*/
        post("/cart", data, function (response) {
            callback(response)
        })
        }
    )
});
function callback(response) {
    value = response.sumOfAllProductsInCart; // miért nem működik get-tel?
    cartButton.innerHTML=`Cart (${value})`;
    console.log(response)
}

function post(url, data, callback) {
    fetch(url, {
        method: 'POST',
        credentials: 'same-origin',
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
        .then(response => response.json())
        .then(json_response => callback(json_response))
}
