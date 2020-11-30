package com.example.pruebanavegacion;

public class Citas_Examen {

    //Declaracion de variables
    private String NombreExamen;
    boolean estado;



    //Creamo un constructor para asignarle valores  a las variables
    public Citas_Examen(String NombreExamen){
        this.NombreExamen=NombreExamen;

    }




    //Metodos de tipo get para retornar el valor seleccionado nombre y letra

    public String getNombreExamen(){
        return NombreExamen;
    }

    public void setChekeado(boolean chekeado){
        estado=chekeado;
    }

    public boolean isChekeado(){
        return estado;
    }



}
