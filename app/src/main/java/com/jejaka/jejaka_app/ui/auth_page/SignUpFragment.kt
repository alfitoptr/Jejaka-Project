package com.jejaka.jejaka_app.ui.auth_page

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.jejaka.jejaka_app.R
import com.jejaka.jejaka_app.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSignUpBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAction()
        emailFocusListener()
        passwordFocusListener()
        passwordConfirmFocusListener()

        binding.btnSignup.setOnClickListener { submitForm() }
    }

    private fun submitForm()
    {

        binding.emailContainer.helperText = validEmail()
        binding.passwordContainer.helperText = validPassword()
        binding.passwordConfirmContainer.helperText = validPasswordConfirm()

        val validEmail = binding.emailContainer.helperText == null
        val validPassword = binding.passwordContainer.helperText == null
        val validPasswordConfirm = binding.passwordConfirmContainer.helperText == null

        if (validEmail && validPassword && validPasswordConfirm) {
            val fragmentManager = parentFragmentManager
            fragmentManager.popBackStack()
        } else {
            invalidForm()
        }
    }

    private fun invalidForm()
    {
        var message = ""
        if(binding.nameContainer.helperText != null)
            message += "\nName: " + binding.nameContainer.helperText
        if(binding.emailContainer.helperText != null)
            message += "\nEmail: " + binding.emailContainer.helperText
        if(binding.passwordContainer.helperText != null)
            message += "\nPassword: " + binding.passwordContainer.helperText
        if(binding.passwordConfirmContainer.helperText != null)
            message += "\nPassword: " + binding.passwordConfirmContainer.helperText

        AlertDialog.Builder(requireContext())
            .setTitle("Invalid Form")
            .setMessage(message)
            .setPositiveButton("Okay"){ _,_ ->
                // do nothing
            }
            .show()
    }

    private fun resetForm()
    {
        var message = "Name: " + binding.nameEditText.text
        message += "\nEmail: " + binding.emailEditText.text
        message += "\nPassword: " + binding.passwordEditText.text
        AlertDialog.Builder(requireContext())
            .setTitle("Form submitted")
            .setMessage(message)
            .setPositiveButton("Okay"){ _,_ ->
                binding.nameEditText.text = null
                binding.emailEditText.text = null
                binding.passwordEditText.text = null
                binding.passwordConfirmEditText.text = null

                binding.nameContainer.helperText = getString(R.string.required)
                binding.emailContainer.helperText = getString(R.string.required)
                binding.passwordContainer.helperText = getString(R.string.required)
                binding.passwordConfirmContainer.helperText = getString(R.string.required)
            }
            .show()
    }

    private fun emailFocusListener()
    {
        binding.emailEditText.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.emailContainer.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String?
    {
        val emailText = binding.emailEditText.text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches())
        {
            return "Invalid Email Address"
        }
        return null
    }

    private fun passwordFocusListener()
    {
        binding.passwordEditText.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.passwordContainer.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String?
    {
        val passwordText = binding.passwordEditText.text.toString()
        if(passwordText.length < 8)
        {
            return "Minimum 8 Characters"
        }
        if(!passwordText.matches(".*[A-Z].*".toRegex()))
        {
            return "Must Contain 1 Upper-case Character"
        }
        if(!passwordText.matches(".*[a-z].*".toRegex()))
        {
            return "Must Contain 1 Lower-case Character"
        }
        if(!passwordText.matches(".*[@#\$%^&+=].*".toRegex()))
        {
            return "Must Contain 1 Special Character (@#\$%^&+=)"
        }

        return null
    }

    private fun passwordConfirmFocusListener()
    {
        binding.passwordConfirmEditText.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.passwordConfirmContainer.helperText = validPasswordConfirm()
            }
        }
    }

    private fun validPasswordConfirm(): String?
    {
        val passwordConfirmText = binding.passwordConfirmEditText.text.toString()
        if(passwordConfirmText != binding.passwordEditText.text.toString())
        {
            return "Password Doesn't Match"
        }
        return null
    }

    private fun setupAction() {

        binding.tvLogin.setOnClickListener{
            val fragmentManager = parentFragmentManager
            fragmentManager.popBackStack()
        }
    }

    companion object {

    }
}