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
    public static final String Campo_idMedicamento="IdMedicamentos";
    public static final String Campo_Indicaciones="Indicaciones";
    public static final String Campo_Fecha_Con="Fecha";
    public static final String Campo_TratamientoC="Tratamiento";

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

    //Declaramos las constantes que seran campos de nuestras tabla Medicamentos
    public static final String Tabla_Medicamentos="Mendicamentos";
    public static final String Campo_IdMedicamentos="IdMedicamentos";
    public static final String Campo_Nombre_M="Nombre";
    public static final String Campo_Tipo_Medicamento="Tipo_Medicamento";
    public static final String Campo_Presentacion="Presentacion";
    public static final String Campo_Via="Via";
    public static final String Campo_Num_Registro="Num_Registro";
    public static final String Campo_Marca="Marca";
    public static final String Campo_Precio="Precio";
    public static final String Campo_Cantidad="Cantidad";
    public static final String Campo_Fecha_Ingreso="Fecha_Ingreso";
    public static final String Campo_Fecha_Caducidad="Fecha_Caducidad";

    //Declaramos las constantes que seran campos de nuestras tabla Areas
    public static final String Tabla_Areas="Area";
    public static final String Campo_IdArea="IdArea";
    public static final String Campo_Area="Nombre";

    //Declaramos las constantes que seran campos de nuestras tabla Lugares
    public static final String Tabla_Lugares="Lugares";
    public static final String Campo_IdLugar="IdLugar";
    public static final String Campo_IdAreaL="IdArea";
    public static final String Campo_Num_Cama="Num_Cama";
    public static final String Campo_Num_Cuarto="Num_Cuarto";
    public static final String Campo_TipoHabitacion="TipoHabitacion";
    public static final String Campo_EstadoL="Estado";

    //Declaramos las constantes que seran campos de nuestras tabla Ingresos
    public static final String Tabla_Ingresos="Ingresos";
    public static final String Campo_IdIngreso="IdIngreso";
    public static final String Campo_IdPacienteI="IdPaciente";
    public static final String Campo_IdUsuariosI="IdUsuarios";
    public static final String Campo_IdLugarI="IdLugar";
    public static final String Campo_Tipo_Intervencion="Tipo_Intervencion";
    public static final String Campo_FechaIngreso="FechaIngreso";
    public static final String Campo_HoraIngreso="HoraIngreso";
    public static final String Campo_EstadoI="Estado";

    //Declaramos las constantes que seran campos de nuestras tabla Lista examenes
    public static final String Tabla_Lista_Examenes="Lista_Examenes";
    public static final String Campo_IdListaExamenes_P="IdListaExamenes";
    public static final String Campo_IdCita_ex="IdCita_E";
    public static final String Campo_IdPaciente_Lex="IdPaciente";
    public static final String Campo_IdUsuariosEx="IdUsuarios";
    public static final String Campo_FechaEx="Fecha";
    public static final String Campo_HoraEx="Hora";
    public static final String Campo_EstadoEx="Estado";

    //Declaramos las constantes que seran campos de nuestras tablas Cita Examen
    public static final String Tabla_Cita_Examen="Citas_Examenes";
    public static final String Campo_IdCita_E="IdCita_E";
    public static final String Campo_IdPaciente_E="IdPaciente";
    public static final String Campo_Fecha_E="Fecha";
    public static final String Campo_Hora_E="Hora";
    public static final String Campo_Tipo_E="Tipo";
    public static final String Campo_Estado_E="Estado";

    //Declaramos las constantes que seran campos de nuestras tablas Resultados_Examenes
    public static final String Tabla_Resultados_Examenes="Resultados_Examenes";
    public static final String Campo_IdResultado="IdResultado";
    public static final String Campo_IdCita_REX="IdCita_E";
    public static final String Campo_Observaciones="Observaciones";
    public static final String Campo_DiagnosticoREx="Diagnostico";
    public static final String Campo_Tratamiento="Tratamiento";
    public static final String Campo_FechaREx="Fecha";
    public static final String Campo_HoraREx="Hora";
    public static final String Campo_Estado_REx="Estado";

    //Declaramos las constantes que seran campos de nuestras tablas detalle_medicamentos
    public static final String Tabla_detalle_medicamentos="detalle_medicamentos";
    public static final String Campo_IdDetalle="IdDetalle";
    public static final String Campo_IdMedicamento_D="IdMedicamentos";
    public static final String Campo_IdPaciente_D="IdPaciente";
    public static final String Campo_IdUsuarios_D="IdUsuarios";
    public static final String Campo_IdConsultasD="IdConsultas";
    public static final String Campo_CantidadD=" Cantidad";

    //Declaramos una variable String donde tendremos un comando SQL para Detalle medicamentos
    public static final String Crear_Tabla_detallemedicamentos="create table "+Tabla_detalle_medicamentos+"("+Campo_IdDetalle+" integer primary key autoincrement, "+Campo_IdMedicamento_D+" integer, " +
            ""+Campo_IdPaciente_D+" integer, "+Campo_IdUsuarios_D+" integer, "+Campo_Tratamiento+" text, " +
            " "+Campo_FechaREx+" text, "+Campo_HoraREx+" text "+Campo_Estado_REx+" Integer, "+Campo_IdConsultasD+" integer, "+Campo_CantidadD+" integer, " +
            " foreign key("+Campo_IdMedicamento_D+") references "+Tabla_Medicamentos+"("+Campo_IdMedicamentos+"), " +
            " foreign key("+Campo_IdPaciente_D+") references "+Tabla_Paciente+"("+Campo_IdPaciente+"), " +
            " foreign key("+Campo_IdUsuarios_D+") references "+Tabla_Usuario+"("+Campo_Id+"), " +
            " foreign key("+Campo_IdConsultasD+") references "+Tabla_Consultas+"("+Campo_IdConsultas+"));";


    //Declaramos una variable String donde tendremos un comando SQL para Resultados Examenes
    public static final String Crear_Tabla_ResultadosExamenes="create table "+Tabla_Resultados_Examenes+"("+Campo_IdResultado+" integer primary key autoincrement, "+Campo_IdCita_REX+" integer, " +
            ""+Campo_Observaciones+" text, "+Campo_DiagnosticoREx+" text, "+Campo_Tratamiento+" text, " +
            " "+Campo_FechaREx+" text, "+Campo_HoraREx+" text "+Campo_Estado_REx+" Integer, " +
            " foreign key("+Campo_IdCita_REX+") references "+Tabla_Cita_Examen+"("+Campo_IdCita_E+"));";

    //Declaramos una variable String donde tendremos un comando SQL para Cita Examen
    public static final String Crear_Tabla_Cita_Examen="create table "+Tabla_Cita_Examen+"("+Campo_IdCita_E+" integer primary key autoincrement, "+Campo_IdPaciente_E+" integer, " +
            " "+Campo_Fecha_E+" text, "+Campo_Hora_E+" text, "+Campo_Tipo_E+" text, "+Campo_Estado_E+" integer, foreign key("+Campo_IdPaciente_E+") references "+Tabla_Paciente+"("+Campo_IdPaciente+") );";

    //Declaramos una variable String donde tendremos un comando SQL para Ingresos
    public static final String Crear_Tabla_ListaExamenes="create table "+Tabla_Lista_Examenes+"("+Campo_IdListaExamenes_P+" integer primary key autoincrement, "+Campo_IdCita_ex+" integer, "+Campo_IdPaciente_Lex+" integer,"+Campo_IdUsuariosEx+" integer, " +
            " "+Campo_FechaEx+" integer, "+Campo_HoraEx+" integer, "+Campo_EstadoEx+" Integer, " +
            " foreign key("+Campo_IdUsuariosEx+") references "+Tabla_Usuario+"("+Campo_Id+"), foreign key("+Campo_IdPaciente_Lex+") references "+Tabla_Paciente+"("+Campo_IdPaciente+"));";


    //Declaramos una variable String donde tendremos un comando SQL para Ingresos
    public static final String Crear_Tabla_Ingresos="create table "+Tabla_Ingresos+"("+Campo_IdIngreso+" integer primary key autoincrement, "+Campo_IdPacienteI+" integer, " +
            " "+Campo_IdUsuariosI+" integer, "+Campo_IdLugarI+" integer, "+Campo_Tipo_Intervencion+" text, "+Campo_FechaIngreso+" text, "+Campo_HoraIngreso+" text, "+Campo_EstadoI+" integer, " +
            " foreign key("+Campo_IdLugarI+") references "+Tabla_Lugares+"("+Campo_IdLugar+"), foreign key("+Campo_IdPacienteI+") references "+Tabla_Paciente+"("+Campo_IdPaciente+"), " +
            " foreign key("+Campo_IdUsuariosI+") references "+Tabla_Usuario+"("+Campo_Id+"));";

    //Declaramos una variable String donde tendremos un comando SQL para Areas
    public static final String Crear_Tabla_Area="create table "+Tabla_Areas+"("+Campo_IdArea+" integer primary key autoincrement, "+Campo_Area+" text );";


    //Declaramos una variable String donde tendremos un comando SQL para Lugares
    public static final String Crear_Tabla_Lugares="create table "+Tabla_Lugares+"("+Campo_IdLugar+" integer primary key autoincrement, "+Campo_IdAreaL+" integer, " +
            " "+Campo_Num_Cama+" text, "+Campo_Num_Cuarto+" text, "+Campo_TipoHabitacion+" text, "+Campo_EstadoL+" integer, " +
            " foreign key("+Campo_IdAreaL+") references "+Tabla_Areas+"("+Campo_IdArea+"));";


    //Declaramos una variable String donde tendremos un comando SQL para Medicamentos
    public static final String Crear_Tabla_Medicamentos="create table "+Tabla_Medicamentos+"("+Campo_IdMedicamentos+" integer primary key autoincrement, "+Campo_Nombre_M+" text, " +
            " "+Campo_Tipo_Medicamento+" text, "+Campo_Presentacion+" text, "+Campo_Via+" text, "+Campo_Num_Registro+" text, "+Campo_Marca+" text, "+Campo_Precio+" text, "+Campo_Cantidad+" text, " +
            " "+Campo_Fecha_Ingreso+" text, "+Campo_Fecha_Caducidad+" text );";



    //Declaramos una variable String donde tendremos un comando SQL para Pacientes
    public static final String Crear_Tabla_Pacientes="create table "+Tabla_Paciente+"("+Campo_IdPaciente+" integer primary key autoincrement, "+Campo_Nombre_P+" text, " +
            " "+Campo_Dui_P+" text, "+Campo_Nit_P+" text, "+Campo_Direccion_P+" text, "+Campo_Fecha_NacP+" text, "+Campo_Aseguradora+" text, "+Campo_Num_Afiliado+" text, "+Campo_Tipo_Sangre+" text, " +
            " "+Campo_Peso+" text, "+Campo_Alergias+" text, "+Campo_Discapacidades+" text, "+Campo_Nombre_Emergencia+" text, "+Campo_Parentesco_Emergencia+" text, "+Campo_Telefono_Emergencia+" text );";


    //Declaramos una variable String donde tendremos un comando SQL para Usuario
   public static final String Crear_Tabla_Usuarios="create table "+Tabla_Usuario+"("+Campo_Id+" integer primary key autoincrement, "+Campo_Nombre+" text, " +
            " "+Campo_Correo+" text, "+Campo_Clave+" text, "+Campo_Tipo_User+" text, "+Campo_Especialidad+" text, "+Campo_Nit+" text, "+Campo_Dui+" text, "+Campo_Telefono+" text, " +
            " "+Campo_Fecha_Nac+" text, "+Campo_Direccion+" text, "+Campo_Estado+" integer );";


    //Declaramos una variable String donde tendremos un comando SQL para Cita General
    public static final String Crear_Tabla_Cita_General="create table "+Tabla_Cita_General+"("+Campo_IdCita_G+" integer primary key autoincrement, "+Campo_IdPaciente_G+" integer, " +
            " "+Campo_IdUsuarios_G+" integer, "+Campo_Fecha_G+" text, "+Campo_Hora_G+" text, "+Campo_Estado_G+" text, foreign key("+Campo_IdPaciente_G+") references "+Tabla_Paciente+"("+Campo_IdPaciente+"), " +
            " foreign key("+Campo_IdUsuarios_G+") references "+Tabla_Usuario+"("+Campo_Id+") );";


    //Declaramos una variable String donde tendremos un comando SQL para Consultas
    public static final String Crear_Tabla_Consultas="create table "+Tabla_Consultas+"("+Campo_IdConsultas+" integer primary key autoincrement, "+Campo_IdCitas+" integer, "+Campo_Presion+" text, " +
            " "+Campo_Respiraciones+" text, "+Campo_Diagnostico+" text, "+Campo_idMedicamento+" integer, "+Campo_Indicaciones+" text, "+Campo_TratamientoC+" text, " +
            " "+Campo_Fecha_Con+" text, foreign key("+Campo_IdCitas+") references "+Tabla_Cita_General+"("+Campo_IdCita_G+") );";








}
