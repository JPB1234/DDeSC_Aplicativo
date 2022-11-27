package com.example.ddesc_aplicativo.ui.graficoDeGastos

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ddesc_aplicativo.databinding.FragmentGraficoDeGastosBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate

class GraficoDeGastosFragment : Fragment() {
    private lateinit var binding: FragmentGraficoDeGastosBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGraficoDeGastosBinding.inflate(inflater, container, false)
        return binding.root
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list: ArrayList<PieEntry> = ArrayList()

        list.add((PieEntry(100f, "100")))
        list.add((PieEntry(101f, "101")))
        list.add((PieEntry(102f, "102")))
        list.add((PieEntry(103f, "103")))
        list.add((PieEntry(104f, "104")))

        val pieDataSet= PieDataSet(list, "List")

        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS, 255)

        pieDataSet.valueTextSize=15f

        pieDataSet.valueTextColor= Color.BLACK

        val pieData= PieData(pieDataSet)

        binding.pieChart.data = pieData

        binding.pieChart.description.text="Pie Chart"

        binding.pieChart.centerText="List"

        binding.pieChart.animateY(2000)
    }

}


