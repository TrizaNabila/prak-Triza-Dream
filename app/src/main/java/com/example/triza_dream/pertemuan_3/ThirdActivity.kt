package com.example.triza_dream.pertemuan_3

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.triza_dream.databinding.ActivityThirdBinding
import com.example.triza_dream.utils.PermissionHelper
import com.example.triza_dream.utils.ReminderHelper
import java.util.Calendar

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding

    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Notifikasi diizinkan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notifikasi ditolak", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Meminta izin notifikasi jika diperlukan
        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(
                    notificationPermissionLauncher,
                    permission
                )
            }
        }

        // Tombol Kirim Pesanan (Set Reminder)
        binding.btnKirim.setOnClickListener {
            val noTujuan = binding.inputNoTujuan.text.toString()
            if (noTujuan.isEmpty()) {
                Toast.makeText(this, "Silahkan isi nama/nomor terlebih dahulu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            val calendar = Calendar.getInstance().apply {
                add(Calendar.MINUTE, 1) // Tambah 1 menit dari sekarang
            }

            ReminderHelper.setReminder(
                context = this,
                hour = calendar.get(Calendar.HOUR_OF_DAY),
                minute = calendar.get(Calendar.MINUTE),
                title = "Reminder 1 Menit ✨",
                message = "Halo $noTujuan, reminder ini muncul 1 menit setelah tombol ditekan 💖",
                targetActivity = ThirdResultActivity::class.java
            )
            Toast.makeText(this, "Berhasil! Silahkan tunggu 1 Menit untuk Notifikasi...", Toast.LENGTH_LONG).show()
        }

        // Tombol Kembali ke Halaman Utama
        binding.btnBack.setOnClickListener {
            finish() // Menutup activity ini dan kembali ke HomeFragment
        }
    }
}
