package com.example.pruebanavegacion.ui_administrador.AgregarUsuario;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AgregarUsuarioVM extends ViewModel {

    private MutableLiveData<String> mText;

    public AgregarUsuarioVM() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}