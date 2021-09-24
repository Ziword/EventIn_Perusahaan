package com.example.eventin_perusahaan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.eventin_perusahaan.Model.ModelAjuan;
import com.example.eventin_perusahaan.Module.DB_Init;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KonfirmasiApproval extends AppCompatActivity {
    TextView backMenu, tv_pengaju, tv_penyelenggara, tv_tgl;
    TextView sts_idAjuan, sts_jenisAjuan, sts_noMinal, sts_Keterangan;
    EditText et_TimeInterview,etlokasi;
    ImageButton btnSimpan;

    DB_Init db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_approval);

        db = new DB_Init();

        ModelAjuan event = getIntent().getParcelableExtra("EVENT");

        backMenu = findViewById(R.id.backMenu);
        backMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_pengaju = findViewById(R.id.tv_pengaju);
        tv_penyelenggara = findViewById(R.id.tv_penyelenggara);
        tv_tgl = findViewById(R.id.tv_tgl);
        sts_idAjuan = findViewById(R.id.sts_idAjuan);
        sts_jenisAjuan = findViewById(R.id.sts_jenisAjuan);
        sts_noMinal = findViewById(R.id.sts_noMinal);
        sts_Keterangan = findViewById(R.id.sts_Keterangan);

        tv_pengaju.setText(event.getNAMA_PENGAJU());
        tv_penyelenggara.setText(event.getLEMBAGA_PENGAJU());
        tv_tgl.setText(event.getTGL_AJUAN());
        sts_idAjuan.setText(event.getID_AJUAN());
        sts_jenisAjuan.setText(event.getNAMA_JENIS());
        sts_noMinal.setText(event.getNOMINAL_AJUAN());
        sts_Keterangan.setText(event.getKETERANGAN_AJUAN());

        et_TimeInterview = findViewById(R.id.et_TimeInterview);
        et_TimeInterview.setInputType(InputType.TYPE_NULL);
        et_TimeInterview.requestFocus();

        etlokasi = findViewById(R.id.etlokasi);

        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tglInterview = et_TimeInterview.getText().toString();
                String alamatInterview = etlokasi.getText().toString();

                if(tglInterview.isEmpty())
                {
                    Toast.makeText(KonfirmasiApproval.this, "Tanggal interview kosong.", Toast.LENGTH_SHORT).show();
                    et_TimeInterview.requestFocus();
                    return;
                }
                if(alamatInterview.isEmpty())
                {
                    Toast.makeText(KonfirmasiApproval.this, "Alamat interview kosong.", Toast.LENGTH_SHORT).show();
                    etlokasi.requestFocus();
                    return;
                }

                MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(KonfirmasiApproval.this);
                dialogBuilder.setTitle("Konfirmasi Event").setMessage("Apakah anda yakin untuk mengkonfirmasi event.").setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Call<ResponseBody> konfirmasi = db.getDb().konfirmasiAjuan(event.getID_AJUAN(), "1", et_TimeInterview.getText().toString(), etlokasi.getText().toString());
                        konfirmasi.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                Toast.makeText(KonfirmasiApproval.this, "Ajuan berhasil dikonfirmasi !", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(KonfirmasiApproval.this, MainActivity.class);
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
    }

    public void setDate(View view)
    {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        c.add(Calendar.DATE, -1);

//Set yesterday time milliseconds as date pickers minimum date
        DatePickerDialog datePickerDialog = new DatePickerDialog(KonfirmasiApproval.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                 int mHour = c.get(Calendar.HOUR_OF_DAY);
                 int mMinute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(KonfirmasiApproval.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        et_TimeInterview.setText(year+"-"+month+"-"+dayOfMonth+" "+hourOfDay+":"+minute+":"+"00");
                    }
                }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
        datePickerDialog.show();
    }
}