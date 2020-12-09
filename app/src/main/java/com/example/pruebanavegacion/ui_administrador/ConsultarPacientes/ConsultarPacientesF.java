package com.example.pruebanavegacion.ui_administrador.ConsultarPacientes;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebanavegacion.R;
import com.example.pruebanavegacion.ui_administrador.ConsultarAreas.RecyclerViewPacientesCA;
import com.example.pruebanavegacion.ui_administrador.ConsultarAreas.csPacientesCA;

import java.util.ArrayList;
import java.util.List;

import BaseHospital.DatosConexion;
import BaseHospital.Sqlite_Base;

public class ConsultarPacientesF extends Fragment {

    private ConsultarPacientesVM ConsultarPacientesVM;
    private Spinner spnAreaCP;
    private Button btnBuscar,btnImprimir;
    private TextView Paciente,Estado,Fecha,Hora;
    private EditText idCita;
    private Sqlite_Base con;
    private RecyclerViewPacientesCP adaptadorPacientes;
    ArrayAdapter adapter;
    private RecyclerView Citas;
    ArrayList<RecyclerViewPacientesCP> list;
    List<csPacientesCP> pacient;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ConsultarPacientesVM =
                ViewModelProviders.of(this).get(ConsultarPacientesVM.class);
        View root = inflater.inflate(R.layout.a_fragment_consultarpacientes, container, false);
        idCita = (EditText) root.findViewById(R.id.edtNumPacienteCP);
        btnBuscar = (Button) root.findViewById(R.id.btnBuscarCP);
        btnImprimir = (Button) root.findViewById(R.id.btnImprimirCP);
        Citas = (RecyclerView) root.findViewById(R.id.rvPacientes);


        con = new Sqlite_Base(getContext(), DatosConexion.NOMBREBD,null,DatosConexion.VERSION);
        con.abrir();
        String d="insert into citas_generales(IdPaciente,IdUsuarios,Fecha,Hora,Estado) values(2,2,'2020-12-16','13:00:00',1),(2,2,'2020-12-16','08:00:00',1),(5,1,'2020-12-22','08:00:00',0),(2,3,'2020-12-22','08:00:00',0),(5,3,'2020-12-22','16:00:00',1),(2,3,'2020-12-22','16:00:00',1)";
        con.getWritableDatabase().execSQL(d);

        Citas.setLayoutManager(new LinearLayoutManager(getContext()));

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!idCita.getText().toString().equals("")){
                    adaptadorPacientes = new RecyclerViewPacientesCP(obtenerPacientes(idCita.getText().toString()));
                    Citas.setAdapter(adaptadorPacientes);


                }else{

                    idCita.setError("Ingrese ID del Paciente");
                    idCita.requestFocus();
                }

            }
        });
        ;

        return root;
    }
    public List<csPacientesCP> obtenerPacientes(String idP){

        pacient = new ArrayList<>();

        con = new Sqlite_Base(getContext(), DatosConexion.NOMBREBD,null,DatosConexion.VERSION);
        con.abrir();
        Cursor GX = null;

        GX= con.getWritableDatabase().rawQuery("select p.Nombre,c.IdCita_G,c.Estado,u.Nombre as Nombre_Medico,c.Fecha,c.Hora from Pacientes p inner join citas_generales c on p.IdPaciente=c.IdPaciente\n" +
                " inner join usuarios u on c.IdUsuarios=u.Id where p.IdPaciente = "+idP,new String[]{});
        while(GX.moveToNext()){
            String esta="";
            switch (GX.getString(2)){

                case "1":
                    esta="En Espera";
                    break;
                case "0":
                    esta="Realizada";
                    break;
            }
            pacient.add(new csPacientesCP(GX.getString(0),GX.getString(1),esta,GX.getString(3),GX.getString(4),GX.getString(5)));
        }
        con.cerrar();
        return pacient;

        //Es un secreto, de tu mirada y la mia 7u7
    }
}