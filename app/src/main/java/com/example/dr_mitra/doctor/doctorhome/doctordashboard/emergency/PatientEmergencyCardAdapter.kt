package com.example.dr_mitra.doctor.doctorhome.doctordashboard.emergency

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dr_mitra.databinding.FragmentDoctorDashboardBinding
import com.example.dr_mitra.databinding.PatientEmergencyCardBinding


//Create a Card Item
class PatientEmergencyCardAdapter(private val cardItem: List<PatientEmergencyCardItem>):RecyclerView.Adapter<PatientEmergencyCardAdapter.CardItemViewHolder>() {

    // ViewHolder class for holding views
    class CardItemViewHolder(val binding:PatientEmergencyCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PatientEmergencyCardBinding.inflate(inflater, parent, false)
        return CardItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardItemViewHolder, position: Int) {
        val currentItem = cardItem[position]
        with(holder.binding) {
            // Binding data to views
            emergencyPatientName.text = currentItem.patientName
            emergencyPatientDisease.text = currentItem.patientDisease
            emergencyPatientTime.text = currentItem.timeAgo
            emergencyPatientLocation.text = currentItem.location
            emergencyPatientImage.setImageResource(currentItem.patientImage)

        }
    }
    override fun getItemCount(): Int = cardItem.size
}