package abk.utilities;

import abk.model.Category;
import abk.utilities.adapter.CategoriesAdapt;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;
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
 * Created by edgar on 24/08/15.
 */
public class CateoryService extends AsyncTask<Void, Void, List<Category>> {
    private String url;
    private Context context;
    private GridView gridView;
    private CategoriesAdapt adapt;

    public CateoryService(GridView gridView, Context context, String url) {
        this.url = url;
        this.context = context;
        this.gridView = gridView;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected List doInBackground(Void... voids) {
        List<Category> categories = new ArrayList();
        URL toConnect;
        HttpURLConnection connection;
        OutputStreamWriter out;
        JSONArray jsonArray;
        try {
            toConnect = new URL(url);
            connection = (HttpURLConnection) toConnect.openConnection();
            connection.setDoInput(true);

            jsonArray = new JSONArray(DataUtil.getInputString(connection));

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);
                Category category = new Category();
                category.setIdentifier(json.getLong("id"));
                category.setName(json.getString("name"));
                category.setImage(DataUtil.getBitMapByBase64(json.getString("image")));

                categories.add(category);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return categories;
    }


    @Override
    protected void onPostExecute(List<Category> categories) {
        super.onPostExecute(categories);

        adapt = new CategoriesAdapt(context, categories);
        gridView.setAdapter(adapt);
        adapt.notifyDataSetChanged();
    }

}
