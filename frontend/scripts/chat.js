const status = document.getElementById('status');
const messages = document.getElementById('messages');
const form = document.getElementById('form');
const textarea = document.getElementById('textarea');

const ws = new WebSocket('ws://localhost:3000');

function setStatus (value){
    status.innerHTML = value;
}

function printMessage(value){
        const messageContainer = document.createElement('div');
        messageContainer.className = "messageDiv";
        messageContainer.innerHTML = value;
        messages.appendChild(messageContainer);

}

form.addEventListener('submit', e => {
    e.preventDefault();

    ws.send(textarea.value);
    textarea.value = '';
})

ws.onopen = () => setStatus('You are online');

ws.onclose = () => setStatus('Disconnected');

ws.onmessage = res => printMessage(res.data);

document.getElementById('button').addEventListener('click', (event) => {
    if(!textarea){
        printMessage(textarea.target.value);
    }
    textarea.target.value = "";

});


function enterButton(event){
    if(event.keyCode === 13){
        printMessage(event.target.value);
        event.preventDefault();
        textarea.value = '';
    }
}
