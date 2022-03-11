package com.example.kisileruygulamasi.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kisileruygulamasi.R
import com.example.kisileruygulamasi.adapter.KisilerAdapter
import com.example.kisileruygulamasi.databinding.FragmentAnasayfaBinding
import com.example.kisileruygulamasi.entity.Kisiler
import com.example.kisileruygulamasi.viewmodel.AnasayfaFragmentViewModel

class AnasayfaFragment : Fragment() , SearchView.OnQueryTextListener {
    private lateinit var tasarim:FragmentAnasayfaBinding
    private lateinit var adapter:KisilerAdapter
    private lateinit var viewModel:AnasayfaFragmentViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_anasayfa, container, false)
        tasarim.anasayfaFragment = this
        tasarim.anasayfaToolbarBaslik = "Kişiler"
        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarAnasayfa)

        viewModel.kisilerListesi.observe(viewLifecycleOwner,{
            adapter = KisilerAdapter(requireContext(),it,viewModel)
            tasarim.kisilerAdapter = adapter
        })

        return tasarim.root
    }

    fun fabTikla(v:View){
        Navigation.findNavController(v).navigate(R.id.kisiKayitGecis)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel:AnasayfaFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu,menu)

        val item = menu.findItem(R.id.action_ara)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(query: String): Boolean {//Arama iconu tıklanıldığında çalışır.
        viewModel.ara(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {//Her harf girdikçe ve sildikçe çalışır.
        viewModel.ara(newText)
        return true
    }

    override fun onResume() {
        super.onResume()
        viewModel.kisileriYukle()
    }
}