function authorization() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    let logIn = {
        login: username,
        password: password
    };

    axios.post("http://localhost:8081/login/auth", logIn, {
        headers: {"Content-Type": "application/json"}
    })
        .then((response) => {
                if (response.status === 202) {
                    localStorage.setItem("token", response.data);
                    console.log(response.data);
                    document.location = "..\\html\\chat.html";
                }
            }, (error) => {
                console.log(error);

            }
        )
}

function registration() {
    const login = document.getElementById('username_reg').value;
    const firstname = document.getElementById('firstname').value;
    const lastname = document.getElementById('lastname').value;
    const password = document.getElementById('password_pass').value;
    const confPassword = document.getElementById('cpassword').value;
    const email = document.getElementById('email').value;
    const nickname = document.getElementById('nickname').value;
    const phone = document.getElementById('phone').value;

    let regData = {
        firstname: firstname,
        lastname: lastname,
        email: email,
        login: login,
        password: password,
        phone: phone,
        confPassword: confPassword,
        nickname: nickname
    };

    axios.post("http://localhost:8081/login/registration", regData, {
        headers: {"Content-Type": "application/json"}
    })
        .then((response) => {
                if (response.status === 200) {
                    console.log(response.status)
                    alert("Success")
                    document.location = "..\\html\\modal.html"
                }
            }, (error) => {
                console.log(error);
            }
        )

}