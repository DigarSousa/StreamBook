package abk.utilities;

import abk.model.Category;
import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by edgar on 24/08/15.
 */
public class GridUtil extends AsyncTask<Void, Void, Void> {
    private String url;
    private List<Category> categories;

    public GridUtil(String url) {
        this.url = url;
        categories = new ArrayList();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        URL toConnect;
        HttpURLConnection connection;
        InputStreamReader in;
        BufferedReader reader;
        try {
            toConnect = new URL(url);
            connection = (HttpURLConnection) toConnect.openConnection();
            connection.setDoInput(true);


            in = new InputStreamReader(connection.getInputStream());
            reader = new BufferedReader(in);
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            line = sb.toString();
            JSONArray jsonArray = new JSONArray(line);

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
        return null;
    }

    public List<Category> getCategories() {
        return categories;
    }
}
