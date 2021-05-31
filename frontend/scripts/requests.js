const login = document.getElementById('username_reg');
const firstname = document.getElementById('firstname');
const lastname = document.getElementById('lastname');
const password = document.getElementById('password_pass');
const cpassword = document.getElementById('cpassword');
const phone = document.getElementById('phone');
const nickname = document.getElementById('nickname');
const email = document.getElementById('email');

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
            console.log(response.status);
                if (response.status === 202) {
                    sessionStorage.setItem("token", response.data);
                    document.location = "..\\html\\chat.html";
                    console.log(response.token);
                    // const ws = new WebSocket("ws://localhost:8081/chat");
                    const auth = JSON.stringify({"topic": "AUTHORIZATION", "payload": response.token});
                } else {
                    console.log(response.status);
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
function isValidInput(){
    const regexLogin = /^[A-Za-z\d]{0,50}\S$/;
    const regexFNLN = /^[a-zA-Z-' ]+[a-zA-Z]\S$/;
    const regexPhone = /[^+][0-9]{0,13}\S$/;
    const regexNick = /^[A-Za-z\d]{0,50}\S$/;
    const regexEmail = /^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{1,6}\S$/
    let checkLogin = regexLogin.test(login.value);
    let checkFName = regexFNLN.test(firstname.value);
    let checkLName = regexFNLN.test(lastname.value);
    let checkPhone = regexPhone.test(phone.value);
    let checkNick = regexNick.test(nickname.value);
    let checkEmail = regexEmail.test(email.value);
    if (!checkLogin || !checkFName || !checkLName || !checkPhone || !checkNick || !checkEmail || password.value !== cpassword.value) {
        alert("Invalid data.\n firstname and lastname must contain only letters\n login and nickname must contain only letters and digits\n phone number example: '+XXXXXXXXXXXXX'\n email example: 'email@example.com'\n password and confirm password must be the same")
    } else{
        alert('SUCCESS');
        registration();
    }
}

function sendEmailForRecovery() {
    const email = document.getElementById('email_for_recovery').value;
    let data = {
        email: email
    }
    axios.post("http://localhost:8081/login/recovery", data, {
        headers: {"Content-Type": "application/json"}
    })
        .then((response) => {
            if (response.status === 200) {
                alert("Check your email")
            } else {
                alert("Wrong email")
            }
        });
}

function sendSecretCode() {
    const secureCode = document.getElementById('secret_code').value;
    const email = document.getElementById('email_for_recovery').value;
    let data = {
        email: email,
        secureCode: secureCode
    }
    axios.post("http://localhost:8081/login/recovery/code", data, {
        headers: {"Content-Type": "application/json"}
    })
        .then((response) => {
            console.log(response.status)
            if (response.status === 200) {
                console.log(response.status)
                document.location = "..\\html\\changePass.html"
            } else {
                alert("Wrong code, try again")
                console.log(response.status)
            }
            console.log(response.status)
        });
}

function sendNewPassword() {
    const email = document.getElementById('email_for_recovery').value;
    const newPassword = document.getElementById('new_password').value;
    const confNewPass = document.getElementById('confirm_new_password').value;

    let data = {
        email: email,
        password: newPassword,
        confPass:confNewPass
    }

    axios.post("http://localhost:8081/login/recovery/change", data, {
        headers: {"Content-Type": "application/json"}
    })
        .then((response) => {
            if(response.status === 200) {
                alert("Your password has been changed")
                document.location = "..\\html\\modal.html"
            } else {
                alert("Wrong email")
            }
        })
}