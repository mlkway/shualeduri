package com.raywenderlich.login_activit.ui.navHome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.raywenderlich.login_activit.R
import com.raywenderlich.login_activit.databinding.FragmentNavHomeBinding


class NavHomeFragment : Fragment(), BottomNavigationView.OnNavigationItemSelectedListener {


    private var binding: FragmentNavHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNavHomeBinding.inflate(inflater,container,false)

        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.homeHavTabBar?.setOnNavigationItemSelectedListener(this)
        Navigation.findNavController(binding?.homeNavContainer!!)
            .addOnDestinationChangedListener { controller, destination, arguments ->
                binding?.homeHavTabBar?.setOnNavigationItemSelectedListener(null)
                binding?.homeHavTabBar?.selectedItemId = destination.id
                binding?.homeHavTabBar?.setOnNavigationItemSelectedListener(this@NavHomeFragment)
            }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val controller = Navigation.findNavController(binding?.homeNavContainer!!)
        when(item.itemId){
            R.id.homeFragment ->{
                controller.navigate(R.id.show_home)
            }
            R.id.favoritesFragment ->{
                controller.navigate(R.id.show_favorites)
            }
            R.id.tvshowsFragment -> {
                controller.navigate(R.id.show_tvshows)
            }
            R.id.profileFragment ->{
                controller.navigate(R.id.show_profile)
            }
        }
        return true
    }


}