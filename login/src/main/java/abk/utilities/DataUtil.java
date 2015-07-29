package abk.utilities;

import android.util.Patterns;

/**
 * Created by edgar on 28/07/15.
 */
public class DataUtil {
    public static boolean isEmailValid(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
