package com.example.triza_dream.pertemuan_6

import android.os.Bundle
import android.view.View
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.triza_dream.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Setup View Binding
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Setup Toolbar agar judulnya "Cek Bansos Kemensos"
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Cek Bansos Kemensos"

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed() // Fungsi tombol kembali di toolbar
        }

        // 3. Izinkan Cookie (agar web bisa dibuka dengan normal)
        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)
        cookieManager.setAcceptThirdPartyCookies(binding.webView, true)

        // 4. Setup WebView
        binding.webView.apply {
            // Agar link yang diklik tetap terbuka di dalam aplikasi (tidak lari ke Chrome)
            webViewClient = WebViewClient()

            // Handle Progress Bar (Loading)
            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    if (newProgress < 100) {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.progressBar.progress = newProgress
                    } else {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }

            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                databaseEnabled = true
                allowContentAccess = true
                allowFileAccess = true
                cacheMode = WebSettings.LOAD_DEFAULT

                // Set agar tampilan web responsif di layar HP
                useWideViewPort = true
                loadWithOverviewMode = true
                builtInZoomControls = true
                displayZoomControls = false
            }

            // URL Bantuan Sosial Kemensos
            loadUrl("https://cekbansos.kemensos.go.id/")
        }
    }
}