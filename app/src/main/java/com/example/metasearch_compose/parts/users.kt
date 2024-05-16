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