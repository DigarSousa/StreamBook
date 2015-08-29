package abk.activities;

import abk.utilities.Constants;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class StartAct extends Activity implements View.OnClickListener {
    private ImageButton btnLogin;
    private ImageButton btnSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*SharedPreferences prefs = getSharedPreferences(Constants.SESSION_LOGIN, 0);
        if (prefs != null) {
            if (prefs.getBoolean(Constants.IS_LOGGED, false)) {
                Intent it = new Intent(this, CategoriesAct.class);
                startActivity(it);
                this.finish();
            }
        }*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_act);
        initFiels();
    }

    private void initFiels() {
        btnLogin = (ImageButton) findViewById(R.id.btnStartLogin);
        btnSignUp = (ImageButton) findViewById(R.id.btnStartSignUp);

        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Intent it;
        if (view.equals(btnLogin)) {
            it = new Intent(this, LoginAct.class);
            startActivity(it);
        } else if (view.equals(btnSignUp)) {
            it = new Intent(this, SignUpAct.class);
            startActivity(it);
        }
    }
}
