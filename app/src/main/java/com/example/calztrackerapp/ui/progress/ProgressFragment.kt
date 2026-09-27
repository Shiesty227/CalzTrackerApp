package com.example.calztrackerapp.ui.progress

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.calztrackerapp.databinding.FragmentProgressBinding
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.text.SimpleDateFormat
import java.util.*

class ProgressFragment : Fragment() {

    private var _binding: FragmentProgressBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ProgressViewModel
    private var selectedDate: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[ProgressViewModel::class.java]

        // 📅 Date Picker
        binding.dateInput.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    val selectedCal = Calendar.getInstance().apply {
                        set(year, month, day)
                    }
                    selectedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selectedCal.time)
                    binding.dateInput.setText(selectedDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }

        // 💾 Save weight
        binding.saveWeightButton.setOnClickListener {
            val weightText = binding.weightInput.text.toString()
            val weight = weightText.toFloatOrNull()

            if (weight != null && !selectedDate.isNullOrEmpty()) {
                viewModel.saveWeight(weight, selectedDate!!)
                Toast.makeText(requireContext(), "Progress saved", Toast.LENGTH_SHORT).show()
                binding.weightInput.text?.clear()
                binding.dateInput.setText("")
                selectedDate = null
            } else {
                Toast.makeText(requireContext(), "Enter weight and pick a date", Toast.LENGTH_SHORT).show()
            }
        }

        // 📊 Load Chart Data
        viewModel.weightHistory.observe(viewLifecycleOwner) { entries ->
            if (!entries.isNullOrEmpty()) {
                val chartEntries = entries.mapIndexed { index, entry ->
                    Entry(index.toFloat(), entry.weight)
                }

                val dataSet = LineDataSet(chartEntries, "Weight Over Time").apply {
                    color = Color.BLUE
                    valueTextColor = Color.BLACK
                    lineWidth = 2f
                    circleRadius = 4f
                }

                binding.weightChart.apply {
                    data = LineData(dataSet)
                    description.text = "Progress"
                    invalidate() // Refresh chart
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
