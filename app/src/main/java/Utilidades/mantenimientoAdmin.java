package Utilidades;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import BaseHospital.DatosConexion;
import BaseHospital.Sqlite_Base;

import static Utilidades.Utilidades.Campo_Clave;
import static Utilidades.Utilidades.Campo_Correo;
import static Utilidades.Utilidades.Tabla_Usuario;

public class mantenimientoAdmin {

   // Sqlite_Base helper=new Sqlite_Base(mantenimientoAdmin.get, DatosConexion.NOMBREBD,null,DatosConexion.VERSION);

    public Cursor ConsultarUsuPas(String usu, String pas) throws SQLException {
        Cursor mcursor=null;
        // mcursor=this.getReadableDatabase().query("usuarios",new String[]{"ID","Nombre","Distrito","Correo","Password"},"Correo like '"+usu+"' and Password like '"+pas+"' ",null,null,null,null);


        //mcursor=.rawQuery("select * from "+ Tabla_Usuario+" where "+ Campo_Correo+" like '"+usu+"' and "+ Campo_Clave+" like '"+pas+"';",new String[]{});




        //Si el valor de el cursor es 1 encontro algo sino 0 y es porque no existe..
        return mcursor;

    }



}
