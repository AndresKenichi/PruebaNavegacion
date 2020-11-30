package com.example.pruebanavegacion.ui_administrador.ConsultarAreas;

public class csPacientesCA {
    //creando variables necesarias
    private String Nombre, Estado,Doctor,NumCama;
    private int imgPaciente;

    //generando constructor vacio
    public csPacientesCA() {

    }

    //generando constructor con atributos de la clase csEscudos
    public csPacientesCA(String Nombre, String NumCama , String Estado, String Doctor, int imgPaciente) {
        this.Nombre = Nombre;
        this.NumCama = NumCama;
        this.Estado = Estado;
        this.Doctor = Doctor;
        this.imgPaciente = imgPaciente;
    }

    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }


    public void setNumCama(String NumCama) {
        this.NumCama = NumCama;
    }
    public String getNumCama() {
        return NumCama;
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

    public int getimgPaciente() {
        return imgPaciente;
    }

    public void setimgPaciente(int imgPaciente) {
        this.imgPaciente = imgPaciente;
    }
}
