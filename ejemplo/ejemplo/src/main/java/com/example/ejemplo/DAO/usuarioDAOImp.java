package com.example.ejemplo.DAO;

import com.example.ejemplo.MODELS.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional

public class usuarioDAOImp implements usuarioDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override

    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void eliminar(Long id) {
        //buscamos al usuario en la base de datos
        Usuario usuario = entityManager.find(Usuario.class, id);
        //eliminamos
        entityManager.remove(usuario);
    }

    @Override
    public void registrarUsuarios(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public Usuario verificarIngreso(Usuario usuario) {
        //de esta manera evitamos inyeccion de sql

        String query = "FROM Usuario WHERE email = :email";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();
        String contraHash= lista.get(0).getContrasena();
        Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        //ahora usamos verufy para comparar contraseña
        if (lista.isEmpty()==true & argon.verify(contraHash, usuario.getContrasena()) == false){
            return null;
        }else{
            return lista.get(0);
        }
    }
}