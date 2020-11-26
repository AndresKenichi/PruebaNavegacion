package com.example.pruebanavegacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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

        txtCorUsu=findViewById(R.id.txtUsuario);
        txtPassUsu=findViewById(R.id.txtPassword);
        tvRegistrese=findViewById(R.id.tvRegistrese);
        btnIngresar=findViewById(R.id.btnIngresar);
        Cursor h =  validacionArea();
        Cursor k =  validacionLugares();


        if(k.getCount()>0){
            Toast.makeText(this,"TENEMOS DATOS LUGARES",Toast.LENGTH_LONG).show();
        }else{
            insertarLugares();
        }

        if(h.getCount()>0){
            Toast.makeText(this,"TENEMOS DATOS AREAS",Toast.LENGTH_LONG).show();

        }else{

            insertarArea();

        }



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
                        Integer rol= cursor.getInt(4);


                        Toast.makeText(getApplicationContext(),"Usuario:"+msj,Toast.LENGTH_SHORT).show();
                      //Nivel 0 Doctor
                        if(rol==0){

                            Intent intent=new Intent(MainActivity.this,Home.class);
                            intent.putExtra("correo",cor);
                            startActivity(intent);

                        }
                       //Nivel 1 Administrador
                        if(rol==1){

                            Intent intent1=new Intent(MainActivity.this,Administrador.class);
                            intent1.putExtra("correo",cor);
                            startActivity(intent1);

                        }
                      //Nivel 2 Archivo
                       else if(rol==2){

                            Intent intent2=new Intent(MainActivity.this,Archivo.class);
                            intent2.putExtra("correo",cor);
                            startActivity(intent2);

                        }

                       //Nivel 3 laboratorio
                        else if(rol==3){

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
                "values('ANESTESIOLOGIA'),('URGENCIAS'),('CIRUGIA'),('ONCOLOGIA'),('CARDIOLOGIA');";

        helper.getWritableDatabase().execSQL(comandoAre4);
        helper.cerrar();

    }
    public void insertarLugares(){
        helper.abrir();
        String comandoL="INSERT INTO "+Utilidades.Tabla_Lugares+"("+Utilidades.Campo_IdAreaL+", "+Utilidades.Campo_Num_Cama+", "+Utilidades.Campo_Num_Cuarto+", "+Utilidades.Campo_TipoHabitacion+", "+Utilidades.Campo_EstadoL+" ) " +
                "values('1','H-0001','H-1','DOS PERSONAS','0'),('1','H-0002','H-1','DOS PERSONAS','0'),('1','H-0003','H-1','PERSONAL','0'),('1','H-0004','H-1','EJECUTIVA','0')," +
                "('1','H-0005','H-1','DOS PERSONAS','0'),('1','H-0006','H-1','DOS PERSONAS','0'),('1','H-0007','H-1','PERSONAL','0'),('1','H-0008','H-1','EJECUTIVA','0')," +
                "('2','K-0001','K-6','DOS PERSONAS','0'),('2','K-0002','K-6','DOS PERSONAS','0'),('1','K-0003','K-6','PERSONAL','0'),('2','K-0003','K-6','EJECUTIVA','0')," +
                "('2','K-0004','K-6','DOS PERSONAS','0'),('2','K-0005','K-6','DOS PERSONAS','0'),('1','K-0006','K-6','PERSONAL','0'),('2','K-0003','K-6','EJECUTIVA','0')," +
                "('3','J-0001','K-6','DOS PERSONAS','0'),('3','J-0002','K-6','DOS PERSONAS','0'),('3','J-0003','K-6','PERSONAL','0'),('3','J-0003','J-6','EJECUTIVA','0')";

        helper.getWritableDatabase().execSQL(comandoL);
        helper.cerrar();
    }
    public Cursor validacionArea(){

        helper.abrir();
        Cursor u = null;

        u=helper.getWritableDatabase().rawQuery("SELECT * FROM "+Utilidades.Tabla_Areas,new String[]{});

        return u;

    }
    public Cursor validacionLugares(){

        helper.abrir();
        Cursor u = null;

        u=helper.getWritableDatabase().rawQuery("SELECT * FROM "+Utilidades.Tabla_Lugares,new String[]{});

        return u;

    }
}