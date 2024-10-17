package com.example.pam5;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity (tableName = "Mahasiswa")
public class Mahasiswa {

    public Mahasiswa () {}

    public Mahasiswa (@NonNull String nama, @NonNull String nim) {
        this.nama = nama;
        this.nim = nim;
    }

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "nama")
    public String nama;

    @ColumnInfo(name = "NIM")
    public String nim;

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }
}
