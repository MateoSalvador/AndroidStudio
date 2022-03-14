package com.example.medicalapp.Clases;
/** Creación de la Clase Cliente*/
public class Cliente {
    private String Nombre;
    private String Apellido;
    private String Cedula;
    private String FechaNacimiento;
    private String Correo;
    private String Direccion;


    public Cliente(){

    }
    /**Creación del constructor de la clase Cliente */
    public Cliente(String nombre, String apellido, String cedula, String Fecha, String correo, String direccion ){
       this.Nombre= nombre;
       this.Apellido= apellido;
       this.Cedula=cedula;
       this.Correo=correo;
       this.Direccion=direccion;
       this.FechaNacimiento=Fecha;
    }
    /** Creación de getters y setters para los diferentes atribrutos de la clase*/
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
    }

    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    /*public void agregarUsuario(Cliente cliente1){
            Cliente cliente = new Cliente(cliente1.Nombre, cliente1.Apellido, cliente1.Cedula, cliente1.FechaNacimiento, cliente1.Correo, cliente1.Direccion);
        }
    public boolean validarUsuario(Cliente cliente1){
        if (cliente1.Nombre == null| cliente1.Apellido == null| cliente1.Cedula == null| cliente1.FechaNacimiento == null| cliente1.Correo == null| cliente1.Direccion == null) {
            return true;
        }
        else return false;
        }*/


}
