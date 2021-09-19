package com.ajailani.resepy.util

import com.ajailani.resepy.R

fun categoryThumbGenerator(key: String) =
    when (key) {
        "dessert" -> R.drawable.thumb_dessert
        "masakan-hari-raya" -> R.drawable.thumb_masakan_hari_raya
        "masakan-tradisional" -> R.drawable.thumb_masakan_tradisional
        "makan-malam" -> R.drawable.thumb_menu_makan_malam
        "makan-siang" -> R.drawable.thumb_menu_makan_siang
        "resep-ayam" -> R.drawable.thumb_resep_ayam
        "resep-daging" -> R.drawable.thumb_resep_daging
        "resep-sayuran" -> R.drawable.thumb_resep_sayuran
        "resep-seafood" -> R.drawable.thumb_resep_seafood
        "sarapan" -> R.drawable.thumb_sarapan
        else -> R.drawable.thumb_default
    }