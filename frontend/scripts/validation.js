const login = document.getElementById('username_reg');
const firstname = document.getElementById('firstname');
const lastname = document.getElementById('lastname');
const password = document.getElementById('password_pass');
const cpassword = document.getElementById('cpassword');
const phone = document.getElementById('phone');
const nickname = document.getElementById('nickname');
const email = document.getElementById('email');
const button = document.getElementById('reg_button')

firstname.addEventListener('input', (e) => {
    const regex = /^[A-Za-z-]/;
    let checkFName = regex.test(e.target.value);
    if (!checkFName){
        firstname.style.borderStyle = "5px"
        firstname.style.borderColor = 'red';
        button.disabled = true;
        button.style.backgroundColor = 'rgba(114,174,219,0.5)'
    }
    if(checkFName){
        firstname.style.border = 'none';
        button.disabled = false;
        button.style.backgroundColor = 'white'
    }
    console.log(checkFName);
})