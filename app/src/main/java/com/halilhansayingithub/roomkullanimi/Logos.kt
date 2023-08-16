package com.halilhansayingithub.roomkullanimi

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(tableName = "logos")
data class Logos(@PrimaryKey(autoGenerate = true)
                 @ColumnInfo(name = "logo_id") @NotNull var logo_id : Int ,
                 @ColumnInfo(name = "logo_name") @NotNull var logo_name : String ,
                 @ColumnInfo(name = "logo_image") @NotNull var logo_image : String) {



}