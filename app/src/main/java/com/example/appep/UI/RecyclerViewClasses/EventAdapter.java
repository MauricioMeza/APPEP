package com.example.appep.UI.RecyclerViewClasses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appep.Data.Model.Evento;
import com.example.appep.Data.Model.EventoAmago;
import com.example.appep.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder>{
    ArrayList<Evento> listaEventos;

    private static DecimalFormat df = new DecimalFormat("#.#####");
    private static DecimalFormat af = new DecimalFormat("#.#");
    private static DecimalFormat nf = new DecimalFormat("#");

    public EventAdapter(ArrayList<Evento> eventos){
        this.listaEventos = eventos;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pozoinfocard_layout, parent, false);
        return new EventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Evento evento = listaEventos.get(position);

        EventoAmago ultimoAmago = evento.getEventoAmago();
        af.setRoundingMode(RoundingMode.CEILING);
        holder.textViewEventDate.setText(evento.getFechaCreacion().toString());
        holder.textViewEventPsoldo1.setText(af.format(evento.getPesoLodo()));
        holder.textViewEventPsoldo2.setText("(" + df.format(evento.getPesoLodo()) + ")");
        holder.textViewEventVol.setText(df.format(evento.getVolTotal()));
        holder.textViewEventLng.setText(df.format(evento.getLongTotal()));
        holder.textViewEventPrsCrrTbo.setText(df.format(ultimoAmago.getPresCierreTubo()));
        holder.textViewEventPrsCrrRev.setText(df.format(ultimoAmago.getPresCierreRev()));
        holder.textViewEventGncSup.setText(df.format(ultimoAmago.getGananciaSuperficie()));
        holder.textViewEventEstHBroca.setText(nf.format(ultimoAmago.getEstrHastaBroca()));
        holder.textViewEventEstFArriba.setText(nf.format(ultimoAmago.getEstrFondoArrb()));
        holder.textViewEventCircTtl.setText(nf.format(ultimoAmago.getCircTotalMatarPozo()));
        holder.textViewEventFract.setText(df.format(ultimoAmago.getPrFractura()));
        holder.textViewEventPoro.setText(df.format(ultimoAmago.getPrPoro()));

        double[][] matrix = evento.getTablaEstr();
        for(int i=0; i<matrix.length; i++) {
            holder.estroques[i][0].setText(nf.format(matrix[i][0]));
            holder.estroques[i][1].setText(af.format(matrix[i][1]));
        }
    }

    @Override
    public int getItemCount() {
        return listaEventos.size();
    }
}
