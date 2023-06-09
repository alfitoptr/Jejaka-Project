package com.jejaka.jejaka_app.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jejaka.jejaka_app.databinding.FragmentProfileBinding
import com.jejaka.jejaka_app.ui.about.AboutActivity
import com.jejaka.jejaka_app.ui.auth.AuthActivity

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
        binding.btnAbout.setOnClickListener {
            startActivity(
                Intent(
                    activity, AboutActivity::class.java
                )
            )
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