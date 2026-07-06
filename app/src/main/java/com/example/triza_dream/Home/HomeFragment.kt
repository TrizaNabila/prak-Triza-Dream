package com.example.triza_dream.Home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.triza_dream.BansosAdapter
import com.example.triza_dream.Home.pertemuan_13.ThirteenthActivity
import com.example.triza_dream.pertemuan_3.LoginActivity
import com.example.triza_dream.TambahBansosActivity
import com.example.triza_dream.R
import com.example.triza_dream.catatan.AppDatabase
import com.example.triza_dream.data.api.CatFactApiClient
import com.example.triza_dream.data.api.PhotoApiClient
import com.example.triza_dream.databinding.FragmentHomeBinding
import com.example.triza_dream.pertemuan_2.RumusBangunRuangActivity
import com.example.triza_dream.pertemuan_3.ThirdActivity
import com.example.triza_dream.pertemuan_4.DashboardActivity
import com.example.triza_dream.pertemuan_10.TenthActivity
import com.example.triza_dream.pertemuan_14.FourteenthActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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

        // 1. Menu Cek Data
        binding.menuCekBansos.setOnClickListener {
            val intent = Intent(requireContext(), TambahBansosActivity::class.java)
            startActivity(intent)
        }

        // 2. Menu Camera & QR
        binding.menuCameraQR.setOnClickListener {
            val intent = Intent(requireContext(), ThirteenthActivity::class.java)
            startActivity(intent)
        }

        // 3. Menu Notifikasi
        binding.menuNotification.setOnClickListener {
            val intent = Intent(requireContext(), ThirdActivity::class.java)
            startActivity(intent)
        }

        // 4. Menu Kalkulator (Pertemuan 14)
        binding.menuKalkulator.setOnClickListener {
            val intent = Intent(requireContext(), FourteenthActivity::class.java)
            startActivity(intent)
        }

        // --- MENU PERTEMUAN 10 (Layanan Info) ---
        binding.menuPertemuan10.setOnClickListener {
            val intent = Intent(requireContext(), TenthActivity::class.java)
            startActivity(intent)
        }

        // 5. Menu Edukasi
        binding.menuEdukasi.setOnClickListener {
            val intent = Intent(requireContext(), DashboardActivity::class.java)
            intent.putExtra("judul", "Edukasi Gizi Keluarga")
            startActivity(intent)
        }

        // 6. Menu Logout
        binding.menuLogout.setOnClickListener {
            Toast.makeText(requireContext(), "Berhasil Keluar", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        // 7. Chip Group Listener (Kategori Bantuan)
        binding.chipGroupKategori.setOnCheckedStateChangeListener { _, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull()
            if (selectedChipId != null) {
                when (selectedChipId) {
                    R.id.chipSemua -> Toast.makeText(requireContext(), "Kategori: Semua Bantuan", Toast.LENGTH_SHORT).show()
                    R.id.chipPokok -> Toast.makeText(requireContext(), "Kategori: Sembako", Toast.LENGTH_SHORT).show()
                    R.id.chipTunai -> Toast.makeText(requireContext(), "Kategori: BLT Tunai", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnBantuan.setOnClickListener {
            Toast.makeText(requireContext(), "Menghubungi Layanan Darurat 🆘", Toast.LENGTH_SHORT).show()
        }

        loadBansosData()
        loadCatFact()
        loadPhoto()

        binding.btnRefresh.setOnClickListener {
            loadCatFact()
        }
    }

    private fun loadBansosData() {
        val database = AppDatabase.getDatabase(requireContext())
        viewLifecycleOwner.lifecycleScope.launch {
            database.bansosDao().getAllBansos().collectLatest { list ->
                _binding?.let { 
                    it.rvBansos.layoutManager = LinearLayoutManager(requireContext())
                    it.rvBansos.adapter = BansosAdapter(list)
                }
            }
        }
    }

    private fun loadCatFact() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = CatFactApiClient.apiService.getCatFact()
                _binding?.tvCatFact?.text = "\"${response.fact}\""
            } catch (e: Exception) {
                _binding?.tvCatFact?.text = "Gagal mengambil fakta kucing."
            }
        }
    }

    private fun loadPhoto() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val photos = PhotoApiClient.apiService.getPhotos()
                val adapter = com.example.triza_dream.Home.PhotoAdapter(photos)
                _binding?.let {
                    it.rvGallery.adapter = adapter
                    it.rvGallery.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                }
            } catch (e: Exception) {
                // Ignore
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
