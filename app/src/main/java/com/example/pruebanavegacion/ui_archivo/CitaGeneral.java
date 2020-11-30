package com.example.pruebanavegacion.ui_archivo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.pruebanavegacion.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import BaseHospital.DatosConexion;
import BaseHospital.Sqlite_Base;
import Entidades.Usuarios;
import Utilidades.Utilidades;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CitaGeneral#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CitaGeneral extends Fragment {
    EditText edtNoPacienteCitaAR;
    TextView tvDate, tvHour,txtNombrePaciente,txtDUIpaciente,txtNITpaciente;
    Button btnDate, btnHour,btnBuscarPacienteAR,btnGuardarCitaGeneral;
    private int year,month,day;
    //elementos del select doctores
    Spinner spinnerDoc;
    ArrayList<String> DoctorName;
    ArrayList<Usuarios> Doctores;
    String DoctorId;
    ArrayAdapter arrayAdapter;
    String combo;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CitaGeneral() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CitaGeneral.
     */
    // TODO: Rename and change types and number of parameters
    public static CitaGeneral newInstance(String param1, String param2) {
        CitaGeneral fragment = new CitaGeneral();
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
        return inflater.inflate(R.layout.ar_fragment_cita_general, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtNoPacienteCitaAR=view.findViewById(R.id.edtNoPacienteCitaAR);
        btnBuscarPacienteAR=view.findViewById(R.id.btnBuscarPacienteAR);
        txtNombrePaciente=view.findViewById(R.id.txtNombrePaciente);
        txtDUIpaciente=view.findViewById(R.id.txtDUIpaciente);
        txtNITpaciente=view.findViewById(R.id.txtNITpaciente);
        spinnerDoc=view.findViewById(R.id.spinnerDoc);
        tvDate=view.findViewById(R.id.tvDate);
        tvHour=view.findViewById(R.id.tvHour);
        btnDate=view.findViewById(R.id.btnDate);
        btnHour=view.findViewById(R.id.btnHour);
        btnGuardarCitaGeneral=view.findViewById(R.id.btnGuardarCitaGeneral);

        final Sqlite_Base conexion=new Sqlite_Base(getContext(),DatosConexion.NOMBREBD,null,DatosConexion.VERSION);
        consultarDoctores();
        arrayAdapter = new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,DoctorName);
        spinnerDoc.setAdapter(arrayAdapter);

        spinnerDoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=0) {
                    DoctorId=(Doctores.get(i-1).getId().toString());
                }
                else {
                    combo=adapterView.getItemAtPosition(i).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //instancia para un objeto de tipo calendar
        final Calendar calendar=Calendar.getInstance();

        //button para buscar paciente y asignar nueva cita
        btnBuscarPacienteAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtNoPacienteCitaAR.setError(null);
                String numeroCuadro=edtNoPacienteCitaAR.getText().toString().trim();
                if(numeroCuadro.isEmpty()){
                    //Primer error
                    edtNoPacienteCitaAR.setError("Deber ingresar No Paciente");
                    //Colocamos un focus
                    edtNoPacienteCitaAR.requestFocus();
                    return;
                }
                Integer numeroC=Integer.parseInt(numeroCuadro);
                try {
                    Cursor InfoConsulta= BuscarPaciente(numeroC);
                    if (InfoConsulta.getCount()>0){
                        InfoConsulta.moveToFirst();
                        String id=InfoConsulta.getString(0);
                        String nombre=InfoConsulta.getString(1);
                        String DUI=InfoConsulta.getString(2);

                        txtNombrePaciente.setText(id);
                        txtDUIpaciente.setText(nombre);
                        txtNITpaciente.setText(DUI);
                    }else {
                        Toast.makeText(getContext(),"El paciente no existe",Toast.LENGTH_SHORT).show();
                    }
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
        });

        //btn para el timepicker
        btnHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                int hours= calendar.get(Calendar.HOUR_OF_DAY);
                int mins=calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog=new TimePickerDialog(getContext(), R.style.Theme_AppCompat_DayNight_Dialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        Calendar calendar1=Calendar.getInstance();
                        calendar1.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar1.set(Calendar.MINUTE, minute);
                        calendar1.setTimeZone(TimeZone.getDefault());
                        SimpleDateFormat format=new SimpleDateFormat("k:mm a");
                        String time=format.format(calendar1.getTime());
                        tvHour.setText(time);
                    }
                },hours, mins, false);
                timePickerDialog.show();
            }
        });

        //btn para el date picker
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year=calendar.get(Calendar.YEAR);
                month=calendar.get(Calendar.MONTH);
                day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        tvDate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },year,month,day);
                //estableciendo la fecha minima
                calendar.add(Calendar.MONTH,0);
                calendar.add(Calendar.DAY_OF_MONTH,0);
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis()-1000);
                datePickerDialog.show();
            }
        });

        btnGuardarCitaGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtNombrePaciente.setError(null);
                tvDate.setError(null);
                tvHour.setError(null);
                String combo="";

                String idusuario,dia,hora;

                idusuario=txtNombrePaciente.getText().toString().trim();
                dia=tvDate.getText().toString().trim();
                hora=tvHour.getText().toString().trim();

                if (idusuario.equals("")){
                    txtNombrePaciente.requestFocus();
                    Toast.makeText(getContext(), "seleccione un paciente", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (dia.equals("DD/MM/YYYY")){
                    tvDate.requestFocus();
                    Toast.makeText(getContext(), "seleccione dia", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (hora.equals("HH:MM")){
                    tvHour.requestFocus();
                    Toast.makeText(getContext(), "seleccione hora", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (spinnerDoc.getSelectedItemPosition()==0){
                    Toast.makeText(getContext(), "seleccione doctor", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    insetarCitaG(txtNombrePaciente.getText().toString(),DoctorId,tvDate.getText().toString(),tvHour.getText().toString(),"1");
                }catch (SQLException e){
                    e.printStackTrace();
                }

            }
        });

    }

    public void insetarCitaG(String IdPaciente, String IdUsuarios_G, String Fecha_G ,String Hora_G, String Estado_G){
        //Estos valores cuando se envien se deben colocar en ...
        ContentValues valores=new ContentValues();
        //Con put agregamos valores a el objeto valores
        valores.put(Utilidades.Campo_IdPaciente_G,IdPaciente);
        valores.put(Utilidades.Campo_IdUsuarios_G,IdUsuarios_G);
        valores.put(Utilidades.Campo_Fecha_G,Fecha_G);
        valores.put(Utilidades.Campo_Hora_G,Hora_G);
        valores.put(Utilidades.Campo_Estado_G,Estado_G);
        Sqlite_Base objCon=new Sqlite_Base(getContext(), DatosConexion.NOMBREBD,null,DatosConexion.VERSION);
        long idResultante=objCon.getWritableDatabase().insert(Utilidades.Tabla_Cita_General, Utilidades.Campo_IdCita_G,valores);
        Toast.makeText(getContext(),"Cita registrada: "+idResultante,Toast.LENGTH_SHORT).show();
    }

    private void consultarDoctores() {
        Cursor cursorConsulta=null;
        Usuarios doc;
        Doctores=new ArrayList<Usuarios>();

        Sqlite_Base objCon=new Sqlite_Base(getContext(), DatosConexion.NOMBREBD,null,DatosConexion.VERSION);
        cursorConsulta=objCon.getWritableDatabase().rawQuery("Select Id, Nombre from usuarios where Tipo_User='Doctor'",new String[]{});

        while (cursorConsulta.moveToNext()){
            doc=new Usuarios();
            doc.setId(cursorConsulta.getInt(0));
            doc.setNombre(cursorConsulta.getString(1).toString());
            Doctores.add(doc);
        }

        obtenerValores();
    }

    private void obtenerValores() {
        DoctorName=new ArrayList<String>();
        DoctorName.add("seleccione:");
        for (int i=0;i<Doctores.size();i++){
            DoctorName.add(Doctores.get(i).getId()+" "+Doctores.get(i).getNombre());
        }
    }

    public Cursor BuscarPaciente(Integer numero) throws SQLException {
        Cursor cursorConsulta=null;

        Sqlite_Base objCon=new Sqlite_Base(getContext(), DatosConexion.NOMBREBD,null,DatosConexion.VERSION);
        cursorConsulta=objCon.getWritableDatabase().rawQuery("Select p.IdPaciente,p.Nombre,p.DUI from Pacientes p where p.IdPaciente ="+numero,new String[]{});
        return  cursorConsulta;
    }

}