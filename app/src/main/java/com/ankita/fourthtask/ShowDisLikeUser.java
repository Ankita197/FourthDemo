package com.ankita.fourthtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ankita.fourthtask.adapter.ContactListAdapter;

public class ShowDisLikeUser extends AppCompatActivity {

    RecyclerView rvDisliked;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_dis_like_user);
        init();
        setAdapter();
    }
    private void setAdapter() {
        ContactListAdapter contactListLike=new ContactListAdapter(MainActivity.dislikePersonList,this);
        rvDisliked.setAdapter(contactListLike);
    }

    private void init() {
        rvDisliked=findViewById(R.id.rvLiked);
        linearLayoutManager=new LinearLayoutManager(this);
        rvDisliked.setLayoutManager(linearLayoutManager);
    }

}
