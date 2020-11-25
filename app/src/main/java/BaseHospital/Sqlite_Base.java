package BaseHospital;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import Utilidades.Utilidades;

import static Utilidades.Utilidades.*;


public class Sqlite_Base extends SQLiteOpenHelper {


    Long idResultante;
    //Nombre de la base de datos en este constructor es donde colocamos el nombre de nuestra base...
    public Sqlite_Base(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DatosConexion.NOMBREBD, factory, DatosConexion.VERSION);
    }


    //En el onCreate es donde vamos a crear la estructura que va tener nuestra base de datos, Nuestras tablas y posibles relaciones entre ellas..
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        /*La primera vez que llamemos una base de datos y le ingresemos el nombre
          automaticamente se crea nuestra base de datos pero solos se crea una vez */
        sqLiteDatabase.execSQL(Crear_Tabla_Usuarios);
        sqLiteDatabase.execSQL(Crear_Tabla_Pacientes);
        sqLiteDatabase.execSQL(Crear_Tabla_Cita_General);
        sqLiteDatabase.execSQL(Crear_Tabla_Consultas);
        sqLiteDatabase.execSQL(Crear_Tabla_Medicamentos);
        sqLiteDatabase.execSQL(Crear_Tabla_Area);
        sqLiteDatabase.execSQL(Crear_Tabla_Lugares);
        sqLiteDatabase.execSQL(Crear_Tabla_Ingresos);
        sqLiteDatabase.execSQL(Crear_Tabla_ListaExamenes);
        sqLiteDatabase.execSQL(Crear_Tabla_Cita_Examen);
        sqLiteDatabase.execSQL(Crear_Tabla_ResultadosExamenes);
        sqLiteDatabase.execSQL(Crear_Tabla_detallemedicamentos);

    }

    //Si queremos modificar alguna estructura de nuestra base de datos utilizaremos el onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int VersionAntigua, int NuevaVersion) {

        //Si volvemos a instalar la aplicacion debemos eliminar la version antigua y luego generarla
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Tabla_Usuario);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Tabla_Paciente);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Tabla_Cita_General);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Tabla_Consultas);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Tabla_Medicamentos);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Tabla_Areas);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Tabla_Lugares);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Tabla_Ingresos);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Tabla_Lista_Examenes);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Tabla_Cita_Examen);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Tabla_Resultados_Examenes);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Tabla_detalle_medicamentos);
        onCreate(sqLiteDatabase);

    }

    //Metodo para insertar registros en la tabla usuarios

    public void insetarReg(String nom, String correo, String clave ,Integer tipo, Integer esp ,String nit, String dui,String tel, String fecha, String direccion ,Integer est){
        //Estos valores cuando se envien se deben colocar en ...
        ContentValues valores=new ContentValues();
        //Con put agregamos valores a el objeto valores
        valores.put(Campo_Nombre,nom);
        valores.put(Campo_Correo,correo);
        valores.put(Campo_Clave,clave);
        valores.put(Campo_Tipo_User,tipo);
        valores.put(Campo_Especialidad,esp);
        valores.put(Campo_Nit,nit);
        valores.put(Campo_Dui,dui);
        valores.put(Campo_Telefono,tel);
        valores.put(Campo_Fecha_Nac,fecha);
        valores.put(Campo_Direccion,direccion);
        valores.put(Campo_Estado,est);

        idResultante= this.getWritableDatabase().insert(Tabla_Usuario, Campo_Id,valores);
        //Toast.makeText(this,"Id Registro: "+idResultante,Toast.LENGTH_SHORT).show();

    }




    public Long IdR(){

        return idResultante;
    }

    //Metodo para abrir la base de datos
    public void abrir(){

        this.getWritableDatabase();

    }

    //Metodo para cerrar la base de datos
    public void cerrar(){
        this.close();
    }

    //Metodo para validar si el usuario existe y asi poder loguarse
    //AGREGAMOS EL CONTROL DE ERRORES SQLException
    public Cursor ConsultarUsuPas(String usu, String pas) throws SQLException {
        Cursor mcursor=null;
        // mcursor=this.getReadableDatabase().query("usuarios",new String[]{"ID","Nombre","Distrito","Correo","Password"},"Correo like '"+usu+"' and Password like '"+pas+"' ",null,null,null,null);

        mcursor=this.getWritableDatabase().rawQuery("select * from "+ Tabla_Usuario+" where "+ Campo_Correo+" like '"+usu+"' and "+ Campo_Clave+" like '"+pas+"';",new String[]{});




        //Si el valor de el cursor es 1 encontro algo sino 0 y es porque no existe..
        return mcursor;

    }















    //comentario de kevin
}
