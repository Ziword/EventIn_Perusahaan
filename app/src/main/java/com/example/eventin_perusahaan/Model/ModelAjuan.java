package com.example.eventin_perusahaan.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ModelAjuan implements Parcelable {
    String ID_AJUAN, ID_PENGAJU, ID_PERUSAHAAN, ID_STATUS, ID_JENIS, NO_HP_UTAMA, NO_HP_ALT, LEMBAGA_PENGAJU, JUDUL_AJUAN, TGL_MULAI_ACARA, TGL_AKHIR_ACARA, LOKASI_ACARA, PROPOSAL_PENGAJU, TGL_AJUAN, KETERANGAN_AJUAN, NOMINAL_AJUAN;
    String NAMA_PENGAJU, NO_TELP, EMAIL, ALAMAT_RUMAH, JENIS_KELAMIN, TANGGAL_DAFTAR;
    String NAMA_PERUSAHAAN, PROFIL_SINGKAT, VISI, WEBSITE_PERUSAHAAN, BIDANG_PERUSAHAAN, LOGO_PERUSAHAAN;
    String NAMA_STATUS;
    String NAMA_JENIS;
    String ID_KONFIRMASI, TGL_KONFIRMASI, TGL_WAWANCARA, LOKASI_WAWANCARA, KETERANGAN_KONFIRMASI, NOMINAL_KONFIRMASI, STATUS_INTERVIEW;

    Calendar calendar;

    public String getID_AJUAN() {
        return ID_AJUAN;
    }

    public String getID_PENGAJU() {
        return ID_PENGAJU;
    }

    public String getID_PERUSAHAAN() {
        return ID_PERUSAHAAN;
    }

    public String getID_STATUS() {
        return ID_STATUS;
    }

    public String getID_JENIS() {
        return ID_JENIS;
    }

    public String getNO_HP_UTAMA() {
        return NO_HP_UTAMA;
    }

    public String getNO_HP_ALT() {
        return NO_HP_ALT;
    }

    public String getLEMBAGA_PENGAJU() {
        return LEMBAGA_PENGAJU;
    }

    public String getJUDUL_AJUAN() {
        return JUDUL_AJUAN;
    }

    public String getTGL_MULAI_ACARA() {
        return TGL_MULAI_ACARA;
    }

    public String getTGL_AKHIR_ACARA() {
        return TGL_AKHIR_ACARA;
    }

    public String getLOKASI_ACARA() {
        return LOKASI_ACARA;
    }

    public String getPROPOSAL_PENGAJU() {
        return PROPOSAL_PENGAJU;
    }

    public String getTGL_AJUAN() {
        return TGL_AJUAN;
    }

    public String getKETERANGAN_AJUAN() {
        return KETERANGAN_AJUAN;
    }

    public String getNOMINAL_AJUAN() {
        return NOMINAL_AJUAN;
    }

    public String getNAMA_PENGAJU() {
        return NAMA_PENGAJU;
    }

    public String getNO_TELP() {
        return NO_TELP;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public String getALAMAT_RUMAH() {
        return ALAMAT_RUMAH;
    }

    public String getJENIS_KELAMIN() {
        return JENIS_KELAMIN;
    }

    public String getTANGGAL_DAFTAR() {
        return TANGGAL_DAFTAR;
    }

    public String getNAMA_PERUSAHAAN() {
        return NAMA_PERUSAHAAN;
    }

    public String getPROFIL_SINGKAT() {
        return PROFIL_SINGKAT;
    }

    public String getVISI() {
        return VISI;
    }

    public String getWEBSITE_PERUSAHAAN() {
        return WEBSITE_PERUSAHAAN;
    }

    public String getBIDANG_PERUSAHAAN() {
        return BIDANG_PERUSAHAAN;
    }

    public String getLOGO_PERUSAHAAN() {
        return LOGO_PERUSAHAAN;
    }

    public String getNAMA_STATUS() {
        return NAMA_STATUS;
    }

    public String getNAMA_JENIS() {
        return NAMA_JENIS;
    }

    public String getID_KONFIRMASI() {
        return ID_KONFIRMASI;
    }

    public String getTGL_KONFIRMASI() {
        return TGL_KONFIRMASI;
    }

    public String getTGL_WAWANCARA() {
        return TGL_WAWANCARA;
    }

    public String getLOKASI_WAWANCARA() {
        return LOKASI_WAWANCARA;
    }

    public String getKETERANGAN_KONFIRMASI() {
        return KETERANGAN_KONFIRMASI;
    }

    public String getNOMINAL_KONFIRMASI() {
        return NOMINAL_KONFIRMASI;
    }

    public String getSTATUS_INTERVIEW() {
        return STATUS_INTERVIEW;
    }


    public void dateFormatter() throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(getTGL_AJUAN());
        calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.setTime(date);
    }

    public String getTahun() throws ParseException {
        dateFormatter();
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ID_AJUAN);
        dest.writeString(this.ID_PENGAJU);
        dest.writeString(this.ID_PERUSAHAAN);
        dest.writeString(this.ID_STATUS);
        dest.writeString(this.ID_JENIS);
        dest.writeString(this.NO_HP_UTAMA);
        dest.writeString(this.NO_HP_ALT);
        dest.writeString(this.LEMBAGA_PENGAJU);
        dest.writeString(this.JUDUL_AJUAN);
        dest.writeString(this.TGL_MULAI_ACARA);
        dest.writeString(this.TGL_AKHIR_ACARA);
        dest.writeString(this.LOKASI_ACARA);
        dest.writeString(this.PROPOSAL_PENGAJU);
        dest.writeString(this.TGL_AJUAN);
        dest.writeString(this.KETERANGAN_AJUAN);
        dest.writeString(this.NOMINAL_AJUAN);
        dest.writeString(this.NAMA_PENGAJU);
        dest.writeString(this.NO_TELP);
        dest.writeString(this.EMAIL);
        dest.writeString(this.ALAMAT_RUMAH);
        dest.writeString(this.JENIS_KELAMIN);
        dest.writeString(this.TANGGAL_DAFTAR);
        dest.writeString(this.NAMA_PERUSAHAAN);
        dest.writeString(this.PROFIL_SINGKAT);
        dest.writeString(this.VISI);
        dest.writeString(this.WEBSITE_PERUSAHAAN);
        dest.writeString(this.BIDANG_PERUSAHAAN);
        dest.writeString(this.LOGO_PERUSAHAAN);
        dest.writeString(this.NAMA_STATUS);
        dest.writeString(this.NAMA_JENIS);
        dest.writeString(this.ID_KONFIRMASI);
        dest.writeString(this.TGL_KONFIRMASI);
        dest.writeString(this.TGL_WAWANCARA);
        dest.writeString(this.LOKASI_WAWANCARA);
        dest.writeString(this.KETERANGAN_KONFIRMASI);
        dest.writeString(this.NOMINAL_KONFIRMASI);
        dest.writeString(this.STATUS_INTERVIEW);
        dest.writeSerializable(this.calendar);
    }

    public void readFromParcel(Parcel source) {
        this.ID_AJUAN = source.readString();
        this.ID_PENGAJU = source.readString();
        this.ID_PERUSAHAAN = source.readString();
        this.ID_STATUS = source.readString();
        this.ID_JENIS = source.readString();
        this.NO_HP_UTAMA = source.readString();
        this.NO_HP_ALT = source.readString();
        this.LEMBAGA_PENGAJU = source.readString();
        this.JUDUL_AJUAN = source.readString();
        this.TGL_MULAI_ACARA = source.readString();
        this.TGL_AKHIR_ACARA = source.readString();
        this.LOKASI_ACARA = source.readString();
        this.PROPOSAL_PENGAJU = source.readString();
        this.TGL_AJUAN = source.readString();
        this.KETERANGAN_AJUAN = source.readString();
        this.NOMINAL_AJUAN = source.readString();
        this.NAMA_PENGAJU = source.readString();
        this.NO_TELP = source.readString();
        this.EMAIL = source.readString();
        this.ALAMAT_RUMAH = source.readString();
        this.JENIS_KELAMIN = source.readString();
        this.TANGGAL_DAFTAR = source.readString();
        this.NAMA_PERUSAHAAN = source.readString();
        this.PROFIL_SINGKAT = source.readString();
        this.VISI = source.readString();
        this.WEBSITE_PERUSAHAAN = source.readString();
        this.BIDANG_PERUSAHAAN = source.readString();
        this.LOGO_PERUSAHAAN = source.readString();
        this.NAMA_STATUS = source.readString();
        this.NAMA_JENIS = source.readString();
        this.ID_KONFIRMASI = source.readString();
        this.TGL_KONFIRMASI = source.readString();
        this.TGL_WAWANCARA = source.readString();
        this.LOKASI_WAWANCARA = source.readString();
        this.KETERANGAN_KONFIRMASI = source.readString();
        this.NOMINAL_KONFIRMASI = source.readString();
        this.STATUS_INTERVIEW = source.readString();
        this.calendar = (Calendar) source.readSerializable();
    }

    public ModelAjuan() {
    }

    protected ModelAjuan(Parcel in) {
        this.ID_AJUAN = in.readString();
        this.ID_PENGAJU = in.readString();
        this.ID_PERUSAHAAN = in.readString();
        this.ID_STATUS = in.readString();
        this.ID_JENIS = in.readString();
        this.NO_HP_UTAMA = in.readString();
        this.NO_HP_ALT = in.readString();
        this.LEMBAGA_PENGAJU = in.readString();
        this.JUDUL_AJUAN = in.readString();
        this.TGL_MULAI_ACARA = in.readString();
        this.TGL_AKHIR_ACARA = in.readString();
        this.LOKASI_ACARA = in.readString();
        this.PROPOSAL_PENGAJU = in.readString();
        this.TGL_AJUAN = in.readString();
        this.KETERANGAN_AJUAN = in.readString();
        this.NOMINAL_AJUAN = in.readString();
        this.NAMA_PENGAJU = in.readString();
        this.NO_TELP = in.readString();
        this.EMAIL = in.readString();
        this.ALAMAT_RUMAH = in.readString();
        this.JENIS_KELAMIN = in.readString();
        this.TANGGAL_DAFTAR = in.readString();
        this.NAMA_PERUSAHAAN = in.readString();
        this.PROFIL_SINGKAT = in.readString();
        this.VISI = in.readString();
        this.WEBSITE_PERUSAHAAN = in.readString();
        this.BIDANG_PERUSAHAAN = in.readString();
        this.LOGO_PERUSAHAAN = in.readString();
        this.NAMA_STATUS = in.readString();
        this.NAMA_JENIS = in.readString();
        this.ID_KONFIRMASI = in.readString();
        this.TGL_KONFIRMASI = in.readString();
        this.TGL_WAWANCARA = in.readString();
        this.LOKASI_WAWANCARA = in.readString();
        this.KETERANGAN_KONFIRMASI = in.readString();
        this.NOMINAL_KONFIRMASI = in.readString();
        this.STATUS_INTERVIEW = in.readString();
        this.calendar = (Calendar) in.readSerializable();
    }

    public static final Creator<ModelAjuan> CREATOR = new Creator<ModelAjuan>() {
        @Override
        public ModelAjuan createFromParcel(Parcel source) {
            return new ModelAjuan(source);
        }

        @Override
        public ModelAjuan[] newArray(int size) {
            return new ModelAjuan[size];
        }
    };
}
