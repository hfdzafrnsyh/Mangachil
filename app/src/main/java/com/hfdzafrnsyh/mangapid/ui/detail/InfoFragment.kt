package com.hfdzafrnsyh.mangapid.ui.detail

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.util.rangeTo
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hfdzafrnsyh.mangapid.BuildConfig
import com.hfdzafrnsyh.mangapid.R
import com.hfdzafrnsyh.mangapid.databinding.FragmentInfoBinding
import com.hfdzafrnsyh.mangapid.source.ViewModelFactory
import com.hfdzafrnsyh.mangapid.source.model.Wrapper
import com.hfdzafrnsyh.mangapid.source.model.local.ComicFavorite
import com.hfdzafrnsyh.mangapid.source.model.local.info.InfoEntity
import com.hfdzafrnsyh.mangapid.source.model.local.info.chapter.ChapterComic
import com.hfdzafrnsyh.mangapid.ui.adapter.ChapterListAdapter
import com.hfdzafrnsyh.mangapid.ui.detail.chapterRead.ChapterReadFragment
import java.util.*
import kotlin.collections.ArrayList

class InfoFragment : Fragment(), ChapterListAdapter.ItemAdapterClickCallback {

    private lateinit var fragmentInfoBinding: FragmentInfoBinding
    private var progressBar : Dialog?=null
    private lateinit var infoViewModel: InfoViewModel
    private lateinit var chapterListAdapter : ChapterListAdapter
    private  var comicURL : String?=null

    companion object{
        const val ENDPOINT_CHAPTER = "ENDPOINT_CHAPTER"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentInfoBinding = FragmentInfoBinding.inflate(layoutInflater, container, false)
        setUpOnBackPressed()
        return fragmentInfoBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        infoViewModel= ViewModelProvider(this , ViewModelFactory(requireContext()))[InfoViewModel::class.java]

        comicURL = arguments?.getString(ENDPOINT_CHAPTER)


        initView()
        viewLoading()
    }


    private fun initView(){

        val endpoint ="${BuildConfig.BASE_URL}comic/info${comicURL}"

        infoViewModel.deleteChapter()


        infoViewModel.infoComic(endpoint).observe(viewLifecycleOwner){
            when(it){
                is Wrapper.Loading -> {
                    showLoading()
                }
                is Wrapper.Success -> {
                    dismissLoading()
                    showInfoView(it.data)

                }
                is Wrapper.Error -> {
                    dismissLoading()
                    showDialogError(it.msg)
                }
                else -> dismissLoading()
            }
        }


    }


    private fun setUpOnBackPressed(){
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner , object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
             if (isEnabled){
                 isEnabled = false
                 requireActivity().finish()
             }
            }

        })
    }

    @SuppressLint("SetTextI18n")
    private fun showInfoView(comic : InfoEntity){
        val imageData = comic.thumbnail
        Glide.with(this)
            .load(imageData)
            .apply(RequestOptions().centerCrop())
            .into(fragmentInfoBinding.ivThumbnail)


        fragmentInfoBinding.tvTitle.text = comic.title
        fragmentInfoBinding.tvType.text = comic.type
        fragmentInfoBinding.tvAuthor.text = comic.author
        fragmentInfoBinding.tvRating.text = comic.rating
        fragmentInfoBinding.tvStatus.text = comic.status
        comic.genre.map { genre ->
            fragmentInfoBinding.tvGenre.setBackgroundColor(Color.TRANSPARENT)
            fragmentInfoBinding.tvGenre.text = genre
        }


        fragmentInfoBinding.tvTitle.setBackgroundColor(Color.TRANSPARENT)
        fragmentInfoBinding.tvTextType.setBackgroundColor(Color.TRANSPARENT)
        fragmentInfoBinding.tvTextType.text ="Type:"
        fragmentInfoBinding.tvType.setBackgroundColor(Color.TRANSPARENT)
        fragmentInfoBinding.tvTextAuthor.setBackgroundColor(Color.TRANSPARENT)
        fragmentInfoBinding.tvTextAuthor.text ="Author:"
        fragmentInfoBinding.tvAuthor.setBackgroundColor(Color.TRANSPARENT)
        fragmentInfoBinding.tvTextStatus.setBackgroundColor(Color.TRANSPARENT)
        fragmentInfoBinding.tvTextStatus.text ="Status:"
        fragmentInfoBinding.tvStatus.setBackgroundColor(Color.TRANSPARENT)
        fragmentInfoBinding.tvTextRating.setBackgroundColor(Color.TRANSPARENT)
        fragmentInfoBinding.tvTextRating.text ="Rating:"
        fragmentInfoBinding.tvRating.setBackgroundColor(Color.TRANSPARENT)
        fragmentInfoBinding.tvTextGenre.setBackgroundColor(Color.TRANSPARENT)
        fragmentInfoBinding.tvTextGenre.text ="Genre:"



        setChapterListToDb(comic)
        showChapterList()
        showStatusFavorite(comic)

    }


    private fun setChapterListToDb(comic : InfoEntity){
        var chap = ArrayList<ChapterComic>()

        var c = comic.chapter_list
        var ind= 1;
        for(i in c.size - 1 downTo 0){

            val id = ind++
            var chapter = ChapterComic(
                id,
                c[i].name,
                c[i].endpoint
            )

            chap.add(chapter)

        }

        infoViewModel.setChapterComic(chap)

    }

    private fun showChapterList(){
        infoViewModel.getChapter().observe(viewLifecycleOwner){ comic ->
            chapterListAdapter = ChapterListAdapter(comic , this)
            fragmentInfoBinding.rvChapterComic.setHasFixedSize(true)
            fragmentInfoBinding.rvChapterComic.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL , false)
            fragmentInfoBinding.rvChapterComic.adapter = chapterListAdapter
        }
    }



    private fun showStatusFavorite(comic : InfoEntity){

        val id = UUID.randomUUID()
        val comicData = ComicFavorite(
            id.toString(),
            comic.thumbnail,
            comic.title,
            comic.type,
            comicURL,
            true
        )

        setStatusFavorite(comic,comicData)
    }

    private fun setStatusFavorite(comic : InfoEntity , comicfav : ComicFavorite){
        infoViewModel.getStatusFavoriteComic(comic.title).observe(viewLifecycleOwner){ favorite ->
            if(favorite != null){
                fragmentInfoBinding.icSave.setOnClickListener{
                    infoViewModel.deleteFavorite(favorite.id)
                }
                fragmentInfoBinding.icSave.setBackgroundResource(R.drawable.ic_baseline_bookmark_unsave)
            }else{
                fragmentInfoBinding.icSave.setOnClickListener{
                    infoViewModel.setFavoriteComic(comicfav)
                }

                fragmentInfoBinding.icSave.setBackgroundResource(R.drawable.ic_baseline_bookmark_save)
            }
        }
    }

    override fun onClickChapter(comic: ChapterComic) {

        val bundle = Bundle()
//        bundle.putString(ChapterReadFragment.ENDPOINT_COMIC ,comic.endpoint)
        bundle.putParcelable(ChapterReadFragment.COMIC_DATA, comic)
        Navigation.findNavController(activity?.findViewById(R.id.nav_info_fragment)!!)
            .navigate(R.id.fragment_chapter_read,bundle)


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
                requireActivity().onBackPressed()
            }
            create()
            show()
        }
    }



}


