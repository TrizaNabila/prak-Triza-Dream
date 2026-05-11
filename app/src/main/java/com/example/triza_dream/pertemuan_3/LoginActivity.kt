package com.example.triza_dream.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.triza_dream.BaseActivity
import com.example.triza_dream.RegisterActivity // Pastikan import ini ada
import com.example.triza_dream.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("LifeCycle", "LoginActivity: onCreate")

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        // --- TOMBOL LOGIN ---
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (username.isNotEmpty() && email.isNotEmpty() && password == username) {
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", username)
                editor.putString("email", email)
                editor.apply()

                val intent = Intent(this, BaseActivity::class.java)
                intent.putExtra("USER_NAME", username)
                intent.putExtra("USER_EMAIL", email)
                startActivity(intent)
                finish()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Gagal Login")
                    .setMessage("Silahkan isi Username & Email dengan benar!\n(Catatan: Password harus sama dengan Username)")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }

        // --- TAMBAHAN: TOMBOL REGISTER ---
        // Karena di XML tadi kita beri ID @+id/btnRegister
        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}