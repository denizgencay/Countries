package com.example.countries.domain.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country_table")
data class Country(
    @NonNull
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val code: String = ""
)
