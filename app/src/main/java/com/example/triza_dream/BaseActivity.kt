package com.example.triza_dream

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.triza_dream.About.AboutFragment
import com.example.triza_dream.Home.HomeFragment
import com.example.triza_dream.Profile.ProfileFragment
import com.example.triza_dream.databinding.ActivityBaseBinding

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi View Binding
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengatur Insets agar UI tidak tertutup System Bar (Status Bar/Nav Bar)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Padding bawah diset 0 agar Bottom Navigation menempel di bawah layar
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        // Menampilkan HomeFragment sebagai tampilan awal saat aplikasi dibuka
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        // Listener untuk navigasi bawah (Bottom Navigation)
        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.about -> {
                    replaceFragment(AboutFragment())
                    true
                }
                R.id.profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    /**
     * Fungsi untuk mengganti fragment di dalam fragment_container
     */
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}