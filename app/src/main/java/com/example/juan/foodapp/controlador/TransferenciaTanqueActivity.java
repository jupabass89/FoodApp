package com.example.juan.foodapp.controlador;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.juan.foodapp.R;
import com.example.juan.foodapp.modelo.serviciosPractica.Guia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TransferenciaTanqueActivity extends AppCompatActivity {

    private static ExpandableListView expandableListView;
    private static ExpandableListAdapterTransferenciaTanque adapter;
    Spinner spinner_agitadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Actividad a pantalla completa

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_transferencia_tanque);

        //Muestra barra de acción
        ActionBar ab= getSupportActionBar();
        //Muestra logo
        ab.setLogo(R.mipmap.ic_icon);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);


        //ExpandableListView en el layout xml asociado a esta actividad

        expandableListView = (ExpandableListView) findViewById(R.id.transferencia_tanque_list);

        // Setting group indicator null for custom indicator
        expandableListView.setGroupIndicator(null);

        setItems();
        setListener();





        // valor1.addTextChangedListener(passwordWatcher);

        final Button ingresarDatos = (Button)findViewById(R.id.btnTransferenciaTanque);
        ingresarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Acción para llevar los datos
            }
        });



    }
    private Spinner spinner;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_transferencia_tanque, container, false);
        setSpinnerContent( view );

        return view;
    }

    private void setSpinnerContent( View view )
    {
        spinner = (Spinner) view.findViewById( R.id.spinner_agitador);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_tipo_agitador, android.R.layout.simple_spinner_item);

        spinner.setAdapter( adapter );

    }






    // Setting headers and childs to expandable listview
    void setItems() {

        // Array list for header
        ArrayList<String> header = new ArrayList<String>();


        // Array list for child items
        List<String> child1 = new ArrayList<String>();
        List<String> child2 = new ArrayList<String>();
        List<String> child3 = new ArrayList<String>();
        List<String> child4 = new ArrayList<String>();
        List<String> child5 = new ArrayList<String>();
        List<String> child6 = new ArrayList<String>();


        // Hash map for both header and child
        HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();


        // Adding headers to list
        // Adding child data MACHETAZO

        header.add("Alimento");
        child1.add("");

        header.add("Tanque");
        child2.add("");

        header.add("Agitador");
        child3.add("");

        header.add("Temperaturas de calentamiento");
        child4.add("");

        header.add("Temperaturas de enfriamiento");
        child5.add("");

        header.add("Fluido de servicio");
        child6.add("");


        // Adding header and childs to hash map

        hashMap.put(header.get(0), child1);
        hashMap.put(header.get(1), child2);
        hashMap.put(header.get(2), child3);
        hashMap.put(header.get(3), child4);
        hashMap.put(header.get(4), child5);
        hashMap.put(header.get(5), child6);

        adapter = new ExpandableListAdapterTransferenciaTanque(TransferenciaTanqueActivity.this, header, hashMap);

        // Setting adpater over expandablelistview

        expandableListView.setAdapter(adapter);
    }
    // Setting different listeners to expandablelistview
    void setListener() {

        // Muestra un mensaje diciendo en cual grupo se dio click

      /*  expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

           @Override
            public boolean onGroupClick(ExpandableListView listview, View view,
                                        int group_pos, long id) {

                Toast.makeText(TransferenciaPlacasActivity.this,
                        "Abriste: " + adapter.getGroup(group_pos),
                        Toast.LENGTH_SHORT).show();
                return false;
           }
        }); */
        // Listview Group collasped listener
          expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
        @Override
        public void onGroupCollapse(int groupPosition) {


        Toast.makeText(getApplicationContext(),
        "Datos Guardados",
        Toast.LENGTH_SHORT).show();
        }


        });

        // Este método solo deja que se abra un grupo a la vez

        /**expandableListView
                .setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                    // Default position
                    int previousGroup = -1;

                    @Override
                    public void onGroupExpand(int groupPosition) {
                        if (groupPosition != previousGroup)

                            // Collapse the expanded group
                            expandableListView.collapseGroup(previousGroup);
                        previousGroup = groupPosition;
                    }

                });

        // Muestra un mensaje diciendo en cual hijo se dio click

      /*  expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView listview, View view,
                                        int groupPos, int childPos, long id) {
                Toast.makeText(
                        TransferenciaPlacasActivity.this,
                        "You clicked : " + adapter.getChild(groupPos, childPos),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }); */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.generaInforme):
                break;
            case (R.id.verGuia):
                Intent intencion = new Intent(this, Guia.class);
                intencion.putExtra("nombrePractica", "tanqueAgitado.pdf");
                startActivity(intencion);
                break;
        }
        return (true);
    }
}

