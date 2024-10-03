package com.example.dr_mitra.doctor.doctorhome.doctordashboard

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dr_mitra.R

import com.example.dr_mitra.databinding.FragmentDoctorDashboardBinding
import com.example.dr_mitra.doctor.doctorhome.doctordashboard.emergency.PatientEmergencyCardAdapter
import com.example.dr_mitra.doctor.doctorhome.doctordashboard.emergency.PatientEmergencyCardItem

import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry


class DoctorDashboard : Fragment() {

    private lateinit var binding: FragmentDoctorDashboardBinding
    private lateinit var pieChart:PieChart


    //Emergency Adapter
    private lateinit var emergencyCardAdapter: PatientEmergencyCardAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        // Inflate the layout for this fragment
        binding= FragmentDoctorDashboardBinding.inflate(layoutInflater)
        pieChart=binding.pieChart


        setUpEmergencyCard()
        setUpPieChart()
        return binding.root
    }
    private fun setUpEmergencyCard(){
// Create sample data for RecyclerView
        val cardItems = listOf(
            PatientEmergencyCardItem("John Doe", "Heart Failure", "20 Min Ago", "Gomti Nagar", R.drawable.img),
            PatientEmergencyCardItem("Jane Smith", "Asthma", "10 Min Ago", "Hazratganj", R.drawable.img),
            // Add more items here
        )

        // Set up adapter
        emergencyCardAdapter = PatientEmergencyCardAdapter(cardItems)

        // Set up RecyclerView with horizontal layout manager
        binding.doctorDashboardRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = this@DoctorDashboard.emergencyCardAdapter
        }
    }

    private fun setUpPieChart(){

        //Make array of PieEntry
        val list:ArrayList<PieEntry> = ArrayList()

        list.add(PieEntry(10f))
        list.add(PieEntry(20f))
        list.add(PieEntry(15f))
        list.add(PieEntry(12f))

        list.add(PieEntry(30f))
        list.add(PieEntry(15f))

        val pieDataset=PieDataSet(list,"List")

        // Define a custom color list for each label
        val colors: ArrayList<Int> = ArrayList()
        context?.let { ContextCompat.getColor(it, R.color.emergency) }?.let { colors.add(it) }       // Emergency
        context?.let { ContextCompat.getColor(it, R.color.pending) }?.let { colors.add(it) }       //pending
        context?.let { ContextCompat.getColor(it, R.color.confirmed) }?.let { colors.add(it) }  //confirmed
        context?.let { ContextCompat.getColor(it, R.color.rescheduled) }?.let { colors.add(it) }   // rescheduled
        context?.let { ContextCompat.getColor(it, R.color.attended) }?.let { colors.add(it) }       //Attended
        context?.let { ContextCompat.getColor(it, R.color.cancelled) }?.let { colors.add(it) }       // Cancelled


        // Set the custom colors to the dataset
        pieDataset.colors = colors
        pieDataset.valueTextSize=15f



        val pieData=PieData(pieDataset)
        pieChart.data=pieData

        pieChart.centerText="Today Stats"
        pieChart.animateY(2000)
        pieChart.legend.isEnabled = false

        pieChart.invalidate()
        setUpCustomLegend()


    }
    private fun setUpCustomLegend() {
        val labels = listOf("Emergency","Pending", "Confirmed", "Rescheduled", "Attended", "Cancelled")
        val colors = listOf(
            R.color.emergency,
            R.color.pending,
            R.color.confirmed,
            R.color.rescheduled,
            R.color.attended,
            R.color.cancelled
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
            params.setMargins(0, 20, 0, 20) // Margin for spacing
            legendItemLayout.layoutParams = params

            // Create a View for the color circle
            val colorCircle = View(requireContext())
            val circleParams = LinearLayout.LayoutParams(40, 40)
            circleParams.setMargins(0, 0, 25, 0) // Margin between circle and label
            colorCircle.layoutParams = circleParams

            // Create a circular drawable programmatically
            val drawable = GradientDrawable()
            drawable.shape = GradientDrawable.OVAL // Set shape to oval (circle)
            drawable.setColor(ContextCompat.getColor(requireContext(), colors[i])) // Set the color

            // Set the drawable as the background of the colorCircle view
            colorCircle.background = drawable

            // Create a TextView for the label
            val labelText = TextView(requireContext())
            labelText.text = labels[i]
            labelText.textSize = 14f
            labelText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))

            // Add the color circle and label to the legend item layout
            legendItemLayout.addView(colorCircle)
            legendItemLayout.addView(labelText)

            // Add the legend item layout to the custom legend layout
            binding.customLegendLayout.addView(legendItemLayout)
        }
    }



}