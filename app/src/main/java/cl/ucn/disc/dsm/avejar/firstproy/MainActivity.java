package cl.ucn.disc.dsm.avejar.firstproy;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import org.json.JSONArray;
import java.io.InputStream;
import java.util.ArrayList;

import cl.ucn.disc.dsm.avejar.firstproy.adapters.PersonAdapter;
import cl.ucn.disc.dsm.avejar.firstproy.models.Person;

public class MainActivity extends AppCompatActivity {

    /**
     * ListView que muestra a las personas
     */
    private ListView listView;

    /**
     * El adapter que transforma las personas a "views" en el ListView
     */
    private PersonAdapter adapter;

    /**
     * Array que almacena los datos JSON
     */
    ArrayList<Person> InitialList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ToolBar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Obtiene personas desde JSON
        InitialList = Person.jsFile(getJsons());

        // Crea el adapter
        if (this.adapter == null)
            this.adapter = new PersonAdapter(this, InitialList);

        // Asigna el adapter al listView de la main activity
        listView = findViewById(R.id.lv_persons);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        // Buscador presente en ToolBar
        MenuItem searchIcon = menu.findItem(R.id.item2_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchIcon);

        // Listener
        searchView.setOnQueryTextListener
                (new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (adapter == null)
                    return false;

                // Si la busqueda es vacia, vuelve a la normalidad (Total personas)
                if (newText.isEmpty()){
                    adapter.Load(InitialList);
                }else {

                    // Si se presenta algo, se presenta una busqueda especifica
                    ArrayList<Person> auxPersons = new ArrayList<>();
                    for (Person p : InitialList) {
                        if (p.getName().toLowerCase().contains(newText.toLowerCase())) {
                            auxPersons.add(p);
                        }
                    }
                    adapter.Load(auxPersons);
                }
                // Notifica al adapter el cambio de datos
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        return true;
    }

    /**
     * Lee el archivo JSON desde "assets" y devuelve un array de ellos
     * @return Array de JSONS
     */
    private JSONArray getJsons(){
        String js;
        InputStream iStream = null;
        JSONArray jArray = null;

        try {
            iStream = getAssets().open("ucn.json");
            int size = iStream.available();
            byte[] buffer = new byte[size];
            iStream.read(buffer);
            iStream.close();

            js = new String(buffer, "UTF-8");
            jArray = new JSONArray(js);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jArray;
    }
}
