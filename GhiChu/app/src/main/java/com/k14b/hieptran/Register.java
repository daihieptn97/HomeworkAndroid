package com.k14b.hieptran;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.k14b.hieptran.Database.Account.Account;
import com.k14b.hieptran.Database.Account.DatabaseAccountConnect;
import com.k14b.hieptran.Main.MainActivity;

public class Register extends AppCompatActivity {

    private EditText edtEmail, edtPassword, edtname, edtRePassword;
    private Button btnRegister;
    private TextView btnLogin;
    private DatabaseAccountConnect model;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context = this;
        mapping();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInputEmpty() && isPasswordSame()) {
                    Account account = new Account(0, edtname.getText().toString().trim(),
                            edtEmail.getText().toString().trim(),
                            edtRePassword.getText().toString().trim()
                    );

                    if (model.createAccount(account)) {
                        Toast.makeText(context, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }

    private boolean isPasswordSame() {
        if (!edtPassword.getText().toString().trim().equals(edtRePassword.getText().toString().trim())) {
            edtRePassword.setError("Mật khẩu không khớp");
            return false;
        }
        return true;
    }

    private boolean isInputEmpty() {

        if (edtEmail.getText().toString().trim().length() <= 0) {
            edtEmail.setError("Trường dữ liệu này bắt buộc");
            return false;
        }

        if (edtname.getText().toString().trim().length() <= 0) {
            edtname.setError("Trường dữ liệu này bắt buộc");
            return false;
        }

        if (edtRePassword.getText().toString().trim().length() <= 0) {
            edtRePassword.setError("Trường dữ liệu này bắt buộc");
            return false;
        }
        if (edtPassword.getText().toString().trim().length() <= 0) {
            edtPassword.setError("Trường dữ liệu này bắt buộc");
            return false;
        }

        return true;
    }

    private void mapping() {
        edtEmail = findViewById(R.id.registerEdtEmail);
        edtPassword = findViewById(R.id.registerEdtPassword);
        edtRePassword = findViewById(R.id.registerEdtRePassword);
        edtname = findViewById(R.id.registerEdtName);

        btnRegister = findViewById(R.id.registerBtnRegister);
        btnLogin = findViewById(R.id.registerBtnLogin);

        model = new DatabaseAccountConnect(context);
    }


}
