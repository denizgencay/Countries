package com.example.countries.ui.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.R
import com.example.countries.domain.model.Country

class CountriesRecyclerAdapter(): RecyclerView.Adapter<CountriesRecyclerAdapter.CountriesViewHolder>() {

    private var countriesList: List<Country>? = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_card,parent,false)
        return CountriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        holder.bind(countriesList?.get(position) ?: Country())
    }

    override fun getItemCount(): Int {
        return countriesList?.size ?: 0
    }

    class CountriesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(country:Country){
            val countryText = itemView.findViewById<TextView>(R.id.country_name)
            countryText.text = country.name
        }

    }

    fun setCountryListData(countryList: List<Country>?){
        this.countriesList = countryList
    }
}