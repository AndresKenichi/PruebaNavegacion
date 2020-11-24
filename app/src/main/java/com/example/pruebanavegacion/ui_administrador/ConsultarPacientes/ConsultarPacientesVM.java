package com.example.pruebanavegacion.ui_administrador.ConsultarPacientes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConsultarPacientesVM extends ViewModel {

    private MutableLiveData<String> mText;

    public ConsultarPacientesVM() {
        mText = new MutableLiveData<>();
        mText.setValue("This is CONSULTAR PACIENTES");
    }

    public LiveData<String> getText() {
        return mText;
    }
}