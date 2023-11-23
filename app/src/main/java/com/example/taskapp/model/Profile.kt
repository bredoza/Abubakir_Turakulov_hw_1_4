package com.example.taskapp.model

import java.io.Serializable

data class Profile(
    val name: String? = null,
    val description: String? = null
): Serializable