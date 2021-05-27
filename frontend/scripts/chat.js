const status = document.getElementById('status');
const messages = document.getElementById('messages');
const form = document.getElementById('form');
const textarea = document.getElementById('textarea');

const ws = new WebSocket('ws://localhost:8081/chat');

function setStatus(value) {
    status.innerHTML = value;
}

function printMessage(value) {
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

ws.onclose = () => setStatus('You are offline');

// ws.onmessage = res => printMessage(res.data);
ws.onmessage = res => console.log(res.data);

document.getElementById('button').addEventListener('click', (event) => {
    if (!textarea) {
        printMessage(textarea.target.value);
    }
    // textarea.value = "";
});


// window.addEventListener('keypress', (e) => {
//     if (e.key === "Enter") {
//         if (!textarea) {
//             printMessage(textarea.value);
//         }
//         e.preventDefault();
//     }
// });

console.log(sessionStorage.getItem("token"))
