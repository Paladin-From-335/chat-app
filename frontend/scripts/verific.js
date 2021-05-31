function verifid(){
    const email = document.getElementById("email").value;

    let data = {email:email}

    axios.post('http://localhost:8081/login/verification', data, {
        headers: {"Content-Type": "application/json"}
    }).then((response) =>{
        if(response.status === 200) {
            console.log(response.status)
            alert('Success')
            document.location = "..\\html\\modal.html"
        }
        else{
            alert('Something wrong')
        }
    })
}
