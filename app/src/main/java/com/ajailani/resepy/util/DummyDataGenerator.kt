package com.ajailani.resepy.util

import com.ajailani.resepy.data.model.Author
import com.ajailani.resepy.data.model.Category
import com.ajailani.resepy.data.model.Recipe
import com.ajailani.resepy.data.model.response.RecipeResponse

fun generateRecipeResponse() =
    RecipeResponse(
        title = "Resep Buntut Sapi Kuah Asam Padeh Khas Minang",
        thumb = "https://www.masakapahariini.com/wp-content/uploads/2019/09/shutterstock_586982774-780x440.jpg",
        key = "test",
        servings = "4 Porsi",
        times = "1jam",
        difficulty = "Medium"
    )

fun generateRecipe() =
    Recipe(
        title = "Resep Buntut Sapi Kuah Asam Padeh Khas Minang",
        thumb = "https://www.masakapahariini.com/wp-content/uploads/2019/09/shutterstock_586982774-780x440.jpg",
        servings = "4 Porsi",
        times = "1jam",
        difficulty = "Medium",
        author = generateAuthor(),
        desc = "Resep peyek kacang hijau yang kriuk dan pedas selalu jadi kletikan atau camilan yang menambah selera makan. Selain sebagai kudapan, peyek juga senantiasa dijadikan pelengkap makan. Dimulai dari peyek kacang tanah, peyek ikan teri, peyek rebon, hingga peyek udang yang sangat akrab disajikan di pilihan lauk nasi Padang.Sebelum meracik adonan peyek, ada beberapa tips yang mungkin bisa membantu kamu menghasilkan peyek sedap yang kriuk.Artinya sebelum memakai tepung beras, ada baiknya kamu memastikan tepung beras baik dan layak untuk diolah. Maksudnya, tepung beras tersebut tidak apek, tidak berkutu, apalagi langu.Adonan yang mulus tanpa adanya tepung yang menggumpal tentu akan menghasilkan peyek yang konsisten. Adonan juga tak boleh terlalu kental agar peyek bisa tipis dan garing saat digoreng.Jaga suhu minyak agar tak terlalu panas. Minyak yang terlalu panas akan membuat peyek keburu kecokelatan atau bahkan gosong disaat peyek belum garing sempurna. Seperti halnya membuat camilan garing lainnya, membuat peyek butuh kesabaran ekstra. Apalagi kalau kamu membuat adonan yang cukup banyak.Ketiga jenis wajan cekung ini dapat menahan adonan peyek di permukaannya  hingga membuat peyek terlepas bersama minyak dan garing. Wajan besi cor yang dimaksud disebut juga cast iron. Kalau kamu bingung, wajan ini berbahan tebal dan biasanya akrab digunakan oleh pedagang bakmi Jawa atau tongseng.Nah, berbekal tips ini, nampaknya kamu sudah siap untuk membuat resep Peyek Kacang Hijau di rumah ataupun resep camilan lainnya. Yuk, siapkan bahannya!",
        ingredients = listOf(
            "100 g kacang hijau, rendam 3 jam, cuci bersih",
            "200 g tepung beras",
            "2 sdm tepung maizena",
            "3 buah cabai rawit merah, cincang halus",
            "1 sdm Royco Kaldu Ayam",
            "6 lembar daun jeruk, iris tipis",
            "350 ml air",
            "minyak, untuk menggoreng",
            "3 siung bawang putih",
            "5 butir kemiri",
            "3 buah cabai rawit merah",
            "1 buah cabai merah besar",
            "1 sdm ketumbar",
            "1 cm kencur"
        ),
        steps = listOf(
            "1 Rebus kacang hijau hingga empuk, angkat. Tiriskan dan sisihkan.",
            "2 Aduk rata tepung beras, tepung maizena, dan Royco Kaldu Ayam dalam mangkuk besar. Tuang air sambil diaduk hingga adonan rata. ",
            "3 Masukkan bumbu halus, cabai rawit cincang, kacang hijau, dan daun jeruk. Aduk rata.",
            "4 Panaskan minyak, ambil masukkan adonan peyek menggunakan sendok sayur sebanyak beberapa sendok ke sisi wajan berbeda. Goreng hingga kecokelatan dan garing. Angkat dan tiriskan. ",
            "5 Sajikan."
        )
    )

fun generateRecipes() =
    listOf(
        generateRecipeResponse(),
        generateRecipeResponse(),
        generateRecipeResponse(),
        generateRecipeResponse(),
        generateRecipeResponse()
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
        generateCategory(),
        generateCategory()
    )

fun generateAuthor() =
    Author(
        user = "Renata Moeloeke",
        datePublished = "Agustus, 13 2021"
    )