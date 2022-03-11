package com.example.kisileruygulamasi.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.kisileruygulamasi.repo.KisilerDaoRepository

class KisiKayitFragmentViewModel : ViewModel() {
    val krepo = KisilerDaoRepository()

    fun kayit(kisi_ad:String,kisi_tel:String){
        krepo.kisiKayit(kisi_ad,kisi_tel)
    }
}