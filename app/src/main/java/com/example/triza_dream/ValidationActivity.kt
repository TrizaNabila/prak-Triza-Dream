package com.example.triza_dream

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ValidationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_validation)

        val sharedPref = getSharedPreferences("UserPref", Context.MODE_PRIVATE)

        val tvNama = findViewById<TextView>(R.id.tvHasilNama)
        val tvEmail = findViewById<TextView>(R.id.tvHasilEmail)
        val tvTgl = findViewById<TextView>(R.id.tvHasilTglLahir)
        val tvGender = findViewById<TextView>(R.id.tvHasilGender)
        val tvUser = findViewById<TextView>(R.id.tvHasilUsername)

        // Ambil data untuk ditampilkan
        val nama = sharedPref.getString("nama", "") ?: ""
        val email = sharedPref.getString("email", "") ?: ""
        val tgl = sharedPref.getString("tgl_lahir", "") ?: ""
        val gender = sharedPref.getString("gender", "") ?: ""
        val user = sharedPref.getString("username", "") ?: ""
        val pass = sharedPref.getString("password", "") ?: ""

        tvNama.text = "Nama: $nama"
        tvEmail.text = "Email: $email"
        tvTgl.text = "Tgl Lahir: $tgl"
        tvGender.text = "Jenis Kelamin: $gender"
        tvUser.text = "Username: $user"

        // LOGIKA SOAL b2: Tombol Submit untuk Validasi
        findViewById<Button>(R.id.btnSubmit).setOnClickListener {
            // 1. Cek inputan tidak boleh kosong
            if (nama.isEmpty() || email.isEmpty() || tgl.isEmpty() || user.isEmpty()) {
                Toast.makeText(this, "Data tidak lengkap!", Toast.LENGTH_SHORT).show()
            }
            // 2. Jika berhasil melewati validasi
            else {
                AlertDialog.Builder(this)
                    .setTitle("Informasi")
                    .setMessage("Registrasi Berhasil")
                    .setPositiveButton("Selesai") { _, _ ->
                        finish() // Selesai
                    }
                    .show()
            }
        }

        // LOGIKA SOAL b2: Tombol Kembali untuk perbaiki isian
        findViewById<Button>(R.id.btnKembali).setOnClickListener {
            finish() // Kembali ke RegisterActivity
        }
    }
}