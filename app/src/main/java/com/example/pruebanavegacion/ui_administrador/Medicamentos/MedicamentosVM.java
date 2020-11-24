package com.example.pruebanavegacion.ui_administrador.Medicamentos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MedicamentosVM extends ViewModel {

    private MutableLiveData<String> mText;

    public MedicamentosVM() {
        mText = new MutableLiveData<>();
        mText.setValue("This is MEDICAMENTOS");
    }

    public LiveData<String> getText() {
        return mText;
    }
}