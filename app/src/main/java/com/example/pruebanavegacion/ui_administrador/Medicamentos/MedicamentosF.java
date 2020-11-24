package com.example.pruebanavegacion.ui_administrador.Medicamentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.pruebanavegacion.R;

public class MedicamentosF extends Fragment {

    private MedicamentosVM MedicamentosVM;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MedicamentosVM =
                ViewModelProviders.of(this).get(MedicamentosVM.class);
        View root = inflater.inflate(R.layout.a_fragment_medicamentos, container, false);
        //final TextView textView = root.findViewById(R.id.text_medicamentos);
        //MedicamentosVM.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            //@Override
            //public void onChanged(@Nullable String s) {
                //textView.setText(s);
            //}
        //});
        return root;
    }
}