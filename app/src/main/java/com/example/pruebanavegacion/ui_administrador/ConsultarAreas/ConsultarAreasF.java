package com.example.pruebanavegacion.ui_administrador.ConsultarAreas;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebanavegacion.R;

import java.util.ArrayList;
import java.util.List;

import BaseHospital.DatosConexion;
import BaseHospital.Sqlite_Base;

public class ConsultarAreasF extends Fragment {

    private ConsultarAreasVM ConsultarAreasVM;
    private RecyclerView rvPacienteX;
    ArrayList<RecyclerViewPacientesCA> list;
    List<csPacientesCA> pacient;

    ArrayAdapter adapterX;
    ArrayList<String> area,areaid;
    private Spinner Area;
    private ImageView ivLogo;
    private Button Buscar;
    private TextView txtN,txtE,txtD,txtC;
    private RecyclerViewPacientesCA adaptadorPacientes;
    private Sqlite_Base xx;
    final String[] gu = new String[1];
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ConsultarAreasVM =
                ViewModelProviders.of(this).get(ConsultarAreasVM.class);
        View root = inflater.inflate(R.layout.a_fragment_consultarareas, container, false);

        llenarArea();

        rvPacienteX= (RecyclerView) root.findViewById(R.id.rvPacientesX);
        ivLogo = (ImageView) root.findViewById(R.id.imgPacienteLogo);
        txtN = (TextView) root.findViewById(R.id.txtNombreP);
        txtE = (TextView) root.findViewById(R.id.txtEstadocv);
        txtD = (TextView) root.findViewById(R.id.txtDoctor);
        txtC = (TextView) root.findViewById(R.id.txtCama);
        Area = (Spinner) root.findViewById(R.id.spnAr);
        Buscar = (Button) root.findViewById(R.id.btnBuscarAreaConsulta);


        //LLENO SPINNER PARA OBTENER AREAS

        adapterX = new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,area);
        Area.setAdapter(adapterX);
        //_______________________________________________________________________________
        rvPacienteX.setLayoutManager(new LinearLayoutManager(getContext()));


        Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gu[0]=Area.getSelectedItem().toString();
                adaptadorPacientes = new RecyclerViewPacientesCA(obtenerPacientes(gu[0]));
                rvPacienteX.setAdapter(adaptadorPacientes);


            }
        });


        return root;
    }
    public void llenarArea(){
        area = new ArrayList<>();
        areaid = new ArrayList<>();
        xx = new Sqlite_Base(getContext(), DatosConexion.NOMBREBD,null,DatosConexion.VERSION);
        xx.abrir();

        Cursor ha = null;

        ha= xx.getWritableDatabase().rawQuery("Select * from Area",new String[]{});

         while(ha.moveToNext()){

            areaid.add(ha.getString(0));
             area.add(ha.getString(1));

         }
    }
    public List<csPacientesCA> obtenerPacientes(String ar){

        pacient = new ArrayList<>();

        xx = new Sqlite_Base(getContext(), DatosConexion.NOMBREBD,null,DatosConexion.VERSION);
        xx.abrir();

        Cursor GX = null;

        GX= xx.getWritableDatabase().rawQuery("select p.Nombre,l.Num_Cama,i.Estado,u.Nombre as Nombre_Doctor from usuarios u inner join Ingresos i on u.Id=i.IdUsuarios inner join Pacientes p on p.IdPaciente=i.IdPaciente inner join Lugares l on l.IdLugar = i.IdLugar inner join Area a on l.IdArea = a.IdArea where a.Nombre = '"+ar+"' and i.Estado=1",new String[]{});
        while(GX.moveToNext()){
            String esta="";
            switch (GX.getString(2)){

                case "1":
                    esta="Ocupado";
                    break;
                case "0":
                    esta="Disponible";
                    break;
            }
            pacient.add(new csPacientesCA(GX.getString(0),GX.getString(1),esta,GX.getString(3),R.drawable.paciente_espera_alta));
        }
        return pacient;
    }

}