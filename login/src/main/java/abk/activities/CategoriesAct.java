package abk.activities;

/**
 * Created by Pedreduardo on 22/08/2015.
 */

import abk.model.Category;
import abk.utilities.Constants;
import abk.utilities.GridUtil;
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
import java.util.List;

public class CategoriesAct extends Activity {

    private CategorieAdpt gridAdapter;
    private GridView gridView;
    private List<Category> categories;

    public CategoriesAct() {
        categories = new ArrayList();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_act);

        new GridUtil(Constants.URL_CATEGORY, categories).execute();

        gridView = (GridView) findViewById(R.id.grdCategories);
        this.gridAdapter = new CategorieAdpt(this, categories);
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