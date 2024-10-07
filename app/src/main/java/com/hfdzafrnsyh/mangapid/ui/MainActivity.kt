package com.hfdzafrnsyh.mangapid.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.hfdzafrnsyh.mangapid.R
import com.hfdzafrnsyh.mangapid.databinding.ActivityMainBinding
import com.hfdzafrnsyh.mangapid.source.ViewModelFactory
import com.hfdzafrnsyh.mangapid.source.model.Wrapper
import com.hfdzafrnsyh.mangapid.source.model.local.Data
import com.hfdzafrnsyh.mangapid.ui.home.search.SearchViewModel
import com.hfdzafrnsyh.mangapid.ui.saves.SaveComicActivity

class MainActivity : AppCompatActivity(), View.OnClickListener, NavigationBarView.OnItemSelectedListener {


    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var navView : BottomNavigationView
    private lateinit var searchViewModel: SearchViewModel
    private var progressBar : Dialog?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        searchViewModel = ViewModelProvider(this , ViewModelFactory(this))[SearchViewModel::class.java]

        viewLoading()
        initView()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_options , menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView


        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_comic)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(title: String): Boolean {
                showLoading()
                searchViewModel.searchComic(title).observe(this@MainActivity){
                    when(it){
                        is Wrapper.Success -> {
                            dismissLoading()
                            toSearchFragment(it.data)
                        }
                        is Wrapper.Loading -> {
                            showLoading()
                        }
                        is Wrapper.Error -> {
                            dismissLoading()
                            showDialogNothing()
                        }
                    }
                }

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }


    private fun initView(){

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController,appBarConfiguration)



        navView = findViewById(R.id.nav_view)
        navView.background=null

        navView.setOnItemSelectedListener(this)
        activityMainBinding.fabBookmarks.setOnClickListener(this)



    }



    private fun toSearchFragment(data : List<Data>){


        searchViewModel.deleteComic()
        searchViewModel.setComic(data)

            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.fragment_home,true)
                .build()
            Navigation.findNavController(findViewById(R.id.nav_host_fragment))
                .navigate(R.id.fragment_search , null , navOptions)


    }




   private fun showDialogNothing(){
       AlertDialog.Builder(this@MainActivity)
           .setTitle("Popup")
           .setMessage("Comic tidak Ada")
           .setNegativeButton("Cancel") { dialog, _ ->
               dialog.cancel()
           }
           .create()
           .show()
   }

    @SuppressLint("InflateParams")
    private fun viewLoading(){
        progressBar = Dialog(this)
        val progressbarLayout = layoutInflater.inflate(R.layout.item_progress_bar , null)

        progressBar?.let {
            it.setContentView(progressbarLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }


    private fun showLoading() {
        progressBar?.show()
    }


    private fun dismissLoading() {
        progressBar?.dismiss()
    }


    override fun onDestroy() {
        super.onDestroy()
        dismissLoading()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home -> {
                item.isEnabled = true
                val intent = Intent(this , MainActivity::class.java)
                startActivity(intent)
                finishAffinity()


            }
            else -> item.isEnabled = false
        }
        return true
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.fab_bookmarks -> {
                val intent = Intent(this , SaveComicActivity::class.java)
                startActivity(intent)
            }
        }
    }



}