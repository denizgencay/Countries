package com.example.countries.ui.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.R

class CountriesRecyclerAdapter(): RecyclerView.Adapter<CountriesRecyclerAdapter.CountriesViewHolder>() {

    private var countriesList: ArrayList<String> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_card,parent,false)
        return CountriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        holder.bind(countriesList[position])
    }

    override fun getItemCount(): Int {
        return countriesList.size
    }

    class CountriesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(country:String){
            val countryText = itemView.findViewById<TextView>(R.id.country_name)
            countryText.text = country
        }
    }

    fun setCountryListData(countryList: ArrayList<String>){
        this.countriesList = countryList
    }

}