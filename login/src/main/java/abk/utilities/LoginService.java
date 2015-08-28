package abk.utilities;

import abk.activities.CategoriesAct;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by edgar on 15/07/15.
 */
public class LoginService extends AsyncTask<String, Void, Short> {
    private Context context;
    private String httpUrl;
    private Short receive;

    public LoginService(Context context, String httpUrl) {
        this.context = context;
        this.httpUrl = httpUrl;
    }

    //todo:verificar conexão com o servidor e passar o retorno para o main frame...

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected Short doInBackground(String... strings) {
        String name = strings[0];
        String login = strings[1];
        String password = strings[2];

        HttpURLConnection connection;
        OutputStreamWriter out;
        InputStreamReader in;
        BufferedReader reader;

        URL url;
        try {

            url = new URL(httpUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");


            out = new OutputStreamWriter(connection.getOutputStream());

            out.write(createJson(name, login, password).toString());
            out.flush();
            out.close();

            String line;
            in = new InputStreamReader(connection.getInputStream());
            reader = new BufferedReader(in);
            StringBuilder sb = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            receive = new Short(sb.toString());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return receive;
    }

    @Override
    protected void onPostExecute(Short aShort) {
        super.onPostExecute(aShort);
        if (aShort == 1) {
            SharedPreferences settings = context.getSharedPreferences(Constants.SESSION_LOGIN, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean(Constants.IS_LOGGED, true).apply();
            Intent intent = new Intent(context, CategoriesAct.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
        }
    }

    /**
     * Cria um objeto json com o parâmetro login mapeado na chave login e o parâmetro password mapeado na chave password
     *
     * @param login:String
     * @param password:String
     * @return :Json
     * @throws JSONException
     */
    private JSONObject createJson(String name, String login, String password) throws JSONException {
        JSONObject jsonLogin = new JSONObject();
        jsonLogin.put("login", login);
        jsonLogin.put("password", password);
        jsonLogin.put("name", name);
        return jsonLogin;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}