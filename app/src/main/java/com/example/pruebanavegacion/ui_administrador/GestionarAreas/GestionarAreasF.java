package com.example.pruebanavegacion.ui_administrador.GestionarAreas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.pruebanavegacion.R;

public class GestionarAreasF extends Fragment {

    private GestionarAreasVM GestionarAreasVM;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GestionarAreasVM =
                ViewModelProviders.of(this).get(GestionarAreasVM.class);
        View root = inflater.inflate(R.layout.a_fragment_gestionarareas, container, false);

        return root;
    }
}