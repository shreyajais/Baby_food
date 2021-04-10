package com.codewithshreya.babyfoodrecipes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<FoodData> myFoodList;
    FoodData mFoodData;

    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;

    ProgressDialog progressDialog;
    MyAdapter myAdapter;
    EditText txt_Search;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        //number of columns==spanCount
        mRecyclerView.setLayoutManager(gridLayoutManager);



        txt_Search = (EditText)findViewById(R.id.txt_searchtext);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Recipe......");




        myFoodList = new ArrayList<>();





        MyAdapter myAdapter = new MyAdapter(MainActivity.this, myFoodList);
        mRecyclerView.setAdapter(myAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("recipe");

        progressDialog.show();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myFoodList.clear();

                for(DataSnapshot itemSnapshot: snapshot.getChildren())
                {
                   FoodData foodData = itemSnapshot.getValue(FoodData.class);
                   myFoodList.add(foodData);
                }


                myAdapter.notifyDataSetChanged();
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();

            }
        });


        txt_Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });
    }

    private void filter(String text) {

        ArrayList<FoodData> filterList = new ArrayList<>();

        for(FoodData item: myFoodList)
        {
            if(item.getItemName().toLowerCase().contains(text.toLowerCase()))
            {
                filterList.add(item);
            }
        }

        myAdapter.filteredList(filterList);
    }

    public void btn_uploadActivity(View view) {
        startActivity(new Intent(this, Upload_Recipe.class));
    }
}