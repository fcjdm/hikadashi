package com.android.hikadashi.model.db

import android.os.Handler
import com.android.hikadashi.dto.Data
import com.android.hikadashi.model.server.JikanClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await

object DbFirestore {
    const val COLLECTION_USERS = "users"
    const val COLLECTION_ANIMES = "animes"
    val email = FirebaseAuth.getInstance().currentUser?.email.toString()

    suspend fun getByStatus(status: String): MutableList<Data> {
        val snapshot = FirebaseFirestore.getInstance().collection(COLLECTION_USERS)
            .document(email)
            .collection(COLLECTION_ANIMES)
            .whereEqualTo("status", status)
            .get()
            .await()
        val animes = mutableListOf<Data>()

        for (documentSnapshot in snapshot){
            val anime = JikanClient.service.getAnimeFullById(documentSnapshot.get("id") as String).data
            animes.add(anime)

            //Delay necesario porque la api solo permite 3 llamadas por segundo
            delay(334)
        }
        return animes
    }

    suspend fun getStatus(id: String): String {
        val snapshot = FirebaseFirestore.getInstance().collection(COLLECTION_USERS)
            .document(email)
            .collection(COLLECTION_ANIMES)
            .whereEqualTo("id", id)
            .get()
            .await()

        return if(!snapshot.isEmpty){
            snapshot.documents[0].get("status") as String
        }else{
            return "notInList"
        }

    }

    suspend fun addAnime(id: String, status: String){
        FirebaseFirestore.getInstance().collection(COLLECTION_USERS)
            .document(email)
            .collection(COLLECTION_ANIMES)
            .document(id)
            .set(hashMapOf(
                "id" to id,
                "status" to status
            ))

    }

    fun deleteAnime(id: String) {
        FirebaseFirestore.getInstance().collection(COLLECTION_USERS)
            .document(email)
            .collection(COLLECTION_ANIMES)
            .document(id)
            .delete()
    }

}