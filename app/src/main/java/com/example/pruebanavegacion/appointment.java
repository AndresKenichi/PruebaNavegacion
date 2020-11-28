package com.example.pruebanavegacion;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import Utilidades.AppointmentAdapter;

public class appointment extends Fragment {

    public appointment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appointment, container, false);
        ListView listview = view.findViewById(R.id.appointmentListView);
        ArrayList<String> list = new ArrayList<String>();
        list.add("HELLLO");

        listview.setAdapter(new AppointmentAdapter(this.getContext(), list));
        return view;
    }
}