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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.Vector


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
        .addOnSuccessListener { new ->
            val name = new.getString("name") ?: ""
            val phone = new.getString("phone") ?: ""
            val birthdate = new.getString("birthdate") ?: ""
            val pictureURI = new.getString("profileImageURL")?: ""

            val userData = Users(name, phone, birthdate, pictureURI)
            callback(userData)
        }
        .addOnFailureListener { e ->
            Log.w(TAG, "Error getting user data", e)
        }
}

fun addNewsToFavs(news:News){
    val userId = FirebaseAuth.getInstance().currentUser?.uid
    val ref = db.collection("favs").document(userId!!).collection("favNews").document()

    ref.set(
        mapOf(
            "newsId" to news.newsId,
            "newsTitle" to news.newsTitle,
            "newsShortenText" to news.newsShortenText,
            "newsFullText" to news.newsText,
            "newsSourceId" to news.source.sourceId,
            "newsTheme" to news.newsTheme,
            "newsDate" to news.newsDate,
            "imageSource" to news.newsImage
        )
    )
}


fun getNews(
    callback: (News) -> Unit,
    newId: String
    ) {
    db.collection("news").document(newId).get()
        .addOnSuccessListener { new ->
            val newsSourceId = new.getString("newsSourceId") ?: ""
            db.collection("sources").document(newsSourceId).get()
                .addOnSuccessListener { sourceResult ->
                    val source = sourceResult.getString("name") ?: ""
                    val sourcePic = sourceResult.getString("image_pic") ?: ""
                    val sourceGot = Sources(newsSourceId,source, sourcePic)
                    Log.d(TAG, "Successfully received source: $sourceGot")
                    val newsTitle = new.getString("newsTitle") ?: ""
                    val newsShortenText = new.getString("newsShortenText") ?: ""
                    val newsFullText = new.getString("newsFullText") ?: ""
                    val newsDate = new.getString("newsDate") ?: ""
                    val newsTheme = new.getString("newsTheme") ?: ""
                    val newsPic = new.getString("imageSource") ?: ""
                    val news = News(newId,newsTitle, newsShortenText, newsFullText, newsDate, newsTheme, newsPic, source = sourceGot)
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

fun getAllNews(callback: (Vector<News>) -> Unit) = CoroutineScope(Dispatchers.IO).launch {
    val vector = Vector<News>()

    var counter = 0

    val news = db.collection("news").get().await()

    val deferredTasks = news.documents.map { new ->
        async {
            val newId = new.id
            val newsSourceId = new.getString("newsSourceId") ?: ""
            val sourceResult = db.collection("sources").document(newsSourceId).get().await()
            val source = sourceResult.getString("name") ?: ""
            val sourcePic = sourceResult.getString("image_pic") ?: ""
            val sourceGot = Sources(newsSourceId,source, sourcePic)

            val newsTitle = new.getString("newsTitle") ?: ""
            val newsShortenText = new.getString("newsShortenText") ?: ""
            val newsFullText = new.getString("newsFullText") ?: ""
            val newsDate = new.getString("newsDate") ?: ""
            val newsTheme = new.getString("newsTheme") ?: ""
            val newsPic = new.getString("imageSource") ?: ""
            val newsGot = News(newId, newsTitle, newsShortenText, newsFullText, newsDate, newsTheme, newsPic, source = sourceGot)
            vector.setSize(news.size())
            vector[counter] = newsGot
            counter++
        }
    }

    deferredTasks.awaitAll()

    callback(vector)
}

fun getFavNew(
    callback: (News) -> Unit,
    newId: String
) {
    val userId = FirebaseAuth.getInstance().currentUser?.uid
    db.collection("favs").document(userId!!).collection("favNews").document(newId).get()
        .addOnSuccessListener { new ->
            val newsSourceId = new.getString("newsSourceId") ?: ""
            db.collection("sources").document(newsSourceId).get()
                .addOnSuccessListener { sourceResult ->
                    val source = sourceResult.getString("name") ?: ""
                    val sourcePic = sourceResult.getString("image_pic") ?: ""
                    val sourceGot = Sources(newsSourceId,source, sourcePic)
                    Log.d(TAG, "Successfully received source: $sourceGot")
                    val newsTitle = new.getString("newsTitle") ?: ""
                    val newsShortenText = new.getString("newsShortenText") ?: ""
                    val newsFullText = new.getString("newsFullText") ?: ""
                    val newsDate = new.getString("newsDate") ?: ""
                    val newsTheme = new.getString("newsTheme") ?: ""
                    val newsPic = new.getString("imageSource") ?: ""
                    val news = News(newId,newsTitle, newsShortenText, newsFullText, newsDate, newsTheme, newsPic, source = sourceGot)
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

fun getFavs(callback: (Vector<News>) -> Unit) = CoroutineScope(Dispatchers.IO).launch{
    val vector = Vector<News>()
    val userId = FirebaseAuth.getInstance().currentUser?.uid

    var counter = 0

    val news = db.collection("favs").document(userId!!).collection("favNews").get().await()

    val deferredTasks = news.documents.map { new ->
        async {
            Log.d(TAG, "${new.data}")
            val newId = new.id
            val newsSourceId = new.getString("newsSourceId") ?: ""
            val sourceResult = db.collection("sources").document(newsSourceId).get().await()
            val source = sourceResult.getString("name") ?: ""
            val sourcePic = sourceResult.getString("image_pic") ?: ""
            val sourceGot = Sources(newsSourceId,source, sourcePic)

            val newsTitle = new.getString("newsTitle") ?: ""
            val newsShortenText = new.getString("newsShortenText") ?: ""
            val newsFullText = new.getString("newsFullText") ?: ""
            val newsDate = new.getString("newsDate") ?: ""
            val newsTheme = new.getString("newsTheme") ?: ""
            val newsPic = new.getString("imageSource") ?: ""
            val newsGot = News(newId, newsTitle, newsShortenText, newsFullText, newsDate, newsTheme, newsPic, source = sourceGot)
            vector.setSize(news.size())
            vector[counter] = newsGot
            counter++
        }
    }

    deferredTasks.awaitAll()

    callback(vector)
}

fun deleteFromFavs(
    news: News
){
    val userId = FirebaseAuth.getInstance().currentUser?.uid

    val favRef = db.collection("favs").document(userId!!).collection("favNews").document(news.newsId).delete()
}

