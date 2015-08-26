package abk.utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.widget.ImageView;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by edgar on 24/08/15.
 */
public class GridUtil extends AsyncTask<Void, Void, Bitmap> {
    private String url;
    private Context context;
    Bitmap bitmap;
    ImageView imageView;

    public GridUtil(String url, Context context, ImageView imageView) {
        this.url = url;
        this.context = context;
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
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
            JSONObject json = new JSONObject(line);
            String imagem = json.getString("image");
            byte[] decode = Base64.decode(imagem, Base64.DEFAULT);

            bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

}
