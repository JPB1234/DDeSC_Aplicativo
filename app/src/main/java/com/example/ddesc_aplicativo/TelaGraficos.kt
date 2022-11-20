package com.example.ddesc_aplicativo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate

class TelaGraficos : AppCompatActivity() {

    lateinit var pieChart: PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_graficos)

        pieChart = findViewById(R.id.pie_chart)

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

        pieChart.data = pieData

        pieChart.description.text="Pie Chart"

        pieChart.centerText="List"

        pieChart.animateY(2000)

    }
}