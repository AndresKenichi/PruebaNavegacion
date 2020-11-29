package com.example.pruebanavegacion.ui_administrador.ModificarUsuario;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.pruebanavegacion.Administrador;
import com.example.pruebanavegacion.R;
import com.example.pruebanavegacion.ui_administrador.Inicio.InicioFragment_A;

import java.util.ArrayList;

import BaseHospital.DatosConexion;
import BaseHospital.Sqlite_Base;
import Utilidades.Utilidades;

public class ModificarUsuarioF extends Fragment {

    private ModificarUsuarioVM modificarUsuarioVM;
    Spinner Especialidad,TipoUsuario,Dia,Mes,Ano;
    ToggleButton Estado;
    EditText Codigo,Nombre,Correo,Clave,ConClave,DUI,NIT,Telefono,Direccion,NoEmpleado;
    Button btnVolver,btnModificar,btnBuscar;
    View vista;
    String box;
    Sqlite_Base X;
    ArrayList<String> data ;
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        modificarUsuarioVM =
                ViewModelProviders.of(this).get(ModificarUsuarioVM.class);
        vista = inflater.inflate(R.layout.a_fragment_modificarusuario, container, false);

        btnVolver=vista.findViewById(R.id.btnCancelar);
        btnModificar=vista.findViewById(R.id.btnModificar);
        btnBuscar=vista.findViewById(R.id.btnBuscarEmpleado);
        Estado = vista.findViewById(R.id.tbEst);
        Codigo = vista.findViewById(R.id.edtCodEmpleado);
        Especialidad = vista.findViewById(R.id.spnEspecialidadMU);
        TipoUsuario = vista.findViewById(R.id.spnTipoUsuarioMU);
        Nombre = vista.findViewById(R.id.edtNombreEmpMU);
        DUI = vista.findViewById(R.id.edtDuiMU);
        NIT = vista.findViewById(R.id.edtNitMU);
        Correo = vista.findViewById(R.id.edtUsuarioMU);
        Clave = vista.findViewById(R.id.edtContrasenaMU);
        ConClave = vista.findViewById(R.id.edtContrasenaMU);
        Telefono = vista.findViewById(R.id.edtTelefonoMU);
        Direccion = vista.findViewById(R.id.etDirMU);
        NoEmpleado = vista.findViewById(R.id.edtNoEmpleadoMU);
        Dia = vista.findViewById(R.id.spnDiaMU);
        Mes = vista.findViewById(R.id.spnMesMU);
        Ano = vista.findViewById(R.id.spnAnoMU);



        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                X = new Sqlite_Base(getContext(), DatosConexion.NOMBREBD,null,DatosConexion.VERSION);
                X.abrir();
                data  = new ArrayList<>();

                Cursor c =null;

                c = X.getWritableDatabase().rawQuery("Select Estado,Tipo_User,Especialidad,Nombre,Id,Dui,Nit,Telefono,Fecha_Nac,Direccion,Correo,Clave from usuarios where Id = '"+Codigo.getText().toString()+"'",new String[]{});
                while(c.moveToNext()){

                    for(int i =0; i<12;i++){

                        box=c.getString(i);
                        data.add(box);
                    }
                }

