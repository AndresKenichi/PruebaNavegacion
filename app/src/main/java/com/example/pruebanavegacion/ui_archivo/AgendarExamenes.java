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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.pruebanavegacion.Citas_Examen2;
import com.example.pruebanavegacion.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import BaseHospital.DatosConexion;
import BaseHospital.Sqlite_Base;
import Utilidades.Utilidades;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgendarExamenes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgendarExamenes extends Fragment {
    EditText edtUsuarioAgendar;
    Button btnBuscarUserAgendarExamen,btnAgregarALista,btnAgregarExamen,btnDateAR,btnHourAR;
    TextView tvObservacionesAR,tvDateAR,tvHourAR,tvNOMBREEXAMEN;
    private int year,month,day;
    String idPaciente, nombreExamen,IdCitasEx,NombrePLista;
    ListView lv1AR, lv2AR;
    ArrayList<Citas_Examen2>listaEx;
    ArrayList<String>listaCitasE;
    ArrayAdapter<String>adapter;
    String numeroPaciente;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AgendarExamenes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgendarExamenes.
     */
    // TODO: Rename and change types and number of parameters
    public static AgendarExamenes newInstance(String param1, String param2) {
        AgendarExamenes fragment = new AgendarExamenes();
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
        return inflater.inflate(R.layout.ar_fragment_agendar_examenes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtUsuarioAgendar=view.findViewById(R.id.edtUsuarioAgendar);
        btnBuscarUserAgendarExamen=view.findViewById(R.id.btnBuscarUserAgendarExamen);
        tvObservacionesAR=view.findViewById(R.id.tvObservacionesAR);
        btnAgregarALista=view.findViewById(R.id.btnAgregarALista);
        btnAgregarExamen=view.findViewById(R.id.btnAgregarExamen);
        lv1AR=view.findViewById(R.id.lv1AR);
        lv2AR=view.findViewById(R.id.lv2AR);
        tvDateAR=view.findViewById(R.id.tvDateAR);
        tvHourAR=view.findViewById(R.id.tvHourAR);
        btnDateAR=view.findViewById(R.id.btnDateAR);
        btnHourAR=view.findViewById(R.id.btnHourAR);
        tvNOMBREEXAMEN=view.findViewById(R.id.tvNOMBREEXAMEN);

        //instancia para un objeto de tipo calendar
        final Calendar calendar=Calendar.getInstance();
        //btn para el timepicker
        btnHourAR.setOnClickListener(new View.OnClickListener() {
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
                        tvHourAR.setText(time);
                    }
                },hours, mins, false);
                timePickerDialog.show();
            }
        });
        //btn para el date picker
        btnDateAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year=calendar.get(Calendar.YEAR);
                month=calendar.get(Calendar.MONTH);
                day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        tvDateAR.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },year,month,day);
                //estableciendo la fecha minima
                calendar.add(Calendar.MONTH,0);
                calendar.add(Calendar.DAY_OF_MONTH,0);
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis()-1000);
                datePickerDialog.show();
            }
        });
        btnBuscarUserAgendarExamen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtUsuarioAgendar.setError(null);
                numeroPaciente=edtUsuarioAgendar.getText().toString().trim();
                if(numeroPaciente.isEmpty()){
                    //Primer error
                    edtUsuarioAgendar.setError("Deber ingresar No Paciente");
                    //Colocamos un focus
                    edtUsuarioAgendar.requestFocus();
                    return;
                }
                Integer numeroP=Integer.parseInt(numeroPaciente);
                try {
                    Cursor InfoConsulta= BuscarPaciente(numeroP);
                    if (InfoConsulta.getCount()>0){
                        InfoConsulta.moveToFirst();
                        idPaciente=InfoConsulta.getString(0);
                        String NOmbre=InfoConsulta.getString(1);
                        String Diagnostico=InfoConsulta.getString(2);
                        tvObservacionesAR.setText(Diagnostico);
                    }else {
                        Toast.makeText(getContext(), "El paciente no existe", Toast.LENGTH_SHORT).show();
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        });
        btnAgregarALista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//El estado debe ser cero ya que aun no se han llenado los resultados
                insetarCitaE(idPaciente,numeroPaciente,tvDateAR.getText().toString(),tvHourAR.getText().toString(),nombreExamen,"0");
                consultarListaExamenes(numeroPaciente);
                adapter=new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,listaCitasE);
                lv2AR.setAdapter(adapter);
            }
        });
        lv1AR.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                nombreExamen=lv1AR.getItemAtPosition(i).toString();
                tvNOMBREEXAMEN.setText(nombreExamen);
            }
        });
    }

    private Cursor BuscarPaciente(Integer numeroP) {
        Cursor cursorConsulta=null;
        Sqlite_Base objCon=new Sqlite_Base(getContext(), DatosConexion.NOMBREBD,null,DatosConexion.VERSION);
        //cursorConsulta=objCon.getWritableDatabase().rawQuery("select * from "+ Utilidades.Tabla_Consultas+" where "+Utilidades.Campo_IdConsultas+" like "+numero+" ;",new String[]{});
        cursorConsulta=objCon.getWritableDatabase().rawQuery("Select p.IdPaciente,p.Nombre,cn.Diagnostico from Pacientes p inner join Citas_Generales c on \n" +
                "c.IdPaciente = p.IdPaciente inner join consultas cn on c.IdCita_G = cn.IdCita_G where cn.IdConsultas="+numeroP,new String[]{});
        return  cursorConsulta;
    }
    private void consultarListaExamenes(String idConsulta) {
        Sqlite_Base obj=new Sqlite_Base(getContext(),DatosConexion.NOMBREBD,null,DatosConexion.VERSION);

        listaEx=new ArrayList<Citas_Examen2>();
        //select * from usuarios
        Cursor cursor=obj.getWritableDatabase().rawQuery("Select IdCita_E, Tipo  from Citas_Examenes where "+Utilidades.Campo_IdConsultas_E+"="+idConsulta+" and Estado=0;",new String[]{});
        //Recoremos nuestros registros si es que contamos con registros
        while (cursor.moveToNext()){
            IdCitasEx=cursor.getString(0);
            NombrePLista=cursor.getString(1);
            listaEx.add(new Citas_Examen2(IdCitasEx,NombrePLista));
        }
        obtenerListaEx();
    }
    private void obtenerListaEx() {
        listaCitasE =new ArrayList<String>();
        //Recorremos la lista de objetos
        for (int i=0; i<listaEx.size();i++){
            listaCitasE.add(listaEx.get(i).getIdEx()+"-"+listaEx.get(i).getNombreExamen());
        }
    }
    public void insetarCitaE(String IdPaciente, String IdConsulta, String Fecha_E ,String Hora_E,String Tipo_E, String Estado_E){
        //Estos valores cuando se envien se deben colocar en ...
        Sqlite_Base obj=new Sqlite_Base(getContext(),DatosConexion.NOMBREBD,null,DatosConexion.VERSION);
        ContentValues valores=new ContentValues();
        //Con put agregamos valores a el objeto valores
        valores.put(Utilidades.Campo_IdPaciente_E,IdPaciente);
        valores.put(Utilidades.Campo_IdConsultas_E,IdConsulta);
        valores.put(Utilidades.Campo_Fecha_E,Fecha_E);
        valores.put(Utilidades.Campo_Hora_E,Hora_E);
        valores.put(Utilidades.Campo_Tipo_E,Tipo_E);
        valores.put(Utilidades.Campo_Estado_E,Estado_E);
        long idR=obj.getWritableDatabase().insert(Utilidades.Tabla_Cita_Examen, Utilidades.Campo_IdCita_E,valores);
        Toast.makeText(getContext(),"Id Registro: "+idR,Toast.LENGTH_SHORT).show();
        //todos los cambios aceptados
    }
}