package com.example.pruebanavegacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import BaseHospital.DatosConexion;
import BaseHospital.Sqlite_Base;
import Utilidades.Utilidades;

public class MainActivity extends AppCompatActivity {

    private Button btnIngresar;
    EditText txtCorUsu,txtPassUsu;
    TextView tvRegistrese;

    //Creamos una instacia de nuestra base de datos
    Sqlite_Base helper=new Sqlite_Base(this, DatosConexion.NOMBREBD,null,DatosConexion.VERSION);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Inicializamos objetos del XML
        txtCorUsu=findViewById(R.id.txtUsuario);
        txtPassUsu=findViewById(R.id.txtPassword);
        tvRegistrese=findViewById(R.id.tvRegistrese);
        btnIngresar=findViewById(R.id.btnIngresar);

        //Cursores para validar existencia de datos en la BD
        Cursor h =  validacionArea();
        Cursor k =  validacionLugares();
        Cursor l =  validacionPacientes();
        Cursor mm = validacionUsuarios();

        //Validaciones para evitar duplicidad de datos
        if(k.getCount()>0){
           // Toast.makeText(this,"TENEMOS DATOS LUGARES",Toast.LENGTH_LONG).show();
        }else{
            insertarLugares();
        }
        if(l.getCount()>0){
          //  Toast.makeText(this,"TENEMOS DATOS PACIENTES",Toast.LENGTH_LONG).show();
        }else{
            insertarPacientes();
            insertarCitaGeneral();
            insertarCitaExamen();
        }

        if(mm.getCount()>0){
           // Toast.makeText(this,"TENEMOS DATOS AREAS",Toast.LENGTH_LONG).show();
        }else{

            insertarUsuario();
        }
        if(h.getCount()>0){
          //  Toast.makeText(this,"TENEMOS DATOS USUARIOS",Toast.LENGTH_LONG).show();
        }else{
            insertarArea();
            insertarConsultas();
        }
        //**

        tvRegistrese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtCorUsu.clearFocus();
                txtPassUsu.clearFocus();
                Intent intent2=new Intent(MainActivity.this,Registrar.class);
                startActivity(intent2);

            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                txtCorUsu.setError(null);
                txtPassUsu.setError(null);
                String correo=txtCorUsu.getText().toString().trim();
                String pass=txtPassUsu.getText().toString().trim();

                if(correo.isEmpty()){
                    //Primer error
                    txtCorUsu.setError("Ingresar un correo valido");
                    //Colocamos un focus
                    txtCorUsu.requestFocus();
                    return;
                }

                if(pass.equals("")){
                    //Primer error
                    txtPassUsu.setError("Ingresar password valida");
                    //Colocamos un focus
                    txtPassUsu.requestFocus();
                    return;
                }

