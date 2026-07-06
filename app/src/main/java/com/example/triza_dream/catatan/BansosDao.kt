package com.example.triza_dream.catatan

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BansosDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBansos(bansos: BansosEntity)

    @Query("SELECT * FROM bansos_table ORDER BY id DESC")
    fun getAllBansos(): Flow<List<BansosEntity>>

    @Delete
    fun deleteBansos(bansos: BansosEntity)
}