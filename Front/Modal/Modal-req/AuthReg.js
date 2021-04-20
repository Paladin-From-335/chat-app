const username = "";
const password = "";

const access_token = null //mytoken;

function authorization() {
    axios.get('https://api.github.com/user', {
        headers: {
            'Authorization': `token ${access_token}`
        }
    })
        .then((res) => {
            console.log(res.data)
        })
        .catch((error) => {
            console.error(error)
        })
}

function registration() {
    const username = document.getElementById('username_reg').value;
    const firstname = document.getElementById('firstname').value;
    const lastname = document.getElementById('lastname').value;
    const password_reg = document.getElementById('password_pass').value;
    const cpassword = document.getElementById('cpassword').value;
    const email_reg = document.getElementById('email').value;

    const options = {
        headers: {'X-Custom-Header': 'value'}
    };

    axios.post("http://localhost:8080", {
        username, firstname, lastname, password_reg, cpassword, email_reg
    }, options)
        .then((response) => {
                console.log(response);
            }, (error) => {
                console.log(error);
            }
        )
    }