package com.example.bibliotecaapp.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "UsuarioEntity")
class UsuarioEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var nombreUsuario: String,
    var contraUsuario: String){

    fun getNombreUsu(): String = this.nombreUsuario
    fun getContUsu(): String = this.contraUsuario
}