package com.example.triza_dream

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.triza_dream.pertemuan_3.LoginActivity
import com.example.triza_dream.pertemuan_2.RumusBangunRuangActivity
import com.example.triza_dream.pertemuan_3.ThirdActivity
import com.example.triza_dream.pertemuan_4.DashboardActivity
import com.example.triza_dream.pertemuan_4.ProfileActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Setup Toolbar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Triza Dream App"

        // 2. Teks Welcome
        val txtWelcome = findViewById<TextView>(R.id.txtWelcome)
        val username = intent.getStringExtra("USER_NAME") ?: "Ms. Triza"
        txtWelcome.text = "Welcome, $username"

        // --- TOMBOL PERTEMUAN 14 ---
        val btnNotif = findViewById<Button>(R.id.btnNotif)
        btnNotif?.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }

        // 3. Tombol Kalkulator Rumus
        val btnRumus = findViewById<Button>(R.id.btnRumus)
        btnRumus?.setOnClickListener {
            val intent = Intent(this, RumusBangunRuangActivity::class.java)
            startActivity(intent)
        }

        // 4. Tombol User Profile
        val btnProfile = findViewById<Button>(R.id.btnProfile)
        btnProfile?.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        // 5. Tombol Dashboard Food
        val btnDashboardFood = findViewById<Button>(R.id.btnDashboardFood)
        btnDashboardFood?.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            intent.putExtra("judul", "Edukasi Gizi Keluarga")
            startActivity(intent)
        }

        // 6. Tombol Bina Desa Website
        val btnWebView = findViewById<Button>(R.id.btnWebView)
        btnWebView?.setOnClickListener {
            try {
                val intent = Intent(this, TambahBansosActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Gagal buka form: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }

        // 7. Tombol Logout
        val btnLogout = findViewById<Button>(R.id.btnLogout)
        btnLogout?.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Logout")
                .setMessage("Apakah anda yakin ingin keluar?")
                .setPositiveButton("Ya") { _, _ ->
                    val intentLogout = Intent(this, LoginActivity::class.java)
                    intentLogout.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intentLogout)
                    finish()
                }
                .setNegativeButton("Batal", null)
                .show()
        }
    }
}