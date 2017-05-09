package com.example.juan.foodapp.controlador;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.juan.foodapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TransferenciaActivity extends AppCompatActivity {

    private static ExpandableListView expandableListView;
    private static ExpandableListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Actividad a pantalla completa
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_transferencia);
        //Muestra barra de acción
        ActionBar ab= getSupportActionBar();
        //Muestra logo
        ab.setLogo(R.drawable.icon);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        expandableListView = (ExpandableListView) findViewById(R.id.transferencia_list);

        // Setting group indicator null for custom indicator
        expandableListView.setGroupIndicator(null);

        setItems();
        setListener();

    }

    // Setting headers and childs to expandable listview
    void setItems() {

        // Array list for header
        ArrayList<String> header = new ArrayList<String>();
        ArrayList<String> header2 = new ArrayList<String>();

        // Array list for child items
        List<String> child1 = new ArrayList<String>();
        List<String> child2 = new ArrayList<String>();
        List<String> child3 = new ArrayList<String>();
        List<String> child4 = new ArrayList<String>();

        // Hash map for both header and child
        HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();

        // Adding headers to list

        header.add("Datos del alimento \n(Fluido frío)");

        // Adding child data

        child1.add("Temperatura del alimento \na la entrada de la sección. (°C)" );
        child1.add("Caudal correspondiente \na la entrada del alimento a la zona. (L/s)");
        child1.add("Coeficiente de incrustación para el fluido frío");

        header.add("Datos del fluido del servicio (Fuido caliente)");

        // Adding child data

        child2.add("Temperatura del fluido de servicio \na la entrada de la sección. (°C)");
        child2.add("Caudal correspondiente a la entrada \ndel fluido de servicio a la zona. (L/s)");
        child2.add("Coeficiente de incrustación para el fluido caliente");

        header.add("Dato general de la práctica");
        child3.add("Coeficiente Global de Transferencia de Calor \nde Diseño a suponer. (W/m^2 °C)\n");


        // Adding header and childs to hash map
        hashMap.put(header.get(0), child1);
        hashMap.put(header.get(1), child2);
        hashMap.put(header.get(2), child3);
        //hashMap.put(header.get(3), child4);

        adapter = new ExpandableListAdapter(TransferenciaActivity.this, header, hashMap);

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

                Toast.makeText(TransferenciaActivity.this,
                        "Abriste: " + adapter.getGroup(group_pos),
                        Toast.LENGTH_SHORT).show();
                return false;
           }
        }); */

        // Este metodo solo deja que se abra un grupo a la vez

        expandableListView
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
                        TransferenciaActivity.this,
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_file) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
