package com.example.triza_dream.pertemuan_4

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.triza_dream.databinding.ActivityFourthBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class FourthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFourthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("LifeCycle", "onCreate: FourthActivity dibuat pertama kali")
        
        binding = ActivityFourthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Mengambil data dari Intent (Materi Intent)
        val name = intent.getStringExtra("name") ?: "Tidak ada nama"
        val from = intent.getStringExtra("from") ?: "Tidak ada asal"
        val age = intent.getIntExtra("age", 0)
        Log.e("Data Intent", "Nama: $name , Usia: $age, Asal: $from")

        // 2. Snackbar (Materi Dialog)
        binding.btnShowSnackbar.setOnClickListener {
            Snackbar.make(binding.root, "Ini adalah Snackbar Pink ✨", Snackbar.LENGTH_SHORT)
                .setAction("Tutup") {
                    Log.e("Info Snackbar", "Snackbar ditutup")
                }
                .show()
        }

        // 3. Alert Dialog (Materi Dialog)
        binding.btnShowAlertDialog.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin melanjutkan?")
                .setPositiveButton("Ya") { dialog, _ ->
                    dialog.dismiss()
                    Log.e("Info Dialog", "Anda memilih Ya!")
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                    Log.e("Info Dialog", "Anda memilih Tidak!")
                }
                .show()
        }

        // 4. Tombol Kembali
        binding.btnBack.setOnClickListener {
            finish() // Menghapus activity dari stack (Materi LifeCycle)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e("LifeCycle", "onStart: FourthActivity terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("LifeCycle", "onDestroy: FourthActivity dihapus dari stack")
    }
}
