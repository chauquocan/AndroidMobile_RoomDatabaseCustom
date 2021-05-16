package com.example.androidmobile_roomdatabasecustom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    RecyclerView view;
    EditText edtLocation;
    Button btn_save,btn_cancel;
    LocationAdapter adapter;
    LocationDao locationDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Anh xa
        edtLocation = findViewById(R.id.edt_location);
        btn_save = findViewById(R.id.btn_save);
        btn_cancel = findViewById(R.id.btn_cancel);
        view = findViewById(R.id.rv_location);

        //database
        AppDatabase appDatabase = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"locations.db")
                .allowMainThreadQueries()
                .build();
        locationDao = appDatabase.locationDao();

        //adapter
        getList();
        view.setLayoutManager(new GridLayoutManager(this,1));
        btn_save.setText("SAVE");
        btn_cancel.setText("CANCEL");
        btn_save.setOnClickListener(v -> {
            String nameLocation = edtLocation.getText().toString().trim();
            if(!nameLocation.equalsIgnoreCase("")){
                locationDao.insertAll(new Location(nameLocation));
                adapter = new LocationAdapter(this,locationDao.getAll(),locationDao);
                view.setAdapter(adapter);
                edtLocation.setText("");
            }
        });
    }

    private void getList() {
        adapter = new LocationAdapter(this,locationDao.getAll(),locationDao);
        view.setAdapter(adapter);
    }

}