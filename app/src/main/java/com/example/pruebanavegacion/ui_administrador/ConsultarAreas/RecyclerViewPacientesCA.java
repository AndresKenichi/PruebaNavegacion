package com.example.pruebanavegacion.ui_administrador.ConsultarAreas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebanavegacion.R;

import java.util.List;

public class RecyclerViewPacientesCA extends RecyclerView.Adapter<RecyclerViewPacientesCA.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView Nombre,Cama,Doctor,EstadoRV;
        ImageView fotoLogo;

        public ViewHolder(View itemView){
            super(itemView);
            Nombre = (TextView) itemView.findViewById(R.id.txtNombreP);
            Cama = (TextView) itemView.findViewById(R.id.txtCama);
            EstadoRV = (TextView) itemView.findViewById(R.id.txtEstadocv);
            Doctor = (TextView) itemView.findViewById(R.id.txtDoctor);
            fotoLogo = (ImageView) itemView.findViewById(R.id.imgPacienteLogo);
        }

    }
    public List<csPacientesCA> PacienteList;
    public RecyclerViewPacientesCA(List<csPacientesCA> pacienteList){
        this.PacienteList = pacienteList;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a_cardview_consultarareas,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }
    public void onBindViewHolder(ViewHolder holder,int position){

        holder.Nombre.setText(PacienteList.get(position).getNombre());
        holder.Cama.setText(PacienteList.get(position).getNumCama());
        holder.EstadoRV.setText(PacienteList.get(position).getEstado());
        holder.Doctor.setText(PacienteList.get(position).getDoctor());
        holder.fotoLogo.setImageResource(PacienteList.get(position).getimgPaciente());
    }

    @Override
    public int getItemCount() {
        return PacienteList.size();
    }

}
