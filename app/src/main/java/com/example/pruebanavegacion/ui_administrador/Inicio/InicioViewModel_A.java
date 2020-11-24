package com.example.pruebanavegacion.ui_administrador.Inicio;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InicioViewModel_A extends ViewModel {

    private MutableLiveData<String> mText;

    public InicioViewModel_A() {
        mText = new MutableLiveData<>();
        mText.setValue("This is INICIO ADMINISTRADOR");
    }

    public LiveData<String> getText() {
        return mText;
    }
}