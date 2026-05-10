package com.example.triza_dream.Home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.triza_dream.databinding.FragmentHomeBinding
import com.example.triza_dream.pertemuan_2.RumusBangunRuangActivity
import com.example.triza_dream.pertemuan_3.LoginActivity
import com.example.triza_dream.pertemuan_4.DashboardActivity
import com.example.triza_dream.pertemuan_4.ProfileActivity
import com.example.triza_dream.pertemuan_6.WebViewActivity
import com.google.android.material.chip.Chip // Tambahkan import chip

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // --- IMPLEMENTASI TUGAS 8: LOGIKA CHIP ---
        binding.chipGroupKategori.setOnCheckedStateChangeListener { group, checkedIds ->
            if (checkedIds.isNotEmpty()) {
                val chip = group.findViewById<Chip>(checkedIds[0])
                Toast.makeText(requireContext(), "Filter: ${chip.text}", Toast.LENGTH_SHORT).show()
            }
        }

        // --- IMPLEMENTASI TUGAS 8: KLIK MENU GRIDLAYOUT ---

        // 1. Menu Kalkulator (Ganti dari btnRumus ke menuKalkulator)
        binding.menuKalkulator.setOnClickListener {
            val intent = Intent(requireContext(), RumusBangunRuangActivity::class.java)
            startActivity(intent)
        }

        // 2. Menu Cek Data (Ganti dari btnWebView ke menuCekBansos)
        binding.menuCekBansos.setOnClickListener {
            val intent = Intent(requireContext(), WebViewActivity::class.java)
            startActivity(intent)
        }

        // 3. Menu Edukasi (Ganti dari btnCustom2 ke menuEdukasi)
        binding.menuEdukasi.setOnClickListener {
            val intent = Intent(requireContext(), DashboardActivity::class.java)
            intent.putExtra("judul", "Edukasi Gizi Keluarga")
            startActivity(intent)
        }

        // 4. Menu Keluar/Logout (Ganti dari btnLogout ke menuLogout)
        binding.menuLogout.setOnClickListener {
            logoutProses()
        }

        // 5. Material Button (Poin 4 Tugas 8)
        binding.btnBantuan.setOnClickListener {
            val intent = Intent(requireContext(), ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun logoutProses() {
        Toast.makeText(requireContext(), "Berhasil Keluar", Toast.LENGTH_SHORT).show()
        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}