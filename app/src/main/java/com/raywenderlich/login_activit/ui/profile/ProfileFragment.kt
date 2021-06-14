package com.raywenderlich.login_activit.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.raywenderlich.login_activit.R
import com.raywenderlich.login_activit.databinding.ProfileScreenFragmentBinding
import com.raywenderlich.login_activit.user.User

class ProfileFragment : Fragment() {



    private var binding: ProfileScreenFragmentBinding? = null




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (binding == null){
            binding = ProfileScreenFragmentBinding.inflate(inflater,container,false)
            fr()
        }

        return binding?.root
    }



    private fun fr(){
        FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().currentUser?.uid.toString()).addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var user  = snapshot.getValue().toString()
                var current = user.drop(7).dropLast(1)
                binding?.user = User(current)

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "${error.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }








}