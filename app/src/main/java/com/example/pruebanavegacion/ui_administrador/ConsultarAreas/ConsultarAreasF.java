package com.example.pruebanavegacion.ui_administrador.ConsultarAreas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.pruebanavegacion.R;

public class ConsultarAreasF extends Fragment {

    private ConsultarAreasVM ConsultarAreasVM;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ConsultarAreasVM =
                ViewModelProviders.of(this).get(ConsultarAreasVM.class);
        View root = inflater.inflate(R.layout.a_fragment_consultarareas, container, false);

        return root;
    }
}