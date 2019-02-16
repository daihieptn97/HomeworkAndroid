package com.k14b.hieptran.Main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.k14b.hieptran.Database.Notes;
import com.k14b.hieptran.R;

import java.util.ArrayList;

public class AdapterListNote extends ArrayAdapter<Notes> {

    private Context context;
    private ArrayList<Notes> arrayNote;
    private TextView txtTitle, txtContent, txtDate;


    public AdapterListNote(Context context, int resource, ArrayList<Notes> objects) {
        super(context, resource, objects);
        this.context = context;
        this.arrayNote = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v;

        v = LayoutInflater.from(context).inflate(R.layout.adapter_list_note, parent, false);
        mapping(v);
        Notes note = arrayNote.get(position);
        txtTitle.setText(note.getTilte());
        txtDate.setText(note.getTimeCreate());
        txtContent.setText(note.getContent());


        return v;
    }

    private void mapping(View v) {
        txtContent = v.findViewById(R.id.adapterTxtContentNote);
        txtDate = v.findViewById(R.id.adapterTxtDateNote);
        txtTitle = v.findViewById(R.id.adapterTxtTileNoe);
    }


}
