package com.example.pam5;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.*;

@Dao
public interface DAOMahasiswa {

    @Insert
    void insertAll(Mahasiswa mahasiswa);

    @Query("SELECT * FROM Mahasiswa")
    List<Mahasiswa> getAllMahasiswa();


}
