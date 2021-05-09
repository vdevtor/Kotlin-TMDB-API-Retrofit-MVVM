package com.example.tmdbmvvm.utils

fun formatK(number: Int) : String {
    var numberString = "";
    if (Math.abs(number / 1000000) > 1) {
        numberString = (number / 1000000).toString() + "m";
    } else if (Math.abs(number / 1000) > 1) {
        numberString = (number / 1000).toString() + "k";
    } else {
        numberString = number.toString();
    }
    return numberString
}