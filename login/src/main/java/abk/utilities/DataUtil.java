package abk.utilities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Patterns;

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

}
