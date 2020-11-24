package com.example.pruebanavegacion.ui_administrador.ModificarUsuario;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ModificarUsuarioVM extends ViewModel {

    private MutableLiveData<String> mText;

    public ModificarUsuarioVM() {
        mText = new MutableLiveData<>();
        mText.setValue("This is MODIFICAR USUARIO");
    }

    public LiveData<String> getText() {
        return mText;
    }
}