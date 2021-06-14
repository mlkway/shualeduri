package com.raywenderlich.login_activit.ui.tvshowdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.raywenderlich.login_activit.databinding.TvShowsDetailFragmentBinding
import com.raywenderlich.login_activit.storage.SavedMovies

class TvShowsDetailFragment : Fragment() {


    private val viewModel:  TvShowsDetailViewModel by viewModels()

    private  var binding: TvShowsDetailFragmentBinding? = null

    private lateinit var adapter: SeasonAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("requestTv"){ requestKey, bundle ->

            val tvShow = bundle.getParcelable<SavedMovies>("bundleKey")
            viewModel.getMoviesFromDataBase()
            viewModel.loadShow(tvShow!!.id)
            viewModel.checker(tvShow)

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (binding == null){
            viewModel.getMoviesFromDataBase()
            binding = TvShowsDetailFragmentBinding.inflate(inflater,container,false)
            initRecycler()
            init()
            buttonListener()

        }

        return binding?.root
    }


    private fun init(){

        viewModel.tvShowDetails.observe(viewLifecycleOwner){

            binding?.apply {
                titleTextView.text = it.name
                try {

                    Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w200/"+it.posterPath)
                        .into(showPoster)

                    Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w200/"+it.createdBy[0]?.profilePath)
                        .into(executivePoster)

                    directorTxt.text = it.createdBy[0]?.name
                    Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w200/"+it.networks[0]?.logoPath)
                        .into(companyLogo)


                }catch (e: IndexOutOfBoundsException){

                }
                companiNameTxt.text = it.networks[0]?.name
                overvievTxt.text = it.overview
                adapter.data = it.seasons
            }

        }
        viewModel.exsistent.observe(viewLifecycleOwner){
            binding?.button2?.text = if (it) "Delete" else "Save"
        }


    }

    private fun buttonListener(){
        viewModel.getMoviesFromDataBase()
        binding?.button2?.setOnClickListener {
            if (viewModel.exsistent.value!!){
                viewModel.deleteMovies(viewModel.currentMovie.value!!)
            }else{
                viewModel.addMovies(viewModel.currentMovie.value!!)
            }
        }
    }



    private fun initRecycler(){
        adapter = SeasonAdapter()
        binding?.apply {
            episodeRecyclerview.layoutManager =
                GridLayoutManager(requireContext(),1,GridLayoutManager.HORIZONTAL,false)

            episodeRecyclerview.adapter = adapter

        }
    }

}