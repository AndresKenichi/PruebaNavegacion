package com.example.pruebanavegacion.ui_administrador.GestionarConsultas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.pruebanavegacion.R;

public class GestionarConsultasF extends Fragment {

    private GestionarConsultasVM GestionarConsultasVM;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GestionarConsultasVM =
                ViewModelProviders.of(this).get(GestionarConsultasVM.class);
        View root = inflater.inflate(R.layout.a_fragment_gestionarconsultas, container, false);

        return root;
    }
}