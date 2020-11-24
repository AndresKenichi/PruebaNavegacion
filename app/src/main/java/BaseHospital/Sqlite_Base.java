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
        sqLiteDatabase.execSQL(Utilidades.Crear_Tabla_Usuarios);

    }

    //Si queremos modificar alguna estructura de nuestra base de datos utilizaremos el onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int VersionAntigua, int NuevaVersion) {

        //Si volvemos a instalar la aplicacion debemos eliminar la version antigua y luego generarla
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Utilidades.Tabla_Usuario);
        onCreate(sqLiteDatabase);

    }

    //Metodo para insertar registros en la tabla usuarios

    public void insetarReg(String nom, String correo, String clave ,Integer tipo, Integer esp ,String nit, String dui,String tel, String fecha, String direccion ,Integer est){
        //Estos valores cuando se envien se deben colocar en ...
        ContentValues valores=new ContentValues();
        //Con put agregamos valores a el objeto valores
        valores.put(Utilidades.Campo_Nombre,nom);
        valores.put(Utilidades.Campo_Correo,correo);
        valores.put(Utilidades.Campo_Clave,clave);
        valores.put(Utilidades.Campo_Tipo_User,tipo);
        valores.put(Utilidades.Campo_Especialidad,esp);
        valores.put(Utilidades.Campo_Nit,nit);
        valores.put(Utilidades.Campo_Dui,dui);
        valores.put(Utilidades.Campo_Telefono,tel);
        valores.put(Utilidades.Campo_Fecha_Nac,fecha);
        valores.put(Utilidades.Campo_Direccion,direccion);
        valores.put(Utilidades.Campo_Estado,est);

        idResultante= this.getWritableDatabase().insert(Utilidades.Tabla_Usuario,Utilidades.Campo_Id,valores);
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

        mcursor=this.getWritableDatabase().rawQuery("select * from "+Utilidades.Tabla_Usuario+" where "+Utilidades.Campo_Correo+" like '"+usu+"' and "+Utilidades.Campo_Clave+" like '"+pas+"';",new String[]{});




        //Si el valor de el cursor es 1 encontro algo sino 0 y es porque no existe..
        return mcursor;

    }
















}
