const WebSocket = require('ws');

const server = new WebSocket.Server({port: 8081});

server.on('connection', ws => {
    ws.on('message', message => {
        if(message === 'exit'){
            ws.close();
        } else{
            server.clients.forEach(client => {
                if(client.readyState === WebSocket.OPEN){
                    client.send(message);
                }
            });
        }
    })
})