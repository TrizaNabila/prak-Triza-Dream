package com.example.triza_dream.pertemuan_10

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.triza_dream.R
import com.example.triza_dream.databinding.ActivityTenthBinding
import com.google.android.material.tabs.TabLayoutMediator

class TenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = getString(R.string.tabs_toolbar_title)
            setDisplayHomeAsUpEnabled(true)
        }

        // 2. Inisialisasi Adapter
        val tabsAdapter = TenthTabsAdapter(this)
        binding.viewPager.adapter = tabsAdapter

        // 3. Hubungkan TabLayout & ViewPager2 dengan Ikon & Judul
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.tab_alur_title)
                    tab.icon = ContextCompat.getDrawable(this, android.R.drawable.ic_menu_edit)
                }
                1 -> {
                    tab.text = getString(R.string.tab_syarat_title)
                    tab.icon = ContextCompat.getDrawable(this, android.R.drawable.ic_menu_info_details)
                }
                2 -> {
                    tab.text = getString(R.string.tab_produk_title)
                    tab.icon = ContextCompat.getDrawable(this, android.R.drawable.ic_menu_gallery)
                    
                    // Tambahkan Badge pada tab Katalog untuk menarik perhatian
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 10
                    badge.backgroundColor = ContextCompat.getColor(this, R.color.pink_primary)
                }
            }
        }.attach()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}