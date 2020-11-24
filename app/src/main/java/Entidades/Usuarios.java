package Entidades;

import java.util.Date;

public class Usuarios {

    Integer Id;
    String Nombre;
    String Correo;
    String Clave;
    Integer Tipo_User;
    String Especialidad;
    String Nit;
    String Dui;
    String Telefono;
    String Fecha_Nac;
    String Direccion;
    Integer Estado;

    public Usuarios(Integer id, String nombre, String correo, String clave, Integer tipo_User, String especialidad, String nit, String dui, String telefono, String fecha_Nac, String direccion, Integer estado) {
        Id = id;
        Nombre = nombre;
        Correo = correo;
        Clave = clave;
        Tipo_User = tipo_User;
        Especialidad = especialidad;
        Nit = nit;
        Dui = dui;
        Telefono = telefono;
        Fecha_Nac = fecha_Nac;
        Direccion = direccion;
        Estado = estado;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }

    public Integer getTipo_User() {
        return Tipo_User;
    }

    public void setTipo_User(Integer tipo_User) {
        Tipo_User = tipo_User;
    }

    public String getEspecialidad() {
        return Especialidad;
    }

    public void setEspecialidad(String especialidad) {
        Especialidad = especialidad;
    }

    public String getNit() {
        return Nit;
    }

    public void setNit(String nit) {
        Nit = nit;
    }

    public String getDui() {
        return Dui;
    }

    public void setDui(String dui) {
        Dui = dui;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getFecha_Nac() {
        return Fecha_Nac;
    }

    public void setFecha_Nac(String fecha_Nac) {
        Fecha_Nac = fecha_Nac;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public Integer getEstado() {
        return Estado;
    }

    public void setEstado(Integer estado) {
        Estado = estado;
    }
}
