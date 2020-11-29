package com.example.pruebanavegacion.ui_administrador.AgregarUsuario;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.pruebanavegacion.Administrador;
import com.example.pruebanavegacion.MainActivity;
import com.example.pruebanavegacion.R;
import com.example.pruebanavegacion.Registrar;
import com.example.pruebanavegacion.ui_administrador.Inicio.InicioFragment_A;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import BaseHospital.DatosConexion;
import BaseHospital.Sqlite_Base;
import Utilidades.Utilidades;

public class AgregarUsuarioF extends Fragment {
    private AgregarUsuarioVM agregarUsuarioVM;
    private Sqlite_Base helper;
    View vista;
    CheckBox e1,e2,e3;
    Button Guardar;
    EditText NombreEmpleado,DUI,NIT ,Usuario,Clave,ConClave,Direccion,NumEmpleado;
    Spinner Dia,Mes,Ano,Especialidad;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        agregarUsuarioVM =
                ViewModelProviders.of(this).get(AgregarUsuarioVM.class);
        vista = inflater.inflate(R.layout.a_fragment_agregarusuario, container, false);

        Utilidades rp = new Utilidades();
        helper = new Sqlite_Base(getContext(),DatosConexion.NOMBREBD,null,DatosConexion.VERSION);

        final String[] select = new String[1];


        Especialidad = vista.findViewById(R.id.spnEspecialidad);
        NumEmpleado = vista.findViewById(R.id.edtNumEmp);
        NombreEmpleado = vista.findViewById(R.id.edtNombreEmpleado);
        DUI = vista.findViewById(R.id.edtDui);
        NIT = vista.findViewById(R.id.edtNIT);
        Usuario = vista.findViewById(R.id.edtUsuario);
        Clave = vista.findViewById(R.id.edtContrasena);
        ConClave = vista.findViewById(R.id.edtConContrasena);
        Dia = vista.findViewById(R.id.spnDia);
        Mes = vista.findViewById(R.id.spnMes);
        Ano = vista.findViewById(R.id.spnAno);
        Direccion =  vista.findViewById(R.id.edtDireccion);
        Guardar = vista.findViewById(R.id.btnAgregar);

        e1 = (CheckBox) vista.findViewById(R.id.chkDoctor);
        e2 = (CheckBox) vista.findViewById(R.id.chkArchivo);
        e3= (CheckBox) vista.findViewById(R.id.chkAdmin);


        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e2.setChecked(false);
                e3.setChecked(false);
                select[0] ="Archivo";
            }
        });
        e2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e1.setChecked(false);
                e3.setChecked(false);
                select[0]="Administrador";
            }
        });
        e3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e1.setChecked(false);
                e2.setChecked(false);
                select[0]="Doctor";
            }
        });
        Guardar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                helper = new Sqlite_Base(getContext(),DatosConexion.NOMBREBD,null,DatosConexion.VERSION);
                Intent ok = new Intent(getContext(),Administrador.class);
                String d,m,a,fecha_nac;

                if(validacorreo(Usuario.getText().toString())==false){
                    Cursor u =helper.ConsultarUsuPas(Usuario.getText().toString(),Clave.getText().toString());
                    //Primer error
                    Usuario.setError("Ingrese un correo valido");
                    //Colocamos un focus
                    Usuario.requestFocus();

                }else{
                    Cursor u =helper.ConsultarUsuPas(Usuario.getText().toString(),Clave.getText().toString());
                    if(u.getCount()<1){

                        if((Clave.getText().toString()).equals(ConClave.getText().toString())){

                            d = Dia.getSelectedItem().toString();
                            m = Mes.getSelectedItem().toString();
                            a = Ano.getSelectedItem().toString();
                            fecha_nac = d+"-"+m+"-"+a;

                            try{

                                helper.abrir();
                                helper.insetarReg(NombreEmpleado.getText().toString(),Usuario.getText().toString(), Clave.getText().toString(),select[0],Especialidad.getSelectedItem().toString(),NIT.getText().toString(),DUI.getText().toString(),"7809-4237", fecha_nac, Direccion.getText().toString(),1);
                                Toast.makeText(getContext(),"COINCIDEN "+NombreEmpleado.getText()+fecha_nac,Toast.LENGTH_SHORT).show();
                                helper.close();
                                startActivity(ok);
                            }catch (Exception e){
                                Toast.makeText(getContext(),e.toString(),Toast.LENGTH_SHORT).show();

                            }

                        }else
                        {
                            ConClave.setError("Contraseñas no Coinciden");
                            //Colocamos un focus
                            Usuario.requestFocus();

                        }
                    }else
                    {
                        Usuario.setError("CORREO YA EXISTENTE");
                        Toast.makeText(getContext(),"CORREO YA EXISTENTE ",Toast.LENGTH_SHORT).show();
                        Usuario.requestFocus();
                    }
                }
            }
        });

        return vista;

    }

    public boolean validacorreo(String crrr){
        boolean g=false;
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(crrr);

        if (mather.find() == true) {
            g=true;
        } else {
            g=false;
        }
        return g;
    }

}