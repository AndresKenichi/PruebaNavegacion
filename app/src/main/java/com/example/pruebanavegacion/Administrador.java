package com.example.pruebanavegacion;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.pruebanavegacion.ui_administrador.AgregarUsuario.AgregarUsuarioF;
import com.example.pruebanavegacion.ui_administrador.Inicio.InicioFragment_A;
import com.google.android.material.navigation.NavigationView;

import BaseHospital.DatosConexion;
import BaseHospital.Sqlite_Base;

public class Administrador extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private Sqlite_Base x;
    String correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);
        Toolbar toolbar = findViewById(R.id.toolbara);
        setSupportActionBar(toolbar);

        x = new Sqlite_Base(getApplicationContext(), DatosConexion.NOMBREBD,null,DatosConexion.VERSION);
        x.abrir();
        correo=getIntent().getStringExtra("correo");

        DrawerLayout drawer = findViewById(R.id.drawer_layoutAdmin);
        NavigationView navigationView = findViewById(R.id.nav_viewAdmin);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.R.id.nav_consultarmedicamentos_a,R.id.nav_medicamentos_a
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio_a, R.id.nav_appointments, R.id.nav_agregarusuario_a, R.id.nav_modificarusuario_a, R.id.nav_medicamentos_a, R.id.nav_consultarmedicamentos_a,
                R.id.nav_consultarpacientes_a, R.id.nav_gestionarconsultas_a, R.id.nav_gestionarareas_a, R.id.nav_consultarareas_a)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_hosta);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_hosta);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onPause(){
        super.onPause();
        finish();
    }
    public void UpdateNavHeader(String corr){

        NavigationView navigationView = findViewById(R.id.nav_viewAdmin);
        View Encabezado=navigationView.getHeaderView(0);
        TextView txtCorreo=Encabezado.findViewById(R.id.txtCorreo);
        txtCorreo.setText(corr);

    }


}