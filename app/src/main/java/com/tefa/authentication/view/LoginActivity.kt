package com.tefa.authentication.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.tefa.authentication.R
import com.tefa.authentication.view_model.AuthViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: AuthViewModel
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)

        val btnLogin: Button = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            performLogin(username, password)
        }

        val tvRegister: TextView = findViewById(R.id.tvRegister)
        tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun performLogin(username: String, password: String) {
        val isLoggedIn = viewModel.login(username, password)
        if (isLoggedIn) {
            // Navigasi ke halaman berikutnya setelah login berhasil
            startActivity(Intent(this, WelcomeActivity::class.java))
            finish()
        } else {
            // Tampilkan pesan error jika login gagal
            Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
        }
    }
}
