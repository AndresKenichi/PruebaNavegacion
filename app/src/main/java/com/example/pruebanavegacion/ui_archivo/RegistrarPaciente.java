package com.example.pruebanavegacion.ui_archivo;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pruebanavegacion.MainActivity;
import com.example.pruebanavegacion.R;
import com.example.pruebanavegacion.Registrar;

import BaseHospital.DatosConexion;
import BaseHospital.Sqlite_Base;
import Utilidades.Utilidades;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistrarPaciente#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrarPaciente extends Fragment {
    EditText edtUserAR,edtDUIAR, edtNITAR,edtDireccionAR,edtDateAR,edtAseguradoraAR,edtNoAfiliadoAR,edtTipoSangreAR,edtPesoAR,edtAlergiasAR,edtDiscapacidadesAR,edtNameContacAR,edtTelAR;
    Spinner spinnerParentescoAR;
    Button btnRegistrarPaciente;
    private Sqlite_Base helper;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegistrarPaciente() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrarPaciente.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrarPaciente newInstance(String param1, String param2) {
        RegistrarPaciente fragment = new RegistrarPaciente();
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
        return inflater.inflate(R.layout.ar_fragment_registrar_paciente, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtUserAR=view.findViewById(R.id.edtUserAR);
        edtDUIAR=view.findViewById(R.id.edtDUIAR);
        edtNITAR=view.findViewById(R.id.edtNITAR);
        edtDireccionAR=view.findViewById(R.id.edtDireccionAR);
        edtDateAR=view.findViewById(R.id.edtDateAR);
        edtAseguradoraAR=view.findViewById(R.id.edtAseguradoraAR);
        edtNoAfiliadoAR=view.findViewById(R.id.edtNoAfiliadoAR);
        edtTipoSangreAR=view.findViewById(R.id.edtTipoSangreAR);
        edtPesoAR=view.findViewById(R.id.edtPesoAR);
        edtAlergiasAR=view.findViewById(R.id.edtAlergiasAR);
        edtDiscapacidadesAR=view.findViewById(R.id.edtDiscapacidadesAR);
        edtNameContacAR=view.findViewById(R.id.edtNameContacAR);
        edtTelAR=view.findViewById(R.id.edtTelAR);
        spinnerParentescoAR=view.findViewById(R.id.spinnerParentescoAR);
        btnRegistrarPaciente=view.findViewById(R.id.btnRegistrarPaciente);

        btnRegistrarPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper = new Sqlite_Base(getContext(),DatosConexion.NOMBREBD,null,DatosConexion.VERSION);
                edtUserAR.setError(null);
                edtDUIAR.setError(null);
                edtNITAR.setError(null);
                edtDireccionAR.setError(null);
                edtDateAR.setError(null);
                edtAseguradoraAR.setError(null);
                edtNoAfiliadoAR.setError(null);
                edtTipoSangreAR.setError(null);
                edtPesoAR.setError(null);
                edtAlergiasAR.setError(null);
                edtDiscapacidadesAR.setError(null);
                edtNameContacAR.setError(null);
                edtTelAR.setError(null);

                String usuario,DUI,NIT,direccion,date,aseguradora,noafiliado
                        ,tiposangre,peso,alergias,discapacidades,nombrecontacto,telefonocontacto;

                usuario=edtUserAR.getText().toString().trim();
                DUI=edtDUIAR.getText().toString().trim();
                NIT=edtNITAR.getText().toString().trim();
                direccion=edtDireccionAR.getText().toString().trim();
                date=edtDateAR.getText().toString().trim();
                aseguradora=edtAseguradoraAR.getText().toString().trim();
                noafiliado=edtNoAfiliadoAR.getText().toString().trim();
                tiposangre=edtTipoSangreAR.getText().toString().trim();
                peso=edtPesoAR.getText().toString().trim();
                alergias=edtAlergiasAR.getText().toString().trim();
                discapacidades=edtDiscapacidadesAR.getText().toString().trim();
                nombrecontacto=edtNameContacAR.getText().toString().trim();
                telefonocontacto=edtTelAR.getText().toString().trim();

                if(usuario.equals("")){
                    //Primer error
                    edtUserAR.setError("No puedes dejar campos vacios");
                    //Colocamos un focus
                    edtUserAR.requestFocus();
                    return;
                }
                if(DUI.equals("")){
                    //Primer error
                    edtDUIAR.setError("No puedes dejar campos vacios");
                    //Colocamos un focus
                    edtDUIAR.requestFocus();
                    return;
                }
                if(NIT.equals("")){
                    //Primer error
                    edtNITAR.setError("No puedes dejar campos vacios");
                    //Colocamos un focus
                    edtNITAR.requestFocus();
                    return;
                }
                if(direccion.equals("")){
                    //Primer error
                    edtDireccionAR.setError("No puedes dejar campos vacios");
                    //Colocamos un focus
                    edtDireccionAR.requestFocus();
                    return;
                }
                if(date.equals("")){
                    //Primer error
                    edtDateAR.setError("No puedes dejar campos vacios");
                    //Colocamos un focus
                    edtDateAR.requestFocus();
                    return;
                }
                if(aseguradora.equals("")){
                    //Primer error
                    edtAseguradoraAR.setError("No puedes dejar campos vacios");
                    //Colocamos un focus
                    edtAseguradoraAR.requestFocus();
                    return;
                }
                if(noafiliado.equals("")){
                    //Primer error
                    edtNoAfiliadoAR.setError("No puedes dejar campos vacios");
                    //Colocamos un focus
                    edtNoAfiliadoAR.requestFocus();
                    return;
                }
                if(tiposangre.equals("")){
                    //Primer error
                    edtTipoSangreAR.setError("No puedes dejar campos vacios");
                    //Colocamos un focus
                    edtTipoSangreAR.requestFocus();
                    return;
                }
                if(peso.equals("")){
                    //Primer error
                    edtPesoAR.setError("No puedes dejar campos vacios");
                    //Colocamos un focus
                    edtPesoAR.requestFocus();
                    return;
                }
                if(alergias.equals("")){
                    //Primer error
                    edtAlergiasAR.setError("No puedes dejar campos vacios");
                    //Colocamos un focus
                    edtAlergiasAR.requestFocus();
                    return;
                }
                if(discapacidades.equals("")){
                    //Primer error
                    edtDiscapacidadesAR.setError("No puedes dejar campos vacios");
                    //Colocamos un focus
                    edtDiscapacidadesAR.requestFocus();
                    return;
                }
                if(nombrecontacto.equals("")){
                    //Primer error
                    edtNameContacAR.setError("No puedes dejar campos vacios");
                    //Colocamos un focus
                    edtNameContacAR.requestFocus();
                    return;
                }
                if(telefonocontacto.equals("")){
                    //Primer error
                    edtTelAR.setError("No puedes dejar campos vacios");
                    //Colocamos un focus
                    edtTelAR.requestFocus();
                    return;
                }
                try {
                    String parentesco = spinnerParentescoAR.getSelectedItem().toString();

                    Sqlite_Base objCon=new Sqlite_Base(getContext(), DatosConexion.NOMBREBD,null,DatosConexion.VERSION);
                    objCon.abrir();
                    String comando="INSERT INTO "+ Utilidades.Tabla_Paciente+"("+Utilidades.Campo_Nombre_P+","+Utilidades.Campo_Dui_P+","+Utilidades.Campo_Nit_P+", "+Utilidades.Campo_Direccion_P+", "+Utilidades.Campo_Fecha_NacP+", "+Utilidades.Campo_Aseguradora+", "+Utilidades.Campo_Num_Afiliado+","+Utilidades.Campo_Tipo_Sangre+","+Utilidades.Campo_Peso+","+Utilidades.Campo_Alergias+","+Utilidades.Campo_Discapacidades+","+Utilidades.Campo_Nombre_Emergencia+","+Utilidades.Campo_Parentesco_Emergencia+","+Utilidades.Campo_Telefono_Emergencia+" ) " +
                            "values('"+usuario+"','"+DUI+"','"+NIT+"','"+direccion+"','"+date+"','"+aseguradora+"','"+noafiliado+"','"+tiposangre+"','"+peso+"','"+alergias+"','"+discapacidades+"','"+nombrecontacto+"','"+parentesco+"','"+telefonocontacto+"')";

                    objCon.getWritableDatabase().execSQL(comando);
                    Toast.makeText(getContext(),"Paciente registrado",Toast.LENGTH_SHORT).show();
                    objCon.cerrar();
                } catch (SQLException e){
                    e.printStackTrace();
                }

            }
        });
    }

}