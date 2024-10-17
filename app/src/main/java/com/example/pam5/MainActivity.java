package com.example.pam5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText inputNama, inputNim;
    Button b1;
    private AppDatabase appDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        appDb = AppDatabase.getInstance((getApplicationContext()));
        inputNama = findViewById(R.id.inputNama);
        inputNim = findViewById(R.id.inputNim);
        b1 = findViewById(R.id.b1);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNama(inputNama.getText().toString());
                mahasiswa.setNim(inputNim.getText().toString());
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        appDb.daoMahasiswa().insertAll(mahasiswa);
                    }
                });
            }
        });


        class bgthread extends Thread {
            public void run() {
                super.run();
                AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "room_db").build();
                DAOMahasiswa daoMahasiswa = db.daoMahasiswa();
                daoMahasiswa.insertAll(new Mahasiswa(inputNama.getText().toString(), inputNim.getText().toString()));
                inputNama.setText("");
                inputNim.setText("");
                Toast.makeText(getApplicationContext(), "Inserted Succesfully", Toast.LENGTH_LONG).show();

            }
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        List<Mahasiswa> mahasiswa = new ArrayList<Mahasiswa>();
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                List<Mahasiswa> fetchedData = appDb.daoMahasiswa().getAllMahasiswa();
                mahasiswa.addAll(fetchedData);
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    recyclerView.setAdapter(new MyAdapter(getApplicationContext(), mahasiswa));
                }
            });
        });

        void runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(new MyAdapter(getApplicationContext(), mahasiswa));
            }
        });

    }
}