package com.example.calztrackerapp.ui.log

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.calztrackerapp.databinding.FragmentLogMealBinding
import com.example.calztrackerapp.data.model.db.Meal
import com.example.calztrackerapp.data.remote.UsdaApi
import com.example.calztrackerapp.data.remote.model.UsdaSearchResponse
import com.example.calztrackerapp.data.remote.model.UsdaFood
import com.example.calztrackerapp.data.remote.model.UsdaNutrient
import kotlinx.coroutines.launch


class LogMealFragment : Fragment() {

    private var _binding: FragmentLogMealBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: LogMealViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogMealBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[LogMealViewModel::class.java]

        // ✅ USDA Search Function
        binding.searchFoodButton.setOnClickListener {
            val query = binding.foodSearchInput.text.toString()

            if (query.isNotBlank()) {
                lifecycleScope.launch {
                    try {
                        val response = UsdaApi.service.searchFoods(query)
                        val firstFood = response.foods.firstOrNull()

                        if (firstFood != null) {
                            binding.mealNameInput.setText(firstFood.description)

                            val nutrients = firstFood.foodNutrients
                            binding.caloriesInput.setText(
                                nutrients.find { it.nutrientName == "Energy" }?.value?.toInt()?.toString() ?: ""
                            )
                            binding.proteinInput.setText(
                                nutrients.find { it.nutrientName == "Protein" }?.value?.toInt()?.toString() ?: ""
                            )
                            binding.carbsInput.setText(
                                nutrients.find { it.nutrientName == "Carbohydrate, by difference" }?.value?.toInt()?.toString() ?: ""
                            )
                            binding.fatsInput.setText(
                                nutrients.find { it.nutrientName == "Total lipid (fat)" }?.value?.toInt()?.toString() ?: ""
                            )

                            Toast.makeText(requireContext(), "Nutrients auto-filled!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(requireContext(), "No results found", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(requireContext(), "API error: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        // ✅ Save Meal to Room DB
        binding.saveButton.setOnClickListener {
            val name = binding.mealNameInput.text.toString()
            val calories = binding.caloriesInput.text.toString().toIntOrNull()
            val carbs = binding.carbsInput.text.toString().toIntOrNull()
            val protein = binding.proteinInput.text.toString().toIntOrNull()
            val fats = binding.fatsInput.text.toString().toIntOrNull()

            if (name.isBlank() || calories == null || carbs == null || protein == null || fats == null) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val meal = Meal(
                name = name,
                calories = calories,
                carbs = carbs,
                protein = protein,
                fats = fats
            )

            viewModel.insertMeal(meal)
            Toast.makeText(requireContext(), "Meal Saved", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
