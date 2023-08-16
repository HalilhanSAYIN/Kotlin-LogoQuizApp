package com.halilhansayingithub.roomkullanimi


import androidx.room.Dao
import androidx.room.Query


@Dao
interface TeamsDao {

    @Query("SELECT * FROM logos")
    suspend fun allTeams() : List<Logos>

    @Query("SELECT * FROM logos ORDER BY RANDOM() LIMIT 5")
    suspend fun loadQuestion() : List<Logos>


    @Query("SELECT * FROM logos WHERE logo_id != :logoId ORDER BY RANDOM() LIMIT 3")
    suspend fun getThreeRandomWrongLogos(logoId: Int): List<Logos>



}