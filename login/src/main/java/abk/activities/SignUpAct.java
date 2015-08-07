package abk.activities;

import abk.utilities.Constants;
import abk.utilities.DataUtil;
import abk.utilities.LoginService;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;


public class SignUpAct extends Activity implements View.OnClickListener {
    private ImageButton signUp;
    private EditText name;
    private EditText email;
    private EditText pass;
    private EditText confirmPass;
    private CheckBox chkMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_act);
        initFields();
        initListeners();
    }

    private void initFields() {
        signUp = (ImageButton) findViewById(R.id.btnSignUP);
        name = (EditText) findViewById(R.id.txtName);
        email = (EditText) findViewById(R.id.txtEmail);
        pass = (EditText) findViewById(R.id.txtPass);
        confirmPass = (EditText) findViewById(R.id.txtConfirmPass);
        chkMail = (CheckBox) findViewById(R.id.chkMail);

        signUp.setEnabled(false);
        chkMail.setChecked(true);
        chkMail.setVisibility(View.INVISIBLE);
    }

    private void initListeners() {
        signUp.setOnClickListener(this);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (DataUtil.isEmailValid(charSequence)) {
                    signUp.setAlpha(1f);
                    signUp.setEnabled(true);

                    chkMail.setVisibility(View.VISIBLE);
                } else {
                    signUp.setEnabled(false);
                    signUp.setAlpha(0.4f);

                    chkMail.setVisibility(View.INVISIBLE);
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
        getMenuInflater().inflate(R.menu.signup_menu, menu);
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
        if (view.equals(signUp)) {
            LoginService loginService = new LoginService(getApplicationContext(), Constants.URL_SIGN_UP);
            loginService.execute(name.getText().toString(), email.getText().toString(), pass.getText().toString());
        }
    }
}
