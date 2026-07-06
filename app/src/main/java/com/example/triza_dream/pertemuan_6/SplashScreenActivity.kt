package com.example.triza_dream.pertemuan_6

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.triza_dream.databinding.ActivitySplashScreenBinding
import com.example.triza_dream.tutorial.TutorialMessageActivity

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            // PINDAH KE ONBOARDING
            val intent = Intent(this@SplashScreenActivity, TutorialMessageActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}