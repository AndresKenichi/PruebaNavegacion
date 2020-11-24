package com.example.pruebanavegacion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionSqlite extends SQLiteOpenHelper {

    //Nombre de la base de datos en este constructor es donde colocamos el nombre de nuestra base...
    public ConexionSqlite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //En el onCreate es donde vamos a crear la estructura que va tener nuestra base de datos, Nuestras tablas y posibles relaciones entre ellas..
    @Override
    public void onCreate(SQLiteDatabase db) {

    }


    //Si queremos modificar alguna estructura de nuestra base de datos utilizaremos el onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int VersionAntigua, int VersionNueva) {

    }
}
