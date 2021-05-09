package com.example.tmdbmvvm.data.business

import androidx.appcompat.app.AppCompatActivity
import com.example.tmdbmvvm.databinding.ActivityHomeBinding
import com.example.tmdbmvvm.utils.Constants.Companion.isClicked
import com.example.tmdbmvvm.utils.SetCheckedHeart
import com.example.tmdbmvvm.utils.SetUncheckedHeart

class OnclickButton() : AppCompatActivity() {

    fun onHeartClick(binding: ActivityHomeBinding) {

        if (!isClicked) {
            binding.mainHeart.SetCheckedHeart()
            binding.secundaryHeart.SetCheckedHeart()
            isClicked = true

        } else {
            binding.mainHeart.SetUncheckedHeart()
            binding.secundaryHeart.SetUncheckedHeart()
            isClicked = false
        }
    }
}