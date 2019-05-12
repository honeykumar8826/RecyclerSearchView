package com.searchView.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.Toast;

import com.searchView.R;
import com.searchView.adapter.StudentInfoAdapter;
import com.searchView.modal.StudentInfoModal;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<StudentInfoModal> listItem;
    private StudentInfoAdapter studentInfoAdapter;
    private Button btnDataSet;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*id initialization*/
        inItIds();
        /*set the data on recyclerView with blank list*/
        //setBlankListOnRecyclerView();
        setDataOnRecyclerView();

/*        btnDataSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               *//* set the data on recyclerView with data list*//*
                setDataOnRecyclerView();

            }
        });*/

    }

/*    @Override
    protected void onResume() {
        super.onResume();
        setDataOnRecyclerView();
    }*/

    private void setDataOnRecyclerView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItem = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            StudentInfoModal studentInfoModalList = new StudentInfoModal("harsh" + i, String.valueOf(i));
            listItem.add(studentInfoModalList);
        }
        studentInfoAdapter = new StudentInfoAdapter(listItem);
        recyclerView.setAdapter(studentInfoAdapter);
        // studentInfoAdapter.notifyDataSetChanged();
    }

    private void setBlankListOnRecyclerView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        listItem = new ArrayList<>();
        studentInfoAdapter = new StudentInfoAdapter(listItem);
        recyclerView.setAdapter(studentInfoAdapter);
        Toast.makeText(this, "size=" + listItem.size(), Toast.LENGTH_SHORT).show();
    }

    private void inItIds() {
        recyclerView = findViewById(R.id.recycleList);
        //btnDataSet = findViewById(R.id.button_dataset);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.custom_menu, menu);
        MenuItem serachItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) serachItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                studentInfoAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }
}
