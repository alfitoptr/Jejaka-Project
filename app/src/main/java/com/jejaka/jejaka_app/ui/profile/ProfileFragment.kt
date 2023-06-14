package com.jejaka.jejaka_app.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jejaka.jejaka_app.databinding.FragmentProfileBinding
import com.jejaka.jejaka_app.ui.auth.AuthActivity
import com.jejaka.jejaka_app.ui.detail_place.DetailPlaceActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val profileViewModel =  ViewModelProvider(this).get(ProfileViewModel::class.java)

        profileViewModel.text.observe(viewLifecycleOwner) {
        }

        binding.btnHelp.setOnClickListener {
            startActivity(
                Intent(
                    activity, DetailPlaceActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }

        binding.btnLogout.setOnClickListener {
            Firebase.auth.signOut()
            deleteLoginSession(requireContext())
            startActivity(
                Intent(
                    activity, AuthActivity::class.java
                )
            )
            activity?.finish()
        }

        val sharedPreferences = context?.getSharedPreferences("LoginSession", Context.MODE_PRIVATE)
        val name = sharedPreferences?.getString("name", "")
        val email = sharedPreferences?.getString("email", "")
        val photo = sharedPreferences?.getString("photo", "")

        binding.tvName.text = name
        binding.tvEmail.text = email
        Glide.with(requireActivity())
            .load(photo)
            .into(binding.ivProfile)
    }

    private fun deleteLoginSession(context: Context) {
        val sharedPreferences = context.getSharedPreferences("LoginSession", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putString("userId", "")
        editor?.putString("name", "")
        editor?.putString("email", "")
        editor?.putString("photo", "")
        editor?.apply()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}