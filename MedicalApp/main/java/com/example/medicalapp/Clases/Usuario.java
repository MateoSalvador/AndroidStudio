package com.example.medicalapp.Clases;

public class Usuario {
    private String user;
    private String password;

    public Usuario(){

    }

    public Usuario(String usuario, String passwd){
        this.user=usuario;
        this.password=passwd;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
