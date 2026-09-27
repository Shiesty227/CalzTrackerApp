package com.example.calztrackerapp.ui.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.calztrackerapp.data.model.db.AppDatabase
import com.example.calztrackerapp.databinding.FragmentSettingsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val prefs = requireContext().getSharedPreferences("calz_prefs", Context.MODE_PRIVATE)

        // Load saved goal
        val savedGoal = prefs.getInt("calorieGoal", 0)
        if (savedGoal > 0) {
            binding.goalInput.setText(savedGoal.toString())
        }

        // Save goal button
        binding.saveGoalButton.setOnClickListener {
            val goalText = binding.goalInput.text.toString()
            val goal = goalText.toIntOrNull()
            if (goal != null && goal > 0) {
                prefs.edit().putInt("calorieGoal", goal).apply()
                Toast.makeText(requireContext(), "Goal saved!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Enter a valid number", Toast.LENGTH_SHORT).show()
            }
        }

        // Apply saved theme
        val darkMode = prefs.getBoolean("darkMode", false)
        binding.themeSwitch.isChecked = darkMode
        AppCompatDelegate.setDefaultNightMode(
            if (darkMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        )

        // Theme switch listener
        binding.themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("darkMode", isChecked).apply()
            AppCompatDelegate.setDefaultNightMode(
                if (isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            )
        }

        // Reset meals/macros
        binding.resetMacrosButton.setOnClickListener {
            val db = AppDatabase.getDatabase(requireContext())
            CoroutineScope(Dispatchers.IO).launch {
                db.mealDao().deleteAllMeals()
            }
            Toast.makeText(requireContext(), "Meal data reset", Toast.LENGTH_SHORT).show()
        }

        // Reset weight entries
        binding.resetWeightButton.setOnClickListener {
            val db = AppDatabase.getDatabase(requireContext())
            CoroutineScope(Dispatchers.IO).launch {
                db.weightDao().deleteAllWeights()
            }
            Toast.makeText(requireContext(), "Weight history reset", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
