package com.ike.wingsshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static boolean VISIBLE_PASSWORD = false;
    private EditText username, password;
    private Button button_sign_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.edit_username);
        password = (EditText) findViewById(R.id.edit_password);
        button_sign_in = (Button) findViewById(R.id.button_sign_in);

        this.button_sign_in.setOnClickListener(this);
        this.password.setOnTouchListener(eyeTouch);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_sign_in: {
                if (username.getText().toString().equals("") || username.getText().toString().equals(null)) {
                    username.setError("Username tidak boleh kosong");
                } if (password.getText().toString().equals("") || password.getText().toString().equals(null)) {
                    password.setError("Password tidak boleh kosong");
                } else {
                    Intent i = new Intent(this, MainActivity_List.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
            }
            break;
        }
    }

    private View.OnTouchListener eyeTouch = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            final int DRAWABLE_RIGHT = 2;

            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (password.getRight() - password.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    if (VISIBLE_PASSWORD) {
                        VISIBLE_PASSWORD = false;
                        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_visibility_off, 0);
                    } else {
                        VISIBLE_PASSWORD = true;
                        password.setInputType(InputType.TYPE_CLASS_TEXT);
                        password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_visibility, 0);
                    }
                    return false;
                }
            }
            return false;
        }
    };

}