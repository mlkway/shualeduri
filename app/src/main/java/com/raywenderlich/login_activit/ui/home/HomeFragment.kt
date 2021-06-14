package com.raywenderlich.login_activit.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.login_activit.R
import com.raywenderlich.login_activit.databinding.FragmentHomeBinding
import com.raywenderlich.login_activit.storage.SavedMovies
import com.raywenderlich.login_activit.util.ImgListener
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {


    private var binding: FragmentHomeBinding? = null

    private val viewmodel: HomeViewModel by viewModels()

    private  val topMovieAdapter by lazy { MovieAdapter(object : ImgListener {
        override fun onClick(id: Int?, poster: String?, isMovies: Boolean?) {
            setAndGo(id,poster,isMovies)
        }

    }) }

    private  val popularMovieAdapter by lazy { MovieAdapter(object : ImgListener{
        override fun onClick(id: Int?, poster: String?, isMovies: Boolean?) {
            setAndGo(id,poster,isMovies)
        }
    }) }
    private  val upComingMovieAdapter by lazy { MovieAdapter(object : ImgListener{
        override fun onClick(id: Int?, poster: String?, isMovies: Boolean?) {
            setAndGo(id,poster,isMovies)
        }
    }) }
    private  val nowPlayingMovieAdapter by lazy { MovieAdapter(object : ImgListener{
        override fun onClick(id: Int?, poster: String?, isMovies: Boolean?) {
            setAndGo(id,poster,isMovies)
        }
    }) }




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        if(binding == null){
            binding = FragmentHomeBinding.inflate(inflater,container,false)
            makeAll()
        }

        return  binding?.root

    }




    private fun makeAll(){
        init(binding?.upcomingRecyclerview,upComingMovieAdapter)
        init(binding?.topRecyclerview,topMovieAdapter)
        init(binding?.popularRecyclerview,popularMovieAdapter)
        init(binding?.nowPlayingRecyclerview,nowPlayingMovieAdapter)
        loadData()
    }


    private fun loadData(){

        viewmodel.upcoming.observe(viewLifecycleOwner,{
            lifecycleScope.launchWhenCreated {
                upComingMovieAdapter.submitData(it)
            }
        })
        viewmodel.topRated.observe(viewLifecycleOwner,{
            lifecycleScope.launch {
                topMovieAdapter.submitData(it)
            }
        })
        viewmodel.popularData.observe(viewLifecycleOwner,{
            lifecycleScope.launch {
                popularMovieAdapter.submitData(it)
            }
        })
        viewmodel.nowPlaying.observe(viewLifecycleOwner,{
            lifecycleScope.launchWhenCreated {
                nowPlayingMovieAdapter.submitData(it)
            }
        })
    }



    private fun init(rec: RecyclerView?, mv: MovieAdapter){
        rec?.layoutManager = GridLayoutManager(context,1, GridLayoutManager.HORIZONTAL,false)
        rec?.adapter = mv
    }




    private fun setAndGo(id: Int?,poster:String?,isMOvie:Boolean?){
        val savedMovie = SavedMovies(id!!,poster,isMOvie!!)
        setFragmentResult("requestKey", bundleOf("bundleKey" to savedMovie))
        findNavController().navigate(R.id.action_homeFragment_to_detailFragmentScreen)
    }



}