// Call the dataTables jQuery plugin
$(document).ready(function () {

});


//estamos trabajando con ajax por eso la sigla async
async function registrarUsuarios() {
    let datos= {};
    datos.nombre= document.getElementById('Nombre').value;
    datos.apellido= document.getElementById('Apellido').value;
    datos.email= document.getElementById('Email').value;
    datos.contrasena= document.getElementById('Contrasena').value;

    let repetirContra= document.getElementById('Contrasena2').value;
    if (repetirContra != datos.contrasena){
        alert("Las contraseñas no coinciden")
        return;
    }
//request
    //la url es la misma solo que el metodo ahora sera post, traemos info del formulario
    const request = await fetch('usuarios', {
        //se usa cuando creamos una nueva entity en la base de datos

        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
    //fin request

  alert('Registro Exitoso');
  window.location.href = 'login.html';
}

