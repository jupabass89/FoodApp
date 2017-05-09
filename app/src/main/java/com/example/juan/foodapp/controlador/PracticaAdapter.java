package com.example.juan.foodapp.controlador;

/**
 * Created by Dj0nt on 8/05/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.juan.foodapp.R;


public class PracticaAdapter extends ArrayAdapter<Practica>{
    Context context;
    int LayoutResortId;
    Practica data[]= null;

    public PracticaAdapter(Context context, int layoutResortId, Practica[] data) {
        super(context, layoutResortId,data);

        this.context= context;
        this.LayoutResortId= layoutResortId;
        this.data= data;

    }

    public View getView(int position, View contentView, ViewGroup parent){
        View row= contentView;
        PracticaHolder holder = null;

        if(row==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row= inflater.inflate(LayoutResortId, parent, false);

            holder= new PracticaHolder();
            holder.imagen= (ImageView) row.findViewById(R.id.imagen);
            holder.texto = (TextView) row.findViewById((R.id.tv));
            row.setTag(holder);
        }
        else{
            holder= (PracticaHolder) row.getTag();
        }

        Practica practicas = data[position];
        holder.texto.setText(practicas.title);
        holder.imagen.setImageResource(practicas.icon);


        return row;
    }

    static class PracticaHolder{
        ImageView imagen;
        TextView texto;
    }
}

