package com.example.triza_dream.pertemuan_10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.triza_dream.databinding.FragmentTabCBinding

class TabCFragment : Fragment() {

    private var _binding: FragmentTabCBinding? = null
    private val binding get() = _binding!!

    // Daftar Bantuan Sosial yang relevan dengan project
    private val bansosList = listOf(
        ProductModel("Beras Premium 10kg", "Program Sembako Reguler", "https://picsum.photos/seed/rice/400/300"),
        ProductModel("Minyak Goreng 2L", "Subsidi Bahan Pokok", "https://picsum.photos/seed/oil/400/300"),
        ProductModel("Telur Ayam 1kg", "Bantuan Nutrisi Protein", "https://picsum.photos/seed/egg/400/300"),
        ProductModel("Paket Sembako Lengkap", "Bantuan Darurat Bencana", "https://picsum.photos/seed/box/400/300"),
        ProductModel("Susu & Gizi Balita", "Program Cegah Stunting", "https://picsum.photos/seed/milk/400/300"),
        ProductModel("Gula Pasir 1kg", "Kebutuhan Pokok Warga", "https://picsum.photos/seed/sugar/400/300"),
        ProductModel("Tepung Terigu 1kg", "Bahan Olahan Pangan", "https://picsum.photos/seed/flour/400/300"),
        ProductModel("Peralatan Sanitasi", "Bantuan Kesehatan Lingkungan", "https://picsum.photos/seed/wash/400/300"),
        ProductModel("Beras Sejahtera 5kg", "Program Rastra Khusus", "https://picsum.photos/seed/rice5/400/300"),
        ProductModel("Paket Lauk Pauk", "Protein Hewani & Nabati", "https://picsum.photos/seed/food/400/300")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabCBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Menggunakan adapter yang sama untuk menampilkan daftar bansos
        val adapter = ProductAdapter(bansosList) { selectedItem ->
            Toast.makeText(requireContext(), "Detail: ${selectedItem.name}", Toast.LENGTH_SHORT).show()
        }

        binding.rvProducts.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
