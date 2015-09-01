package abk.utilities;

import abk.model.Book;
import abk.utilities.adapter.BookListAdpt;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by edgar on 29/08/15.
 */
public class BookService extends AsyncTask<Void, Void, List<Book>> {
    private String url;
    private Long idCategory;
    private ListView view;
    private BookListAdpt adapt;
    private Context context;

    public BookService(ListView view, Context context, String url, Long idCategory) {
        this.view = view;
        this.url = url;
        this.context = context;
        this.idCategory = idCategory;
    }


    @Override
    protected List<Book> doInBackground(Void... voids) {
        List<Book> books = new ArrayList();
        URL toConnect;
        HttpURLConnection connection;
        OutputStreamWriter out;
        JSONArray jsonArray;

        try {
            toConnect = new URL(url);
            connection = (HttpURLConnection) toConnect.openConnection();

            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            out = new OutputStreamWriter(connection.getOutputStream());
            out.write(createJson(idCategory).toString());
            out.flush();
            out.close();

            jsonArray = new JSONArray(DataUtil.getInputString(connection));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);

                Book book = new Book();
                book.setName(json.getString("name"));
                book.setIdentifier(json.getLong("id"));
                book.setImage(DataUtil.getBitMapByBase64(json.getString("image")));
                book.setIdCategory(json.getLong("idCategory"));
                book.setAuthor(json.getString("author"));
                book.setDescription(json.getString("description"));

                books.add(book);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    protected void onPostExecute(List<Book> books) {
        super.onPostExecute(books);
        adapt = new BookListAdpt(context, books);
        view.setAdapter(adapt);
        adapt.notifyDataSetChanged();
    }

    public JSONObject createJson(Long idCategory) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("idCategory", idCategory);
        return json;
    }
}

