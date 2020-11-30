package com.example.pruebanavegacion.ui_Laboratorio;

import android.content.Context;
import android.database.Cursor;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.pruebanavegacion.R;

import BaseHospital.DatosConexion;
import BaseHospital.Sqlite_Base;
import Utilidades.Utilidades;

import static Utilidades.Utilidades.Campo_Clave;
import static Utilidades.Utilidades.Campo_Correo;
import static Utilidades.Utilidades.Tabla_Consultas;
import static Utilidades.Utilidades.Tabla_Usuario;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link lbCuadrosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class lbCuadrosFragment extends Fragment {

  EditText edtNumCuadro;
  TextView txtNombrePaciente,txtEdadPaciente,txtDuiPaciente,txtObservacionesP,txtDiagnosticoP,txtTratamientoP;
  Button btnInsertarCuadros,btnBuscarCuadro,btnLimpiar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public lbCuadrosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment lbCuadrosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static lbCuadrosFragment newInstance(String param1, String param2) {
        lbCuadrosFragment fragment = new lbCuadrosFragment();
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
        return inflater.inflate(R.layout.fragment_lb_cuadros, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        edtNumCuadro=view.findViewById(R.id.edtNumCuadro);
        txtNombrePaciente=view.findViewById(R.id.txtNombrePaciente);
        txtEdadPaciente=view.findViewById(R.id.txtEdadPaciente);
        txtDuiPaciente=view.findViewById(R.id.txtDuiPaciente);
        txtObservacionesP=view.findViewById(R.id.txtObservacionesP);
        txtDiagnosticoP=view.findViewById(R.id.txtDiagnosticoP);
        txtTratamientoP=view.findViewById(R.id.txtTratamientoP);
        btnInsertarCuadros=view.findViewById(R.id.btnInsertarCuadros);
        btnLimpiar=view.findViewById(R.id.btnLimpiar);
        btnBuscarCuadro=view.findViewById(R.id.btnBuscarCuadro);
        btnInsertarCuadros.setVisibility(View.INVISIBLE);

        btnInsertarCuadros.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

              //Toast.makeText(getContext(),"Holiiii",Toast.LENGTH_SHORT).show();
              InsertarCuadro();
                //new
            }
        });

        btnBuscarCuadro.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                edtNumCuadro.setError(null);
                String numeroCuadro=edtNumCuadro.getText().toString().trim();
                if(numeroCuadro.isEmpty()){
                    //Primer error
                    edtNumCuadro.setError("Deber ingresar No Cuadro");
                    //Colocamos un focus
                    edtNumCuadro.requestFocus();
                    return;
                }


             Integer numeroC=Integer.parseInt(numeroCuadro);

              try {
             Cursor InfoConsulta= BuscarCuadronConsulta(numeroC);
                if (InfoConsulta.getCount()>0){
                   InfoConsulta.moveToFirst();
                   String idCita=InfoConsulta.getString(0);
                   String Fecha=InfoConsulta.getString(1);
                   String Dui=InfoConsulta.getString(2);
                   String indicaciones=InfoConsulta.getString(3);
                   String Diagnostic=InfoConsulta.getString(4);
                   String trata=InfoConsulta.getString(5);

                   // Toast.makeText(getContext(),"hay existencias "+idCita,Toast.LENGTH_SHORT).show();
                   txtNombrePaciente.setText(idCita);
                   txtEdadPaciente.setText(Fecha);
                   txtDuiPaciente.setText(Dui);
                   txtObservacionesP.setText(indicaciones);
                   txtDiagnosticoP.setText(Diagnostic);
                   txtTratamientoP.setText(trata);


                }else {
                    Toast.makeText(getContext(),"No hay existencias",Toast.LENGTH_SHORT).show();
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
     //   cursorConsulta=objCon.getWritableDatabase().rawQuery("select * from "+ Utilidades.Tabla_Consultas+" where "+Utilidades.Campo_IdConsultas+" like "+numero+" ;",new String[]{});
        cursorConsulta=objCon.getWritableDatabase().rawQuery("Select p.Nombre,p.Fecha,p.DUI, cn.Indicaciones,cn.Diagnostico,cn.Tratamiento from Pacientes p inner join Citas_Generales c on \n" +
                "c.IdPaciente = p.IdPaciente inner join consultas cn on c.IdCita_G = cn.IdCita_G where cn.IdConsultas="+numero,new String[]{});
        return  cursorConsulta;

    }

    private void InsertarCuadro() throws SQLException {

        Sqlite_Base objCon=new Sqlite_Base(getContext(), DatosConexion.NOMBREBD,null,DatosConexion.VERSION);
       objCon.abrir();

        String comando="INSERT INTO "+Utilidades.Tabla_Consultas+"("+Utilidades.Campo_IdCitas+","+Utilidades.Campo_Presion+", "+Utilidades.Campo_Respiraciones+", "+Utilidades.Campo_Diagnostico+", "+Utilidades.Campo_idMedicamento+", "+Utilidades.Campo_Indicaciones+","+Utilidades.Campo_Fecha_Con+","+Utilidades.Campo_TratamientoC+" ) " +
                "values('1','120','12','Presion y respiracion normal temperatura elevada','1','No realizar actividades fisicas exigentes','24/11/2020','Acetaminofen')";

        objCon.getWritableDatabase().execSQL(comando);
        Toast.makeText(getContext(),"Insert exitoso..",Toast.LENGTH_SHORT).show();
        objCon.cerrar();

        String comandoCG="INSERT INTO "+Utilidades.Tabla_Cita_General+"("+Utilidades.Campo_IdPaciente_G+","+Utilidades.Campo_IdUsuarios_G+", "+Utilidades.Campo_Fecha_G+", "+Utilidades.Campo_Hora_G+", "+Utilidades.Campo_Estado_G+" ) " +
                "values('1','1','24/11/2020','14:00','1')";

        objCon.getWritableDatabase().execSQL(comandoCG);
        Toast.makeText(getContext(),"Insert exitoso..",Toast.LENGTH_SHORT).show();
        objCon.cerrar();

        String comandoP="INSERT INTO "+Utilidades.Tabla_Paciente+"("+Utilidades.Campo_Nombre_P+", "+Utilidades.Campo_Dui_P+", "+Utilidades.Campo_Nit_P+", "+Utilidades.Campo_Direccion_P+", "+Utilidades.Campo_Fecha_NacP+", "+Utilidades.Campo_Aseguradora+", "+Utilidades.Campo_Num_Afiliado+", "+Utilidades.Campo_Tipo_Sangre+", "+Utilidades.Campo_Peso+", "+Utilidades.Campo_Alergias+", "+Utilidades.Campo_Discapacidades+", "+Utilidades.Campo_Nombre_Emergencia+", "+Utilidades.Campo_Parentesco_Emergencia+", "+Utilidades.Campo_Telefono_Emergencia+" ) " +
                "values('Cristian Nehemias','05384708-2','12555','San Salvador xxx','24/12/2000','Aseguradora x','22988245','Tipo A','75kg','No padece','No padece discapacidad','Juan Miguel','Padre','2498-0584')";

        objCon.getWritableDatabase().execSQL(comandoP);
        Toast.makeText(getContext(),"Insert exitoso..",Toast.LENGTH_SHORT).show();
        objCon.cerrar();









    }
}