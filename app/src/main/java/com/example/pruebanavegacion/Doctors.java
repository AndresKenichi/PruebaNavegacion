package com.example.pruebanavegacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import BaseHospital.DatosConexion;
import BaseHospital.Sqlite_Base;

public class Doctors extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private Sqlite_Base x;
    String correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);
        Toolbar toolbar = findViewById(R.id.toolbarDoctors);
        setSupportActionBar(toolbar);

        x = new Sqlite_Base(getApplicationContext(), DatosConexion.NOMBREBD,null,DatosConexion.VERSION);
        x.abrir();
        correo=getIntent().getStringExtra("correo");

        DrawerLayout drawer = findViewById(R.id.drawer_layoutDoctors);
        NavigationView navigationView = findViewById(R.id.nav_viewDoctors);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio_a, R.id.nav_appointments, R.id.nav_medical_chart)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_hosta_doctors);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        UpdateNavHeader(correo);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.doctors, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_hosta_doctors);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public void UpdateNavHeader(String corr){

        NavigationView navigationView = findViewById(R.id.nav_viewDoctors);
        View Encabezado=navigationView.getHeaderView(0);
        TextView txtCorreo=Encabezado.findViewById(R.id.txtCorreo);
        txtCorreo.setText(corr);

    }
}