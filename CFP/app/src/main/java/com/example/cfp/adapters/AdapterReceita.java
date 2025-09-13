package com.example.cfp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cfp.R;


import com.example.cfp.model.Despesa;
import com.example.cfp.model.Receita;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class AdapterReceita extends RecyclerView.Adapter<AdapterReceita.MyViewHolder> {

    private List<Receita> listaReceita;
    private Context context;
    private SimpleDateFormat dateFormat;

    public AdapterReceita(List<Receita> listaReceita){
        this.listaReceita = listaReceita;
        this.dateFormat = new SimpleDateFormat("yyyy/MM/YYYY", Locale.getDefault());
    }

    @NonNull
    @Override
    public AdapterReceita.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_receita, null);

        return new AdapterReceita.MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterReceita.MyViewHolder holder, int position) {
        Receita receita = listaReceita.get(position);
        holder.descricaoReceita.setText(receita.getDescricao());
        holder.valorReceita.setText(String.format(Locale.getDefault(),"%$.2f",receita.getValor()));
        holder.dataReceita.setText(dateFormat.format(receita.getData()));
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView descricaoReceita;
        TextView valorReceita;
        TextView dataReceita;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            descricaoReceita = itemView.findViewById(R.id.lblDescricaoReceita);
            valorReceita = itemView.findViewById(R.id.lblValorReceita);
            dataReceita = itemView.findViewById(R.id.lblDataReceita);
        }
    }


}
