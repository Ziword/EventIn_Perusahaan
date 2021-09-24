package com.example.eventin_perusahaan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventin_perusahaan.AjuanDetail;
import com.example.eventin_perusahaan.InterviewDetail;
import com.example.eventin_perusahaan.Model.ModelAjuan;
import com.example.eventin_perusahaan.R;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.util.ArrayList;

public class AdapterAjuanEvent extends RecyclerView.Adapter<AdapterAjuanEvent.MyViewHolder> {
    private ArrayList<ModelAjuan> items;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView namaPengaju, namaPenyelenggara, tglAjuan, thnAjuan;
        public MyViewHolder(View itemView) {
            super(itemView);

            namaPengaju = itemView.findViewById(R.id.namaPengaju);
            namaPenyelenggara = itemView.findViewById(R.id.namaPenyelenggara);
            tglAjuan = itemView.findViewById(R.id.tglAjuan);
            thnAjuan = itemView.findViewById(R.id.thnAjuan);
        }
    }

    public void setItems(ArrayList<ModelAjuan> items) {
        this.items = items;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public AdapterAjuanEvent.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterAjuanEvent.MyViewHolder holder, int position) {
        ModelAjuan obj = items.get(position);
        holder.namaPengaju.setText(obj.getNAMA_PENGAJU());
        holder.namaPenyelenggara.setText(obj.getJUDUL_AJUAN());
        holder.tglAjuan.setText(obj.getTGL_AJUAN());
        try {
            holder.thnAjuan.setText(obj.getTahun());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(obj.getID_STATUS().equalsIgnoreCase("1"))
        {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, InterviewDetail.class);
                    i.putExtra("EVENT", obj);
                    context.startActivity(i);
                }
            });
        }
        else {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, AjuanDetail.class);
                    i.putExtra("EVENT", obj);
                    context.startActivity(i);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
