package com.example.pruebanavegacion.ui_administrador.GestionarAreas;

import android.content.ContentValues;
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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.pruebanavegacion.R;

import java.util.ArrayList;

import BaseHospital.DatosConexion;
import BaseHospital.Sqlite_Base;
import Utilidades.Utilidades;

public class GestionarAreasF extends Fragment {

    private Sqlite_Base x;
    private GestionarAreasVM GestionarAreasVM;
    ArrayList<String> AreasA,HabitacionesA,DoctoresA;
    ArrayList<String> AreasI,HabitacionesI,DoctoresI;
    ArrayAdapter adapter1,adapter2,adapter3;
    String box;
    Button AgregarGA;
    EditText NoPa;
    int indexHabitacion;
    Spinner Areas,Habitacion,Especialida,Medico,Dia,Mes,Ano,Hora,Min;
    View vista;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GestionarAreasVM =
                ViewModelProviders.of(this).get(GestionarAreasVM.class);
        vista= inflater.inflate(R.layout.a_fragment_gestionarareas, container, false);


        Areas = vista.findViewById(R.id.spnAreasGA);
        Habitacion= vista.findViewById(R.id.spnHabi);
        Especialida = vista.findViewById(R.id.spnEsp);
        Medico = vista.findViewById(R.id.spnNMedico);
        AgregarGA = vista.findViewById(R.id.btnAgregarGA);
        Dia = vista.findViewById(R.id.spnDiaGA);
        Mes = vista.findViewById(R.id.spnMesGA);
        Ano = vista.findViewById(R.id.spnAnoGA);
        Hora =vista.findViewById(R.id.spnHoraGA);
        Min = vista.findViewById(R.id.spnMinGA);
        NoPa = vista.findViewById(R.id.edtNumPacienteGA);
        x= new Sqlite_Base(getContext(),DatosConexion.NOMBREBD,null,DatosConexion.VERSION);

        x.abrir();

        consultarArea();
        final String[] id1 = new String[1];
        final String[] id2= new String[1];
        final String[] id3= new String[1];
        adapter1 = new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,AreasA);
        Areas.setAdapter(adapter1);



        Areas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                    indexHabitacion= i+1;
                    consultarLugares(indexHabitacion);
                    adapter2 = new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,HabitacionesA);
                    Habitacion.setAdapter(adapter2);
                    id1[0] =AreasI.get(i);
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
                id2[0] =AreasI.get(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Medico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                id3[0] =AreasI.get(i);
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

                FechaS = Dia.getSelectedItem().toString()+"-"+Mes.getSelectedItem().toString()+"-"+Ano.getSelectedItem().toString();
                HoraS = Hora.getSelectedItem().toString()+":"+Min.getSelectedItem().toString();

                if((NoPa.getText().toString()).equals("")){
                    NoPa.setError("Ingrese un ID");
                    NoPa.requestFocus();
                }else
                {

                    insetarIngresos(NoPa.getText().toString(),id3[0],id2[0],id1[0],FechaS,HoraS,1);

                }

            }
        });

        x.close();

        return vista;

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

        u=x.getWritableDatabase().rawQuery("SELECT Id,Nombre FROM "+Utilidades.Tabla_Usuario+"  where Especialidad='"+esp+"'",new String[]{});

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
}