package com.example.ejemplo.DAO;

import com.example.ejemplo.MODELS.Usuario;

import java.util.List;

public interface usuarioDAO {

    List<Usuario> getUsuarios();

    void eliminar(Long id);

    void registrarUsuarios(Usuario usuario);

    Usuario verificarIngreso(Usuario usuario);
}
