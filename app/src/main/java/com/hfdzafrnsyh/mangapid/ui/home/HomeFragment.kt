package com.hfdzafrnsyh.mangapid.ui.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfdzafrnsyh.mangapid.R
import com.hfdzafrnsyh.mangapid.databinding.FragmentHomeBinding
import com.hfdzafrnsyh.mangapid.source.ViewModelFactory
import com.hfdzafrnsyh.mangapid.source.model.Wrapper
import com.hfdzafrnsyh.mangapid.source.model.local.Data
import com.hfdzafrnsyh.mangapid.ui.adapter.ComicListAdapter
import com.hfdzafrnsyh.mangapid.ui.adapter.PopularComicAdapter
import com.hfdzafrnsyh.mangapid.ui.detail.InfoActivity



class HomeFragment : Fragment(), PopularComicAdapter.ItemAdapterClickCallback,
    ComicListAdapter.ItemAdapterClickCallback {

    private lateinit var fragmentHomeBinding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var popularComicAdapter: PopularComicAdapter
    private lateinit var comicListAdapter: ComicListAdapter
    private var progressBar : Dialog?=null
    private var popularData : ArrayList<Data> = arrayListOf()
    private var comicData : ArrayList<Data> = arrayListOf()


    companion object{
        const val POPULAR_DATA="POPULAR_DATA"
        const val COMIC_DATA="COMIC_DATA"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle? ) {
        super.onViewCreated(view, savedInstanceState)

        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        homeViewModel = ViewModelProvider(this , ViewModelFactory(requireContext()))[HomeViewModel::class.java]


        initView()
        viewLoading()

        if(savedInstanceState != null){
            val popular = savedInstanceState.get(POPULAR_DATA)
            val comic = savedInstanceState.get(COMIC_DATA)

            showCardPopularAdapter(popular as List<Data>)
            showHomeAdapter(comic as List<Data>)
        }
    }


    private fun initView(){


        homeViewModel.getComicPopular().observe(viewLifecycleOwner){
            when(it){
                is Wrapper.Loading -> {
                showLoading()
                }
                is Wrapper.Success -> {
                    dismissLoading()

                    popularData.addAll(it.data)
                    showCardPopularAdapter(it.data)

                }
                is Wrapper.Error -> {
                dismissLoading()
                    showDialogError(it.msg)
                }
                else -> dismissLoading()
            }
        }

        homeViewModel.getComicList().observe(viewLifecycleOwner){
            when(it){
                is Wrapper.Loading ->{
                    showLoading()
                }
                is Wrapper.Success -> {
                    dismissLoading()
                    comicData.addAll(it.data)
                    showHomeAdapter(it.data)

                }
                is Wrapper.Error -> {
                    dismissLoading()
                    showDialogError(it.msg)
                }
                else -> dismissLoading()
            }
        }

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)


        outState.putParcelableArrayList(POPULAR_DATA , popularData)
        outState.putParcelableArrayList(COMIC_DATA, comicData)

    }


    private fun showCardPopularAdapter(comic : List<Data>){

        popularComicAdapter = PopularComicAdapter(comic ,this)

        fragmentHomeBinding.rvPopularList.setHasFixedSize(true)
        fragmentHomeBinding.rvPopularList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL , false)
        fragmentHomeBinding.rvPopularList.adapter = popularComicAdapter

    }

    private fun showHomeAdapter(comic : List<Data>){
        comicListAdapter = ComicListAdapter(comic, this)
        fragmentHomeBinding.rvHome.setHasFixedSize(true)
        fragmentHomeBinding.rvHome.layoutManager = GridLayoutManager(context , 2)
        fragmentHomeBinding.rvHome.adapter = comicListAdapter

    }

    override fun clickComic(comic: Data) {
        val intent = Intent(activity , InfoActivity::class.java)
        intent.putExtra(InfoActivity.INFO_COMIC , comic)
        startActivity(intent)

    }

    @SuppressLint("InflateParams")
    private fun viewLoading(){
        progressBar = Dialog(requireContext())
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



    private fun showDialogError(message : String?){
        AlertDialog.Builder(requireContext()).apply {
            setMessage(message)
            setTitle("Error")
            setNegativeButton("Cancel"
            ) { dialogInterface, _ ->
                dialogInterface.cancel()
                requireActivity().finish()
            }
            create()
            show()
        }
    }
    override fun onClickComic(comic: Data) {
        val intent = Intent(activity , InfoActivity::class.java)
        intent.putExtra(InfoActivity.INFO_COMIC , comic)
        startActivity(intent)

    }

}