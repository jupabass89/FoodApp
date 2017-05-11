package com.example.juan.foodapp.controlador;


        import java.util.HashMap;
        import java.util.List;

        import android.content.Context;
        import android.graphics.Typeface;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseExpandableListAdapter;
        import android.widget.EditText;
        import android.widget.TextView;

        import com.example.juan.foodapp.R;


public class ExpandableListAdapterTransferenciaPlacas extends BaseExpandableListAdapter {
    private Context _context;

    private List<String> header; // Titulos para los headers

    // Child data in format of header title, child title
    private HashMap<String, List<String>> child;

    //variables para obtener los datos ingresados

    private String tempAlimentoEntrada;
    private String caudalEntradaAlimento;
    private String coefIncrustacionFrio;
    private String tempFluidoServicioEntrada;
    private String caudalEntradaFluido;
    private String coefIncrustacionCaliente;
    private String coefGlobalTransfer;

    public String getCoefGlobalTransfer() {
        return coefGlobalTransfer;
    }

    public String getCoefIncrustacionCaliente() {
        return coefIncrustacionCaliente;
    }

    public String getCaudalEntradaFluido() {
        return caudalEntradaFluido;
    }

    public String getTempFluidoServicioEntrada() {
        return tempFluidoServicioEntrada;
    }

    public String getCoefIncrustacionFrio() {
        return coefIncrustacionFrio;
    }

    public String getCaudalEntradaAlimento() {
        return caudalEntradaAlimento;
    }

    public String getTempAlimentoEntrada() {
        return tempAlimentoEntrada;
    }

    public ExpandableListAdapterTransferenciaPlacas(Context context, List<String> listDataHeader,
                                                    HashMap<String, List<String>> listChildData) {
        this._context = context;
        this.header = listDataHeader;
        this.child = listChildData;
    }

    public ExpandableListAdapterTransferenciaPlacas(){}


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

            if(("Temperatura del alimento (Fluido frío)").equals(header.get(groupPosition))) {
                convertView = infalInflater.inflate(R.layout.child_1_transferencia_placas, parent, false);

                //Creo lo necesario para crear el observador, cada que se oprime una telca, actualiza la variable linkeada con ese campo

                final EditText valor1;
                valor1 = (EditText)convertView.findViewById(R.id.campo1);
                valor1.addTextChangedListener(new TextWatcher() {


                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        tempAlimentoEntrada = s.toString();

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
                        caudalEntradaAlimento = s.toString();



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
                        coefIncrustacionFrio = s.toString();


                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }

                } );
                //TextView child_text = (TextView) convertView.findViewById(R.id.child_transfer4);
               // child_text.setText(childText);
            }
        if(("Temperatura del fluido del servicio (Fuido caliente)").equals(header.get(groupPosition))) {
            convertView = infalInflater.inflate(R.layout.child_2_transferencia_placas, parent, false);
            final EditText valor4;
            valor4 = (EditText)convertView.findViewById(R.id.campo4);

            valor4.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    tempFluidoServicioEntrada = s.toString();



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
                    caudalEntradaFluido = s.toString();



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
                    coefIncrustacionCaliente = s.toString();



                }

                @Override
                public void afterTextChanged(Editable s) {

                }

            } );
            //TextView child_text = (TextView) convertView.findViewById(R.id.child_transfer1);
            //child_text.setText(childText);
        }
        if(("Dato general de la práctica").equals(header.get(groupPosition))) {
            convertView = infalInflater.inflate(R.layout.child_3_transferencia_placas, parent, false);
            final EditText valor7;
            valor7 = (EditText)convertView.findViewById(R.id.campo7);

            valor7.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {


                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    coefGlobalTransfer = s.toString();

                }

                @Override
                public void afterTextChanged(Editable s) {


                }

            } );
            //TextView child_text = (TextView) convertView.findViewById(R.id.child_transfer1);
            //child_text.setText(childText);
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
            convertView = infalInflater.inflate(R.layout.header_expandable_transferencia_placas, parent, false);
        }

        TextView header_text = (TextView) convertView.findViewById(R.id.header_transferencia_placas);

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