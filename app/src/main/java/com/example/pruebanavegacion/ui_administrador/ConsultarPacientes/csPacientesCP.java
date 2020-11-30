package com.example.pruebanavegacion.ui_administrador.ConsultarPacientes;

public class csPacientesCP{
    //creando variables necesarias
    private String Nombre,IdCita,Estado,Doctor,Fecha,Hora;

    //generando constructor vacio
    public csPacientesCP() {

    }

    //generando constructor con atributos de la clase csEscudos
    public csPacientesCP(String Nombre, String IdCita , String Estado, String Doctor ,String Fecha,String Hora) {
        this.Nombre = Nombre;
        this.IdCita = IdCita;
        this.Estado = Estado;
        this.Doctor = Doctor;
        this.Fecha = Fecha;
        this.Hora = Hora;
    }

    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }


    public void setIdCita(String IdCita) {
        this.IdCita = IdCita;
    }
    public String getIdCita() {
        return IdCita;
    }


    public String getEstado() {
        return Estado;
    }
    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getDoctor() {
        return Doctor;
    }
    public void setDoctor(String Doctor) {
        this.Doctor = Doctor;
    }

    public String getFecha() {
        return Fecha;
    }
    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getHora() {
        return Hora;
    }
    public void setHora(String Hora) {
        this.Hora = Hora;
    }

}