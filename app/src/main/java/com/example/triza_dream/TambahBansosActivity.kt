package com.example.triza_dream

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.triza_dream.catatan.AppDatabase
import com.example.triza_dream.catatan.BansosEntity
import com.google.android.material.textfield.TextInputEditText
import java.util.concurrent.Executors

class TambahBansosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_bansos)

        val database = AppDatabase.getDatabase(this)

        // DISESUAIKAN DENGAN STRUKTUR TextInputEditText ADS ASLI DARI XML KAMU
        val etNama = findViewById<TextInputEditText>(R.id.etNamaPenerima)
        val etJenis = findViewById<TextInputEditText>(R.id.etJenisBantuan)
        val etKet = findViewById<TextInputEditText>(R.id.etKeterangan)

        // ID TOMBOL YANG SESUAI DI XML KAMU: btnSimpan
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)

        btnSimpan?.setOnClickListener {
            val nama = etNama?.text?.toString()?.trim() ?: ""
            val jenis = etJenis?.text?.toString()?.trim() ?: ""
            val ket = etKet?.text?.toString()?.trim() ?: ""

            if (nama.isNotEmpty() && jenis.isNotEmpty()) {
                val dataBansos = BansosEntity(
                    namaPenerima = nama,
                    jenisBantuan = jenis,
                    keterangan = ket
                )

                // Simpan ke database Room via Executor di background thread biar anti-crash
                Executors.newSingleThreadExecutor().execute {
                    try {
                        database.bansosDao().insertBansos(dataBansos)
                        runOnUiThread {
                            Toast.makeText(this@TambahBansosActivity, "Data Berhasil Masuk Database Room!", Toast.LENGTH_SHORT).show()
                            finish() // Menutup halaman input dan kembali ke halaman utama
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        runOnUiThread {
                            Toast.makeText(this@TambahBansosActivity, "Gagal masuk database: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Nama dan Jenis Bantuan tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}