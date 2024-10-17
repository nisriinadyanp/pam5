package com.example.pam5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<Mahasiswa> mahasiswa;

    public MyAdapter(@NonNull Context context, List<Mahasiswa> mahasiswa) {
        this.context = context;
        this.mahasiswa = mahasiswa;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull  MyViewHolder holder, int position) {
        holder.namaView.setText(mahasiswa.get(position).getNama());
        holder.nimView.setText(mahasiswa.get(position).getNim());
    }

    @Override
    public int getItemCount() {
        return mahasiswa.size();
    }


}