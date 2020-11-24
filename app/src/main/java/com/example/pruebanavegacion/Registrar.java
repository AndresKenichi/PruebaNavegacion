package com.example.pruebanavegacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import BaseHospital.DatosConexion;
import BaseHospital.Sqlite_Base;

public class Registrar extends AppCompatActivity {

    Button btnRegistrar;
    EditText txtNomUsu,txtDisUsu,txtCorUsu,txtPassUsu, txtTipo;
    EditText txtEsp, txtEst, txtNit, txtDui, txtTel, txtFecha;

    String nom, correo, clave , tipo;
    Integer esp,est;
    String nit, dui, tel, fecha, direccion;


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
        txtTipo=findViewById(R.id.txtTipo);
        txtEsp=findViewById(R.id.txtEsp);
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
                txtTipo.setError(null);
                txtEsp.setError(null);
                txtEst.setError(null);
                txtNit.setError(null);
                txtDui.setError(null);
                txtTel.setError(null);
                txtFecha.setError(null);

                String nom, correo, clave;
                Integer esp,est,tipo;;
                String nit, dui, tel, fecha, direccion;

                nom=txtNomUsu.getText().toString();
                correo=txtCorUsu.getText().toString();
                clave=txtPassUsu.getText().toString();
                tipo=Integer.parseInt(txtTipo.getText().toString());
                esp=Integer.parseInt(txtEsp.getText().toString());
                nit=txtNit.getText().toString();
                dui=txtDui.getText().toString();
                tel=txtTel.getText().toString();
                fecha=txtFecha.getText().toString();
                direccion=txtDisUsu.getText().toString();
                est=Integer.parseInt(txtEst.getText().toString());


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

                if(tipo.equals("")){
                    //Primer error
                    txtTipo.setError("No puedes dejar campos vacios");
                    //Colocamos un focus
                    txtTipo.requestFocus();
                    return;
                }

                if(esp.equals("")){
                    //Primer error
                    txtTipo.setError("No puedes dejar campos vacios");
                    //Colocamos un focus
                    txtTipo.requestFocus();
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
                if(est.equals("")){
                    //Primer error
                    txtEst.setError("No puedes dejar campos vacios");
                    //Colocamos un focus
                    txtEst.requestFocus();
                    return;
                }






                helper.abrir();
                helper.insetarReg(nom, correo, clave ,tipo, esp ,nit, dui, tel, fecha, direccion ,est);
                Toast.makeText(getApplicationContext(),"Id Registro: "+helper.IdR(),Toast.LENGTH_SHORT).show();
                helper.close();
                Intent intent=new Intent(Registrar.this,MainActivity.class);
                startActivity(intent);

            }
        });


    }
}