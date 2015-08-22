package abk.activities;

import abk.utilities.Constants;
import abk.utilities.LoginService;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;


public class SignUpPassword extends Activity implements View.OnClickListener {
    private String name;
    private String email;

    private ImageButton btnSignUp;
    private EditText pass;
    private EditText confirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_password_act);
        initFields();
        initListeners();
    }


    private void initFields() {
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");
        email = bundle.getString("email");

        btnSignUp = (ImageButton) findViewById(R.id.btnSignUp);
        pass = (EditText) findViewById(R.id.txtPass);
        confirmPass = (EditText) findViewById(R.id.txtConfirmPass);

        btnSignUp.setEnabled(false);
    }

    private void initListeners() {
        btnSignUp.setOnClickListener(this);

        confirmPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (pass.getText().length() > 6 && charSequence.toString().equals(pass.getText().toString())) {
                    btnSignUp.setAlpha(1f);
                    btnSignUp.setEnabled(true);
                } else {
                    btnSignUp.setAlpha(0.4f);
                    btnSignUp.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.signup_password_menu, menu);
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
        if (view.equals(btnSignUp)) {
            LoginService loginService = new LoginService(getApplicationContext(), Constants.URL_SIGN_UP);
            loginService.execute(name, email, pass.getText().toString());
        }
    }
}
