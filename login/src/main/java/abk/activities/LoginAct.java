package abk.activities;

import abk.utilities.LoginService;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginAct extends Activity implements View.OnClickListener {

    private Button btnLogin;
    private EditText login;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_atc);
        initFields();
    }

    private void initFields() {
        btnLogin = (Button) findViewById(R.id.btnLogin);
        login = (EditText) findViewById(R.id.txtLogin);
        password = (EditText) findViewById(R.id.txtPassword);

        btnLogin.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_menu, menu);
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
        if (view.equals(btnLogin)) {
            Toast.makeText(getApplicationContext(), "Tentando Login", Toast.LENGTH_SHORT).show();
            new LoginService(getApplicationContext()).execute(login.getText().toString(), password.getText().toString());
        }
    }
}
