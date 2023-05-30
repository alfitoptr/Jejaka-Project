package com.jejaka.jejaka_app.ui.user

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.load.engine.Resource
import com.jejaka.jejaka_app.MainActivity
import com.jejaka.jejaka_app.R
import com.jejaka.jejaka_app.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    //private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setupAction()
        setAnimation()
        emailFocusListener()
        passwordFocusListener()

        binding.btnLogin.setOnClickListener { submitForm() }
    }

    private fun submitForm()
    {
        binding.emailContainer.helperText = validEmail()
        binding.passwordContainer.helperText = validPassword()

        val validEmail = binding.emailContainer.helperText == null
        val validPassword = binding.passwordContainer.helperText == null

        if (validEmail && validPassword)
            startActivity(
                Intent(
                    this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        else
            invalidForm()
    }

    private fun invalidForm()
    {
        var message = ""
        if(binding.emailContainer.helperText != null)
            message += "\n\nEmail: " + binding.emailContainer.helperText
        if(binding.passwordContainer.helperText != null)
            message += "\n\nPassword: " + binding.passwordContainer.helperText

        AlertDialog.Builder(this)
            .setTitle("Invalid Form")
            .setMessage(message)
            .setPositiveButton("Okay"){ _,_ ->
                // do nothing
            }
            .show()
    }

    private fun resetForm()
    {
        var message = "Email: " + binding.emailEditText.text
        message += "\nPassword: " + binding.passwordEditText.text
        AlertDialog.Builder(this)
            .setTitle("Form submitted")
            .setMessage(message)
            .setPositiveButton("Okay"){ _,_ ->
                binding.emailEditText.text = null
                binding.passwordEditText.text = null

                binding.emailContainer.helperText = getString(R.string.required)
                binding.passwordContainer.helperText = getString(R.string.required)
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

    private fun setupAction() {

        binding.tvSignup.setOnClickListener{
            startActivity(
                Intent(
                    this, LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }
    }

    private fun setAnimation() {
        val appName = ObjectAnimator.ofFloat(binding.tvAppName, View.ALPHA, 1f).setDuration(700)
        val tvEmail = ObjectAnimator.ofFloat(binding.emailContainer, View.ALPHA, 1f).setDuration(700)
        val tvPassword = ObjectAnimator.ofFloat(binding.passwordContainer, View.ALPHA, 1f).setDuration(700)

        val btnLogin = ObjectAnimator.ofFloat(binding.btnLogin, View.ALPHA, 1f).setDuration(700)
        val btnGoogle = ObjectAnimator.ofFloat(binding.btnLoginGoogle, View.ALPHA, 1f).setDuration(700)
        val btnFacebook = ObjectAnimator.ofFloat(binding.btnLoginFb, View.ALPHA, 1f).setDuration(700)
        val txtContinue = ObjectAnimator.ofFloat(binding.tvContinue, View.ALPHA, 1f).setDuration(700)
        val txtHaveAcc = ObjectAnimator.ofFloat(binding.tvNoAcc, View.ALPHA, 1f).setDuration(700)
        val txtSignup = ObjectAnimator.ofFloat(binding.tvSignup, View.ALPHA, 1f).setDuration(700)

        val emailAnimation = AnimatorSet().apply {
            playTogether(tvEmail, tvPassword)
        }

        val buttonAnimation = AnimatorSet().apply {
            playTogether(btnLogin, txtHaveAcc, txtSignup,btnGoogle,btnFacebook,txtSignup)
        }

        AnimatorSet().apply {
            playSequentially(
                appName,
                emailAnimation,
                buttonAnimation
            )
            start()
        }
    }


}