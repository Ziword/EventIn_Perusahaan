package com.example.eventin_perusahaan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.eventin_perusahaan.Adapter.AdapterAjuanEvent;
import com.example.eventin_perusahaan.Model.ModelAjuan;
import com.example.eventin_perusahaan.Module.DB_Init;
import com.example.eventin_perusahaan.Module.SessionManager;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    ImageButton btnAjuan, btnInterview;

    TextView tv_select;

    SessionManager session;

    private RecyclerView rv;
    private ArrayList<ModelAjuan> data = new ArrayList<>();
    private AdapterAjuanEvent adapterAjuanEvent;

    DB_Init db;
    HashMap<String, String> user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new SessionManager(MainActivity.this);
        user = session.getUserDetails();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        System.out.println("ID PERUSAHAAN : "+user.get(session.KEY_ID));

        tv_select = findViewById(R.id.tv_select);
        btnAjuan = findViewById(R.id.btnAjuan);
        btnAjuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetRV();
                tv_select.setText("Ajuan Baru");
                Call<ArrayList<ModelAjuan>> baru = db.getDb().cekAjuan("6", user.get(session.KEY_ID));
                baru.enqueue(new Callback<ArrayList<ModelAjuan>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ModelAjuan>> call, Response<ArrayList<ModelAjuan>> response) {
                        data = response.body();
                        if(data.size()>0)
                        {
                            adapterAjuanEvent.setContext(MainActivity.this);
                            adapterAjuanEvent.setItems(data);
                            rv.setAdapter(adapterAjuanEvent);
                            adapterAjuanEvent.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ModelAjuan>> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
            }
        });

        btnInterview = findViewById(R.id.btnInterview);
        btnInterview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetRV();
                tv_select.setText("Interview Ajuan");
                Call<ArrayList<ModelAjuan>> interview = db.getDb().cekAjuan("1", user.get(session.KEY_ID));
                interview.enqueue(new Callback<ArrayList<ModelAjuan>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ModelAjuan>> call, Response<ArrayList<ModelAjuan>> response) {
                        data = response.body();
                        if(data.size()>0)
                        {
                            adapterAjuanEvent.setContext(MainActivity.this);
                            adapterAjuanEvent.setItems(data);
                            rv.setAdapter(adapterAjuanEvent);
                            adapterAjuanEvent.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ModelAjuan>> call, Throwable t) {

                    }
                });
            }
        });

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_close, R.string.navigation_drawer_open);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        session = new SessionManager(MainActivity.this);

        rv = findViewById(R.id.rv_item);
        RecyclerView.LayoutManager layRv = new LinearLayoutManager(MainActivity.this);
        rv.setLayoutManager(layRv);
        rv.setItemAnimator(new DefaultItemAnimator());

        db = new DB_Init();
        adapterAjuanEvent = new AdapterAjuanEvent();

        getData();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.nav_utama:
                resetRV();
                tv_select.setText("Semua Ajuan");
                Call<ArrayList<ModelAjuan>> call = db.getDb().cekAjuan("",user.get(session.KEY_ID));
                call.enqueue(new Callback<ArrayList<ModelAjuan>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ModelAjuan>> call, Response<ArrayList<ModelAjuan>> response) {
                        data = response.body();
                        if(data.size()>0)
                        {
                            adapterAjuanEvent.setContext(MainActivity.this);
                            adapterAjuanEvent.setItems(data);
                            rv.setAdapter(adapterAjuanEvent);
                            adapterAjuanEvent.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ModelAjuan>> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;

            case R.id.nav_baru:
                resetRV();
                tv_select.setText("Ajuan Baru");
                Call<ArrayList<ModelAjuan>> baru = db.getDb().cekAjuan("6", user.get(session.KEY_ID));
                baru.enqueue(new Callback<ArrayList<ModelAjuan>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ModelAjuan>> call, Response<ArrayList<ModelAjuan>> response) {
                        data = response.body();
                        if(data.size()>0)
                        {
                            adapterAjuanEvent.setContext(MainActivity.this);
                            adapterAjuanEvent.setItems(data);
                            rv.setAdapter(adapterAjuanEvent);
                            adapterAjuanEvent.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ModelAjuan>> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;

            case R.id.nav_belumdiproses:
                resetRV();
                tv_select.setText("Ajuan Belum Diproses");
                Call<ArrayList<ModelAjuan>> belumdiproses = db.getDb().cekAjuan("3", user.get(session.KEY_ID));
                belumdiproses.enqueue(new Callback<ArrayList<ModelAjuan>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ModelAjuan>> call, Response<ArrayList<ModelAjuan>> response) {
                        data = response.body();
                        if(data.size()>0)
                        {
                            adapterAjuanEvent.setContext(MainActivity.this);
                            adapterAjuanEvent.setItems(data);
                            rv.setAdapter(adapterAjuanEvent);
                            adapterAjuanEvent.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ModelAjuan>> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;

            case R.id.nav_selesai:
                resetRV();
                tv_select.setText("Ajuan Selesai");
                Call<ArrayList<ModelAjuan>> selesai = db.getDb().cekAjuan("5", user.get(session.KEY_ID));
                selesai.enqueue(new Callback<ArrayList<ModelAjuan>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ModelAjuan>> call, Response<ArrayList<ModelAjuan>> response) {
                        data = response.body();
                        if(data.size()>0)
                        {
                            adapterAjuanEvent.setContext(MainActivity.this);
                            adapterAjuanEvent.setItems(data);
                            rv.setAdapter(adapterAjuanEvent);
                            adapterAjuanEvent.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ModelAjuan>> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;

            case R.id.nav_interview:
                resetRV();
                tv_select.setText("Interview Ajuan");
                Call<ArrayList<ModelAjuan>> interview = db.getDb().cekAjuan("1", user.get(session.KEY_ID));
                interview.enqueue(new Callback<ArrayList<ModelAjuan>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ModelAjuan>> call, Response<ArrayList<ModelAjuan>> response) {
                        data = response.body();
                        if(data.size()>0)
                        {
                            adapterAjuanEvent.setContext(MainActivity.this);
                            adapterAjuanEvent.setItems(data);
                            rv.setAdapter(adapterAjuanEvent);
                            adapterAjuanEvent.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ModelAjuan>> call, Throwable t) {

                    }
                });
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;

            case R.id.nav_ditolak:
                resetRV();
                tv_select.setText("Ajuan Ditolak");
                Call<ArrayList<ModelAjuan>> ditolak = db.getDb().cekAjuan("2", user.get(session.KEY_ID));
                ditolak.enqueue(new Callback<ArrayList<ModelAjuan>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ModelAjuan>> call, Response<ArrayList<ModelAjuan>> response) {
                        data = response.body();
                        if(data.size()>0)
                        {
                            adapterAjuanEvent.setContext(MainActivity.this);
                            adapterAjuanEvent.setItems(data);
                            rv.setAdapter(adapterAjuanEvent);
                            adapterAjuanEvent.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ModelAjuan>> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;

            case R.id.nav_approve:
                resetRV();
                tv_select.setText("Ajuan Diterima");
                Call<ArrayList<ModelAjuan>> diterima = db.getDb().cekAjuan("1", user.get(session.KEY_ID));
                diterima.enqueue(new Callback<ArrayList<ModelAjuan>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ModelAjuan>> call, Response<ArrayList<ModelAjuan>> response) {
                        data = response.body();
                        if(data.size()>0)
                        {
                            adapterAjuanEvent.setContext(MainActivity.this);
                            adapterAjuanEvent.setItems(data);
                            rv.setAdapter(adapterAjuanEvent);
                            adapterAjuanEvent.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ModelAjuan>> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;

            case R.id.nav_laporan:

                return true;

            case R.id.nav_logout:
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Anda yakin ingin keluar ?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                session.logoutUser();
                            }
                        })
                        .setNegativeButton("Tidak", null)
                        .create();
                dialog.show();
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
        }
        return true;
    }

    public void resetRV()
    {
        data.clear();
        adapterAjuanEvent.setContext(MainActivity.this);
        adapterAjuanEvent.setItems(data);
        adapterAjuanEvent.notifyDataSetChanged();
    }

    public void getData(){
        resetRV();
        tv_select.setText("Semua");
        Call<ArrayList<ModelAjuan>> baru = db.getDb().cekAjuan("",user.get(session.KEY_ID));
        baru.enqueue(new Callback<ArrayList<ModelAjuan>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelAjuan>> call, Response<ArrayList<ModelAjuan>> response) {
                data = response.body();
                if(data.size()>0)
                {
                    adapterAjuanEvent.setContext(MainActivity.this);
                    adapterAjuanEvent.setItems(data);
                    rv.setAdapter(adapterAjuanEvent);
                    adapterAjuanEvent.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ModelAjuan>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}