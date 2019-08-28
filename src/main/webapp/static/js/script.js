var button = document.getElementsByName("button");
console.log(button);
button.forEach(function (element) {
    element.addEventListener("click", function () {
            console.log("az");
            var id = element.getAttribute("id");
            var data = {"id": id};
            var post = new XMLHttpRequest();
            post.open("POST", "/cart");
            post.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
            post.send(JSON.stringify(data));
            post.onreadystatechange = function (ev) {
                if (post.readyState === 4) {
                    callback(post.response);
                }

            }
        }
    )
})


function callback(response) {
    console.log(JSON.parse(response))
}

