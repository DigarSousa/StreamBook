package abk.utilities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Patterns;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by edgar on 28/07/15.
 */
public class DataUtil {

    /**
     * Receive a mail char sequence to be verified
     * Return true if is a valid mail or returns false in negative case...
     *
     * @param email
     * @return Boolean
     */
    public static Boolean isValidMail(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    /**
     * Receive a base64 string image
     * Build and return a BitMap
     *
     * @param base64
     * @return BitMap
     */
    public static Bitmap getBitMapByBase64(String base64) {
        byte[] decode = Base64.decode(base64, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }

    /**
     * Receive a HttpURLConnection
     * Return a string value from the input stream...
     *
     * @param connection
     * @return
     * @throws IOException
     */
    public static String getInputString(HttpURLConnection connection) throws IOException {

        InputStreamReader in = new InputStreamReader(connection.getInputStream());
        BufferedReader reader = new BufferedReader(in);
        String line;
        StringBuilder sb = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        return sb.toString();

    }


}
