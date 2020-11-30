package com.example.pruebanavegacion.ui_administrador.GestionarAreas;

import android.app.Activity;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.pruebanavegacion.Administrador;
import com.example.pruebanavegacion.R;
import com.example.pruebanavegacion.ui_administrador.Inicio.InicioFragment_A;

import java.util.ArrayList;
import java.util.Date;

import BaseHospital.DatosConexion;
import BaseHospital.Sqlite_Base;
import Utilidades.Utilidades;

public class GestionarAreasF extends Fragment {

    private Sqlite_Base x;
    private GestionarAreasVM GestionarAreasVM;
    ArrayList<String> AreasA,HabitacionesA,DoctoresA;
    ArrayList<String> AreasI,HabitacionesI,DoctoresI;
    ArrayAdapter adapter1,adapter2,adapter3;
    String box,numcam;
    Button AgregarGA,Cancelar;
    EditText NoPa,ErrorDoc;
    int indexHabitacion;
    Spinner Areas,Habitacion,Especialida,Intervencion,Medico,Dia,Mes,Ano,Hora,Min;
    View vista;

    final String[] idintervencion = new String[1];
    final String[] idarea= new String[1];
    final String[] iduser= new String[1];
    final String[]  idhabitacion = new String[1];
    final String[] di = new String[1];
    final String[] me= new String[1];
    final String[] an= new String[1];
    final String[] hh= new String[1];
    final String[] min= new String[1];
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GestionarAreasVM =
                ViewModelProviders.of(this).get(GestionarAreasVM.class);
        vista= inflater.inflate(R.layout.a_fragment_gestionarareas, container, false);


        Areas = vista.findViewById(R.id.spnAreasGA);
        Habitacion= vista.findViewById(R.id.spnHabi);
        Especialida = vista.findViewById(R.id.spnEsp);
        Medico = vista.findViewById(R.id.spnNMedico);
        Intervencion = vista.findViewById(R.id.spnIntervencion);
        AgregarGA = vista.findViewById(R.id.btnAgregarGA);
        Dia = vista.findViewById(R.id.spnDiaGA);
        Mes = vista.findViewById(R.id.spnMesGA);
        Ano = vista.findViewById(R.id.spnAnoGA);
        Hora =vista.findViewById(R.id.spnHoraGA);
        Min = vista.findViewById(R.id.spnMinGA);
        NoPa = vista.findViewById(R.id.edtNumPacienteGA);
        Cancelar = vista.findViewById(R.id.btnCancelarGA);
        ErrorDoc = vista.findViewById(R.id.txtErrorDoc);
        x= new Sqlite_Base(getContext(),DatosConexion.NOMBREBD,null,DatosConexion.VERSION);
        x.abrir();
        consultarArea();

