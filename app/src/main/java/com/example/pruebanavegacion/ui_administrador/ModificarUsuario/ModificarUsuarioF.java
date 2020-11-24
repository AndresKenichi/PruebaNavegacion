package com.example.pruebanavegacion.ui_administrador.ModificarUsuario;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.pruebanavegacion.R;

public class ModificarUsuarioF extends Fragment {

    private ModificarUsuarioVM modificarUsuarioVM;
    Button btnVolver,btnModificar;
    View vista;
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        modificarUsuarioVM =
                ViewModelProviders.of(this).get(ModificarUsuarioVM.class);
        vista = inflater.inflate(R.layout.a_fragment_modificarusuario, container, false);
        modificarUsuarioVM.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        btnVolver=vista.findViewById(R.id.btnCancelar);
        btnModificar=vista.findViewById(R.id.btnModificar);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });
        return vista;
    }
}