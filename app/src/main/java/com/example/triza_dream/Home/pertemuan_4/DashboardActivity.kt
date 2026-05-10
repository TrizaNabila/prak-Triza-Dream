package com.example.triza_dream.Home.pertemuan_4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
// Import binding sesuai package project kamu
import com.example.triza_dream.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Setup View Binding
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Ambil data dari Intent
        val judul = intent.getStringExtra("judul") ?: "Triza Food Gallery"

        // 3. Set data ke komponen UI
        // Nama kita sesuaikan dengan nama project kamu
        binding.txtWelcomeDashboard.text = "Welcome, Ms. Triza"
        binding.txtJudulDetail.text = judul

        // 4. Tombol Back
        binding.btnBack.setOnClickListener {
            finish() // Menutup activity dan kembali ke halaman sebelumnya
        }
    }
}