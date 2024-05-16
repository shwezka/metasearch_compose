package com.example.metasearch_compose.parts


data class Users(
    var name: String = "",
    var phone: String = "",
    var birthDate: String = "",
    var pictureUri: String = ""
){
    fun updateUser(
        newName: String,
        newPhone: String,
        newBirthdate: String,
        newPictureUri: String
    ){
        name = newName
        phone = newPhone
        birthDate = newBirthdate
        pictureUri = newPictureUri
    }

}

data class Sources(
    val sourceName: String = "",
    var sourcePic: String = ""
)

data class News(
    val newsTitle: String = "Тестовый заголовок",
    val newsShortenText: String = "Это укороченный текст новости",
    val newsText: String = "",
    val newsDate: String = "02 фев, 01:32",
    val newsTheme: String = "Россия",
    val newsImage: String = "",
    val source: Sources = Sources(),
)