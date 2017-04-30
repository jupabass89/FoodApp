package com.example.juan.foodapp.modelo;

public class Estudiante {

    private String nombre;
    private String id;
    private String mail;

    private static Estudiante estudiante = new Estudiante();

    public static Estudiante getEstudiante(){
        return estudiante;
    }

    //GETTERS AND SETTERS
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;

    }
}
