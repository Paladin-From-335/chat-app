const status = document.getElementById('status');
const messages = document.getElementById('messages');
const form = document.getElementById('form');
const textarea = document.getElementById('textarea');

const ws = new WebSocket('ws://localhost:8081/chat');

function setStatus(value) {
    status.innerHTML = value;
}

// form.addEventListener('submit', e => {
//     e.preventDefault();
// let stringMessage = textarea.value.toString();
//     ws.send(stringMessage);
//     // textarea.value = '';
// })
ws.onopen = () => setStatus('You are online');


// ws.onmessage = function processMessage(value) {
//     let jsonData = JSON.parse(value.data);
//
//         let div = document.createElement('div');
//         let message = jsonData.value;
//         div.className = "messageDiv";
//         div.appendChild(message);
//         form.appendChild(div);
//
// }
function printMessage() {
    const messageContainer = document.createElement('div');
    messageContainer.className = "messageDiv";
    messageContainer.innerHTML = textarea.value;
    messages.appendChild(messageContainer);

}

function sendMsg() {
    ws.send(JSON.stringify({payload:textarea.value, topic:"MESSAGES"}));
    textarea.value = '';
}

document.getElementById('button').addEventListener('click', (event) => {
    printMessage();
    sendMsg();

});


ws.onclose = () => setStatus('You are offline');

console.log(sessionStorage.getItem("token"))

