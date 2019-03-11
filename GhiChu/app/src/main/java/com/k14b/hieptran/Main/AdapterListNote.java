package com.k14b.hieptran.Main;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v4.content.ContextCompat;
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
        
        txtContent.setText(note.getContent());
//        layout.setBackgroundColor(note.getColor());


        Drawable background = layout.getBackground();
        if (background instanceof ShapeDrawable) {
            // cast to 'ShapeDrawable'
            ShapeDrawable shapeDrawable = (ShapeDrawable) background;
            shapeDrawable.getPaint().setColor(ContextCompat.getColor(context, R.color.bgRed));
        } else if (background instanceof GradientDrawable) {
            // cast to 'GradientDrawable'
            GradientDrawable gradientDrawable = (GradientDrawable) background;
            gradientDrawable.setColor(ContextCompat.getColor(context, note.getColor()));
        } else if (background instanceof ColorDrawable) {
            // alpha value may need to be set again after this call
            ColorDrawable colorDrawable = (ColorDrawable) background;
            colorDrawable.setColor(ContextCompat.getColor(context, R.color.bgDeepOrange));
        }


        return v;
    }

    private void mapping(View v) {
        txtContent = v.findViewById(R.id.adapterTxtContentNote);

        txtTitle = v.findViewById(R.id.adapterTxtTileNoe);
        layout = v.findViewById(R.id.layoutNoteItem);
    }


}
