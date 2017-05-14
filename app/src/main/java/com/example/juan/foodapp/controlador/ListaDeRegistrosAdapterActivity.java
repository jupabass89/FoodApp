package com.example.juan.foodapp.controlador;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.juan.foodapp.R;

public class ListaDeRegistrosAdapterActivity extends ArrayAdapter<String> {

    Context context;
    int LayoutResortId;
    String data[]= null;

    public ListaDeRegistrosAdapterActivity(Context context, int layoutResortId, String[] data) {
        super(context, layoutResortId,data);

        this.context= context;
        this.LayoutResortId= layoutResortId;
        this.data= data;

    }

    public View getView(int position, View contentView, ViewGroup parent){
        View row= contentView;
        ListaDeRegistrosAdapterActivity.PracticaHolder holder = null;

        if(row==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row= inflater.inflate(LayoutResortId, parent, false);

            holder= new ListaDeRegistrosAdapterActivity.PracticaHolder();
            holder.texto = (TextView) row.findViewById((R.id.tvRegistros));
            row.setTag(holder);
        }
        else{
            holder= (ListaDeRegistrosAdapterActivity.PracticaHolder) row.getTag();
        }

        String registro = data[position];
        holder.texto.setText(registro);

        return row;
    }

    static class PracticaHolder{
        TextView texto;
    }
}
