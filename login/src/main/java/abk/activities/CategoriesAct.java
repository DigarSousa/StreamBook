package abk.activities;

/**
 * Created by Pedreduardo on 22/08/2015.
 */

import abk.utilities.Adapter.CategorieAdpt;
import abk.utilities.adapter.CategorieAdpt;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesAct extends Activity {

    CategorieAdpt gridAdapter;
    GridView gridView;
    //ArrayList<Categorie> categories;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_act);

        //Buscar categorias aqui
        //---------------------------------------
        //this.categories = new Categories();
        //---------------------------------------

        ArrayList<String> lala = new ArrayList<String>();
        lala.add("eita");
        lala.add("lelele");
        lala.add("OIEOEIOEI");
        lala.add("eita");
        lala.add("lelele");
        lala.add("OIEOEIOEI");
        lala.add("eita");
        lala.add("lelele");
        lala.add("OIEOEIOEI");
        lala.add("eita");
        lala.add("lelele");
        lala.add("OIEOEIOEI");
        lala.add("eita");
        lala.add("lelele");
        lala.add("OIEOEIOEI");

        gridView = (GridView) findViewById(R.id.grdCategories);
        this.gridAdapter = new CategorieAdpt(this, lala);
        gridView.setAdapter(gridAdapter);
        gridAdapter.notifyDataSetChanged();
        //setListeners();

    }

    private void setListeners() {
        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}