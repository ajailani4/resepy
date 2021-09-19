package com.ajailani.resepy.util

import com.ajailani.resepy.data.model.Author
import com.ajailani.resepy.data.model.Category
import com.ajailani.resepy.data.model.Recipe

fun generateRecipe() =
    Recipe(
        title = "Resep Buntut Sapi Kuah Asam Padeh Khas Minang",
        thumb = "https://www.masakapahariini.com/wp-content/uploads/2019/09/shutterstock_586982774-780x440.jpg",
        servings = "4 Porsi",
        time = "1jam",
        difficulty = "Mudah",
        author = Author(
            user = "Wina",
            datePublished = "September 23, 2019"
        ),
        desc = "Resep buntut sapi kuah asam padeh yang segar siap dicoba di dapur akhir pekan ini. Siapa yang bisa menolak kelezatan kuliner khas Minang ini? Sajikan selagi hangat dengan nasi hangat maupun ketupat.Terkenal akan kesegarannya, menu ini memiliki banyak penggemar di Indonesia. Pada umumnya, boga bahari seperti ikan yang menjadi bahan utamanya. Kali ini, kita akan menggunakan buntut sapi yang bisa memberikan rasa yang otentik. Kandungan gelatin alias protein kolagen pada buntut akan keluar setelah diproses dalam waktu lama. Kandungan ini yang nantinya membuat buntut empuk dan menimbulkan rasa gurih. Lalu rasa ini bertemu dengan bumbu cabai yang cukup pedas serta asam dari belimbing sayur. Istimewa bukan menu ini, yuk segera praktikkan resep ini di rumah.Selain buntut kuah asam padeh, ada kuliner dari Sumatra Barat lainnya yang bisa dicoba seperti resep soto Padang, bebek lado mudo, dan telur dadar Padang.",
        ingredient = listOf(
            "1 kg buntut sapi, rebus hingga setengah matang, tiriskan",
            "1 batang serai, memarkan",
            "3 cm lengkuas, memarkan",
            "3 buah asam kandis",
            "5 lembar daun jeruk, buang tulang dan iris memanjang",
            "3 lembar daun salam",
            "5 buah belimbing sayur, belah 2",
            "1 lembar daun kunyit",
            "2 L air mendidih",
            "1 sdt Royco Kaldu Sapi",
            "2 sdm Bango Kecap Manis",
            "2 sdm minyak, untuk menumis",
            "200 g cabai merah keriting",
            "15 butir bawang merah",
            "10 siung bawang putih"
        ),
        step = listOf(
            "1 Panaskan minyak, tumis bumbu halus hingga harum. Angkat dan sisihkan.",
            "2 Panaskan air dalam panci, masukkan buntut dan semua bahan kecuali belimbing sayur. Aduk rata.",
            "3 Masukkan tumisan bumbu, Royco Kaldu Sapi dan Bango Kecp Manis, aduk rata. Masak hingga kuah mengental dan buntut empuk.",
            "4 Masukkan potongan belimbing sayur, aduk rata. Angkat dan sajikan."
        )
    )

fun generateRecipes() =
    listOf(
        generateRecipe(),
        generateRecipe(),
        generateRecipe(),
        generateRecipe()
    )

fun generateCategory() =
    Category(
        category = "Dessert",
        key = "dessert"
    )

fun generateCategories() =
    listOf(
        generateCategory(),
        generateCategory(),
        generateCategory(),
        generateCategory()
    )