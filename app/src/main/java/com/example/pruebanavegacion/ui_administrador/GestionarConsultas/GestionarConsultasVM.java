package com.example.pruebanavegacion.ui_administrador.GestionarConsultas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GestionarConsultasVM extends ViewModel {

    private MutableLiveData<String> mText;

    public GestionarConsultasVM() {
        mText = new MutableLiveData<>();
        mText.setValue("This is GESTIONAR CONSULTAS");
    }

    public LiveData<String> getText() {
        return mText;
    }
}