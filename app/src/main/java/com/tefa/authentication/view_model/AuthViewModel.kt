package com.tefa.authentication.view_model

import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {

    private val userList = listOf(
        User("luthfi", "luth@example.com", "password123"),
        User("hani", "hani@example.com", "password456")
    )

    fun login(username: String, password: String): Boolean {
        val hardcodedUsername = "admin"
        val hardcodedPassword = "password"

        // Cocokkan dengan data hardcoded
        return username == hardcodedUsername && password == hardcodedPassword
    }

    fun register(username: String, email: String, password: String) {
        // Implementasi untuk registrasi dengan data hardcoded
        // Misalnya, simpan data pengguna baru ke dalam variabel atau struktur data
        val newUser = User(username, email, password)
        // Lakukan sesuatu dengan data pengguna baru, seperti menyimpannya di database
    }

}
