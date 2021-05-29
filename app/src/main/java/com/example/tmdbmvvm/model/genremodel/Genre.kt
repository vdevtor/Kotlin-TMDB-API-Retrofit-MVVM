package com.example.tmdbmvvm.model.genremodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(val id: Int, val name: String): Parcelable