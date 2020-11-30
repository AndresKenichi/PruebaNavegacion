package com.example.pruebanavegacion;

public class Citas_Examen2 {

    //Declaracion de variables
    private String NombreExamen;
    private String idEx;



    //Creamo un constructor para asignarle valores  a las variables
    public Citas_Examen2(String idEx, String NombreExamen){
        this.NombreExamen=NombreExamen;
        this.idEx=idEx;

    }




    //Metodos de tipo get para retornar el valor seleccionado nombre y letra


    public String getNombreExamen() {
        return NombreExamen;
    }

    public String getIdEx() {
        return idEx;
    }
}
