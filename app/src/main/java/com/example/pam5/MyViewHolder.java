package com.example.pam5;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView namaView, nimView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        namaView = itemView.findViewById(R.id.nama);
        nimView = itemView.findViewById(R.id.nim);
    }
}
