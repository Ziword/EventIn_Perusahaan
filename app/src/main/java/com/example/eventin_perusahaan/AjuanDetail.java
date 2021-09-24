package com.example.eventin_perusahaan;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventin_perusahaan.Model.ModelAjuan;
import com.example.eventin_perusahaan.Module.DB_Init;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AjuanDetail extends AppCompatActivity {
    ImageButton btnAcc, btnReject;
    TextView backMenu1;
    TextView tv_pengaju, tv_penyelenggara, tv_tgl;
    TextView sts_idAjuan, sts_jenisAjuan, sts_lokasi, sts_noHp1, sts_noHp2, sts_noMinal, sts_tglAcara, sts_DownloadFile, sts_keterangan;

    DB_Init db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuan_detail);

        db = new DB_Init();

        ModelAjuan event = getIntent().getParcelableExtra("EVENT");

        btnAcc = findViewById(R.id.btnAcc);
        btnReject = findViewById(R.id.btnReject);

//        Deklarasi Text
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
        sts_keterangan = findViewById(R.id.sts_Keterangan);

//        SET TEXT
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
        sts_keterangan.setText(event.getKETERANGAN_AJUAN());

        backMenu1 = findViewById(R.id.backMenu1);
        backMenu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if(event.getID_STATUS().equalsIgnoreCase("3") || event.getID_STATUS().equalsIgnoreCase("6"))
        {
            if(event.getID_STATUS().equalsIgnoreCase("6"))
            {
                Call<ResponseBody> ubahStatus = db.getDb().konfirmasiAjuan(event.getID_AJUAN(),"3","","");
                ubahStatus.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        } else {
            btnAcc.setVisibility(View.GONE);
            btnReject.setVisibility(View.GONE);
        }

        sts_DownloadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(DB_Init.DB_BASE_URL+"MDM_EVENTIN/storage/proposal_pengaju/"+event.getPROPOSAL_PENGAJU()));
                startActivity(browserIntent);
            }
        });

        btnAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AjuanDetail.this, KonfirmasiApproval.class);
                i.putExtra("EVENT", event);
                startActivity(i);
            }
        });

        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseBody> batal = db.getDb().konfirmasiAjuan(event.getID_AJUAN(),"2", "","");
                batal.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(AjuanDetail.this, "Ajuan berhasil dikonfirmasi !", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(AjuanDetail.this, MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });

    }
}