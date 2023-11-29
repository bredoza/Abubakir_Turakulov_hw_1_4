package com.example.taskapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskapp.model.Profile
import com.example.taskapp.model.Task

@Database(entities = [Task::class, Profile::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun profileDao(): ProfileDao
}