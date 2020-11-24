package com.example.pruebanavegacion.ui_administrador.GestionarAreas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GestionarAreasVM extends ViewModel {

    private MutableLiveData<String> mText;

    public GestionarAreasVM() {
        mText = new MutableLiveData<>();
        mText.setValue("This is GESTIONAR AREAS");
    }

    public LiveData<String> getText() {
        return mText;
    }
}