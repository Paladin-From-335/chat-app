const headers = {
    'Content-Type': '*',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Credentials': 'true',
    'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept',
    'crossdomain': 'true',
}

function authorization() {
    const login = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    let authData = {
        'login': login,
        'password': password
    }


    axios.get('http://127.0.0.1:8080', authData)
        .then((res) => {
            console.log(res.status)
            if(res.status === 200){
                console.log("OK");
                document.location.href='../../Chat/chat.html'
            }
        })
        .catch((error) => {
            console.error(error)
        })
}

function registration() {
    const login = document.getElementById('username_reg').value;
    const firstname = document.getElementById('firstname').value;
    const lastname = document.getElementById('lastname').value;
    const password = document.getElementById('password_pass').value;
    const confPassword = document.getElementById('cpassword').value;
    const email = document.getElementById('email').value;

    let postData = {
        'login': login,
        'firstname': firstname,
        'lastname': lastname,
        'password': password,
        'confPassword': confPassword,
        'email': email
    };

    axios.post("http://127.0.0.1:8080", postData, {headers})
        .then((response) => {
                console.log(response);
            }, (error) => {
                console.log(error);
            }
        )
}