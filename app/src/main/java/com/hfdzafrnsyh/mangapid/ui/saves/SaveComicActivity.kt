package com.hfdzafrnsyh.mangapid.ui.saves

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfdzafrnsyh.mangapid.databinding.ActivitySaveComicBinding
import com.hfdzafrnsyh.mangapid.source.ViewModelFactory
import com.hfdzafrnsyh.mangapid.source.model.local.ComicFavorite
import com.hfdzafrnsyh.mangapid.source.model.local.Data
import com.hfdzafrnsyh.mangapid.ui.adapter.BookmarkListAdapter
import com.hfdzafrnsyh.mangapid.ui.detail.InfoActivity

class SaveComicActivity : AppCompatActivity(), BookmarkListAdapter.ItemAdapterClickCallback {

    private lateinit var activitySaveComicViewBinding: ActivitySaveComicBinding
    private lateinit var bookmarkViewModel: BookmarkViewModel
    private lateinit var bookmarkListAdapter: BookmarkListAdapter
     private var comicFavorite : ArrayList<ComicFavorite> = arrayListOf()

    companion object{
        const val COMIC_FAVORITE = "COMIC_FAVORITE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySaveComicViewBinding = ActivitySaveComicBinding.inflate(layoutInflater)
        setContentView(activitySaveComicViewBinding.root)


        bookmarkViewModel= ViewModelProvider(this , ViewModelFactory(this))[BookmarkViewModel::class.java]

        supportActionBar!!.title="Saves"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        if(savedInstanceState != null){
            val comic = savedInstanceState.get(COMIC_FAVORITE)
            showListFavoriteAdapter(comic as List<ComicFavorite>)

        }

        initView()

    }


    private fun initView(){
        bookmarkViewModel.getFavorite().observe(this){
            comicFavorite.addAll(it)
            showListFavoriteAdapter(it)

        }
    }

    private fun showListFavoriteAdapter(comic : List<ComicFavorite>){
        bookmarkListAdapter = BookmarkListAdapter(comic , this)
        activitySaveComicViewBinding.rvBookmarkFavorite.setHasFixedSize(true)
        activitySaveComicViewBinding.rvBookmarkFavorite.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL , false)
        activitySaveComicViewBinding.rvBookmarkFavorite.adapter = bookmarkListAdapter
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelableArrayList(COMIC_FAVORITE , comicFavorite)
    }

    override fun onClickComic(comic: ComicFavorite) {
        val comics = Data(comic.title!!, comic.thumbnail!!, null, comic.type!!, comic.endpoint!!)
        val intent = Intent(this , InfoActivity::class.java)
        intent.putExtra(InfoActivity.INFO_COMIC, comics)
        startActivity(intent)
    }

}