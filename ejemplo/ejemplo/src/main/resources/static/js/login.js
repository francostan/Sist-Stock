$(document).ready(function () {

});


//estamos trabajando con ajax por eso la sigla async
async function iniciarSesion() {
    let datos1= {};
    datos1.email= document.getElementById('emailIngresado').value;
    datos1.contrasena= document.getElementById('contraIngresada').value;


//request
    //la url es la misma solo que el metodo ahora sera post, traemos info del formulario
    const request = await fetch('login', {
        //se usa cuando creamos una nueva entity en la base de datos

        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos1)
    });
    //nos sirve para traer informacion del inicio de sesion en este caso
     const respuesta = await request.text();
    if(respuesta == 'No se encontro usuario'){
        alert('Intente nuevamente, usuario o contraseña incorrectos')
    }else{
        localStorage.token=respuesta;
        localStorage.email=datos1.email;
        window.location.href='usuarios.html'
    }

    //fin request

    //creamos la variable para concatenar los usuarios
    //por ahora solo dejamos la request asiq voy a comentartodo esto
    /*
    let listadoHtml = '';


    for (let usuario of usuarios) {
        //traemos el codigo html de las filas y lo colocamos en una variable
        //vamos seleccionando el atributo que queremos de usuario
        let botonEliminar = '<button onclick="eliminarUsuario(' + usuario.id + ')" style=\"background-color:red\"; name=\"button\" > Eliminar</button>';
        let usuarioHtml = '<tr><td>' + usuario.id + '</td><td>' + usuario.nombre + ' ' + usuario.apellido + ' </td><td>' + usuario.email + '</td><td>' + usuario.telefono + '</td><td>' + botonEliminar + '</td> </tr>';
        listadoHtml += usuarioHtml;
    }
//aqui va mapeado en base al controller que necesitamos
    console.log(usuarios);

    //selector para verificar si funca, sumamos la variable con el codigo para las filas
    document.querySelector('#usuarios tbody').outerHTML = listadoHtml;
*/
}