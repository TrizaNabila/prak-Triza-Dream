package com.example.triza_dream.pertemuan_6

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.triza_dream.BaseActivity
import com.example.triza_dream.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi View Binding
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Sembunyikan ActionBar jika ada
        supportActionBar?.hide()

        // Handler untuk berpindah ke BaseActivity (Tempat Bottom Nav berada)
        Handler(Looper.getMainLooper()).postDelayed({
            // Berpindah dari Splash Screen langsung ke BaseActivity
            val intent = Intent(this@SplashScreenActivity, BaseActivity::class.java)
            startActivity(intent)

            // Tutup SplashScreenActivity agar tidak bisa kembali ke sini dengan tombol back
            finish()
        }, 3000) // Durasi Splash Screen (3000ms = 3 detik)
    }
}