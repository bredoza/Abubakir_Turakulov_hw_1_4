package com.example.taskapp.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.taskapp.model.Profile

@Dao
interface ProfileDao {

    @Insert
    fun insert(profile: Profile)

    @Query("SELECT * FROM profile ORDER BY uid DESC LIMIT 1")
    fun getProfile(): Profile?
}