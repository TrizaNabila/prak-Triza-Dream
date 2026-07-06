package com.example.triza_dream.catatan

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bansos_table")
data class BansosEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val namaPenerima: String,
    val jenisBantuan: String,
    val keterangan: String
)