package com.example.taskapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Profile(
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    val name: String? = null,
    val description: String? = null
) : Serializable