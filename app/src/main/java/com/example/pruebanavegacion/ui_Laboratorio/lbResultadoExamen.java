package com.example.pruebanavegacion.ui_Laboratorio;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.pruebanavegacion.Citas_Examen;
import com.example.pruebanavegacion.Citas_Examen2;
import com.example.pruebanavegacion.R;

import java.util.ArrayList;

import BaseHospital.DatosConexion;
import BaseHospital.Sqlite_Base;
import Utilidades.Utilidades;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link lbResultadoExamen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class lbResultadoExamen extends Fragment {

  EditText edtNumCuadro;
  TextView txtNombrePaciente,txtEdadPaciente,txtDuiPaciente,txtObservacionesP;
  Button btnInsertarCuadros,btnBuscarCuadro,btnContinuar;
  //ArrayList<Citas_Examen> listExamen;
    ArrayList<Citas_Examen2> listaEx;
    ArrayList<String> ExamenenesEnProceso,listaCitasE ;
  //AdaptadorExamen Adaptadorex;
  ArrayAdapter adapter;
  String idCita,NombrePLista,IdCitasEx;
  ListView lvlExamenes;
  String numeroCuadro;
  String   Ids;
  String NombreExamen;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public lbResultadoExamen() {
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
    public static lbResultadoExamen newInstance(String param1, String param2) {
        lbResultadoExamen fragment = new lbResultadoExamen();
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
        return inflater.inflate(R.layout.lb_fragment_resultadoexamen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        edtNumCuadro=view.findViewById(R.id.edtNumCuadro);
        txtNombrePaciente=view.findViewById(R.id.txtNombrePaciente);
        txtEdadPaciente=view.findViewById(R.id.txtEdadPaciente);
        txtDuiPaciente=view.findViewById(R.id.txtDuiPaciente);
        txtObservacionesP=view.findViewById(R.id.txtObservacionesP);
        btnInsertarCuadros=view.findViewById(R.id.btnInsertarCuadros);
        btnInsertarCuadros.setVisibility(View.INVISIBLE);
        btnContinuar=view.findViewById(R.id.btnContinuar);
        btnBuscarCuadro=view.findViewById(R.id.btnBuscarCuadro);
        lvlExamenes=view.findViewById(R.id.lvlExamenes);


        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });





/*
        ExamenenesEnProceso=new ArrayList<String>();
        lvlExamenes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //En caso que la posicion seleccionada gracias a i sea true que lo cambie a false
                if(listExamen.get(i).isChekeado()){
                    listExamen.get(i).setChekeado(false);
                    ExamenenesEnProceso.remove(listExamen.get(i).getNombreExamen());

                }
                else {
                    //Aqui lo contrario que la anterio a true
                    listExamen.get(i).setChekeado(true);
                    ExamenenesEnProceso.add(listExamen.get(i).getNombreExamen());

                }
                Adaptadorex.notifyDataSetChanged();
            }
        }); */

        ExamenenesEnProceso=new ArrayList<String>();
        lvlExamenes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Ids=listaEx.get(i).getIdEx();
                NombreExamen=listaEx.get(i).getNombreExamen();

               //Toast.makeText(getContext(),"IdCita "+Ids+"Nombre "+NombreExamen,Toast.LENGTH_SHORT).show();
            }
        });


        btnInsertarCuadros.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

              //Toast.makeText(getContext(),"Holiiii",Toast.LENGTH_SHORT).show();
              InsertarCuadro();

            }
        });

        btnBuscarCuadro.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                edtNumCuadro.setError(null);
                 numeroCuadro=edtNumCuadro.getText().toString().trim();
                if(numeroCuadro.isEmpty()){
                    //Primer error
                    edtNumCuadro.setError("Deber ingresar No Cuadro");
                    //Colocamos un focus
                    edtNumCuadro.requestFocus();
                    return;
                }


                Integer numeroC=Integer.parseInt(numeroCuadro);
                consultarListaExamenes(numeroCuadro);
                adapter=new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_single_choice,listaCitasE);
                lvlExamenes.setAdapter(adapter);
              //  Adaptadorex=new AdaptadorExamen((AppCompatActivity) getContext(),listExamen);
              //  lvlExamenes.setAdapter(Adaptadorex);
                lvlExamenes.setDividerHeight(3);




              try {
             Cursor InfoConsulta= BuscarCuadronConsulta(numeroC);
                if (InfoConsulta.getCount()>0){
                   InfoConsulta.moveToFirst();
                   idCita=InfoConsulta.getString(0);
                   String Fecha=InfoConsulta.getString(1);
                   String Dui=InfoConsulta.getString(2);
                   String indicaciones=InfoConsulta.getString(3);


                   // Toast.makeText(getContext(),"hay existencias "+idCita,Toast.LENGTH_SHORT).show();
                   txtNombrePaciente.setText(idCita);
                   txtEdadPaciente.setText(Fecha);
                   txtDuiPaciente.setText(Dui);
                   txtObservacionesP.setText(indicaciones);






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

    private void consultarListaExamenes(String idPaciente) {
        Sqlite_Base obj=new Sqlite_Base(getContext(),DatosConexion.NOMBREBD,null,DatosConexion.VERSION);



        listaEx=new ArrayList<Citas_Examen2>();
        //select * from usuarios
        Cursor cursor=obj.getWritableDatabase().rawQuery("Select IdCita_E, Tipo  from Citas_Examenes where IdPaciente="+idPaciente+" and Estado=1;",new String[]{});

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



    //llamar a la base de datos y consultar la lista de examenes asociadas a CitasExamen al IdPaciente
  /*    private void consultarListaExamenes(String idPaciente) {
        Sqlite_Base obj=new Sqlite_Base(getContext(),DatosConexion.NOMBREBD,null,DatosConexion.VERSION);



        listExamen=new ArrayList<Citas_Examen>();
        //select * from usuarios
        Cursor cursor=obj.getWritableDatabase().rawQuery("Select Tipo  from Citas_Examenes where IdPaciente="+idPaciente+" and Estado=1;",new String[]{});

        //Recoremos nuestros registros si es que contamos con registros
        while (cursor.moveToNext()){

        NombrePLista=cursor.getString(0);

        listExamen.add(new Citas_Examen(NombrePLista));
        }
        //obtenerLista();

    }
    //Construir la lista que se mostrara en el spinner
  private void obtenerLista() {
        listaPersonas =new ArrayList<String>();
        listaPersonas.add("Seleccione");

        //Recorremos la lista de objetos
        for (int i=0; i<personasList.size();i++){
            listaPersonas.add(personasList.get(i).getId()+"-"+personasList.get(i).getNombre());
        }

    } */

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

        String comando="INSERT INTO "+Utilidades.Tabla_Cita_Examen+"("+Utilidades.Campo_IdPaciente_E+","+Utilidades.Campo_Fecha_E+", "+Utilidades.Campo_Hora_E+", "+Utilidades.Campo_Tipo_E+", "+Utilidades.Campo_Estado_E+" ) " +
                "values('2','16/12/2020','13:00','Examen VIH','1'),('2','16/12/2020','13:00','Examen Orina','1'),('1','16/12/2020','13:00','Examen Glucosa','1')";

        objCon.getWritableDatabase().execSQL(comando);
        Toast.makeText(getContext(),"Insert exitoso..",Toast.LENGTH_SHORT).show();
        objCon.cerrar();

       /* String comandoCG="INSERT INTO "+Utilidades.Tabla_Cita_General+"("+Utilidades.Campo_IdPaciente_G+","+Utilidades.Campo_IdUsuarios_G+", "+Utilidades.Campo_Fecha_G+", "+Utilidades.Campo_Hora_G+", "+Utilidades.Campo_Estado_G+" ) " +
                "values('1','1','24/11/2020','14:00','1')";

        objCon.getWritableDatabase().execSQL(comandoCG);
        Toast.makeText(getContext(),"Insert exitoso..",Toast.LENGTH_SHORT).show();
        objCon.cerrar();

        String comandoP="INSERT INTO "+Utilidades.Tabla_Paciente+"("+Utilidades.Campo_Nombre_P+", "+Utilidades.Campo_Dui_P+", "+Utilidades.Campo_Nit_P+", "+Utilidades.Campo_Direccion_P+", "+Utilidades.Campo_Fecha_NacP+", "+Utilidades.Campo_Aseguradora+", "+Utilidades.Campo_Num_Afiliado+", "+Utilidades.Campo_Tipo_Sangre+", "+Utilidades.Campo_Peso+", "+Utilidades.Campo_Alergias+", "+Utilidades.Campo_Discapacidades+", "+Utilidades.Campo_Nombre_Emergencia+", "+Utilidades.Campo_Parentesco_Emergencia+", "+Utilidades.Campo_Telefono_Emergencia+" ) " +
                "values('Cristian Nehemias','05384708-2','12555','San Salvador xxx','24/12/2000','Aseguradora x','22988245','Tipo A','75kg','No padece','No padece discapacidad','Juan Miguel','Padre','2498-0584')";

        objCon.getWritableDatabase().execSQL(comandoP);
        Toast.makeText(getContext(),"Insert exitoso..",Toast.LENGTH_SHORT).show();
        objCon.cerrar(); */




    }

    //Creamos una clase y heredamos de la clase ArrayAdapter para llenar el listView con los componentes View de xmlContinente
    class AdaptadorExamen extends ArrayAdapter<Citas_Examen> {
        AppCompatActivity appCompatActivity;
        ArrayList<Citas_Examen> listExamenes;

        //Creamos un constructor y usamos super para poder acceder a los elementos de la clase ArrayAdapter
        AdaptadorExamen(AppCompatActivity context, ArrayList<Citas_Examen> listExamenes){
            super(context, R.layout.xmlexamen, listExamenes);
            this.appCompatActivity=context;
            this.listExamenes=listExamenes;
        }

        //Creamos un metodo de tipo View para poder generara los componentes en el ListView



        public View getView(int position, View convertView,  ViewGroup parent) {

            //LayoutInflater inflater=appCompatActivity.getLayoutInflater();
            View item=convertView;     //inflater.inflate(R.layout.xmlpaises, null);
            VistaItem vistaItem;

            if(item==null){
                //Obtenemos una referencia de Inflater para poder inflar el disenio
                LayoutInflater inflador=appCompatActivity.getLayoutInflater();
                item=inflador.inflate(R.layout.xmlexamen,null);
                vistaItem= new VistaItem();

                vistaItem.nombre=(TextView) item.findViewById(R.id.txtNomContinen1);
                vistaItem.chkEstado=(CheckBox) item.findViewById(R.id.chkEstado);
                item.setTag(vistaItem);
            }else {
                vistaItem = (VistaItem) item.getTag();
            }


            vistaItem.nombre.setText(listExamenes.get(position).getNombreExamen());
            vistaItem.chkEstado.setChecked(listExamenes.get(position).isChekeado());




            return (item);
        }
    }
    //Esta clase se usa para almacenar el TextView y el checkBox de una vista y es donde esta el truco para la vista se guarden
    static class VistaItem{
        TextView nombre;
        CheckBox chkEstado;
    }
}