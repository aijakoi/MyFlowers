package com.example.myflowers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {
    private ArrayList<Kukka> kukatLista;
    private RecyclerViewClickListener listener;

    public recyclerAdapter(ArrayList<Kukka> kukatLista, RecyclerViewClickListener listener) {
        this.kukatLista = kukatLista;
        this.listener = listener;
    }

    public void setFilteredList(ArrayList<Kukka> filteredList) {
        this.kukatLista = filteredList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView nimiTxt;
        private ImageView kuvaImg;

        public MyViewHolder(final View view) {
            super(view);
            nimiTxt = view.findViewById(R.id.kukatNimi);
            kuvaImg = view.findViewById(R.id.kukatKuva);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getBindingAdapterPosition());
        }
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        String nimi = kukatLista.get(position).getKukkaNimi();
        holder.nimiTxt.setText(nimi);
        int kuva = kukatLista.get(position).getKukkaKuva();
        holder.kuvaImg.setImageResource(kuva);
    }

    @Override
    public int getItemCount() {
        return  kukatLista.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
}
