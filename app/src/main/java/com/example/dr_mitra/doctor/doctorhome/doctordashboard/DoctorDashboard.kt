package com.example.dr_mitra.doctor.doctorhome.doctordashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.dr_mitra.R

import com.example.dr_mitra.databinding.FragmentDoctorDashboardBinding

import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.database.collection.LLRBNode.Color


class DoctorDashboard : Fragment() {

    private lateinit var binding: FragmentDoctorDashboardBinding
    private lateinit var pieChart:PieChart


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        binding= FragmentDoctorDashboardBinding.inflate(layoutInflater)
        pieChart=binding.pieChart



        setUpPieChart()
        return binding.root
    }

    private fun setUpPieChart(){

        //Make array of PieEntry
        val list:ArrayList<PieEntry> = ArrayList()

        list.add(PieEntry(10f))
        list.add(PieEntry(20f))
        list.add(PieEntry(15f))

        list.add(PieEntry(30f))
        list.add(PieEntry(15f))

        val pieDataset=PieDataSet(list,"List")

        // Define a custom color list for each label
        val colors: ArrayList<Int> = ArrayList()
        context?.let { ContextCompat.getColor(it, R.color.red) }?.let { colors.add(it) }       // Emergency
        context?.let { ContextCompat.getColor(it, R.color.tab_bg) }?.let { colors.add(it) }       // Emergency
        context?.let { ContextCompat.getColor(it, R.color.green) }?.let { colors.add(it) }       // Emergency
        context?.let { ContextCompat.getColor(it, R.color.blue) }?.let { colors.add(it) }       // Emergency
        context?.let { ContextCompat.getColor(it, R.color.tab_bg) }?.let { colors.add(it) }       // Emergency


        // Set the custom colors to the dataset
        pieDataset.colors = colors
        pieDataset.valueTextSize=15f



        val pieData=PieData(pieDataset)
        pieChart.data=pieData
        pieChart.description.text="Patient Stats"
        pieChart.centerText="Today Stats"
        pieChart.animateY(2000)
        pieChart.invalidate()
        setUpCustomLegend()


    }
    private fun setUpCustomLegend() {
        val labels = listOf("Emergency", "Rescheduled", "Confirmed", "Attended", "Cancelled")
        val colors = listOf(
            R.color.red,
            R.color.tab_bg,
            R.color.green,
            R.color.blue,
            R.color.grey
        )

        // Clear the layout before adding new views (in case of re-inflation)
        binding.customLegendLayout.removeAllViews()

        // Loop through labels and colors to create custom legend
        for (i in labels.indices) {
            // Create a horizontal LinearLayout for each legend item
            val legendItemLayout = LinearLayout(requireContext())
            legendItemLayout.orientation = LinearLayout.HORIZONTAL
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(0, 16, 0, 16) // Margin for spacing
            legendItemLayout.layoutParams = params

            // Create a View for the color circle
            val colorCircle = View(requireContext())
            val circleParams = LinearLayout.LayoutParams(40, 40)
            circleParams.setMargins(0, 0, 16, 0) // Margin between circle and label
            colorCircle.layoutParams = circleParams
            colorCircle.setBackgroundColor(ContextCompat.getColor(requireContext(), colors[i]))

            // Create a TextView for the label
            val labelText = TextView(requireContext())
            labelText.text = labels[i]
            labelText.textSize = 16f
            labelText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))

            // Add the color circle and label to the legend item layout
            legendItemLayout.addView(colorCircle)
            legendItemLayout.addView(labelText)

            // Add the legend item layout to the custom legend layout
            binding.customLegendLayout.addView(legendItemLayout)
        }
    }



}