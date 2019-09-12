let cartButton = document.getElementById("cart-button");
let value = 0;
cartButton.innerHTML = `Cart (${value})`;

let logoutButtonElement = document.createElement("button");
logoutButtonElement.setAttribute('class', "btn btn-outline-secondary");
logoutButtonElement.setAttribute('id', "logout-button");
logoutButtonElement.textContent = "Logout";

let headerRight = document.getElementById("header-right");


let loginButton = document.getElementById("login-button");
loginButton.innerHTML = "Login";

loginButton.addEventListener("click", function () {
    let loginStatusPharagraph = document.getElementById("loginStatus");
    let inputUsername = document.getElementById("username");
    let inputPassword = document.getElementById("password");
    loginStatusPharagraph.textContent = "";
    inputUsername.value = null;
    inputPassword.value = null;
    $('#login-modal').modal('show');
})

logoutButtonElement.addEventListener("click", function () {
    get("/logout", function (response) {
        headerRight.replaceChild(loginButton, headerRight.childNodes.item(5))

    })
})

let loginSubmit = document.getElementById("login");
loginSubmit.addEventListener("click", function () {
    let name = document.getElementById("username").value;
    console.log(name);
    let password = document.getElementById("password").value;
    console.log(password);
    let data = {"username": name, "password": password};
    post("/login", data, function (response) {
        console.log(response.status);
        if (response.status) {
            $('#login-modal').modal('hide');
            headerRight.replaceChild(logoutButtonElement, headerRight.childNodes.item(5))
            getSession()

        } else {
            let loginStatusPharagraph = document.getElementById("loginStatus");
            loginStatusPharagraph.innerText = "Username or Password is wrong"
        }
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
        if (response.status) {
            $('#registration-modal').modal('hide')
        } else {
            let registrationStatusPharagraph = document.getElementById("registrationStatus");
            registrationStatusPharagraph.textContent = "Username is already in use";
        }

    })
});


let registrationButton = document.getElementById("reg-button");
registrationButton.innerHTML = "Register";

registrationButton.addEventListener("click", function () {
    let registrationStatusPharagraph = document.getElementById("registrationStatus");
    let inputUsername = document.getElementById("uname");
    let inputPassword = document.getElementById("passwd");
    let inputEmail = document.getElementById("email");
    let inputPhoneNumber = document.getElementById("phone");
    let inputBaddress = document.getElementById("badress");
    let inputSadress = document.getElementById("sadress");
    registrationStatusPharagraph.textContent = "";
    inputUsername.value = null;
    inputPassword.value = null;
    inputEmail.value = null;
    inputBaddress.value = null;
    inputSadress.value = null;
    inputPhoneNumber.value = null;
    $('#registration-modal').modal('show');
})

let buttons = document.getElementsByName("button");
buttons.forEach(function (button) {
    button.addEventListener("click", function () {
            let id = button.getAttribute("id");
            let data = {"id": id};
            post("/cart", data, function (response) {
                callback(response)
            })
        }
    )
});

function callback(response) {
    value = response.sumOfAllProductsInCart; // miért nem működik get-tel?
    cartButton.innerHTML = `Cart (${value})`;
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

function get(url, callback) {
    fetch(url)
        .then(response => response.json())
        .then(json_response => callback(json_response))

}

function getSession() {
    get("/session", function (response) {
        let cartSize = response.cartSize;
        let username = response.username;
        let loginStatus = response.loginStatus;
        cartButton.innerHTML = `Cart (${cartSize})`;
        if (loginStatus) {
            headerRight.replaceChild(logoutButtonElement, headerRight.childNodes.item(5));
            if (username !== "Guest") {
                let welcomeMessage = document.getElementById("welcome-message");
                welcomeMessage.innerHTML = `You are logged in as ${username}`
            }
        }
    })
}

getSession();


