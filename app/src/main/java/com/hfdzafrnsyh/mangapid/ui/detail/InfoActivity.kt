package com.hfdzafrnsyh.mangapid.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import com.hfdzafrnsyh.mangapid.R
import com.hfdzafrnsyh.mangapid.databinding.ActivityInfoBinding
import com.hfdzafrnsyh.mangapid.source.model.local.Data


class InfoActivity : AppCompatActivity(){

    private lateinit var activityInfoBinding: ActivityInfoBinding

    private lateinit var comic : Data



    companion object{
        const val INFO_COMIC = "INFO_COMIC"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityInfoBinding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(activityInfoBinding.root)


        comic = intent?.getParcelableExtra(INFO_COMIC)!!

        toInfoFragment(comic)



    }

    private fun toInfoFragment(comic: Data){
         val bundle = Bundle()
        bundle.putString(InfoFragment.ENDPOINT_CHAPTER , comic.endpoint)
        Navigation.findNavController(findViewById(R.id.nav_info_fragment))
            .navigate(R.id.fragment_info , bundle)

    }




}