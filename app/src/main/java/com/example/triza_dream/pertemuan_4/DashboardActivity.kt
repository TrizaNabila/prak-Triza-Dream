package com.example.triza_dream.pertemuan_4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.triza_dream.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Setup View Binding
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Ambil data dari Intent (Jika dipanggil dari HomeFragment)
        val judul = intent.getStringExtra("judul") ?: "Edukasi Gizi Keluarga"

        // 3. Set data ke komponen UI
        binding.txtWelcomeDashboard.text = "Welcome, Ms. Triza"
        binding.txtJudulDetail.text = judul

        // 4. Tombol Back untuk kembali ke HomeFragment (BaseActivity)
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}