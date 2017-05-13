package com.example.juan.foodapp.controlador;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juan.foodapp.R;

public class SeleccionarLaboratorioActivity extends AppCompatActivity {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_selecciona_laboratorio);

        Laboratorio laboratorios_data[] = new Laboratorio[]{
                new Laboratorio(R.mipmap.icons_lab, "Procesos Lácteos"),
                new Laboratorio(R.mipmap.icons_lab3, "Laboratorio 2"),
                new Laboratorio(R.mipmap.icon_labs4, "Laboratorio 3"),
                new Laboratorio(R.mipmap.icons_lab5, "Laboratorio 4"),

        };

        ListaDeLaboratoriosAdapterActivity adapter = new ListaDeLaboratoriosAdapterActivity(this, R.layout.listview_item_row_selecciona_laboratorio, laboratorios_data);

        lv= (ListView) findViewById(R.id.lv);

        View header = (View) getLayoutInflater().inflate(R.layout.list_header_row_selecciona_laboratorio, null);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Lista de laboratorios disponibles", Toast.LENGTH_SHORT).show();
            }
        });
        lv.addHeaderView(header);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView v = (TextView) view.findViewById(R.id.tvlab);
                Toast.makeText(getApplicationContext(), v.getText(), Toast.LENGTH_SHORT).show();
                if (v.getText().equals("Procesos Lácteos") ) {
                    Intent intent = new Intent(v.getContext(), SeleccionaTipoPracticaProcesosLacteosActivity.class);
                    startActivityForResult(intent, 0);
                }

            }
        });

    }

    public void onClick(View v){

        Toast.makeText(getApplicationContext(), "Sesión cerrada", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(v.getContext(), LoginActivity.class);
        startActivityForResult(intent, 0);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu, menu);
        return false;
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


