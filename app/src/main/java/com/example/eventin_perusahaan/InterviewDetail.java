package com.example.eventin_perusahaan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.eventin_perusahaan.Model.ModelAjuan;
import com.example.eventin_perusahaan.Module.DB_Init;

public class InterviewDetail extends AppCompatActivity {
    TextView tv_pengaju, tv_penyelenggara, tv_tgl;
    TextView sts_idAjuan, sts_jenisAjuan, sts_lokasi, sts_noHp1, sts_noHp2, sts_noMinal, sts_tglAcara, sts_DownloadFile, sts_LokasiInterview;
    TextView btnKonfirmasiInterview, backMenu2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview_detail);

        ModelAjuan event = getIntent().getParcelableExtra("EVENT");
        tv_pengaju = findViewById(R.id.tv_pengaju);
        tv_penyelenggara = findViewById(R.id.tv_penyelenggara);
        tv_tgl = findViewById(R.id.tv_tgl);

        sts_idAjuan = findViewById(R.id.sts_idAjuan);
        sts_jenisAjuan = findViewById(R.id.sts_jenisAjuan);
        sts_lokasi = findViewById(R.id.sts_lokasi);
        sts_noHp1 = findViewById(R.id.sts_noHp1);
        sts_noHp2 = findViewById(R.id.sts_noHp2);
        sts_noMinal = findViewById(R.id.sts_noMinal);
        sts_tglAcara = findViewById(R.id.sts_tglAcara);
        sts_DownloadFile = findViewById(R.id.sts_DownloadFile);
        sts_LokasiInterview = findViewById(R.id.sts_LokasiInterview);

        tv_pengaju.setText(event.getNAMA_PENGAJU());
        tv_penyelenggara.setText(event.getLEMBAGA_PENGAJU());
        tv_tgl.setText(event.getTGL_AJUAN());

        sts_idAjuan.setText(event.getID_AJUAN());
        sts_jenisAjuan.setText(event.getNAMA_JENIS());
        sts_lokasi.setText(event.getLOKASI_ACARA());
        sts_noHp1.setText(event.getNO_HP_UTAMA());
        sts_noHp2.setText(event.getNO_HP_ALT());
        sts_noMinal.setText(event.getNOMINAL_AJUAN());
        sts_tglAcara.setText(event.getTGL_MULAI_ACARA());
        sts_DownloadFile.setText(event.getPROPOSAL_PENGAJU());
        sts_LokasiInterview.setText(event.getLOKASI_WAWANCARA());

        sts_DownloadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(DB_Init.DB_BASE_URL+"MDM_EVENTIN/storage/proposal_pengaju/"+event.getPROPOSAL_PENGAJU()));
                startActivity(browserIntent);
            }
        });

        btnKonfirmasiInterview = findViewById(R.id.btnKonfirmasiInterview);
        btnKonfirmasiInterview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InterviewDetail.this, KonfirmasiInterview.class);
                i.putExtra("EVENT", event);
                startActivity(i);
            }
        });

        backMenu2 = findViewById(R.id.backMenu2);
        backMenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}