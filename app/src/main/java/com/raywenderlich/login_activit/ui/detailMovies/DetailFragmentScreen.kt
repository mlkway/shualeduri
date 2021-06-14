package com.raywenderlich.login_activit.ui.detailMovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.raywenderlich.login_activit.databinding.DetailFragmentScreenFragmentBinding
import com.raywenderlich.login_activit.storage.SavedMovies

class DetailFragmentScreen : Fragment() {

    private var binding: DetailFragmentScreenFragmentBinding? = null

    private val viewModel: DetailFragmentScreenViewModel by viewModels()

    private lateinit var logoAdapter: DetailAdapter
    private lateinit var castAdapter: CastAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("requestKey"){ requestKey, bundle ->
            val Movie = bundle.getParcelable<SavedMovies>("bundleKey")
            viewModel.getMoviesFromDataBase()
            viewModel.loadDetails(Movie!!.id)
            viewModel.checker(Movie)

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       if (binding == null){
           binding = DetailFragmentScreenFragmentBinding.inflate(inflater,container,false)
           viewModel.getMoviesFromDataBase()
           fillScreen()
           initCompany()
           initCast()
           buttonListener()

       }

        return binding?.root

    }













    private fun initCompany(){
        logoAdapter = DetailAdapter()
        binding?.companyRecyclerview?.layoutManager = GridLayoutManager(requireContext(),1,
            GridLayoutManager.HORIZONTAL,false)
        binding?.companyRecyclerview?.adapter = logoAdapter
        viewModel.movieDetails.observe(viewLifecycleOwner){
            logoAdapter.data = it.productionCompanies
        }
    }

    private fun initCast(){
        castAdapter = CastAdapter()
        binding?.castRecyclerView?.layoutManager =
            GridLayoutManager(
                requireContext(), 1, GridLayoutManager.HORIZONTAL, false)

        binding?.castRecyclerView?.adapter = castAdapter
        viewModel.castDetails.observe(viewLifecycleOwner){
            castAdapter.data = it
        }
    }








    private fun fillScreen(){
        viewModel.movieDetails.observe(viewLifecycleOwner){
            binding?.apply {
                titleText.text = it?.title
                Glide.with(this@DetailFragmentScreen).load("https://image.tmdb.org/t/p/w200/"+it.posterPath)
                    .into(movieImg)
                releaseDateTxt.text = it.releaseDate
                overVievTxt.text = it.overview
                budgetTxt.text = it.budget.toString()
                languageTxt.text = it.originalLanguage
                statusTxt.text = it.status
                tagLineTxt.text = it.tagline


            }
        }

        viewModel.exsistent.observe(viewLifecycleOwner){
            binding?.button?.text = if (it) "Delete" else "Save"
        }





    }

    private fun buttonListener(){
        viewModel.getMoviesFromDataBase()
        binding?.button?.setOnClickListener {
            if (viewModel.exsistent.value!!){
                viewModel.deleteMovies(viewModel.currentMovie.value!!)
            }else{
                viewModel.addMovies(viewModel.currentMovie.value!!)
            }
        }
    }



}