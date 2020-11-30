package com.example.pruebanavegacion;

public class Citas_Examen3 {

   /* //Declaracion de variables
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
    } */

    //Declaracion de variables
    private String IdResultadoe;
    private String NombreExamen;
    private String idEx;



    //Creamo un constructor para asignarle valores  a las variables
    public Citas_Examen3(String IdResultadoe, String idEx, String NombreExamen){
        this.IdResultadoe=IdResultadoe;
        this.NombreExamen=NombreExamen;
        this.idEx=idEx;

    }




    //Metodos de tipo get para retornar el valor seleccionado nombre y letra


    public String getIdResultadoe() {
        return IdResultadoe;
    }

    public String getNombreExamen() {
        return NombreExamen;
    }

    public String getIdEx() {
        return idEx;
    }



}
