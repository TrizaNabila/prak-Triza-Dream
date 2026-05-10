package com.example.triza_dream.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.triza_dream.BaseActivity
// Import MainActivity agar bisa pindah halaman ke Dashboard 4 tombol
import com.example.triza_dream.MainActivity
import com.example.triza_dream.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("LifeCycle", "LoginActivity: onCreate")

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            // LOGIKA: Username & Email tidak boleh kosong
            // Password harus sama dengan Username (untuk mempermudah test)
            if (username.isNotEmpty() && email.isNotEmpty() && password == username) {

                // 1. SIMPAN STATUS LOGIN KE SHAREDPREFERENCES
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", username)
                editor.putString("email", email)
                editor.apply()

                // 2. PINDAH KE MAINACTIVITY (Dashboard dengan 4 Tombol)
                val intent = Intent(this, BaseActivity::class.java)

                // Kirim username agar MainActivity bisa menampilkan "Welcome, Triza"
                intent.putExtra("USER_NAME", username)
                intent.putExtra("USER_EMAIL", email)

                startActivity(intent)

                // 3. TUTUP LOGIN ACTIVITY
                // Supaya kalau user tekan tombol 'Back' di HP, tidak balik ke halaman login lagi
                finish()

            } else {
                // Tampilkan pesan error jika input salah
                AlertDialog.Builder(this)
                    .setTitle("Gagal Login")
                    .setMessage("Silahkan isi Username & Email dengan benar!\n(Catatan: Password harus sama dengan Username)")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }
    }
}