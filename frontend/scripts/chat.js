const status = document.getElementById('status');
const messages = document.getElementById('messages');
const form = document.getElementById('form');
const textarea = document.getElementById('textarea');

const ws = new WebSocket('ws://localhost:8081/chat');

function setStatus(value) {
    status.innerHTML = value;
}

// function printMessage(value) {
//     const messageContainer = document.createElement('div');
//     messageContainer.className = "messageDiv";
//     messageContainer.innerHTML = value;
//     messages.appendChild(messageContainer);
//
// }
//
// form.addEventListener('submit', e => {
//     e.preventDefault();
// let stringMessage = textarea.value.toString();
//     ws.send(stringMessage);
//     // textarea.value = '';
// })
ws.onopen = () => setStatus('You are online');


ws.onmessage = function processMessage(value) {
    let jsonData = JSON.parse(value.data);
    if (jsonData.value != null) {
        let div = document.createElement('div');
        let message = jsonData.value;
        div.appendChild(message);
        form.appendChild(div);
    }
}

function sendMsg() {
    ws.send(JSON.stringify({payload:textarea.value, topic:"MESSAGES"}));
    textarea.value = '';
}

document.getElementById('button').addEventListener('click', (event) => {
    sendMsg();
});


ws.onclose = () => setStatus('You are offline');
