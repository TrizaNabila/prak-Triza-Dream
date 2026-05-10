package com.example.triza_dream.pertemuan_3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.triza_dream.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengambil data yang dikirim dari LoginActivity
        val username = intent.getStringExtra("USER_NAME") ?: "User"
        val email = intent.getStringExtra("USER_EMAIL") ?: "Email tidak tersedia"

        // Menampilkan data ke TextView
        binding.txtWelcome.text = "Selamat Datang, $username!"
        binding.txtEmail.text = email
    }
}