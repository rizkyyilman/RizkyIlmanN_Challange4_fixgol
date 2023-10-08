package com.catnip.rizkyilmann_challange4.data

import com.catnip.rizkyilmann_challange4.model.DetailMenu


interface MenuDataSource{
    fun getDetailMenu(): List<DetailMenu>
}

class MenuDataSourceImpl() : MenuDataSource {
    override fun getDetailMenu(): List<DetailMenu> = listOf(
        DetailMenu(
            id = 1,
            position = 1,
            name = "Burger",
            price = 15.000,
            imgUrl = "https://raw.githubusercontent.com/rizkyyilman/Challange3/feature/feature_detail/app/src/main/res/drawable/burger.jpg",
            desc = "Perpaduan antara roti dengan ham yang memiliki cita rasa tiada tara !"
        ),
        DetailMenu(
            id = 2,
            position = 2,
            name = "Ayam Goyeng",
            price = 20.000,
            imgUrl = "https://raw.githubusercontent.com/rizkyyilman/Challange3/feature/feature_detail/app/src/main/res/drawable/ayamgoreng.jpg",
            desc = "Ayam yang dibalur dengan tepung dan bumbu rempah pilihan khas nusantara"
        ),
        DetailMenu(
            id = 3,
            position = 3,
            name = "Dumpling",
            price = 10.000,
            imgUrl = "https://raw.githubusercontent.com/rizkyyilman/Challange3/feature/feature_detail/app/src/main/res/drawable/dumpling.jpg",
            desc = "Nama lain Siomay"
        ),
        DetailMenu(
            id = 4,
            position = 4,
            name = "Mie Goreng",
            price = 25.000,
            imgUrl = "https://raw.githubusercontent.com/rizkyyilman/Challange3/feature/feature_detail/app/src/main/res/drawable/miegoreng.jpg",
            desc = "Makanan Terenak Sejagat Raya"
        ),
        DetailMenu(
            id = 5,
            position = 5,
            name = "Kopi",
            price = 20.000,
            imgUrl = "https://raw.githubusercontent.com/rizkyyilman/Challange3/feature/feature_detail/app/src/main/res/drawable/kopi.jpg",
            desc = "Kopi yang bikin badan jadi ga ngantuk !"
        ),
        DetailMenu(
            id = 6,
            position = 6,
            name = "Kenteng Goreng",
            price = 15.000,
            imgUrl = "https://raw.githubusercontent.com/rizkyyilman/Challange3/feature/feature_detail/app/src/main/res/drawable/kentanggoreng.jpg",
            desc = " Kentang Goreng yang digoreng dengan minyak pilihan"
        ),
        DetailMenu(
            id = 7,
            position = 7,
            name = "Sate Ayam",
            price = 15.000,
            imgUrl = "https://raw.githubusercontent.com/rizkyyilman/Challange3/feature/feature_detail/app/src/main/res/drawable/sateayam.jpg",
            desc = "Sate ayam seperti biasanya"
        ),
        DetailMenu(
            id = 8,
            position = 8,
            name = "Jus Strawberry",
            price = 15.000,
            imgUrl = "https://raw.githubusercontent.com/rizkyyilman/Challange3/feature/feature_detail/app/src/main/res/drawable/jusstrawberry.jpg",
            desc = "Buah strawberry yang diblender dengan air dan susu kental manis"
        )
    )
}
