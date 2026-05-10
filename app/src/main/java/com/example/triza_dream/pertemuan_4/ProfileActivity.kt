package com.example.triza_dream.pertemuan_4

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
// Sesuaikan import binding ke package project kamu
import com.example.triza_dream.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Setup View Binding
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.e("LifeCycle", "ProfileActivity: onCreate")

        // 2. Menerima data Intent
        val judul = intent.getStringExtra("judul") ?: "Profil Pengguna"
        val desc = intent.getStringExtra("desc") ?: "Halaman detail profil Triza Dream"

        // 3. Set data ke komponen UI
        binding.txtJudulDetail.text = judul
        binding.txtDescDetail.text = desc

        // 4. Tombol Back
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}