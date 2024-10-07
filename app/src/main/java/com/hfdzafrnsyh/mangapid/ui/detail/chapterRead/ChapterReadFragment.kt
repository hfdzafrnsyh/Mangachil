package com.hfdzafrnsyh.mangapid.ui.detail.chapterRead

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfdzafrnsyh.mangapid.BuildConfig
import com.hfdzafrnsyh.mangapid.R
import com.hfdzafrnsyh.mangapid.databinding.FragmentChapterReadBinding
import com.hfdzafrnsyh.mangapid.source.ViewModelFactory
import com.hfdzafrnsyh.mangapid.source.model.Wrapper
import com.hfdzafrnsyh.mangapid.source.model.local.info.chapter.ChapterComic
import com.hfdzafrnsyh.mangapid.source.model.local.info.chapter.ChapterRead
import com.hfdzafrnsyh.mangapid.ui.adapter.ChapterReadListAdapter


class ChapterReadFragment : Fragment(), View.OnClickListener {

    private lateinit var fragmentChapterReadBinding: FragmentChapterReadBinding
    private lateinit var chapterReadViewModel: ChapterReadViewModel
    private lateinit var chapterReadListAdapter: ChapterReadListAdapter
    private var progressBar : Dialog?=null
    private var comicData : ChapterRead? = null
    private var comic  : ChapterComic? = null

    companion object {
        const val COMIC_DATA = "COMIC_DATA"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentChapterReadBinding =
            FragmentChapterReadBinding.inflate(layoutInflater, container, false)
        return fragmentChapterReadBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chapterReadViewModel =
            ViewModelProvider(this, ViewModelFactory(requireContext()))[ChapterReadViewModel::class.java]


             comic = arguments?.getParcelable(COMIC_DATA)!!


        initView(comic)
        viewLoading()
    }

    private fun initView(comic: ChapterComic?) {

        val endpoint = "${BuildConfig.BASE_URL}comic/chapter${comic!!.endpoint}"



        chapterReadViewModel.getChapter().observe(viewLifecycleOwner){
          it.map { ch ->
                if(ch.id === comic.id){
                    fragmentChapterReadBinding.btnNextChapter.visibility = View.GONE

                }else{
                    fragmentChapterReadBinding.btnNextChapter.visibility = View.VISIBLE
                }
          }
        }


        fragmentChapterReadBinding.btnNextChapter.setOnClickListener(this)



        chapterReadViewModel.chapterRead(endpoint).observe(viewLifecycleOwner) {

            when (it) {
                is Wrapper.Loading -> {
                    showLoading()
                }
                is Wrapper.Success -> {
                    dismissLoading()
                    comicData = it.data
                    showChapterRead(it.data)

                }
                is Wrapper.Error -> {
                    dismissLoading()
                    showDialogError(it.msg)
                }
                else -> dismissLoading()
            }


        }

    }

    private fun showChapterRead(comic : ChapterRead){
        Toast.makeText(activity, " ${comic.title}", Toast.LENGTH_SHORT).show()

         chapterReadListAdapter = ChapterReadListAdapter(comic.image)

        fragmentChapterReadBinding.rvChapterComic.setHasFixedSize(true)
        fragmentChapterReadBinding.rvChapterComic.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL , false)
        fragmentChapterReadBinding.rvChapterComic.adapter = chapterReadListAdapter

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        Log.d("comicData" , comicData.toString())
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

 private fun chapterNext(){


        val id = comic!!.id + 1

            chapterReadViewModel.getChapterNext(id).observe(viewLifecycleOwner){
                comic = ChapterComic(
                    it.id,
                    it.name,
                    it.endpoint
                )

                val bundle = Bundle()
                bundle.putParcelable(context@ChapterReadFragment.COMIC_DATA, comic)
                Navigation.findNavController(activity?.findViewById(R.id.nav_info_fragment)!!)
                    .navigate(R.id.fragment_chapter_read,bundle)


            }
 }

    override fun onClick(view: View) {
        when(view.id){
            R.id.btn_nextChapter -> {

            chapterNext()

            }
        }

    }
}