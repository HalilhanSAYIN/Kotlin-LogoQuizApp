package com.halilhansayingithub.roomkullanimi

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Logos :: class], version = 1)
abstract class Database : RoomDatabase(){
    abstract fun getTeamsDao() : TeamsDao

    companion object{
        var INSTANCE:com.halilhansayingithub.roomkullanimi.Database? = null

        fun databaseAccess(context: Context): com.halilhansayingithub.roomkullanimi.Database? {
            if (INSTANCE== null){
                synchronized(Database::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        com.halilhansayingithub.roomkullanimi.Database::class.java,
                        "teams.sqlite").createFromAsset("teams.sqlite").build()
                }
            }
            return INSTANCE
        }
    }
}