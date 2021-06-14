package com.raywenderlich.login_activit.ui.tvShows

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.login_activit.R
import com.raywenderlich.login_activit.databinding.TvshowsFragmentBinding
import com.raywenderlich.login_activit.storage.SavedMovies
import com.raywenderlich.login_activit.util.ImgListener

class TvshowsFragment : Fragment() {



    private var binding: TvshowsFragmentBinding? = null

    private val topTvShowsAdapter by lazy { TvshowAdapter(object : ImgListener {


        override fun onClick(id: Int?, poster: String?, isMovies: Boolean?) {
            setAndGo(id,poster,isMovies)
        }

    }) }
    private val popularTvShowsAdapter by lazy { TvshowAdapter(object : ImgListener{
        override fun onClick(id: Int?, poster: String?, isMovies: Boolean?) {
            setAndGo(id,poster,isMovies)
        }

    }) }

    private val viewModel: TvshowsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (binding == null){
            binding = TvshowsFragmentBinding.inflate(inflater,container,false)
            makeAll()
        }

        return binding?.root
    }


    private fun makeAll(){
        init(binding?.popularRecyclerview,popularTvShowsAdapter)
        init(binding?.topRecyclerview,topTvShowsAdapter)
        loadData()
    }

    private fun loadData(){
        viewModel.popularData.observe(viewLifecycleOwner,{
            lifecycleScope.launchWhenCreated {
                popularTvShowsAdapter.submitData(it)
            }
        })
        viewModel.topRatedData.observe(viewLifecycleOwner,{
            lifecycleScope.launchWhenCreated {
                topTvShowsAdapter.submitData(it)
            }
        })
    }

    private fun setAndGo(id: Int?,poster:String?,isMOvie:Boolean?){
        val savedMovie = SavedMovies(id!!,poster,isMOvie!!)
        setFragmentResult("requestTv", bundleOf("bundleKey" to savedMovie))
        findNavController().navigate(R.id.action_tvshowsFragment_to_tvShowsDetailFragment)
    }

    private fun init(rec: RecyclerView?, mv: TvshowAdapter){
        rec?.layoutManager = GridLayoutManager(context,1, GridLayoutManager.HORIZONTAL,false)
        rec?.adapter = mv
    }





}