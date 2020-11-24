package com.example.pruebanavegacion.ui_administrador.ConsultarMedicamentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.pruebanavegacion.R;

public class ConsultarMedicamentosF extends Fragment {

    private ConsultarMedicamentosVM ConsultarMedicamentosVM;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ConsultarMedicamentosVM=
                ViewModelProviders.of(this).get(ConsultarMedicamentosVM.class);
        View root = inflater.inflate(R.layout.a_fragment_consultarmedicamentos, container, false);

        return root;
    }
}