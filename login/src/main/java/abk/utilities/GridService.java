package abk.utilities;

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
    private GridView gridView;
    private CategoriesAdapt categoriesAdapt;

    public GridService(GridView gridView, Context context, String url, Short gridType) {
        this.url = url;
        this.context = context;
        this.gridView = gridView;
        this.gridType = gridType;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected List<Category> doInBackground(Void... voids) {
        List<Category> categories = new ArrayList<Category>();
        URL toConnect;
        HttpURLConnection connection;
        InputStreamReader in;
        OutputStreamWriter out;
        BufferedReader reader;
        try {
            toConnect = new URL(url);
            connection = (HttpURLConnection) toConnect.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);

            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");


            out = new OutputStreamWriter(connection.getOutputStream());
            out.write(gridType.toString());
            out.flush();
            out.close();


            in = new InputStreamReader(connection.getInputStream());
            reader = new BufferedReader(in);
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            line = sb.toString();
            JSONArray jsonArray = new JSONArray(line);
            if (gridType.equals(Constants.CATEGORY)) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json = (JSONObject) jsonArray.get(i);
                    Category category = new Category();
                    category.setIdentifier(json.getLong("id"));
                    category.setName(json.getString("name"));
                    category.setImage(DataUtil.getBitMapByBase64(json.getString("image")));

                    categories.add(category);
                }
            } else {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json = (JSONObject) jsonArray.get(i);


                }
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
        categoriesAdapt = new CategoriesAdapt(context, categories);
        gridView.setAdapter(categoriesAdapt);
        categoriesAdapt.notifyDataSetChanged();
    }
}