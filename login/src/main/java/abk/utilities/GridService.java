package abk.utilities;

import abk.model.Book;
import abk.model.Category;
import abk.utilities.adapter.CategoriesAdapt;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by edgar on 24/08/15.
 */
public class GridService extends AsyncTask<Void, Void, List<Category>> {
    private String url;
    private Context context;
    private Short gridType;
    private Short idCategory;
    private GridView gridView;
    private CategoriesAdapt adapt;

    public GridService(GridView gridView, Context context, String url, Short gridType, Short idCategory) {
        this.url = url;
        this.context = context;
        this.gridView = gridView;
        this.gridType = gridType;
        this.idCategory = idCategory;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected List doInBackground(Void... voids) {
        List objects = new ArrayList<Category>();
        URL toConnect;
        HttpURLConnection connection;
        InputStreamReader in;
        OutputStreamWriter out;
        JSONArray jsonArray;
        try {
            toConnect = new URL(url);
            connection = (HttpURLConnection) toConnect.openConnection();
            connection.setDoInput(true);

            if (gridType.equals(Constants.CATEGORY)) {
                jsonArray = new JSONArray(getJsonString(connection));
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json = (JSONObject) jsonArray.get(i);
                    Category category = new Category();
                    category.setIdentifier(json.getLong("id"));
                    category.setName(json.getString("name"));
                    category.setImage(DataUtil.getBitMapByBase64(json.getString("image")));

                    objects.add(category);
                }
            } else {
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                out = new OutputStreamWriter(connection.getOutputStream());
                out.write(createJson(gridType, idCategory).toString());
                out.flush();
                out.close();

                jsonArray = new JSONArray(getJsonString(connection));
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json = (JSONObject) jsonArray.get(i);

                    Book book = new Book();
                    book.setName(json.getString("name"));
                    book.setIdentifier(json.getLong("id"));
                    book.setImage(DataUtil.getBitMapByBase64(json.getString("image")));

                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return objects;
    }

    private String getJsonString(HttpURLConnection connection) throws IOException {
        InputStreamReader in = new InputStreamReader(connection.getInputStream());
        BufferedReader reader = new BufferedReader(in);
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        return sb.toString();

    }

    @Override
    protected void onPostExecute(List objects) {
        super.onPostExecute(objects);
        adapt = new CategoriesAdapt(context, objects);
        gridView.setAdapter(adapt);
        adapt.notifyDataSetChanged();
    }

    private JSONObject createJson(Short gridType, Short idCategory) throws JSONException {
        JSONObject jsonLogin = new JSONObject();
        jsonLogin.put("gridType", gridType);
        jsonLogin.put("idCategory", idCategory);
        return jsonLogin;
    }


}
