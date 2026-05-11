package com.example.triza_dream

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent // Penting untuk perpindahan halaman
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        // Mengatur padding agar layout tidak tertutup status bar/navigation bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Inisialisasi Komponen View dari XML
        val etNama = findViewById<EditText>(R.id.etNama)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etTanggalLahir = findViewById<EditText>(R.id.etTanggalLahir)
        val rgGender = findViewById<RadioGroup>(R.id.rgGender)
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        val btnSelanjutnya = findViewById<Button>(R.id.btnSelanjutnya)

        // 2. Logika DatePicker (Klik EditText muncul Kalender)
        etTanggalLahir.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                // Format tanggal yang akan ditampilkan
                val date = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                etTanggalLahir.setText(date)
            }, year, month, day)

            datePickerDialog.show()
        }

        // 3. Logika Simpan Data ke SharedPreferences saat tombol diklik
        btnSelanjutnya.setOnClickListener {
            val nama = etNama.text.toString()
            val email = etEmail.text.toString()
            val tglLahir = etTanggalLahir.text.toString()
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            val confirm = etConfirmPassword.text.toString()

            // Ambil teks dari RadioButton yang dipilih
            val selectedId = rgGender.checkedRadioButtonId
            val gender = if (selectedId != -1) {
                findViewById<RadioButton>(selectedId).text.toString()
            } else {
                ""
            }

            // Validasi Sederhana
            if (nama.isEmpty() || email.isEmpty() || tglLahir.isEmpty() || gender.isEmpty() || username.isEmpty()) {
                Toast.makeText(this, "Semua kolom harus diisi!", Toast.LENGTH_SHORT).show()
            } else if (password != confirm) {
                Toast.makeText(this, "Password tidak cocok!", Toast.LENGTH_SHORT).show()
            } else {
                // PROSES SIMPAN KE SHAREDPREFERENCES
                val sharedPref = getSharedPreferences("UserPref", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()

                editor.putString("nama", nama)
                editor.putString("email", email)
                editor.putString("tgl_lahir", tglLahir)
                editor.putString("gender", gender)
                editor.putString("username", username)
                editor.putString("password", password)

                editor.apply() // Menyimpan data

                Toast.makeText(this, "Data berhasil disimpan!", Toast.LENGTH_SHORT).show()

                // PINDAH KE HALAMAN VALIDASI
                val intent = Intent(this, ValidationActivity::class.java)
                startActivity(intent)
            }
        }
    }
}