package com.example.pruebanavegacion.ui_administrador.ConsultarAreas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConsultarAreasVM extends ViewModel {

    private MutableLiveData<String> mText;

    public ConsultarAreasVM() {
        mText = new MutableLiveData<>();
        mText.setValue("This is CONSULTAR AREAS");
    }

    public LiveData<String> getText() {
        return mText;
    }
}