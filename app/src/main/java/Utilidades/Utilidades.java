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
    public static final String Tabla_Consultas="consultas";
    public static final String Campo_IdConsultas="IdConsultas";
    public static final String Campo_IdCitas="IdCita_G";
    public static final String Campo_Presion="Presion_Arterial";
    public static final String Campo_Respiraciones="Respiraciones";
    public static final String Campo_Diagnostico="Diagnostico";
    public static final String Campo_idMedicamento="IdMedicamento";
    public static final String Campo_Indicaciones="Indicaciones";
    public static final String Campo_Fecha_Con="Fecha";

    //Declaramos las constantes que seran campos de nuestras tablas= Cita General
    public static final String Tabla_Cita_General="citas_generales";
    public static final String Campo_IdCita_G="IdCita_G";
    public static final String Campo_IdPaciente_G="IdPaciente";
    public static final String Campo_IdUsuarios_G="IdUsuarios";
    public static final String Campo_Fecha_G="Fecha";
    public static final String Campo_Hora_G="Hora";
    public static final String Campo_Estado_G="Estado";

    //Declaramos las constantes que seran campos de nuestras tabla Paciente
    public static final String Tabla_Paciente="Pacientes";
    public static final String Campo_IdPaciente="IdPaciente";
    public static final String Campo_Nombre_P="Nombre";
    public static final String Campo_Dui_P="Dui";
    public static final String Campo_Nit_P="Nit";
    public static final String Campo_Direccion_P="Direccion";
    public static final String Campo_Fecha_NacP="Fecha";
    public static final String Campo_Aseguradora="Aseguradora";
    public static final String Campo_Num_Afiliado="Num_Afiliado";
    public static final String Campo_Tipo_Sangre="Tipo_Sangre";
    public static final String Campo_Peso="Peso";
    public static final String Campo_Alergias="Alergias";
    public static final String Campo_Discapacidades="Discapacidades";
    public static final String Campo_Nombre_Emergencia="Nombre_Emergencia";
    public static final String Campo_Parentesco_Emergencia="Parentesco_Emergencia";
    public static final String Campo_Telefono_Emergencia="Telefono_Emergencia";




    //Declaramos una variable String donde tendremos un comando SQL para Pacientes
    public static final String Crear_Tabla_Pacientes="create table "+Tabla_Paciente+"("+Campo_IdPaciente+" integer primary key autoincrement, "+Campo_Nombre_P+" text, " +
            " "+Campo_Dui_P+" text, "+Campo_Nit_P+" text, "+Campo_Direccion_P+" text, "+Campo_Fecha_NacP+" text, "+Campo_Aseguradora+" text, "+Campo_Num_Afiliado+" text, "+Campo_Tipo_Sangre+" text, " +
            " "+Campo_Peso+" text, "+Campo_Alergias+" text, "+Campo_Discapacidades+" text, "+Campo_Nombre_Emergencia+" text, "+Campo_Parentesco_Emergencia+" text, "+Campo_Telefono_Emergencia+" text );";


    //Declaramos una variable String donde tendremos un comando SQL para Usuario
   public static final String Crear_Tabla_Usuarios="create table "+Tabla_Usuario+"("+Campo_Id+" integer primary key autoincrement, "+Campo_Nombre+" text, " +
            " "+Campo_Correo+" text, "+Campo_Clave+" text, "+Campo_Tipo_User+" integer, "+Campo_Especialidad+" text, "+Campo_Nit+" text, "+Campo_Dui+" text, "+Campo_Telefono+" text, " +
            " "+Campo_Fecha_Nac+" text, "+Campo_Direccion+" text, "+Campo_Estado+" integer );";


    //Declaramos una variable String donde tendremos un comando SQL para Cita General
    public static final String Crear_Tabla_Cita_General="create table "+Tabla_Cita_General+"("+Campo_IdCita_G+" integer primary key autoincrement, "+Campo_IdPaciente_G+" text, " +
            " "+Campo_IdUsuarios_G+" text, "+Campo_Fecha_G+" text, "+Campo_Hora_G+" text, "+Campo_Estado_G+" text, foreign key("+Campo_IdPaciente_G+") references "+Tabla_Paciente+"("+Campo_IdPaciente+") );";



    //Declaramos una variable String donde tendremos un comando SQL para Consultas
    public static final String Crear_Tabla_Consultas="create table "+Tabla_Consultas+"("+Campo_IdConsultas+" integer primary key autoincrement, "+Campo_IdCitas+" text, "+Campo_Presion+" text, " +
            " "+Campo_Respiraciones+" text, "+Campo_Diagnostico+" text, "+Campo_idMedicamento+" integer, "+Campo_Indicaciones+" text, " +
            " "+Campo_Fecha_Con+" text, foreign key("+Campo_IdCitas+") references "+Tabla_Cita_General+"("+Campo_IdCita_G+") );";








}
