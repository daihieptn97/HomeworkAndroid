package com.k14b.hieptran;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.k14b.hieptran.Database.DatabaseAccountConnect;
import com.k14b.hieptran.Main.MainActivity;

public class Login extends AppCompatActivity {
    private TextView btnRegister;
    private Button btnLogin;
    private EditText edtEmail, edtPassword;
    private Context context;
    private DatabaseAccountConnect db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle("Đăng nhập");
        init();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                context.startActivity(intent);
            }
        });

        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (isEmpty(edtEmail.getText().toString().trim(), edtPassword.getText().toString().trim())) {
//                    Account user = db.login(edtEmail.getText().toString().trim(), edtPassword.getText().toString().trim());
//                    if (user != null) {
//                        Toast.makeText(context, "xin chào " + user.getName(), Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(context, MainActivity.class);
//                        startActivity(intent);
//                    } else {
//                        Toast.makeText(context, "Tai khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });
    }

    private boolean isEmpty(String mail, String pass) {
        if (mail.length() <= 0) {
            edtEmail.setError("Tường này là bắt buộc ");
            return false;
        }
        if (pass.length() <= 0) {
            edtEmail.setError("Tường này là bắt buộc ");
            return false;
        }
        return true;
    }


    private void init() {
        context = this;
        btnRegister = findViewById(R.id.loginTxtRegister);
        btnLogin = findViewById(R.id.loginBtnLogin);
        edtEmail = findViewById(R.id.loginEdtUsername);
        edtPassword = findViewById(R.id.loginEdtPassword);
        db = new DatabaseAccountConnect(context);
    }


}