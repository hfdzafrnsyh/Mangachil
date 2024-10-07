package com.hfdzafrnsyh.mangapid.ui.home.search

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfdzafrnsyh.mangapid.databinding.FragmentSearchBinding
import com.hfdzafrnsyh.mangapid.source.ViewModelFactory
import com.hfdzafrnsyh.mangapid.source.model.local.Data
import com.hfdzafrnsyh.mangapid.ui.adapter.SearchComicListAdapter
import com.hfdzafrnsyh.mangapid.ui.detail.InfoActivity


class SearchFragment : Fragment(), SearchComicListAdapter.ItemAdapterClickCallback {

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var fragmentSearchBinding: FragmentSearchBinding
    private lateinit var comicListAdapter: SearchComicListAdapter
    private var comicSearch : ArrayList<Data> = arrayListOf()

    companion object{
        const val COMIC_SEARCH = "COMIC_SEARCH"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentSearchBinding = FragmentSearchBinding.inflate(layoutInflater, container, false)
       setUpOnBackPressed()
        return fragmentSearchBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel = ViewModelProvider(this , ViewModelFactory(requireContext()))[SearchViewModel::class.java]


        if(savedInstanceState != null){
            showComicList()
        }

        showComicList()


    }


     private fun setUpOnBackPressed(){
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner , object :
            OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                if (isEnabled){
                    isEnabled = false
                    requireActivity().finish()
                }
            }

        })
    }


    private fun showComicList(){

        searchViewModel.getComic().observe(viewLifecycleOwner) { comic ->

                comicListAdapter = SearchComicListAdapter(comic , this)
                fragmentSearchBinding.rvSearchComic.setHasFixedSize(true)
                fragmentSearchBinding.rvSearchComic.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL , false)
                fragmentSearchBinding.rvSearchComic.adapter = comicListAdapter

                comicSearch.addAll(comic)
        }



    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelableArrayList(COMIC_SEARCH, comicSearch)
    }

    override fun onClickComic(comic: Data) {
         val intent = Intent(activity , InfoActivity::class.java)
        intent.putExtra(InfoActivity.INFO_COMIC , comic)
        startActivity(intent)
    }



}