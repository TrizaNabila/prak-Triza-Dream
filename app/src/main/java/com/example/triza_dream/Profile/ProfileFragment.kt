package com.example.triza_dream.Profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.triza_dream.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // --- SETUP TOOLBAR ---
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar)
        activity.supportActionBar?.title = "Profil Pengembang"

        // Menampilkan Nama
        binding.txtDeveloperName.text = "Ms. Triza Dream"

        // --- IMPLEMENTASI TUGAS 8: KLIK MATERIAL BUTTON ---
        // Kita panggil ID btnUpdateProfile yang ada di XML tadi
        binding.btnUpdateProfile.setOnClickListener {
            Toast.makeText(requireContext(), "Profil Berhasil Diperbarui!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}