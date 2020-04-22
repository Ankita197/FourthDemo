package com.ankita.fourthtask.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ankita.fourthtask.R;
import com.ankita.fourthtask.modal.Contact;
import java.util.ArrayList;

public class SwipeDeskAdapter extends BaseAdapter {

    ArrayList<Contact> contactsList;
    Context context;

    public SwipeDeskAdapter(ArrayList<Contact> contactsList, Context context) {
        this.contactsList = contactsList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return contactsList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){

            // normally use a viewholder
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacts, parent, false);
        }
        ((TextView) v.findViewById(R.id.tvName)).setText(contactsList.get(position).getName());


        return v;
    }
}
