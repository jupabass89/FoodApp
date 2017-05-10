package com.example.juan.foodapp.controlador;


import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.juan.foodapp.R;

import java.util.HashMap;
import java.util.List;


public class ExpandableListAdapterTransferenciaTanque extends BaseExpandableListAdapter {
    private Context _context;

    private List<String> header; // Titulos para los headers

    // Child data in format of header title, child title
    private HashMap<String, List<String>> child;







    //variables para obtener los datos ingresados

    private String campo1;
    private String campo2;
    private String campo3;
    private String campo4;
    private String campo5;
    private String campo6;
    private String campo7;

    public String getCampo7() {
        return campo7;
    }

    public String getCampo6() {
        return campo6;
    }

    public String getCampo5() {
        return campo5;
    }

    public String getCampo4() {
        return campo4;
    }

    public String getCampo3() {
        return campo3;
    }

    public String getCampo2() {
        return campo2;
    }

    public String getCampo1() {
        return campo1;
    }

    public ExpandableListAdapterTransferenciaTanque(Context context, List<String> listDataHeader,
                                                    HashMap<String, List<String>> listChildData) {
        this._context = context;
        this.header = listDataHeader;
        this.child = listChildData;
    }

    public ExpandableListAdapterTransferenciaTanque(){}


    @Override
    public Object getChild(int groupPosition, int childPosititon) {

        // This will return the child
        return this.child.get(this.header.get(groupPosition)).get(
                childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {



        //TextView child_text = (TextView) convertView.findViewById(R.id.child_transfer1);
        //child_text.setText(childText);

        // Getting child text
        final String childText = (String) getChild(groupPosition, childPosition);
        // Inflating child layout and setting textview
        LayoutInflater infalInflater = (LayoutInflater) this._context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Selecciono que se me cargue una vista según lo que dice en el header (Título), TIENE QUE COINCIDIR EXACTAMENTE con el definido en la clase TransferenciaPlacasActivity

            if(("Alimento").equals(header.get(groupPosition))) {
                convertView = infalInflater.inflate(R.layout.child_1_transferencia_tanque, parent, false);

                //Creo lo necesario para crear el observador, cada que se oprime una telca, actualiza la variable linkeada con ese campo

               /** final EditText valor1;
                valor1 = (EditText)convertView.findViewById(R.id.campo1);
                valor1.addTextChangedListener(new TextWatcher() {


                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        campo1 = s.toString();
                        Log.i(campo1, "test");

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }

                } );
                final EditText valor2;
                valor2 = (EditText)convertView.findViewById(R.id.campo2);

                valor2.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        campo2 = s.toString();
                        Log.i(campo2, "test");


                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }

                } );
                final EditText valor3;
                valor3 = (EditText)convertView.findViewById(R.id.campo3);

                valor3.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        campo3 = s.toString();
                        Log.i(campo3, "test");

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }

                } );
                //TextView child_text = (TextView) convertView.findViewById(R.id.child_transfer4);
               // child_text.setText(childText);**/
            }
        if(("Tanque").equals(header.get(groupPosition))) {
            convertView = infalInflater.inflate(R.layout.child_2_transferencia_tanque, parent, false);
            /**final EditText valor4;
            valor4 = (EditText)convertView.findViewById(R.id.campo4);

            valor4.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    campo4 = s.toString();
                    Log.i(campo4, "test");


                }

                @Override
                public void afterTextChanged(Editable s) {

                }

            } );
            final EditText valor5;
            valor5 = (EditText)convertView.findViewById(R.id.campo5);

            valor5.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    campo5 = s.toString();
                    Log.i(campo5, "test");


                }

                @Override
                public void afterTextChanged(Editable s) {

                }

            } );

            final EditText valor6;
            valor6 = (EditText)convertView.findViewById(R.id.campo6);

            valor6.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    campo6 = s.toString();
                    Log.i(campo6, "test");


                }

                @Override
                public void afterTextChanged(Editable s) {

                }

            } );
            //TextView child_text = (TextView) convertView.findViewById(R.id.child_transfer1);
            //child_text.setText(childText);**/
        }
        if(("Agitador").equals(header.get(groupPosition))) {
            convertView = infalInflater.inflate(R.layout.child_3_transferencia_tanque, parent, false);

            final Spinner spinner;
            spinner = (Spinner)convertView.findViewById(R.id.spinner_agitador);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(_context,
                    R.array.spinner_tipo_agitador, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);


            /**final EditText valor7;
            valor7 = (EditText)convertView.findViewById(R.id.campo7);

            valor7.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    campo7 = s.toString();
                    Log.i(campo7, "test");

                }

                @Override
                public void afterTextChanged(Editable s) {

                }

            } );
            //TextView child_text = (TextView) convertView.findViewById(R.id.child_transfer1);
            //child_text.setText(childText);**/

        }
        if(("Temperaturas de calentamiento").equals(header.get(groupPosition))) {
            convertView = infalInflater.inflate(R.layout.child_4_transferencia_tanque, parent, false);
        }
        if(("Temperaturas de enfriamiento").equals(header.get(groupPosition))) {
            convertView = infalInflater.inflate(R.layout.child_5_transferencia_tanque, parent, false);
        }


        return convertView;
    }


    @Override
    public int getChildrenCount(int groupPosition) {

        // return children count
        return this.child.get(this.header.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {

        // Get header position
        return this.header.get(groupPosition);
    }

    @Override
    public int getGroupCount() {

        // Get header size
        return this.header.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        // Getting header title
        String headerTitle = (String) getGroup(groupPosition);

        // Inflating header layout and setting text
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.header_expandable_transferencia_tanque, parent, false);
        }

        TextView header_text = (TextView) convertView.findViewById(R.id.header_transferencia_tanque);

        header_text.setText(headerTitle);

        // If group is expanded then change the text into bold and change the
        // icon
        if (isExpanded) {
            header_text.setTypeface(null, Typeface.BOLD);
            header_text.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                    R.mipmap.ic_up, 0);
        } else {
            // If group is not expanded then change the text back into normal
            // and change the icon

            header_text.setTypeface(null, Typeface.NORMAL);
            header_text.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                    R.mipmap.ic_down, 0);
        }

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}