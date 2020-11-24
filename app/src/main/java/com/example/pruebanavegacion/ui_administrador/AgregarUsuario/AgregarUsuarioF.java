package com.example.pruebanavegacion.ui_administrador.AgregarUsuario;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.pruebanavegacion.R;

public class AgregarUsuarioF extends Fragment {

    private AgregarUsuarioVM agregarUsuarioVM;
    View vista;
    CheckBox e1,e2,e3;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        agregarUsuarioVM =
                ViewModelProviders.of(this).get(AgregarUsuarioVM.class);
        vista = inflater.inflate(R.layout.a_fragment_agregarusuario, container, false);

        final TextView textView = vista.findViewById(R.id.text_gallery);

        agregarUsuarioVM.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        e1 = (CheckBox) vista.findViewById(R.id.chkDoctor);
        e2 = (CheckBox) vista.findViewById(R.id.chkArchivo);
        e3= (CheckBox) vista.findViewById(R.id.chkAdmin);

        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e2.setChecked(false);
                e3.setChecked(false);
            }
        });
        e2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e1.setChecked(false);
                e3.setChecked(false);
            }
        });
        e3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e1.setChecked(false);
                e2.setChecked(false);
            }
        });
        return vista;

    }

}