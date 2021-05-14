const username = document.getElementById("username").value;
const password = document.getElementById("password").value;

let logIn = {
    login: username,
    password: password
};

function authorization() {
    axios.post("http://localhost:8081/login/auth", logIn)
        .then((response) => {
                if (response.status >= 200 && response.status < 300) {
                    localStorage.setItem("token", response.data);
                    console.log(response.data);
                    document.location = "..\\html\\chat.html";
                }
            }, (error) => {
                console.log(error);
                window.location = '..\\html\\NotFound.html';
            }
        )
}


function registration() {

    let xhr = new XMLHttpRequest();

    const login = document.getElementById('username_reg').value;
    const firstname = document.getElementById('firstname').value;
    const lastname = document.getElementById('lastname').value;
    const password = document.getElementById('password_pass').value;
    const confPassword = document.getElementById('cpassword').value;
    const email = document.getElementById('email').value;

    let postData = {
        firstname: firstname,
        lastname: lastname,
        email: email,
        login: login,
        nickname: "Pupka",
        password: password,
        confPassword: confPassword,
        phone: "88005553535",
    };

    axios.post("http://localhost:8081/login/registration", postData, {
        headers: {
            'Content-Type':'application/json'
        }
    })
        .then((response) => {
                if (response.status >= 200 && response.status < 300) {
                    console.log("success");
                    document.location = "..\\html\\modal.html"
                }
            }, (error) => {
                console.log(error);
            }
        )

}