        adapter1 = new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,AreasA);
        Areas.setAdapter(adapter1);

        Areas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    indexHabitacion= i+1;
                    //Mando el id de la seleccion
                    consultarLugares(indexHabitacion);
                    adapter2 = new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,HabitacionesA);
                    Habitacion.setAdapter(adapter2);
                    //Lleno el Array buscando en AreasI la posicion que selecione
                    idarea[0] = AreasI.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        Intervencion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idintervencion[0]=adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Especialida.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                consultarDoc(adapterView.getSelectedItem().toString());
                adapter3 = new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,DoctoresA);
                Medico.setAdapter(adapter3);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        Dia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                di[0]=adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Mes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                me[0]=adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Ano.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                an[0]=adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Hora.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    hh[0]= adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Min.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                min[0]= adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Medico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    iduser[0]=DoctoresI.get(i);

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Habitacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idhabitacion[0] = HabitacionesI.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        AgregarGA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String FechaS;
                String HoraS;
                String idALs[] = {Habitacion.getSelectedItem().toString()};
                FechaS = Dia.getSelectedItem().toString()+"-"+Mes.getSelectedItem().toString()+"-"+Ano.getSelectedItem().toString();
                HoraS = Hora.getSelectedItem().toString()+":"+Min.getSelectedItem().toString();
                String cadFe = di[0]+"/"+me[0]+"/"+an[0];
                String cadHor = hh[0]+":"+min[0];

                boolean bv= validarFechaHoraDoc(cadFe,cadHor,iduser[0]);
                if((NoPa.getText().toString()).equals("")){
                    NoPa.setError("Ingrese un ID");
                    NoPa.requestFocus();

                }else {
                    if(bv==true){
                        Toast.makeText(getContext(),"DOCTOR NO DISPONIBLE A ESTA HORA",Toast.LENGTH_LONG).show();
                    }else
                    {

                        Toast.makeText(getContext(),"INGRESADO",Toast.LENGTH_LONG).show();
                        insetarIngresos(NoPa.getText().toString(),iduser[0],idhabitacion[0],idintervencion[0],FechaS,HoraS,1);
                        actualizarEstado(idALs,1);

                        Intent ga = new Intent(getContext(), Administrador.class);
                        startActivity(ga);
                    }
                }


            }
        });
        x.close();
        return vista;
    }

    public void actualizarEstado(String[] idAL,int est){

        ContentValues val = new ContentValues();
        x = new Sqlite_Base(getContext(), DatosConexion.NOMBREBD,null,DatosConexion.VERSION);

        val.put(Utilidades.Campo_EstadoL,est);

        x.getWritableDatabase().update(Utilidades.Tabla_Lugares,val ,Utilidades.Campo_Num_Cama+"=?",idAL);
    }
    public void consultarArea(){

        AreasA= new ArrayList<>();
        AreasI = new ArrayList<>();

        x= new Sqlite_Base(getContext(),DatosConexion.NOMBREBD,null,DatosConexion.VERSION);
        x.abrir();

        Cursor u = null;

        u=x.getWritableDatabase().rawQuery("SELECT IdArea,Nombre FROM "+Utilidades.Tabla_Areas,new String[]{});

        while(u.moveToNext()){
            box=u.getString(0);
            AreasI.add(box);
            box=u.getString(1);
            AreasA.add(box);
        }
    }
    public void consultarLugares(int i){

        HabitacionesA= new ArrayList<>();
        HabitacionesI = new ArrayList<>();
        x= new Sqlite_Base(getContext(),DatosConexion.NOMBREBD,null,DatosConexion.VERSION);

        x.abrir();

        Cursor u = null;
        u=x.getWritableDatabase().rawQuery("SELECT IdLugar,Num_Cama FROM "+Utilidades.Tabla_Lugares+" where "+Utilidades.Campo_IdAreaL+" = "+i+" and Estado="+0,new String[]{});

        while(u.moveToNext()){

            box=u.getString(0);
            HabitacionesI.add(box);
            box=u.getString(1);
            HabitacionesA.add(box);
        }
    }

    public void consultarDoc(String esp){

        DoctoresA= new ArrayList<>();
        DoctoresI = new ArrayList<>();

        x= new Sqlite_Base(getContext(),DatosConexion.NOMBREBD,null,DatosConexion.VERSION);

        x.abrir();

        Cursor u = null;

        u=x.getWritableDatabase().rawQuery("SELECT Id,Nombre FROM "+Utilidades.Tabla_Usuario+"  where Especialidad='"+esp+"' and Estado= "+1+" and Tipo_User = 'Doctor'",new String[]{});

        while(u.moveToNext()){

            box=u.getString(0);
            DoctoresI.add(box);
            box = u.getString(1);
            DoctoresA.add(box);

        }
    }

    public void insetarIngresos( String idPaciente, String IdUsuario ,String IdLugar, String tipoIntervencion ,String FechaI, String HoraI, Integer est){
        //Estos valores cuando se envien se deben colocar en ...

        x= new Sqlite_Base(getContext(),DatosConexion.NOMBREBD,null,DatosConexion.VERSION);
        ContentValues valores=new ContentValues();
        //Con put agregamos valores a el objeto valores
        valores.put(Utilidades.Campo_IdPacienteI,idPaciente);
        valores.put(Utilidades.Campo_IdUsuariosI,IdUsuario);
        valores.put(Utilidades.Campo_IdLugarI,IdLugar);
        valores.put(Utilidades.Campo_Tipo_Intervencion,tipoIntervencion);
        valores.put(Utilidades.Campo_FechaIngreso,FechaI);
        valores.put(Utilidades.Campo_HoraIngreso,HoraI);
        valores.put(Utilidades.Campo_EstadoI,est);

        Long idResultante= x.getWritableDatabase().insert(Utilidades.Tabla_Ingresos, Utilidades.Campo_Id,valores);
        Toast.makeText(getContext(),"INGRESO INSERTADO: "+idResultante,Toast.LENGTH_SHORT).show();

    }
    public boolean validarDiaExist(String fec,String idP){

        x = new Sqlite_Base(getContext(),DatosConexion.NOMBREBD,null,DatosConexion.VERSION);
        x.abrir();
        String fech=" ",hora=" ";
        Boolean s=false;
        Cursor gf = null;

        gf = x.getWritableDatabase().rawQuery("Select FechaIngreso,HoraIngreso from Ingresos where IdPaciente = "+idP+"and Estado = 1",new String[]{});

        while(gf.moveToNext()){

            fech=gf.getString(0);
            hora = gf.getString(1);
        }
        if(fec.equals(fech)){
            s= false;
        }else{
            s= true;
        }

        return s;
    }
    public boolean validarFechaHoraDoc(String fec,String hor,String idD){

        x = new Sqlite_Base(getContext(),DatosConexion.NOMBREBD,null,DatosConexion.VERSION);
        x.abrir();
        String fech=" ",hora=" ",es=" ";
        boolean xc = false;
        Cursor gf = null;

        gf = x.getWritableDatabase().rawQuery("Select FechaIngreso,HoraIngreso from Ingresos where IdUsuarios = "+idD+" and Estado = 1",new String[]{});
        while(gf.moveToNext()){
            fech=gf.getString(0);
            hora = gf.getString(1);
        }

        if(fech.equals(fec)){
            if(hora.equals(hor)){
                xc=true;
            }
            else
            {
                xc = false;
            }
        }
        return xc;
    }
}