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


public class LaboratorioAdapter extends ArrayAdapter<Laboratorio>{
        Context context;
        int LayoutResortId;
        Laboratorio data[]= null;

        public LaboratorioAdapter(Context context, int layoutResortId, Laboratorio[] data) {
            super(context, layoutResortId,data);

            this.context= context;
            this.LayoutResortId= layoutResortId;
            this.data= data;

        }

        public View getView(int position, View contentView, ViewGroup parent){
            View row= contentView;
            LaboratorioHolder holder = null;

            if(row==null){
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                row= inflater.inflate(LayoutResortId, parent, false);

                holder= new LaboratorioHolder();
                holder.imagen= (ImageView) row.findViewById(R.id.imagenlab);
                holder.texto = (TextView) row.findViewById((R.id.tvlab));
                row.setTag(holder);
            }
            else{
                holder= (LaboratorioHolder) row.getTag();
            }

            Laboratorio laboratorios = data[position];
            holder.texto.setText(laboratorios.title);
            holder.imagen.setImageResource(laboratorios.icon);


            return row;
        }

        static class LaboratorioHolder{
            ImageView imagen;
            TextView texto;
        }
    }

