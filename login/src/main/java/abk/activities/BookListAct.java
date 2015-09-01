package abk.activities;

/**
 * Created by Pedreduardo on 22/08/2015.
 */

import android.app.Activity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;

import abk.utilities.BookService;
import abk.utilities.Constants;
import abk.utilities.adapter.BookListAdpt;

public class BookListAct extends Activity {

    BookListAdpt listAdapter;
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_act);

        Bundle b = new Bundle();
        int categoryId = b.getInt("ctg");

        ArrayList<String> lala = new ArrayList<String>();
        lala.add("Livro 1");
        lala.add("Livro 2");
        lala.add("Livro 3");
        lala.add("Livro 4");
        lala.add("Livro 5 ");
        lala.add("Livro 6");
        lala.add("Livro 7");
        lala.add("Livro 8");
        lala.add("Livro 9");
        lala.add("Livro 10");

        listView = (ListView) findViewById(android.R.id.list);
        new BookService(listView, getApplicationContext(), Constants.URL_BOOKS, Long.valueOf(0)).execute();
        //setListeners();

    }

    private void setListeners() {
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}