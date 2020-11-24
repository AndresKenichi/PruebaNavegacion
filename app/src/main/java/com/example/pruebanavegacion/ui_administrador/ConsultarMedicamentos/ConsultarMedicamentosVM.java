package com.example.pruebanavegacion.ui_administrador.ConsultarMedicamentos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConsultarMedicamentosVM extends ViewModel {

    private MutableLiveData<String> mText;

    public ConsultarMedicamentosVM() {
        mText = new MutableLiveData<>();
        mText.setValue("This is CONSULTAR MEDICAMENTOS");
    }

    public LiveData<String> getText() {
        return mText;
    }
}