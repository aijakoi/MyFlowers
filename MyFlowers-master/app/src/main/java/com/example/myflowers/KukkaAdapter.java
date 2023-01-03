package com.example.myflowers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class KukkaAdapter extends RecyclerView.Adapter<KukkaAdapter.MyViewHolder> {

    RecyclerViewClickListener listener;
    String data1[];
    int images[];
    Context context;

    public KukkaAdapter(Context ct, String s1[], int img[], RecyclerViewClickListener listener) {
        context = ct;
        data1 = s1;
        images = img;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.teksti.setText(data1[position]);
        holder.kuva.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView teksti;
        ImageView kuva;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            teksti = itemView.findViewById(R.id.kukatNimi);
            kuva = itemView.findViewById(R.id.kukatKuva);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View itemView) {
            listener.onClick(itemView, getBindingAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }
}
