package com.example.calztrackerapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calztrackerapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        // ✅ Setup MealAdapter
        val adapter = MealAdapter { meal ->
            val msg = """
                🍽 ${meal.name}
                🔥 ${meal.calories} kcal
                🥩 Protein: ${meal.protein}g
                🍞 Carbs: ${meal.carbs}g
                🧈 Fats: ${meal.fats}g
            """.trimIndent()
            Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // ✅ Observe meals list
        viewModel.recentMeals.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        // ✅ Observe total macros
        viewModel.totalCalories.observe(viewLifecycleOwner) {
            binding.caloriesTotal.text = "Calories: ${it ?: 0}"
        }

        viewModel.totalProtein.observe(viewLifecycleOwner) {
            binding.proteinTotal.text = "Protein: ${it ?: 0}g"
        }

        viewModel.totalCarbs.observe(viewLifecycleOwner) {
            binding.carbsTotal.text = "Carbs: ${it ?: 0}g"
        }

        viewModel.totalFats.observe(viewLifecycleOwner) {
            binding.fatsTotal.text = "Fats: ${it ?: 0}g"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
