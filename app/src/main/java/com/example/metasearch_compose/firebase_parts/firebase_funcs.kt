package com.example.metasearch_compose.firebase_parts

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import com.example.metasearch_compose.parts.News
import com.example.metasearch_compose.parts.Sources
import com.example.metasearch_compose.parts.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask



val storage = FirebaseStorage.getInstance()
val db = FirebaseFirestore.getInstance()





internal fun createFirebaseAccount(
    email: String,
    password: String
) {
    FirebaseAuth
        .getInstance()
        .createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener{
            Log.d(TAG,"Inside_OnCompleteListener")
            Log.d(TAG,"Is succesful ${it.isSuccessful}")
        }
        .addOnFailureListener {
            Log.d(TAG, "Inside_OnFailureListene")
            Log.d(TAG, "Exception: ${it.message}")
            Log.d(TAG, "Exception: ${it.localizedMessage}")
        }
}

internal fun login(
    email: String,
    password: String
){
    FirebaseAuth
        .getInstance()
        .signInWithEmailAndPassword(email, password)
        .addOnCompleteListener{
            Log.d(TAG,"Inside_OnCompleteListener")
            Log.d(TAG,"Is succesful ${it.isSuccessful}")
        }
        .addOnFailureListener{
            Log.d(TAG,"Inside_OnFailureListene")
            Log.d(TAG,"Exception: ${it.message}")
            Log.d(TAG,"Exception: ${it.localizedMessage}")
        }
}

internal fun sendRecoveryEmail(
    email: String
){
    FirebaseAuth.getInstance()
        .sendPasswordResetEmail(email)
        .addOnCompleteListener{
            Log.d(TAG,"Inside_OnCompleteListener")
            Log.d(TAG,"Is succesful ${it.isSuccessful}")
        }
        .addOnFailureListener{
            Log.d(TAG,"Inside_OnFailureListene")
            Log.d(TAG,"Exception: ${it.message}")
            Log.d(TAG,"Exception: ${it.localizedMessage}")
        }
}

internal fun addUserData(user: Users){
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid
        val userRef = db.collection("users").document(userId!!)
        val storageRef = storage.reference
        val imageRef = storageRef.child("profile_pics/${FirebaseAuth.getInstance().currentUser?.uid}_profile_pic")

        val uploadTask: UploadTask = imageRef.putFile(Uri.parse(user.pictureUri))

        uploadTask.addOnSuccessListener {
            Log.d(TAG, "Profile image uploaded successfully")

            // Получаем ссылку на загруженное изображение
            imageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                val profileImageURL = downloadUri.toString()
                // Обновляем данные пользователя и ссылку на профильное изображение в базе данных Firestore
                userRef.set(
                    mapOf(
                        "name" to user.name,
                        "phone" to user.phone,
                        "birthdate" to user.birthDate,
                        "profileImageURL" to profileImageURL
                    )
                ).addOnSuccessListener {
                    Log.d(TAG, "User data added successfully")
                }.addOnFailureListener { e ->
                    Log.w(TAG, "Error adding user data", e)
                }

            }.addOnFailureListener { e ->
                Log.w(TAG, "Error getting download URL", e)
            }
        }.addOnFailureListener { e ->
            Log.w(TAG, "Error uploading profile image", e)
        }
}

internal fun updateUserData(user: Users){
    val currentUser = FirebaseAuth.getInstance().currentUser
    val userId = currentUser?.uid
    val userRef = db.collection("users").document(userId!!)
    val storageRef = storage.reference
    val imageRef = storageRef.child("profile_pics/${FirebaseAuth.getInstance().currentUser?.uid}_profile_pic")

    imageRef.delete()

    userRef.update(
        mapOf(
            "profileImageUrl" to ""
        )
    )

    val uploadTask: UploadTask = imageRef.putFile(Uri.parse(user.pictureUri))

    uploadTask.addOnSuccessListener {
        Log.d(TAG, "Profile image uploaded successfully")

        // Получаем ссылку на загруженное изображение
        imageRef.downloadUrl.addOnSuccessListener { downloadUri ->
            val profileImageURL = downloadUri.toString()
            // Обновляем данные пользователя и ссылку на профильное изображение в базе данных Firestore
            userRef.update(
                mapOf(
                    "name" to user.name,
                    "phone" to user.phone,
                    "birthdate" to user.birthDate,
                    "profileImageURL" to profileImageURL
                )
            ).addOnSuccessListener {
                Log.d(TAG, "User data updated successfully")
            }.addOnFailureListener { e ->
                Log.w(TAG, "Error updating user data", e)
            }

        }.addOnFailureListener { e ->
            Log.w(TAG, "Error getting download URL", e)
        }
    }.addOnFailureListener { e ->
        Log.w(TAG, "Error uploading profile image", e)
    }
}


