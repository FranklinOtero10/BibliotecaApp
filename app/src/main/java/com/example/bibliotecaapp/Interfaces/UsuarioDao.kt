package com.example.bibliotecaapp.Interfaces

import androidx.room.*
import com.example.bibliotecaapp.Models.UsuarioEntity

@Dao
interface UsuarioDao {

    @Query("SELECT * FROM UsuarioEntity")
    fun getAll(): MutableList<UsuarioEntity>

    @Query("SELECT * from UsuarioEntity where nombreUsuario = :nombre AND contraUsuario = :contra")
    fun getUsuario(nombre: String, contra: String): Boolean

    @Insert
    fun addUsuario(usuarioEntity: UsuarioEntity)

    @Update
    fun updateUsuario(usuarioEntity: UsuarioEntity)

    @Delete
    fun deleteUsuario(usuarioEntity: UsuarioEntity)
}