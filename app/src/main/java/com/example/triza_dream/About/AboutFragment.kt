package com.example.triza_dream.About

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter // WAJIB ADA untuk Tugas 8
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.triza_dream.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity() as AppCompatActivity

        // Setup Toolbar
        activity.setSupportActionBar(binding.toolbar)
        activity.supportActionBar?.title = "Tentang Aplikasi Bansos"

        // --- AWAL IMPLEMENTASI TUGAS 8 (LISTVIEW) ---

        // 1. Siapkan data array untuk ListView
        val dataLayanan = arrayOf(
            "Visi & Misi Triza Bansos",
            "Kebijakan Privasi",
            "Syarat dan Ketentuan",
            "Panduan Penggunaan",
            "Hubungi Layanan Pengaduan",
            "Tentang Pengembang"
        )

        // 2. Buat Adapter (ArrayAdapter) - Poin utama Tugas 8
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1, // Layout bawaan Android untuk teks saja
            dataLayanan
        )

        // 3. Pasang adapter ke ListView menggunakan Binding
        binding.lvFiturUtama.adapter = adapter

        // 4. Tambahan: Biar interaktif, kalau diklik muncul pesan (Toast)
        binding.lvFiturUtama.setOnItemClickListener { _, _, position, _ ->
            val itemDipilih = dataLayanan[position]
            Toast.makeText(context, "Membuka: $itemDipilih", Toast.LENGTH_SHORT).show()
        }

        // --- AKHIR IMPLEMENTASI TUGAS 8 ---
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}