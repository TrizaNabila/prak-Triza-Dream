package com.example.triza_dream.Home.pertemuan_6

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.triza_dream.MainActivity
import com.example.triza_dream.R
import com.example.triza_dream.pertemuan_3.LoginActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Mengatur tampilan ke layout splash screen
        setContentView(R.layout.activity_splash_screen)

        // Mengambil status login dari SharedPreferences
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("isLogin", false)

        // Menjalankan delay 2 detik sebelum pindah halaman
        lifecycleScope.launch {
            delay(2000)

            if (isLogin) {
                // Jika sudah login, langsung ke Dashboard (MainActivity)
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            } else {
                // Jika belum login, arahkan ke LoginActivity
                startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
            }
            finish() // Menutup splash screen agar tidak bisa di-back
        }
    }
}