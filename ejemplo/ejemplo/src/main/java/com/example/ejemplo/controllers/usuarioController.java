package com.example.ejemplo.controllers;

import com.example.ejemplo.DAO.usuarioDAO;
import com.example.ejemplo.MODELS.Usuario;
import com.example.ejemplo.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class usuarioController {

    @Autowired
    private usuarioDAO usuarioDAO;
    //AQUI ESTARIA LA INYEC DE INDEPENDENCIAS 1 (ARRIBA)


    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "usuarios1", method = RequestMethod.GET)

    public Usuario getUsuario(@PathVariable Long id) {

        Usuario usuario = new Usuario();
        usuario.setNombre("Franco");
        usuario.setId(id);
        usuario.setApellido("Stanziola");
        usuario.setEmail("francostan98@hotmail.com");
        return usuario;
    }

    @RequestMapping(value = "usuarios")
    public List <Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token) {

        String usuarioId = jwtUtil.getKey(token);
        if (usuarioId == null){
            return new ArrayList<>();
        }
        return usuarioDAO.getUsuarios();
    }
    @RequestMapping(value = "usuarios", method = RequestMethod.POST)
    public void registrarUsuarios(@RequestBody Usuario usuario) {

        Argon2 hash = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        hash.hash(1,1024,1,usuario.getContrasena());
        usuario.setContrasena(hash.toString());

        usuarioDAO.registrarUsuarios(usuario);
    }

    public Usuario CrearUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Franco");
        usuario.setApellido("Stanziola");
        usuario.setEmail("francostan98@hotmail.com");
        return usuario;
    }

    @RequestMapping(value = "usuario/{Id}", method = RequestMethod.DELETE)
    public void eliminarUsuario(@RequestHeader(value = "Authorization") String token, @PathVariable(value = "Id") Long Id) {

        String usuarioId = jwtUtil.getKey(token);
        if (usuarioId == null){
            return;
        }
        usuarioDAO.eliminar(Id);

    }
    public Usuario modificarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Franco");
        usuario.setApellido("Stanziola");
        usuario.setEmail("francostan98@hotmail.com");
        return usuario;
    }
    public Usuario buscarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Franco");
        usuario.setApellido("Stanziola");
        usuario.setEmail("francostan98@hotmail.com");
        return usuario;
    }
}
