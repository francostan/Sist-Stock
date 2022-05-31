package com.example.ejemplo.MODELS;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table (name="usuarios1")
public class Usuario {
    @Id
    @Getter @Setter @Column(name= "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter @Setter @Column(name= "Nombre")
    private String nombre;
    @Getter @Setter @Column(name= "apellido")
    private String apellido;
    @Getter @Setter @Column(name= "email")
    private String email;
    @Getter @Setter @Column(name= "telefono")
    private Integer telefono;
    @Getter @Setter @Column(name= "contrasena")
    private String contrasena;


}
