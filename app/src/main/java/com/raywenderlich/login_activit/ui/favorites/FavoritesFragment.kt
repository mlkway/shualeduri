package com.raywenderlich.login_activit.ui.favorites

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.raywenderlich.login_activit.R
import com.raywenderlich.login_activit.databinding.FavoritesFragmentBinding
import com.raywenderlich.login_activit.storage.SavedMovies
import com.raywenderlich.login_activit.util.ImgListener

class FavoritesFragment : Fragment() {



    private var binding: FavoritesFragmentBinding? = null

    private lateinit var adapter: FavoriteMoviesAdapter

    private val viewmodel: FavoritesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (binding == null){
            binding = FavoritesFragmentBinding.inflate(inflater,container,false)
            initRec()
        }



        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.getDatabase()
        viewmodel.favMoviesList.observe(viewLifecycleOwner){
            adapter.data = it
        }
    }


    private fun initRec(){
        viewmodel.getDatabase()
        adapter = FavoriteMoviesAdapter(object : ImgListener {
            override fun onClick(id: Int?, poster: String?, isMovies: Boolean?) {
                val currentMovie = SavedMovies(id!!,poster,isMovies!!)

                if (isMovies){
                    goToMovies(currentMovie)
                }else{
                    goToTvShows(currentMovie)
                }
            }

        })
        binding?.favMoviesRecyclerView?.layoutManager = GridLayoutManager(requireContext(),2)
        binding?.favMoviesRecyclerView?.adapter = adapter
        viewmodel.favMoviesList.observe(viewLifecycleOwner){
            adapter.data = it
        }


    }

    private fun goToMovies(savedMovies: SavedMovies){
        setFragmentResult("requestKey", bundleOf("bundleKey" to savedMovies))
        findNavController().navigate(R.id.action_favoritesFragment_to_detailFragmentScreen)
    }
    private fun goToTvShows(savedMovies: SavedMovies){
        setFragmentResult("requestTv", bundleOf("bundleKey" to savedMovies))
        findNavController().navigate(R.id.action_favoritesFragment_to_tvShowsDetailFragment)
    }






}