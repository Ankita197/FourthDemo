package com.ankita.fourthtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ankita.fourthtask.adapter.ContactListAdapter;

public class ShowUsers extends AppCompatActivity {

    private RecyclerView rvLiked;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users);
        init();
        setAdapter();
    }

    private void setAdapter() {
        ContactListAdapter contactListLike=new ContactListAdapter(MainActivity.likePersonList,this);
        rvLiked.setAdapter(contactListLike);
    }

    private void init() {
        rvLiked=findViewById(R.id.rvLiked);
        linearLayoutManager=new LinearLayoutManager(this);
        rvLiked.setLayoutManager(linearLayoutManager);
    }
}