                if(data.isEmpty()==false){
                    //Validar El Estado para el TooggleButton
                    if(data.get(0).equals("1")){

                        Estado.setChecked(true);
                        Estado.setText("ACTIVO");

                    }
                    if(data.get(0).equals("0")){

                        Estado.setChecked(false);
                        Estado.setText("DE BAJA");

                    }
                    //Fin
                    Nombre.setText(data.get(3));
                    NoEmpleado.setText(data.get(4));
                    DUI.setText(data.get(5));
                    NIT.setText(data.get(6));
                    Telefono.setText(data.get(7));
                    Direccion.setText(data.get(9));
                    Correo.setText(data.get(10));
                    Clave.setText(data.get(11));


                    ArrayAdapter adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.snpespecialidades, android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    Especialidad.setAdapter(adapter1);
                    if (data.get(2) != null){
                        int spinnerPosition1 = adapter1.getPosition(data.get(2));
                        Especialidad.setSelection(spinnerPosition1);
                    }

                    ArrayAdapter adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.snproles, android.R.layout.simple_spinner_item);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    TipoUsuario.setAdapter(adapter2);
                    if (data.get(1) != null){
                        int spinnerPosition2 = adapter2.getPosition(data.get(1));
                        TipoUsuario.setSelection(spinnerPosition2);
                    }


                }else{
                    if(Codigo.getText().toString().equals("")){
                        Codigo.setError("Ingrese un ID");
                        Codigo.requestFocus();
                    }else{
                        Toast.makeText(getContext(),"NO ENCONTRADO",Toast.LENGTH_LONG).show();

                    }



                }
            }
        });
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String[] idUsu={Codigo.getText().toString()};
                String Es1=Estado.getTextOff().toString();
                String Es11=Estado.getTextOn().toString();
                String dd,mm,aa;
                String Fecc=" ";
                dd=Dia.getSelectedItem().toString();
                mm=Mes.getSelectedItem().toString();
                aa=Ano.getSelectedItem().toString();

                Fecc=dd+"-"+mm+"-"+aa ;
                int est2=0;

                if(Es1.equals("DE BAJA")){est2=0;}
                if(Es11.equals("ACTIVO")){est2=1;}

                if(Codigo.getText().toString().equals("")){

                    Codigo.setError("Ingrese un ID");
                    Codigo.requestFocus();


                }else{
                        if(Nombre.equals("")||NoEmpleado.equals("")||DUI.equals("")||NIT.equals("")||Direccion.equals(""))
                        {
                            Codigo.setError("DEBE INGRESAR EL MALDITO CODIGO!");
                            Codigo.requestFocus();
                        }else {
                            ModificarReg(idUsu, Nombre.getText().toString(), Correo.getText().toString(), Clave.getText().toString(), TipoUsuario.getSelectedItem().toString(), Especialidad.getSelectedItem().toString(), NIT.getText().toString(), DUI.getText().toString(), Telefono.getText().toString(), Fecc, Direccion.getText().toString(), est2);
                            Toast.makeText(getContext(), "Usuario Modificado!", Toast.LENGTH_LONG).show();

                            Intent ja = new Intent(getContext(), Administrador.class);
                            startActivity(ja);
                        }

                }

            }
        });
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent fa = new Intent(getContext(), Administrador.class);
                startActivity(fa);

            }
        });
        return vista;
    }
    public void ModificarReg(String[] idUsu, String nom, String correo, String clave ,String tipo, String esp ,String nit, String dui,String tel, String fecha, String direccion ,Integer est){
        //Estos valores cuando se envien se deben colocar en ...
        ContentValues valores=new ContentValues();
        X = new Sqlite_Base(getContext(), DatosConexion.NOMBREBD,null,DatosConexion.VERSION);
        //Con put agregamos valores a el objeto valores
        valores.put(Utilidades.Campo_Nombre,nom);
        valores.put(Utilidades.Campo_Correo,correo);
        valores.put(Utilidades.Campo_Clave,clave);
        valores.put(Utilidades.Campo_Tipo_User,tipo);
        valores.put(Utilidades.Campo_Especialidad,esp);
        valores.put(Utilidades.Campo_Nit,nit);
        valores.put(Utilidades.Campo_Dui,dui);
        valores.put(Utilidades.Campo_Telefono,tel);
        valores.put(Utilidades.Campo_Fecha_Nac,fecha);
        valores.put(Utilidades.Campo_Direccion,direccion);
        valores.put(Utilidades.Campo_Estado,est);

        X.getWritableDatabase().update(Utilidades.Tabla_Usuario,valores,Utilidades.Campo_Id+"=?",idUsu);
        //Toast.makeText(this,"Id Registro: "+idResultante,Toast.LENGTH_SHORT).show();

    }
}