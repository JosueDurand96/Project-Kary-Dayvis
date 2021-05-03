package com.example.boosters;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.boosters.ui.Soporte.DAOException;
import com.example.boosters.ui.Soporte.SoporteDAO;
import com.example.boosters.ui.Soporte.SoporteViewModel;

import java.util.ArrayList;

public class DetalleSoporteActivity extends AppCompatActivity {
    ArrayList<SoporteViewModel> resultados = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_soporte);
    }

    public void buscar(View view) {
        EditText criterio = (EditText) findViewById(R.id.criterio);
        SoporteDAO dao = new SoporteDAO(getBaseContext());
        try {
            resultados = dao.buscar(criterio.getText().toString());
            String[] encontrados = new String[resultados.size()];
            int i = 0;
            for (SoporteViewModel gm : resultados) {
                encontrados[i++] = gm.getCodigo() +"  " + gm.getDescripcion();
            }
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this.getApplicationContext(),
                    android.R.layout.simple_list_item_1,
                    encontrados);
            ListView listaResultados = (ListView)findViewById(R.id.listaResultados);
            listaResultados.setAdapter(adaptador);
            registerForContextMenu(listaResultados);

        } catch (DAOException e) {
            Log.i("SoporteBuscarAc", "====> " + e.getMessage());
        }
    }
}
