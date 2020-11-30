package com.example.pruebanavegacion.ui_administrador.ConsultarPacientes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebanavegacion.R;
import com.example.pruebanavegacion.ui_administrador.ConsultarAreas.RecyclerViewPacientesCA;
import com.example.pruebanavegacion.ui_administrador.ConsultarAreas.csPacientesCA;

import java.util.List;

public class RecyclerViewPacientesCP extends RecyclerView.Adapter<RecyclerViewPacientesCP.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView Nombre,IdCita,Doctor,Estado,Fecha,Hora;

        public ViewHolder(View itemView){
            super(itemView);
            Nombre = (TextView) itemView.findViewById(R.id.txtNombreCP);
            IdCita = (TextView) itemView.findViewById(R.id.txtCitaCP);
            Estado = (TextView) itemView.findViewById(R.id.txtEstadoCP);
            Doctor = (TextView) itemView.findViewById(R.id.txtDoctorCP);
            Fecha = (TextView) itemView.findViewById(R.id.txtDiaCP);
            Hora = (TextView) itemView.findViewById(R.id.txtHoraCP);

        }

    }
    public List<csPacientesCP> PacienteList;
    public RecyclerViewPacientesCP(List<csPacientesCP> pacienteList){
        this.PacienteList = pacienteList;
    }
    public RecyclerViewPacientesCP.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a_cardview_consultarpacientes,parent,false);
        RecyclerViewPacientesCP.ViewHolder viewHolder = new RecyclerViewPacientesCP.ViewHolder(view);

        return viewHolder;
    }
    public void onBindViewHolder(RecyclerViewPacientesCP.ViewHolder holder, int position){

        holder.Nombre.setText(PacienteList.get(position).getNombre());
        holder.IdCita.setText(PacienteList.get(position).getIdCita());
        holder.Estado.setText(PacienteList.get(position).getEstado());
        holder.Doctor.setText(PacienteList.get(position).getDoctor());
        holder.Fecha.setText(PacienteList.get(position).getFecha());
        holder.Hora.setText(PacienteList.get(position).getHora());
    }

    @Override
    public int getItemCount() {
        return PacienteList.size();
    }
}
