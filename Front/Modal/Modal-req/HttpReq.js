function login(){
    let newSocket = new WebSocket("ws://echo.websocket.org")
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value
    const logIn = {
        method: "GET",
        type: "login",
        username: username,
        password: password
    }
    newSocket.onopen = function (ev){
        console.log("Connection is opened");

        newSocket.send(JSON.stringify(logIn));
        // newSocket.onmessage = function (getEv){
        //     const msg = JSON.parse(getEv.data);
        // }
    }
    newSocket.onclose = function (ev){
        console.log("Connection closed");
    }
    newSocket.onmessage = function (ev){

        console.log(JSON.parse(ev.data));
    }
    newSocket.onerror = function (ev){
        console.log(ev.code + "ERROR");
    }
}

function registration() {
    const pass = document.getElementById("password_pass").value;
    const checkPass = document.getElementById("cpassword").value;
    let regIn;
    if(pass === checkPass){
        let newSocket = new WebSocket("ws://echo.websocket.org");
        regIn = {
            type: "registration",
            firstName: document.getElementById("firstname").value,
            lastName: document.getElementById("lastname").value,
            username: document.getElementById("username_reg").value,
            email: document.getElementById("email").value,
            password: document.getElementById("password_pass").value,
        };

        newSocket.onopen = function (event) {
            console.log("CONNECTION");

            newSocket.send(JSON.stringify(regIn));
            // newSocket.onmessage = function (getEvent) {
            //     const msg = JSON.parse(getEvent.data);
            //     if(msg === "ok") {
            //
            //     }
            // }
        }
        newSocket.onmessage = function (ev) {
            let msg = JSON.parse(ev.data);
            alert("Registration successful");
            setTimeout(() => { window.location.reload()}, 1000);
            newSocket.close()
        }
        newSocket.onclose = function (ev){
            console.log("Closed")
        };
    } else if(pass !== checkPass){
        alert("Password mismatch");
    }
}