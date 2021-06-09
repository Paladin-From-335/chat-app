const status = document.getElementById('status');
const messages = document.getElementById('messages');
const form = document.getElementById('form');
const textarea = document.getElementById('textarea');


const ws = new WebSocket('ws://localhost:8081/chat');

function setStatus(value) {
    status.innerHTML = value;
}

// function printMessage(value) {
//     console.log(value.data)
//     let msg = JSON.parse(value)
//     const messageContainer = document.createElement('div');
//     messageContainer.className = "messageDiv";
//     messageContainer.innerHTML = msg.message;
//     messages.appendChild(messageContainer);
// }

function printMessage(value){
    const messageContainer = document.createElement('div');
    messageContainer.className = "messageDiv";
    messageContainer.innerHTML = value;
    messages.appendChild(messageContainer)
    sendMsg();
}

function sendMsg() {
    ws.send(JSON.stringify({message:textarea.value, topic:"MESSAGES", payload:sessionStorage.getItem("token")}));
    textarea.value = '';

}

document.getElementById('button').addEventListener('click', (event) => {

    printMessage(textarea.value)

});

document.getElementById('exit').addEventListener('click', (e) => {
    ws.close()
    document.location = "..\\html\\modal.html"
})

// let localToken = sessionStorage.getItem("token").split(".")
// console.log(localToken[0]);

function onopenSend() {
    let env = JSON.stringify({
        topic: 'AUTHORIZATION',
        payload: sessionStorage.getItem("token"),
        message: "qwerty123",
        nickname: 'nick',
        date: ""
    });
    ws.send(env);
}

ws.onopen = () => {
    onopenSend();
    setStatus('You are online');
}

ws.onmessage = res => {
    console.log(res);
    console.log(res.data);
    printMessage(res.data);
}

ws.onclose = () => setStatus("You are offline")
