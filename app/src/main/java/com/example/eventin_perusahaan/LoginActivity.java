package com.example.eventin_perusahaan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.eventin_perusahaan.Model.ModelPerusahaan;
import com.example.eventin_perusahaan.Module.DB_Init;
import com.example.eventin_perusahaan.Module.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText etEmailLog, etPwLog;
    ImageButton btnLog;

    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmailLog = findViewById(R.id.etEmailLog);
        etPwLog = findViewById(R.id.etPwLog);

//        Cek Session
        session = new SessionManager(getApplicationContext());
        if(session.isLoggedIn())
        {
            finish();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }

        btnLog = findViewById(R.id.btnLog);
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

    }

    private void userLogin()
    {
        String id = etEmailLog.getText().toString().toUpperCase().trim();
        String password = etPwLog.getText().toString();

        if(id.isEmpty()){
            etEmailLog.setError("Dibutuhkan Email");
            etEmailLog.requestFocus();
            return;
        }

        if(password.isEmpty())
        {
            etPwLog.setError("Dibutuhkan Password");
            etPwLog.requestFocus();
            return;
        }

        try
        {
            DB_Init db = new DB_Init();
            Call<ModelPerusahaan> call = db.getDb().cekLogin(id, password);

            call.enqueue(new Callback<ModelPerusahaan>() {
                @Override
                public void onResponse(Call<ModelPerusahaan> call, Response<ModelPerusahaan> response) {
                    System.out.println("LOGIN BERHASIL : ID "+response.body().getID_PERUSAHAAN());
                    if (response.body().getCEK_DATA())
                    {
                        session.createLoginSession(response.body().getID_PERUSAHAAN(),response.body().getNAMA_PERUSAHAAN());
                        finish();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(LoginActivity.this, "Email atau Password salah!",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ModelPerusahaan> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e)
        {
            Toast.makeText(LoginActivity.this, e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}