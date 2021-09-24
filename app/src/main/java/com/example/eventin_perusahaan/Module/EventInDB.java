package com.example.eventin_perusahaan.Module;

import com.example.eventin_perusahaan.Model.ModelAjuan;
import com.example.eventin_perusahaan.Model.ModelJenisSponsor;
import com.example.eventin_perusahaan.Model.ModelPerusahaan;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EventInDB
{
    @POST("/MDM_EVENTIN/ControllerAndroid/cekLoginPerusahaan")
    @FormUrlEncoded
    Call<ModelPerusahaan> cekLogin(@Field("idPerusahaan") String idPerusahaan, @Field("pwPerusahaan") String pwPerusahaan);

    @POST("/MDM_EVENTIN/ControllerAndroid/getAjuan")
    @FormUrlEncoded
    Call<ArrayList<ModelAjuan>> cekAjuan(@Field("idStatus") String idStatus, @Field("idPerusahaan") String idPerusahaan);

    @POST("/MDM_EVENTIN/ControllerAndroid/konfirmasiPengajuan")
    @FormUrlEncoded
    Call<ResponseBody> konfirmasiAjuan(@Field("idAjuan") String idAjuan, @Field("stsAjuan") String stsAjuan, @Field("tglKonfirmasi") String tglKonfirmasi, @Field("lokAjuan") String lokAjuan);

    @GET("/MDM_EVENTIN/ControllerAndroid/getjenisAjuan")
    Call<List<ModelJenisSponsor>> getJenisSponsor();

    @POST("/MDM_EVENTIN/ControllerAndroid/konfirmasiInterview")
    @FormUrlEncoded
    Call<ResponseBody> konfirmasiInterview (@Field("idAjuan")String idAjuan, @Field("idKonfirmasi")String idKonfirmasi, @Field("keteranganKonfirmasi") String keteranganKonfirmasi, @Field("nominalKonfirmasi")String nominalKonfirmasi, @Field("namaJenisK")String namaJenisK);
}
