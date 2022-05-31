package com.example.ejemplo.controllers;


import com.example.ejemplo.DAO.usuarioDAO;
import com.example.ejemplo.MODELS.Usuario;
import com.example.ejemplo.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class authController {
    @Autowired
    private usuarioDAO usuarioDAO;
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String loginUsuarios(@RequestBody Usuario usuario) {

        Usuario logUsuario= usuarioDAO.verificarIngreso(usuario);
        if (logUsuario != null) {
           String token = jwtUtil.create(logUsuario.getId().toString(), logUsuario.getEmail());
           return token;
        }else{
            return "No se encontro usuario";
        }
    }

}
