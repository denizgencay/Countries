package com.example.countries.ui.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.R
import com.example.countries.domain.model.Country

class CountriesRecyclerAdapter: RecyclerView.Adapter<CountriesRecyclerAdapter.CountriesViewHolder>() {

    private var countriesList: List<Country>? = arrayListOf()
    private var listener: OnCardListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_card,parent,false)
        return CountriesViewHolder(view,listener!!)
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        holder.bind(countriesList?.get(position) ?: Country())
    }

    override fun getItemCount(): Int {
        return countriesList?.size ?: 0
    }

    class CountriesViewHolder(itemView: View, listener: OnCardListener): RecyclerView.ViewHolder(itemView) {
        private val countryText: TextView = itemView.findViewById(R.id.country_name)
        private val button: ImageView = itemView.findViewById(R.id.save_button)
        fun bind(country:Country){
            countryText.text = country.name
        }
        init {
            itemView.setOnClickListener {
                listener.onCardClicked(adapterPosition)
            }
            button.setOnClickListener{
                listener.onLikeClicked(adapterPosition)
            }

            button.setOnClickListener{
                listener.onDislikeClicked(adapterPosition)
            }
        }

    }

    fun setCountryListData(countryList: List<Country>?){
        this.countriesList = countryList
    }

    interface OnCardListener{
        fun onCardClicked(position: Int)
        fun onLikeClicked(position: Int)
        fun onDislikeClicked(position: Int)
    }

    fun setOnCardClickedListener(listener: OnCardListener){
        this.listener = listener
    }


}