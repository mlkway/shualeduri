package com.raywenderlich.login_activit.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.raywenderlich.login_activit.R
import com.raywenderlich.login_activit.databinding.ProfileScreenFragmentBinding

class ProfileFragment : Fragment() {



    private var binding: ProfileScreenFragmentBinding? = null

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (binding == null){
            binding = ProfileScreenFragmentBinding.inflate(inflater,container,false)
        }
          out()
        return binding?.root
    }


    private fun out(){
        auth = FirebaseAuth.getInstance()
        binding?.logOutimg?.setOnClickListener {
            auth.signOut()
        }
    }





}