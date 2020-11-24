package Utilidades;

public class Utilidades {

    //Declaramos las constantes que seran campos de nuestras tablas USUARIOS
    public static final String Tabla_Usuario="usuarios";
    public static final String Campo_Id="Id";
    public static final String Campo_Nombre="Nombre";
    public static final String Campo_Correo="Correo";
    public static final String Campo_Clave="Clave";
    public static final String Campo_Tipo_User="Tipo_User";
    public static final String Campo_Especialidad="Especialidad";
    public static final String Campo_Nit="Nit";
    public static final String Campo_Dui="Dui";
    public static final String Campo_Telefono="Telefono";
    public static final String Campo_Fecha_Nac="Fecha_Nac";
    public static final String Campo_Direccion="Direccion";
    public static final String Campo_Estado="Estado";

    //Declaramos las constantes que seran campos de nuestras tablas CONSULTAS
    public static final String Tabla_Consultas="Consultas";
    public static final String Campo_IdConsultas="IdConsultas";
    public static final String Campo_Presion="Presion_Arterial";
    public static final String Campo_Respiraciones="Respiraciones";
    public static final String Campo_Diagnostico="Diagnostico";
    public static final String Campo_idMedicamento="IdMedicamento";
    public static final String Campo_Indicaciones="Indicaciones";
    public static final String Campo_Fecha_Con="Nit";

    //Declaramos las constantes que seran campos de nuestras tablas Cita General
  /*  public static final String Tabla_Cita_General="Consultas";
    public static final String Campo_IdPaciente="IdConsultas";
    public static final String Campo_Presion="Presion_Arterial";
    public static final String Campo_Respiraciones="Respiraciones";
    public static final String Campo_Diagnostico="Diagnostico";
    public static final String Campo_idMedicamento="IdMedicamento";
    public static final String Campo_Indicaciones="Indicaciones";
    public static final String Campo_Fecha_Con="Nit"; */






    //Declaramos una variable String donde tendremos un comando SQL para Usuario
   public static final String Crear_Tabla_Usuarios="create table "+Tabla_Usuario+"("+Campo_Id+" integer primary key autoincrement, "+Campo_Nombre+" text, " +
            " "+Campo_Correo+" text, "+Campo_Clave+" text, "+Campo_Tipo_User+" integer, "+Campo_Especialidad+" text, "+Campo_Nit+" text, "+Campo_Dui+" text, "+Campo_Telefono+" text, " +
            " "+Campo_Fecha_Nac+" text, "+Campo_Direccion+" text, "+Campo_Estado+" integer );";


    //Declaramos una variable String donde tendremos un comando SQL para Usuario
    public static final String Crear_Tabla_Consultas="create table "+Tabla_Consultas+"("+Campo_IdConsultas+" integer primary key autoincrement, "+Campo_Presion+" text, " +
            " "+Campo_Respiraciones+" text, "+Campo_Diagnostico+" text, "+Campo_idMedicamento+" integer, "+Campo_Indicaciones+" text, "+Campo_Nit+" text, "+Campo_Dui+" text, "+Campo_Telefono+" text, " +
            " "+Campo_Fecha_Con+" text);";

}