fun getDataFromDB(callback: (Users) -> Unit) {
    val userId = FirebaseAuth.getInstance().currentUser?.uid

    db.collection("users").document(userId!!).get()
        .addOnSuccessListener { result ->
            val name = result.getString("name") ?: ""
            val phone = result.getString("phone") ?: ""
            val birthdate = result.getString("birthdate") ?: ""
            val pictureURI = result.getString("profileImageURL")?: ""

            val userData = Users(name, phone, birthdate, pictureURI)
            callback(userData)
        }
        .addOnFailureListener { e ->
            Log.w(TAG, "Error getting user data", e)
        }
}

fun addNews(){
    val ref = db.collection("news").document()

    ref.set(
        mapOf(
            "id" to ref.id,
            "newsTitle" to "Россия нашла альтернативу продаже пиломатериалов в Европу Кто теперь вместо Европы покупает российскую древесину и фанкряж",
            "newsShortenText" to "В 2023 году в топ-10 стран — импортеров российских пиломатериалов не осталось ни одной европейской страны. Российские лесопромышленники заменили рынки сбыта на азиатские страны, их доля в экспорте выросла до 98% Объем экспорта российских пиломатериалов в 2023 году снизился на 10% относительно предыдущего года и составил 20,7 млн куб. м, следует из статистики федерального лесоучетного учреждения «Рослесинфорг»",
            "newsFullText" to "В 2023 году в топ-10 стран — импортеров российских пиломатериалов не осталось ни одной европейской страны. Российские лесопромышленники заменили рынки сбыта на азиатские страны, их доля в экспорте выросла до 98%\n\nОбъем экспорта российских пиломатериалов в 2023 году снизился на 10% относительно предыдущего года и составил 20,7 млн куб. м, следует из статистики федерального лесоучетного учреждения «Рослесинфорг», с которой ознакомился РБК. В 2022 году объем экспорта пиломатериалов составил 23 млн куб. м.",
            "newsSource" to "КИЛ",
            "newsTheme" to "Россия",
            "newsDate" to "02 фев, 01:32",
            "imageSource" to "",
            "sourceIcon" to ""
        )
    )
}


fun getNews(callback: (News) -> Unit) {
    db.collection("news").document("Fpq02xy7EOLE9cYyqzZr").get()
        .addOnSuccessListener { result ->
            val newsSourceId = result.getString("newsSourceId") ?: ""
            db.collection("sources").document(newsSourceId).get()
                .addOnSuccessListener { sourceResult ->
                    val source = sourceResult.getString("name") ?: ""
                    val sourcePic = sourceResult.getString("image_pic") ?: ""
                    val sourceGot = Sources(source, sourcePic)
                    Log.d(TAG, "Successfully received source: $sourceGot")
                    val newsTitle = result.getString("newsTitle") ?: ""
                    val newsShortenText = result.getString("newsShortenText") ?: ""
                    val newsFullText = result.getString("newsFullText") ?: ""
                    val newsDate = result.getString("newsDate") ?: ""
                    val newsTheme = result.getString("newsTheme") ?: ""
                    val newsPic = result.getString("imageSource") ?: ""
                    val news = News(newsTitle, newsShortenText, newsFullText, newsDate, newsTheme, newsPic, source = sourceGot)
                    Log.d(TAG, "News data: $news")
                    callback(news)
                }
                .addOnFailureListener {
                    Log.d(TAG, "Failed to get source")
                }
        }
        .addOnFailureListener {
            Log.d(TAG, "Failed to get news")
        }
}

//private fun codeGenerator() : Int{
//    val random = Random.Default
//    return (100000..999999).random(random)
//}

