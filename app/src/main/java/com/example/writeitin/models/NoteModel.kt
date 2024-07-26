package com.example.writeitin.models

import java.io.Serializable

data class NoteModel(val id : Int,var title : String, var description : String) : Serializable