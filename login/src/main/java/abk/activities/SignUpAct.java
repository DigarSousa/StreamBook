package abk.activities;

import abk.utilities.DataUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class SignUpAct extends Activity implements View.OnClickListener {
    private EditText name;
    private EditText email;
    private ImageView imgCheckMail;
    private TextView lblNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_act);
        initFields();
        initListeners();
    }

    private void initFields() {
        name = (EditText) findViewById(R.id.txtName);
        email = (EditText) findViewById(R.id.txtEmail);
        imgCheckMail = (ImageView) findViewById(R.id.imgCheckMail);
        lblNext = (TextView) findViewById(R.id.lblNext);

        lblNext.setOnClickListener(this);
        lblNext.setVisibility(View.INVISIBLE);
    }

    private void initListeners() {
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (DataUtil.isValidMail(charSequence)) {
                    imgCheckMail.setImageResource(R.drawable.ok_icon);
                    lblNext.setVisibility(View.VISIBLE);
                } else {
                    imgCheckMail.setImageResource(R.drawable.warning_icon);
                    lblNext.setVisibility(View.INVISIBLE);
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
        if (view.equals(lblNext)) {
            Intent it = new Intent(this, SignUpPassword.class);
            it.putExtra("email", email.getText().toString());
            it.putExtra("name", name.getText().toString());
            startActivity(it);
        }
    }
}
