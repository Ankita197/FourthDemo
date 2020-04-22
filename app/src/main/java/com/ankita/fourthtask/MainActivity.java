package com.ankita.fourthtask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.ContactsContract;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.ankita.fourthtask.adapter.ContactListAdapter;
import com.ankita.fourthtask.adapter.SwipeDeskAdapter;
import com.ankita.fourthtask.modal.Contact;
import com.daprlabs.cardstack.SwipeDeck;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Contact> arrayList;
    private Cursor phones;
    FloatingActionButton fabShowLike,fabShowDislike;
    public  static ArrayList<Contact> likePersonList,dislikePersonList;
    SwipeDeck swipeDeck;

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        showContacts();
        //setAdapter();
        setSwipe();
        setCallBack();
        fabShowLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLikePersonList();
                startActivity(new Intent(MainActivity.this,ShowUsers.class));

            }
        });
        fabShowDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDislikePersonList();
                startActivity(new Intent(MainActivity.this,ShowDisLikeUser.class));
            }
        });

    }

    private void showDislikePersonList() {
        for(Contact contact:dislikePersonList){
            Log.d("disliked",contact.getName());
        }
    }

    private void showLikePersonList() {
        for(Contact contact:likePersonList){
            Log.d("liked",contact.getName());
        }
    }

    private void setCallBack() {
        swipeDeck.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                dislikePersonList.add(arrayList.get(position));
            }

            @Override
            public void cardSwipedRight(int position) {
                likePersonList.add(arrayList.get(position));

            }

            @Override
            public void cardsDepleted() {

            }

            @Override
            public void cardActionDown() {

            }

            @Override
            public void cardActionUp() {

            }
        });
    }

    private void setSwipe() {
        SwipeDeskAdapter swipeDeskAdapter=new SwipeDeskAdapter(arrayList,this);
        swipeDeck.setAdapter(swipeDeskAdapter);
    }



    private void show() {
        for(Contact contact:likePersonList){
            Log.d("$$$$",contact.getName());
        }
    }



    private void init() {
        arrayList=new ArrayList<>();

        fabShowDislike=findViewById(R.id.fabShowDislike);
        fabShowLike=findViewById(R.id.fabShowlike);
        likePersonList=new ArrayList<>();
        dislikePersonList=new ArrayList<>();
        swipeDeck=findViewById(R.id.svContacts);
    }

    private void showContacts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {
            // Android version is lesser than 6.0 or the permission is already granted.
            phones = getApplicationContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
            LoadContact loadContact = new LoadContact();
            loadContact.execute();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                showContacts();
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class LoadContact extends AsyncTask<Void,Void,Void>{


        @Override
        protected Void doInBackground(Void... voids) {
            if (phones != null) {
                Log.e("count", "" + phones.getCount());
                if (phones.getCount() == 0) {

                }

                while (phones.moveToNext()) {
                    Bitmap bit_thumb = null;
                    String id = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                    String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));


                    Contact contact = new Contact();
                    contact.setName(name);
                    contact.setPhone(phoneNumber);
                    arrayList.add(contact);


                }
            } else {
                Log.e("Cursor close 1", "----------------");
            }

            return null;
        }
    }


}
