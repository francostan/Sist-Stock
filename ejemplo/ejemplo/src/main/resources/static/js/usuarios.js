// Call the dataTables jQuery plugin
$(document).ready(function () {
    cargarUsuarios();
    $('#Usuarios').DataTable();
    cargarEmailUsuario();
});


function cargarEmailUsuario() {
document.getElementById('txt-email-usuario').outerHTML = localStorage.email;

}


function getHeaders(){

      return  {'Accept': 'application/json','Content-Type': 'application/json','Authorization': localStorage.token};
}

//estamos trabajando con ajax por eso la sigla async
async function cargarUsuarios() {
//request
    const request = await fetch('usuarios', {
        method: 'GET',
        headers:getHeaders()
    });
    const usuarios = await request.json();
    //fin request

    //creamos la variable para concatenar los usuarios
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

}

async function eliminarUsuario(id){

    if (!confirm('Desea eliminar el usuario?')){
        return;
    }
    //request
    //concatenamos el id (por la ruta de usuario controller
    console.log(id)
    const request = await fetch('usuario/' + id, {
        method: 'DELETE',
        headers: getHeaders()
    });
    //Dejamos en desuso porque no necesitamos transformar la respuesta en json
    //const usuarios = await request.json();
    //fin request
    //hacemos que recargue automaticamente
    location.reload();

}