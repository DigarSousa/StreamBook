package abk.utilities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by edgar on 15/07/15.
 */
public class LoginService extends AsyncTask<String, Void, Short> {
    private Context context;
    private ProgressDialog progress;
    private Short receive;

    public LoginService(Context context) {
        this.context = context;
    }

    @Override
    protected Short doInBackground(String... strings) {
        String login = strings[0];
        String password = strings[1];

        HttpURLConnection connection;
        OutputStreamWriter out;
        InputStreamReader in;
        BufferedReader reader;

        URL url;
        try {

            url = new URL("http://192.168.0.107/edgar/IdeaProjects/HttpLogin/login.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setConnectTimeout(5000);


            out = new OutputStreamWriter(connection.getOutputStream());

            out.write(createJson(login, password).toString());
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
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return receive;
    }

    /**
     * Cria um objeto json com o parâmetro login mapeado na chave login e o parâmetro password mapeado na chave password
     *
     * @param login:String
     * @param password:String
     * @return :Json
     * @throws JSONException
     */
    private JSONObject createJson(String login, String password) throws JSONException {
        JSONObject jsonLogin = new JSONObject();
        jsonLogin.put("login", login);
        jsonLogin.put("password", password);
        return jsonLogin;
    }
}