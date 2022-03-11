package com.example.kisileruygulamasi.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.kisileruygulamasi.entity.CRUDCevap
import com.example.kisileruygulamasi.entity.Kisiler
import com.example.kisileruygulamasi.entity.KisilerCevap
import com.example.kisileruygulamasi.retrofit.ApiUtils
import com.example.kisileruygulamasi.retrofit.KisilerDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KisilerDaoRepository {
    var kisilerListesi:MutableLiveData<List<Kisiler>>
    var kdao : KisilerDaoInterface

    init {
        kdao = ApiUtils.getKisilerDaoInterface()
        kisilerListesi = MutableLiveData()
    }

    fun kisileriGetir() : MutableLiveData<List<Kisiler>> {
        return kisilerListesi
    }

    fun kisiKayit(kisi_ad:String,kisi_tel:String){
        kdao.kisiEkle(kisi_ad,kisi_tel).enqueue(object : Callback<CRUDCevap>{
            override fun onResponse(call: Call<CRUDCevap>?, response: Response<CRUDCevap>?) {}
            override fun onFailure(call: Call<CRUDCevap>?, t: Throwable?) {}
        })
    }

    fun kisiGuncelle(kisi_id:Int,kisi_ad:String,kisi_tel:String){
        kdao.kisiGuncelle(kisi_id,kisi_ad,kisi_tel).enqueue(object : Callback<CRUDCevap>{
            override fun onResponse(call: Call<CRUDCevap>?, response: Response<CRUDCevap>?) {}
            override fun onFailure(call: Call<CRUDCevap>?, t: Throwable?) {}
        })
    }

    fun kisiAra(aramaKelimesi:String){
        kdao.kisiAra(aramaKelimesi).enqueue(object : Callback<KisilerCevap>{
            override fun onResponse(call: Call<KisilerCevap>, response: Response<KisilerCevap>) {
                val liste = response.body().kisiler
                kisilerListesi.value = liste
            }
            override fun onFailure(call: Call<KisilerCevap>?, t: Throwable?) {}
        })
    }

    fun kisiSil(kisi_id:Int){
        kdao.kisiSil(kisi_id).enqueue(object : Callback<CRUDCevap>{
            override fun onResponse(call: Call<CRUDCevap>?, response: Response<CRUDCevap>?) {
                tumKisileriAl()
            }
            override fun onFailure(call: Call<CRUDCevap>?, t: Throwable?) {}
        })
    }

    fun tumKisileriAl(){
        kdao.tumKisiler().enqueue(object : Callback<KisilerCevap>{
            override fun onResponse(call: Call<KisilerCevap>, response: Response<KisilerCevap>) {
                val liste = response.body().kisiler
                kisilerListesi.value = liste
            }
            override fun onFailure(call: Call<KisilerCevap>?, t: Throwable?) {}
        })
    }
}