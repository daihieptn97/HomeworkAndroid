package com.k14b.hieptran.Main;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.k14b.hieptran.Database.Note.Notes;
import com.k14b.hieptran.R;

import java.util.ArrayList;

public class AdapterListNote extends ArrayAdapter<Notes> {

    private Context context;
    private ArrayList<Notes> arrayNote;
    private TextView txtTitle, txtContent, txtDate;
    private LinearLayout layout;

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
        layout.setBackgroundColor(note.getColor());

        GradientDrawable gradientDrawable = (GradientDrawable) v.getBackground();
        gradientDrawable.setColor(0xFF000000);

//        drawable.setColor(0xFF000000);
        return v;
    }

    private void mapping(View v) {
        txtContent = v.findViewById(R.id.adapterTxtContentNote);
        txtDate = v.findViewById(R.id.adapterTxtDateNote);
        txtTitle = v.findViewById(R.id.adapterTxtTileNoe);
        layout = v.findViewById(R.id.layoutNoteItem);
    }


}
