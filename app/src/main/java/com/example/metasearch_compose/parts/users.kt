package com.example.metasearch_compose.parts

import androidx.compose.ui.tooling.preview.PreviewParameterProvider


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
    val sourceId: String ="",
    val sourceName: String = "",
    var sourcePic: String = ""
)

data class News(
    val newsId: String = "",
    val newsTitle: String = "",
    val newsShortenText: String = "",
    val newsText: String = "",
    val newsDate: String = "",
    val newsTheme: String = "",
    val newsImage: String = "",
    val source: Sources = Sources(),
)

val previewSource = Sources(
    "",
    "КИЛ",
    "https://firebasestorage.googleapis.com/v0/b/metasearch-compose.appspot.com/o/source_icons%2FKIL_logo.svg%201.png?alt=media&token=efd50bac-2c9b-4539-b979-105f0c182064"
)

val  previewNew = News(
    "ll",
    "Россия нашла альтернативу продаже пиломатериалов в Европу Кто теперь вместо Европы покупает российскую древесину и фанкряж",
    "В 2023 году в топ-10 стран — импортеров российских пиломатериалов не осталось ни одной европейской страны. Российские лесопромышленники заменили рынки сбыта на азиатские страны, их доля в экспорте выросла до 98% Объем экспорта российских пиломатериалов в 2023 году снизился на 10% относительно предыдущего года и составил 20,7 млн куб. м, следует из статистики федерального лесоучетного учреждения «Рослесинфорг»...",
    "В 2023 году в топ-10 стран — импортеров российских пиломатериалов не осталось ни одной европейской страны. Российские лесопромышленники заменили рынки сбыта на азиатские страны, их доля в экспорте выросла до 98% Объем экспорта российских пиломатериалов в 2023 году снизился на 10% относительно предыдущего года и составил 20,7 млн куб. м, следует из статистики федерального лесоучетного учреждения «Рослесинфорг», с которой ознакомился РБК. В 2022 году объем экспорта пиломатериалов составил 23 млн куб. м.",
    "02 фев, 01:32",
    "Россия",
    "https://firebasestorage.googleapis.com/v0/b/metasearch-compose.appspot.com/o/news_pictures%2Fnews1_image.jpg?alt=media&token=df80852c-0d23-4215-8330-6ce9a29b4741",
    previewSource
    )

class NewsProvider : PreviewParameterProvider<News> {
    override val values: Sequence<News> = listOf(previewNew).asSequence()
}