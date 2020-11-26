package com.example.pruebanavegacion.ui_administrador.GestionarAreas;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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
    ArrayList<String> a;
    ArrayList<String> L;
    ArrayAdapter adapter1,adapter2;
    String nombreA;
    int indexHabitacion;
    Spinner Areas,Habitacion;
    View vista;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GestionarAreasVM =
                ViewModelProviders.of(this).get(GestionarAreasVM.class);
        vista= inflater.inflate(R.layout.a_fragment_gestionarareas, container, false);


        Areas = vista.findViewById(R.id.spnAreasGA);
        Habitacion= vista.findViewById(R.id.spnHabi);

        x= new Sqlite_Base(getContext(),DatosConexion.NOMBREBD,null,DatosConexion.VERSION);

        x.abrir();
        consultarArea();

        adapter1 = new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,a);
        Areas.setAdapter(adapter1);


        Areas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                    indexHabitacion= i+1;
                    consultarLugares(indexHabitacion);
                    adapter2 = new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,L);
                    Habitacion.setAdapter(adapter2);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        x.close();

        return vista;

    }

    public void consultarArea(){
        a= new ArrayList<>();
        x= new Sqlite_Base(getContext(),DatosConexion.NOMBREBD,null,DatosConexion.VERSION);

        x.abrir();

        Cursor u = null;

        u=x.getWritableDatabase().rawQuery("SELECT * FROM "+Utilidades.Tabla_Areas,new String[]{});

        while(u.moveToNext()){

            nombreA=u.getString(1);
            a.add(nombreA);
        }
    }
    public void consultarLugares(int i){

        L= new ArrayList<>();
        x= new Sqlite_Base(getContext(),DatosConexion.NOMBREBD,null,DatosConexion.VERSION);

        x.abrir();

        Cursor u = null;

        u=x.getWritableDatabase().rawQuery("SELECT * FROM "+Utilidades.Tabla_Lugares+" where "+Utilidades.Campo_IdAreaL+" = "+i,new String[]{});

        while(u.moveToNext()){

            nombreA=u.getString(2);
            L.add(nombreA);

        }
    }
}