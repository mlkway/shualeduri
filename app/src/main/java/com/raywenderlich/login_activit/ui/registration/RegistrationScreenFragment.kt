package com.raywenderlich.login_activit.ui.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.raywenderlich.login_activit.R
import com.raywenderlich.login_activit.databinding.RegistrationScreenFragmentBinding
import com.raywenderlich.login_activit.user.User

class RegistrationScreenFragment : Fragment() {


    private var binding: RegistrationScreenFragmentBinding? = null

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        if (binding == null){
            binding = RegistrationScreenFragmentBinding.inflate(inflater,container,false)
            init()
        }

        return binding?.root

    }

    private fun init(){
        auth = Firebase.auth
        binding?.button?.setOnClickListener {
            check()
        }


    }

    private fun check(){
        val email = binding?.editTextEmail?.text.toString()
        val password = binding?.editTextPasword?.text.toString()
        if (email.isNotEmpty() && password.isNotEmpty()){

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        succsess()
                        FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().currentUser?.uid.toString()).setValue(User(email))
                    } else {
                        // If sign in fails, display a message to the user.

                        Toast.makeText(requireContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }


        }
    }


    private fun succsess(){
        findNavController().navigate(R.id.action_registrationScreenFragment_to_navHomeFragment)
    }





}