                try {
                    //Declaramos un objeto de tipo cursor para recibirlo y enviarlo la siguiente actividad
                    //le enviamos estos datos al objeto de tipo cursor
                    Cursor cursor=helper.ConsultarUsuPas(correo,pass);

                    //Consultamos el resultado si es mayor a cero es porque si existen registros
                    if(cursor.getCount()>0){

                        cursor.moveToFirst();
                        //USUARIO YA REGISTRADO
                        String msj = cursor.getString(1);
                        String cor= cursor.getString(2);
                        String rol= cursor.getString(4);


                        Toast.makeText(getApplicationContext(),"Usuario:"+msj,Toast.LENGTH_SHORT).show();
                      //Nivel 0 Doctor

                        if(rol.equals("Doctor")){

                            Intent intent=new Intent(MainActivity.this,Home.class);
                            intent.putExtra("correo",cor);
                            startActivity(intent);

                        }
                       //Nivel 1 Administrador
                        if(rol.equals("Administrador")){

                            Intent intent1=new Intent(MainActivity.this,Administrador.class);
                            intent1.putExtra("correo",cor);
                            startActivity(intent1);

                        }
                      //Nivel 2 Archivo
                       else if(rol.equals("Archivo")){

                            Intent intent2=new Intent(MainActivity.this,Archivo.class);
                            intent2.putExtra("correo",cor);
                            startActivity(intent2);

                        }
                       //Nivel 3 laboratorio
                        else if(rol.equals("Laboratorio")){

                            Intent intent3=new Intent(MainActivity.this,Laboratorio.class);
                            intent3.putExtra("correo",cor);
                            startActivity(intent3);

                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Error de Autenticacion",Toast.LENGTH_SHORT).show();
                    }
                    txtCorUsu.setText("");
                    txtPassUsu.setText("");
                    txtCorUsu.findFocus();


                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
        });

    }

    public void insertarArea(){

        helper.abrir();

        String comandoAre4 = "INSERT INTO "+Utilidades.Tabla_Areas+"("+Utilidades.Campo_Area+") " +
                "values('ANESTESIOLOGIA'),('URGENCIAS'),('CIRUGIA'),('ONCOLOGIA'),('CARDIOLOGIA'),('NEUROLOGIA'),('PSICOLOGIA'),('UROLOGIA'),('NUTRIOLOGIA'),('DERMATOLOGIA'),('OTORRINOLARINGOLOGIA'),('FARMACEUTICA');";

        helper.getWritableDatabase().execSQL(comandoAre4);


    }
    public void insertarUsuario(){
        helper.abrir();
        String comandoL="INSERT INTO "+Utilidades.Tabla_Usuario+"("+Utilidades.Campo_Nombre+","+Utilidades.Campo_Correo+" ,"+Utilidades.Campo_Clave+", "+Utilidades.Campo_Tipo_User+", "+Utilidades.Campo_Especialidad+","+Utilidades.Campo_Nit+", "+Utilidades.Campo_Dui+","+Utilidades.Campo_Telefono+","+Utilidades.Campo_Fecha_Nac+","+Utilidades.Campo_Direccion+","+Utilidades.Campo_Estado+" ) " +
                "values('Douglas Calderon','douglas@gmail.com','123','Laboratorio','Cardiologo','5899998-8','05384522-7','2298-8270','16/05/1997','Santa Ana','1'),('Kevin Vazques','kevin@gmail.com','123','Archivo','Anesteciologo','4899997-8','05374522-7','2298-8270','16/05/1997','Cuscatlan','1'),('Isabel Ceron','isa@gmail.com','123','Doctor','Cirujano','4899997-8','05374522-7','2298-8270','16/05/1997','Cuscatlan','1')," +
                "('Pascacio Calderon','pasca@gmail.com','123','Administrador','Anesteciologo','5899998-8','05384522-7','2298-8270','16/05/1997','Santa Ana','1')";

        helper.getWritableDatabase().execSQL(comandoL);
    }
    public void insertarPacientes(){
        helper.abrir();
        String comandoP="INSERT INTO "+Utilidades.Tabla_Paciente+"("+Utilidades.Campo_Nombre_P+", "+Utilidades.Campo_Dui_P+", "+Utilidades.Campo_Nit_P+", "+Utilidades.Campo_Direccion_P+", "+Utilidades.Campo_Fecha_NacP+", "+Utilidades.Campo_Aseguradora+", "+Utilidades.Campo_Num_Afiliado+", "+Utilidades.Campo_Tipo_Sangre+", "+Utilidades.Campo_Peso+", "+Utilidades.Campo_Alergias+", "+Utilidades.Campo_Discapacidades+", "+Utilidades.Campo_Nombre_Emergencia+", "+Utilidades.Campo_Parentesco_Emergencia+", "+Utilidades.Campo_Telefono_Emergencia+" ) " +
                "values('Cristian Nehemias','05384708-2','12555','San Salvador xxx','24/12/2000','Aseguradora x','22988245','Tipo A','75kg','No padece','No padece discapacidad','Juan Miguel','Padre','2498-0584')," +
                "('Luis Miguel','05384708-2','12555','San Salvador xxx','24/12/2000','Aseguradora x','22988245','Tipo A','105kg','No padece','No padece discapacidad','Ana Cerritos','Esposa','2498-0584')," +
                "('Erika Soltala','05384708-2','12555','San Salvador xxx','24/12/2000','Aseguradora x','22988245','Tipo A','105kg','No padece','No padece discapacidad','Cristian Cerritos','Hermano','2498-0584')," +
                "('Alejandron Fernandez','05384708-2','12555','San Salvador xxx','24/12/2000','Aseguradora x','22988245','Tipo A','105kg','No padece','No padece discapacidad','Ana Cerritos','Esposa','2498-0584')";

        helper.getWritableDatabase().execSQL(comandoP);

    }
    public void insertarLugares(){
        helper.abrir();
        String comandoL="INSERT INTO "+Utilidades.Tabla_Lugares+"("+Utilidades.Campo_IdAreaL+", "+Utilidades.Campo_Num_Cama+", "+Utilidades.Campo_Num_Cuarto+", "+Utilidades.Campo_TipoHabitacion+", "+Utilidades.Campo_EstadoL+" ) " +
                "values('1','H-0001','H-1','DOS PERSONAS','1'),('1','H-0002','H-1','DOS PERSONAS','0'),('1','H-0003','H-1','PERSONAL','0'),('1','H-0004','H-1','EJECUTIVA','0')," +
                "('1','H-0005','H-1','DOS PERSONAS','0'),('1','H-0006','H-1','DOS PERSONAS','0'),('1','H-0007','H-1','PERSONAL','0'),('1','H-0008','H-1','EJECUTIVA','0')," +
                "('2','K-0001','K-6','DOS PERSONAS','1'),('2','K-0002','K-6','DOS PERSONAS','0'),('1','K-0003','K-6','PERSONAL','0'),('2','K-0004','K-6','EJECUTIVA','0')," +
                "('2','K-0005','K-6','DOS PERSONAS','0'),('2','K-0006','K-6','DOS PERSONAS','0'),('1','K-0007','K-6','PERSONAL','0'),('2','K-0008','K-6','EJECUTIVA','0')," +
                "('3','J-0001','K-6','DOS PERSONAS','1'),('3','J-0002','K-6','DOS PERSONAS','0'),('3','J-0003','K-6','PERSONAL','0'),('3','J-0004','J-6','EJECUTIVA','0')," +
                "('3','J-0005','J-6','EJECUTIVA','0'),('3','J-0006','J-6','EJECUTIVA','0'),('3','J-0007','J-6','DOS PERSONAS','0'),('3','J-0008','J-6','PERSONAL','0')," +
                "('4','X-0001','X-12','PERSONAL','0'),('4','X-0002','X-12','EJECUTIVA','0'),('4','X-0003','X-12','EJECUTIVA','0'),('4','X-0004','X-12','EJECUTIVA','0')," +
                "('4','X-0005','X-12','DOS PERSONAS','0'),('5','F-0001','F-9','EJECUTIVA','0'),('5','F-0002','F-9','EJECUTIVA','0'),('5','F-0003','F-9','EJECUTIVA','0')," +
                "('5','F-0004','F-9','PERSONAL','0'),('5','F-0005','F-9','PERSONAL','0'),('5','F-0006','F-9','DOS PERSONAS','0'),('5','F-0007','F-9','PERSONAL','0')," +
                "('6','Q-0001','Q-0','PERSONAL','0'),('6','Q-0002','Q-0','PERSONAL','0'),('6','Q-0003','Q-0','PERSONAL','0'),('6','Q-0004','Q-0','PERSONAL','0')," +
                "('6','Q-0005','Q-0','DOS PERSONAS','0'),('6','Q-0006','Q-0','DOS PERSONAS','0'),('6','Q-0007','Q-0','EJECUTIVA','0'),('6','Q-0008','Q-0','EJECUTIVA','0')," +
                "('7','T-0001','T-15','DOS PERSONAS','0'),('7','T-0002','T-15','DOS PERSONAS','0'),('7','T-0003','T-15','DOS PERSONAS','0'),('7','T-0004','T-15','DOS PERSONAS','0')," +
                "('7','T-0005','T-15','PERSONL','0'),('7','T-0006','T-15','PERSONAL','0'),('7','T-0007','T-15','PERSONAL','0'),('7','T-0008','T-15','PERSONAL','0')," +
                "('8','G-0001','G-33','PERSONAL','0'),('8','G-0002','G-33','PERSONAL','0'),('8','G-0003','G-33','PERSONAL','0'),('8','G-0004','G-33','PERSONAL','0')," +
                "('9','B-0021','B-34','PERSONAL','0'),('9','B-0022','B-34','PERSONAL','0'),('9','B-0023','B-34','PERSONAL','0'),('9','B-0024','B-34','PERSONAL','0')," +
                "('10','C-0021','C-15','EJECUTIVA','0'),('10','C-0022','C-15','PERSONAL','0'),('10','C-0023','C-15','PERSONAL','0'),('10','C-0024','C-15','PERSONAL','0')";

        helper.getWritableDatabase().execSQL(comandoL);

    }

    public void insertarCitaGeneral(){
        helper.abrir();
        String comandoCG="INSERT INTO "+Utilidades.Tabla_Cita_General+"("+Utilidades.Campo_IdPaciente_G+","+Utilidades.Campo_IdUsuarios_G+", "+Utilidades.Campo_Fecha_G+", "+Utilidades.Campo_Hora_G+", "+Utilidades.Campo_Estado_G+" ) " +
                "values('1','3','24/11/2020','14:00','1'),('2','3','24/11/2020','15:00','1'),('3','3','24/11/2020','16:00','1'),('4','3','24/11/2020','16:30','1')";
        helper.getWritableDatabase().execSQL(comandoCG);

    }

    public void insertarConsultas(){
        helper.abrir();
        String comando="INSERT INTO "+Utilidades.Tabla_Consultas+"("+Utilidades.Campo_IdCitas+","+Utilidades.Campo_Presion+", "+Utilidades.Campo_Respiraciones+", "+Utilidades.Campo_Diagnostico+", "+Utilidades.Campo_idMedicamento+", "+Utilidades.Campo_Indicaciones+","+Utilidades.Campo_Fecha_Con+","+Utilidades.Campo_TratamientoC+" ) " +
                "values('1','120','12','Presion y respiracion normal temperatura elevada','1','No realizar actividades fisicas exigentes','24/11/2020','Acetaminofen')," +
                "('2','150','12','Presion elevada y temperatura elevada','1','Guardar reposo','24/11/2020','Vasotec')," +
                "('3','120','12','Presion, respiracion y temperatura alta','1','Posible caso de dengue','24/11/2020','Acetaminofen')";

        helper.getWritableDatabase().execSQL(comando);

    }

    public void insertarCitaExamen(){
        helper.abrir();


        String comando="INSERT INTO "+Utilidades.Tabla_Cita_Examen+"("+Utilidades.Campo_IdPaciente_E+","+Utilidades.Campo_IdConsultas_E+", "+Utilidades.Campo_Fecha_E+", "+Utilidades.Campo_Hora_E+", "+Utilidades.Campo_Tipo_E+", "+Utilidades.Campo_Estado_E+" ) " +
                "values('2','2','16/12/2020','13:00','Examen VIH','0'),('2','2','16/12/2020','13:00','Examen Orina','0'),('1','1','16/12/2020','13:00','Examen Glucosa','0'),('1','1','16/12/2020','13:00','Examen VIH','0')";

        helper.getWritableDatabase().execSQL(comando);
        //Toast.makeText(getApplicationContext(),"Insert exitoso..",Toast.LENGTH_SHORT).show();

    }

    public Cursor validacionArea(){

        helper.abrir();
        Cursor u = null;

        u=helper.getWritableDatabase().rawQuery("SELECT * FROM "+Utilidades.Tabla_Areas,new String[]{});

        return u;

    }
    public Cursor validacionPacientes(){

        helper.abrir();
        Cursor u = null;

        u=helper.getWritableDatabase().rawQuery("SELECT * FROM "+Utilidades.Tabla_Paciente,new String[]{});

        return u;

    }
    public Cursor validacionUsuarios(){

        helper.abrir();
        Cursor u = null;

        u=helper.getWritableDatabase().rawQuery("SELECT * FROM "+Utilidades.Tabla_Usuario,new String[]{});

        return u;

    }
    //un SELECT para verificar si existen datos en tabla Lugares
    public Cursor validacionLugares(){

        helper.abrir();
        Cursor u = null;

        u=helper.getWritableDatabase().rawQuery("SELECT * FROM "+Utilidades.Tabla_Lugares,new String[]{});

        return u;

    }
    @Override
    public void onPause(){
        super.onPause();
        finish();
    }
}