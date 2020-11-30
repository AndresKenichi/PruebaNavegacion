package com.example.pruebanavegacion.ui;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pruebanavegacion.R;

import BaseHospital.DatosConexion;
import BaseHospital.Sqlite_Base;
import Utilidades.Utilidades;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConsultasDc#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConsultasDc extends Fragment {
    EditText edtNoDOC;
    TextView tvNombre,tvEdad,tvDUI;
    EditText edtPresion,edtRespiraciones,edtObservaciones,edtDiagnostico;
    Button btnBusca,btnGuardarConsulta;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ConsultasDc() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConsultarCuadros.
     */
    // TODO: Rename and change types and number of parameters
    public static ConsultasDc newInstance(String param1, String param2) {
        ConsultasDc fragment = new ConsultasDc();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.doc_consulta, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtNoDOC=view.findViewById(R.id.edtNoDOC);
        tvNombre=view.findViewById(R.id.tvNombre);
        tvEdad=view.findViewById(R.id.tvEdad);
        tvDUI=view.findViewById(R.id.tvDUI);
        edtPresion=view.findViewById(R.id.edtPresion);
        edtRespiraciones=view.findViewById(R.id.edtRespiraciones);
        edtObservaciones=view.findViewById(R.id.edtObservaciones);
        edtDiagnostico=view.findViewById(R.id.edtDiagnostico);
        btnBusca=view.findViewById(R.id.btnBusca);
        btnGuardarConsulta=view.findViewById(R.id.btnGuardarConsulta);

        btnBusca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtNoDOC.setError(null);
                String numeroCuadro=edtNoDOC.getText().toString().trim();
                if(numeroCuadro.isEmpty()){
                    //Primer error
                    edtNoDOC.setError("Deber ingresar el No del paciente");
                    //Colocamos un focus
                    edtNoDOC.requestFocus();
                    return;
                }

                Integer numeroC=Integer.parseInt(numeroCuadro);

                try {
                    Cursor InfoConsulta= BuscarCuadronConsulta(numeroC);
                    if (InfoConsulta.getCount()>0){
                        InfoConsulta.moveToFirst();
                        String idCita=InfoConsulta.getString(0);
                        String Fecha=InfoConsulta.getString(1);
                        String DUI=InfoConsulta.getString(2);

                        // Toast.makeText(getContext(),"hay existencias "+idCita,Toast.LENGTH_SHORT).show();
                        tvNombre.setText(idCita);
                        tvEdad.setText(Fecha);
                        tvDUI.setText(DUI);
                    }else {
                        Toast.makeText(getContext(),"Paciente no registrado",Toast.LENGTH_SHORT).show();
                    }
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
        });

    }


    public Cursor BuscarCuadronConsulta(Integer numero) throws SQLException {
        Cursor cursorConsulta=null;

        Sqlite_Base objCon=new Sqlite_Base(getContext(), DatosConexion.NOMBREBD,null,DatosConexion.VERSION);
        cursorConsulta=objCon.getWritableDatabase().rawQuery("Select p.Nombre,p.Fecha,p.DUI, cn.Indicaciones,cn.Diagnostico,cn.Tratamiento from Pacientes p inner join Citas_Generales c on \n" +
                "c.IdPaciente = p.IdPaciente inner join consultas cn on c.IdCita_G = cn.IdCita_G where cn.IdConsultas="+numero,new String[]{});
        return  cursorConsulta;
    }
}