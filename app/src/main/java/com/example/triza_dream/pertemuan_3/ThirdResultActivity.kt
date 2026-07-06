package com.example.triza_dream.pertemuan_3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.triza_dream.databinding.ActivityThirdResultBinding

class ThirdResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThirdResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
