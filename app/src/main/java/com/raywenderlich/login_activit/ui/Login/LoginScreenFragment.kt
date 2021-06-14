package com.raywenderlich.login_activit.ui.Login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.raywenderlich.login_activit.R
import com.raywenderlich.login_activit.databinding.LoginScreenFragmentBinding

class LoginScreenFragment : Fragment() {


    private  var binding: LoginScreenFragmentBinding? = null


    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (binding == null){
            binding = LoginScreenFragmentBinding.inflate(inflater,container,false)
            auth = FirebaseAuth.getInstance()

            listener()
        }
        return binding?.root
    }


    private fun listener() {

        binding?.registerTextView?.setOnClickListener{
                findNavController().navigate(R.id.action_loginScreenFragment_to_registrationScreenFragment)
        }
        binding?.loginButton?.setOnClickListener {

            val email = binding?.editTextUsername?.text.toString()
            val password = binding?.editTextPassword?.text.toString()

            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(requireContext(), "carielia", Toast.LENGTH_SHORT).show()
            }else{
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                    if (it.isSuccessful){
                        findNavController().navigate(R.id.action_loginScreenFragment_to_navHomeFragment)
                    }else{
                        Toast.makeText(requireContext(), "${it}", Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }

    }



}