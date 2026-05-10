package com.example.triza_dream.Home.pertemuan_2

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.triza_dream.databinding.ActivityRumusBangunRuangBinding
import kotlin.math.PI
import kotlin.math.pow

class RumusBangunRuangActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRumusBangunRuangBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Setup Binding
        binding = ActivityRumusBangunRuangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Ambil data dari Intent (Opsional, jika kamu mengirim data dari MainActivity)
        val judulHalaman = intent.getStringExtra("judul") ?: "Triza Dream Calculator"
        val deskripsiHalaman = intent.getStringExtra("desc") ?: "Hitung Lingkaran & Tabung"

        binding.tvJudul.text = "✨ $judulHalaman ✨"
        binding.tvSubtitle.text = deskripsiHalaman

        // 3. Tombol Hitung Luas Lingkaran
        binding.btnLingkaran.setOnClickListener {
            val rString = binding.etJariJari.text.toString()

            if (rString.isNotEmpty()) {
                val r = rString.toDouble()
                val luas = PI * r.pow(2)
                binding.tvHasil.text = "%.2f".format(luas)
                Log.d("TrizaLog", "Berhasil hitung luas lingkaran: $luas")
            } else {
                Toast.makeText(this, "Jari-jarinya diisi dulu ya!", Toast.LENGTH_SHORT).show()
            }
        }

        // 4. Tombol Hitung Volume Tabung
        binding.btnTabung.setOnClickListener {
            val rString = binding.etJariJari.text.toString()
            val tString = binding.etTinggi.text.toString()

            if (rString.isNotEmpty() && tString.isNotEmpty()) {
                val r = rString.toDouble()
                val t = tString.toDouble()
                val volume = PI * r.pow(2) * t
                binding.tvHasil.text = "%.2f".format(volume)
                Log.d("TrizaLog", "Berhasil hitung volume tabung: $volume")
            } else {
                Toast.makeText(this, "Isi jari-jari dan tinggi dulu!", Toast.LENGTH_SHORT).show()
            }
        }

        // 5. Tombol Back
        binding.btnBackMain.setOnClickListener {
            finish()
        }
    }
}