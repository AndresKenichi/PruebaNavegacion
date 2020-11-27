package com.example.pruebanavegacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import BaseHospital.DatosConexion;
import BaseHospital.Sqlite_Base;

public class Registrar extends AppCompatActivity {

    Button btnRegistrar;
    EditText txtNomUsu,txtDisUsu,txtCorUsu,txtPassUsu;
    EditText  txtEst, txtNit, txtDui, txtTel, txtFecha;
    Spinner Especialidad,TipoU;
/*    String nom, correo, clave , tipo;
    Integer esp,est;
    String nit, dui, tel, fecha, direccion; */


    //Creamos una instacia de nuestra base de datos
    Sqlite_Base helper=new Sqlite_Base(this, DatosConexion.NOMBREBD,null,DatosConexion.VERSION);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);


        btnRegistrar=findViewById(R.id.btnRegistrar);
        txtNomUsu=findViewById(R.id.txtNomUsu);
        txtDisUsu=findViewById(R.id.txtDisUsu);
        txtCorUsu=findViewById(R.id.txtCorUsu);
        txtPassUsu=findViewById(R.id.txtPasUsu);
        TipoU=findViewById(R.id.spnTi);
        Especialidad= findViewById(R.id.spnEspe);
        txtEst=findViewById(R.id.txtEst);
        txtNit=findViewById(R.id.txtNit);
        txtDui=findViewById(R.id.txtDui);
        txtTel=findViewById(R.id.txtTel);
        txtFecha=findViewById(R.id.txtFecha);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtNomUsu.setError(null);
                txtCorUsu.setError(null);
                txtDisUsu.setError(null);
                txtPassUsu.setError(null);
                txtEst.setError(null);
                txtNit.setError(null);
                txtDui.setError(null);
                txtTel.setError(null);
                txtFecha.setError(null);

                String nom, correo, clave,Sest;
                Integer est;
                String nit, dui, tel, fecha, direccion;

                nom=txtNomUsu.getText().toString().trim();
                correo=txtCorUsu.getText().toString().trim();
                clave=txtPassUsu.getText().toString().trim();

                nit=txtNit.getText().toString().trim();
                dui=txtDui.getText().toString().trim();
                tel=txtTel.getText().toString().trim();
                fecha=txtFecha.getText().toString().trim();
                direccion=txtDisUsu.getText().toString().trim();
                Sest=txtEst.getText().toString().trim();


                if(nom.equals("")){
                    //Primer error
                    txtNomUsu.setError("No puedes dejar campos vacios");
                    //Colocamos un focus
                    txtNomUsu.requestFocus();
                    return;
                }

                if(correo.equals("")){
                    //Primer error
                    txtCorUsu.setError("No puedes dejar campos vacios");
                    //Colocamos un focus
                    txtCorUsu.requestFocus();
                    return;
                }

                if(clave.equals("")){
                    //Primer error
                    txtPassUsu.setError("No puedes dejar campos vacios");
                    //Colocamos un focus
                    txtPassUsu.requestFocus();
                    return;
                }




                if(nit.equals("")){
                    //Primer error
                    txtNit.setError("No puedes dejar campos vacios");
                    //Colocamos un focus
                    txtNit.requestFocus();
                    return;

                }
                if(dui.equals("")){
                    //Primer error
                    txtDui.setError("No puedes dejar campos vacios");
                    //Colocamos un focus
                    txtDui.requestFocus();
                    return;

                }
                if(tel.equals("")){
                    //Primer error
                    txtTel.setError("No puedes dejar campos vacios");
                    //Colocamos un focus
                    txtTel.requestFocus();
                    return;

                }
                if(fecha.equals("")){
                    //Primer error
                    txtFecha.setError("No puedes dejar campos vacios");
                    //Colocamos un focus
                    txtFecha.requestFocus();
                    return;

                }

                if(direccion.equals("")){
                    //Primer error
                    txtDisUsu.setError("No puedes dejar campos vacios");
                    //Colocamos un focus
                    txtDisUsu.requestFocus();
                    return;
                }
                if(Sest.equals("")){
                    //Primer error
                    txtEst.setError("No puedes dejar campos vacios");
                    //Colocamos un focus
                    txtEst.requestFocus();
                    return;
                }



                try {

                    est=Integer.parseInt(Sest);
                    String hue = Especialidad.getSelectedItem().toString();
                    String euh = TipoU.getSelectedItem().toString();

                    helper.abrir();
                    helper.insetarReg(nom, correo, clave, euh, hue, nit, dui, tel, fecha, direccion, est);
                    Toast.makeText(getApplicationContext(), "Id Registro: " + helper.IdR(), Toast.LENGTH_SHORT).show();
                    helper.close();

                    Intent intent = new Intent(Registrar.this, MainActivity.class);
                    startActivity(intent);
                } catch (SQLException e){
                    e.printStackTrace();
                }

            }
        });


    }

}