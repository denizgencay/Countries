package com.example.countries.ui.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.R
import com.example.countries.domain.model.Country
import com.example.countries.util.SelectedCountryHelper

class CountriesRecyclerAdapter: RecyclerView.Adapter<CountriesRecyclerAdapter.CountriesViewHolder>() {

    private var countriesList: List<Country>? = arrayListOf()
    private var selectedCountriesList: List<Country>? = arrayListOf()
    private var listener: OnCardListener? = null
    private val selectedCountryHelper = SelectedCountryHelper()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_card,parent,false)
        return CountriesViewHolder(view,listener!!)
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        val isSelected = selectedCountriesList?.let {
            selectedCountryHelper.isSelected(
                it,
                countriesList?.get(position) ?: Country()
            )
        }
        holder.bind(countriesList?.get(position) ?: Country())
        if (isSelected == true){
            holder.button.setImageResource(R.drawable.ic_baseline_bookmark_filled)
        }else{
            holder.button.setImageResource(R.drawable.ic_baseline_bookmark_un_filled)
        }

    }

    override fun getItemCount(): Int {
        return countriesList?.size ?: 0
    }

    class CountriesViewHolder(itemView: View, listener: OnCardListener): RecyclerView.ViewHolder(itemView) {
        private val countryText: TextView = itemView.findViewById(R.id.country_name)
        val button: ImageView = itemView.findViewById(R.id.save_button)
        fun bind(country:Country){
            countryText.text = country.name
        }
        init {
            itemView.setOnClickListener {
                listener.onCardClicked(adapterPosition)
            }
            button.setOnClickListener{
                listener.onClicked(adapterPosition)
            }
        }

    }

    fun setCountryListData(countryList: List<Country>?){
        this.countriesList = countryList
    }

    fun setSelectedCountryListData(selectedCountry: List<Country>){
        this.selectedCountriesList = selectedCountry
    }

    interface OnCardListener{
        fun onCardClicked(position: Int)
        fun onClicked(position: Int)
    }

    fun setOnCardClickedListener(listener: OnCardListener){
        this.listener = listener
    }


}