package com.example.tmdbmvvm.model.GeneroModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(val id: Int, val name: String): Parcelable