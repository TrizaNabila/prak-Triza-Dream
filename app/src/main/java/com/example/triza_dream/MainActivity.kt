package com.example.triza_dream

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.triza_dream.databinding.ActivityMainBinding
import com.example.triza_dream.pertemuan_2.RumusBangunRuangActivity
import com.example.triza_dream.pertemuan_3.LoginActivity
import com.example.triza_dream.pertemuan_4.DashboardActivity
import com.example.triza_dream.pertemuan_4.ProfileActivity
import com.example.triza_dream.pertemuan_6.WebViewActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Triza Dream App"

        // Ambil data username dari Login
        val username = intent.getStringExtra("USER_NAME") ?: "Ms. Triza"
        binding.txtWelcome.text = "Welcome, $username"

        // 1. Tombol Kalkulator Rumus (Pertemuan 2)
        binding.btnRumus.setOnClickListener {
            val intent = Intent(this, RumusBangunRuangActivity::class.java)
            intent.putExtra("judul", "Kalkulator Bangun Ruang")
            startActivity(intent)
        }

        // 2. Tombol User Profile (Pertemuan 4)
        binding.btnProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("judul", "User Profile")
            intent.putExtra("desc", "Halaman informasi akun Triza")
            startActivity(intent)
        }

        // 3. Tombol Dashboard Food (Pertemuan 4)
        binding.btnDashboardFood.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            intent.putExtra("judul", "Triza Food Gallery")
            startActivity(intent)
        }

        // 4. Tombol WebView Bansos (Pertemuan 6) - SEKARANG SUDAH BISA DIKLIK
        binding.btnWebView.setOnClickListener {
            val intentWeb = Intent(this, WebViewActivity::class.java)
            startActivity(intentWeb)
        }

        // Tombol Logout
        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Logout")
                .setMessage("Apakah anda yakin ingin keluar?")
                .setPositiveButton("Ya") { _, _ ->
                    val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                    sharedPref.edit().clear().apply()

                    val intentLogout = Intent(this, LoginActivity::class.java)
                    startActivity(intentLogout)
                    finish()
                }
                .setNegativeButton("Batal", null)
                .show()
        }
    }
}