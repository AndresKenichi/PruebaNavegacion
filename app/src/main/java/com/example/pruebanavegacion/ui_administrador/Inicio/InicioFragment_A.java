package com.example.pruebanavegacion.ui_administrador.Inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.pruebanavegacion.R;

public class InicioFragment_A extends Fragment {

    private InicioViewModel_A inicioViewModelA;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        inicioViewModelA =
                ViewModelProviders.of(this).get(InicioViewModel_A.class);
        View root = inflater.inflate(R.layout.a_fragment_inicio, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        inicioViewModelA.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}