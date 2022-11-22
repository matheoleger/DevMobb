package com.example.devmobb.ui.defibrillator

import allDefibrillators
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.devmobb.adapter.DefibrillatorAdapter
import com.example.devmobb.api.DefibrillatorApi
import com.example.devmobb.api.RetrofitHelper
import com.example.devmobb.databinding.FragmentHomeBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DefibrillatorFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(DefibrillatorViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.recyclerView
        val progressBarSation = binding.progressBarSation

        homeViewModel.defibrillators.observe(viewLifecycleOwner) {
            //val adapter : StationAdapter(it)
            //val adapter : StationAdapter(it.requireContext())
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = DefibrillatorAdapter(it, requireContext())
            progressBarSation.visibility = View.GONE

            allDefibrillators = it
        }

        val defibrillatorApi = RetrofitHelper().getInstance().create(DefibrillatorApi::class.java)
        GlobalScope.launch {
            val result = defibrillatorApi.getDefibrillators()
            Log.d("HOME", result.body().toString())
            homeViewModel.defibrillators.postValue(result.body())
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}