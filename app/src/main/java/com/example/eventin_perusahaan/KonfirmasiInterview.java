package com.example.eventin_perusahaan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventin_perusahaan.Model.ModelAjuan;
import com.example.eventin_perusahaan.Model.ModelJenisSponsor;
import com.example.eventin_perusahaan.Module.DB_Init;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KonfirmasiInterview extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView tv_pengaju, tv_penyelenggara, tv_tgl;
    TextView sts_idAjuan, sts_jenisAjuan, sts_noMinal, sts_Keterangan;

    TextView tvNominal, backMenu;
    EditText etNominal, et_keterangan;

    List<String> jenisAjuan = new ArrayList<String>();

    DB_Init db;

    ImageButton btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_interview);

        ModelAjuan event = getIntent().getParcelableExtra("EVENT");

        tv_pengaju = findViewById(R.id.tv_pengaju);
        tv_penyelenggara = findViewById(R.id.tv_penyelenggara);
        tv_tgl = findViewById(R.id.tv_tgl);

        sts_idAjuan = findViewById(R.id.sts_idAjuan);
        sts_jenisAjuan = findViewById(R.id.sts_jenisAjuan);
        sts_noMinal = findViewById(R.id.sts_noMinal);
        sts_Keterangan = findViewById(R.id.sts_Keterangan);

        et_keterangan = findViewById(R.id.et_keterangan);

        backMenu = findViewById(R.id.backMenu);
        backMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_pengaju.setText(event.getNAMA_PENGAJU());
        tv_penyelenggara.setText(event.getLEMBAGA_PENGAJU());
        tv_tgl.setText(event.getTGL_AJUAN());

        sts_idAjuan.setText(event.getID_AJUAN());
        sts_jenisAjuan.setText(event.getNAMA_JENIS());
        sts_noMinal.setText(event.getNOMINAL_AJUAN());
        sts_Keterangan.setText(event.getKETERANGAN_AJUAN());

        Spinner spin = (Spinner) findViewById(R.id.spinner_jenis);
        spin.setOnItemSelectedListener(this);
        db = new DB_Init();

        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(KonfirmasiInterview.this);
                dialogBuilder.setTitle("Konfirmasi Interview Event").setMessage("Apakah anda yakin untuk mengkonfirmasi Interview Event.").setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String keterangan = et_keterangan.getText().toString();
                        if(keterangan.isEmpty())
                        {
                            Toast.makeText(KonfirmasiInterview.this, "Keterangan harus diisi.", Toast.LENGTH_LONG).show();
                            et_keterangan.requestFocus();
                            return;
                        }
                        Call<ResponseBody> call = db.getDb().konfirmasiInterview(event.getID_AJUAN(), event.getID_KONFIRMASI(), keterangan, etNominal.getText().toString(), spin.getSelectedItem().toString());
                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                Toast.makeText(KonfirmasiInterview.this, "Interview berhasil dikonfirmasi !", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(KonfirmasiInterview.this, MainActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                            }
                        });
                    }
                }).show();
            }
        });

        Call<List<ModelJenisSponsor>> getSpinner = db.getDb().getJenisSponsor();
        getSpinner.enqueue(new Callback<List<ModelJenisSponsor>>() {
            @Override
            public void onResponse(Call<List<ModelJenisSponsor>> call, Response<List<ModelJenisSponsor>> response) {
                for (int i = 0; i < response.body().size(); i++) {
                    jenisAjuan.add(response.body().get(i).getNAMA_JENIS());
                }

                ArrayAdapter aa = new ArrayAdapter(KonfirmasiInterview.this,android.R.layout.simple_spinner_item,jenisAjuan);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Setting the ArrayAdapter data on the Spinner
                spin.setAdapter(aa);
            }

            @Override
            public void onFailure(Call<List<ModelJenisSponsor>> call, Throwable t) {

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 3)
        {
            tvNominal = findViewById(R.id.tvnominal);
            etNominal = findViewById(R.id.enominal);
            tvNominal.setVisibility(View.VISIBLE);
            etNominal.setVisibility(View.VISIBLE);
        } else {
            tvNominal = findViewById(R.id.tvnominal);
            etNominal = findViewById(R.id.enominal);
            tvNominal.setVisibility(View.GONE);
            etNominal.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}