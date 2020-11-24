package com.example.pruebanavegacion.ui_administrador.ConsultarPacientes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.pruebanavegacion.R;

public class ConsultarPacientesF extends Fragment {

    private ConsultarPacientesVM ConsultarPacientesVM;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ConsultarPacientesVM =
                ViewModelProviders.of(this).get(ConsultarPacientesVM.class);
        View root = inflater.inflate(R.layout.a_fragment_consultarpacientes, container, false);
        //final TextView textView = root.findViewById(R.id.text_cpacientes);
        //ConsultarPacientesVM.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
         //   @Override
           // public void onChanged(@Nullable String s) {
            //    textView.setText(s);
            //}
        //});
        return root;
    }
}