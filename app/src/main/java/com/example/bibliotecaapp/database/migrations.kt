package com.example.bibliotecaapp.database

import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object migrations {
    val MIGRATION_1_2 = object : Migration(1, 2){
        override fun migrate(database: SupportSQLiteDatabase) {
/*            database.execSQL("DROP TABLE IF EXISTS `LibroEntity`")
            database.execSQL("DROP TABLE IF EXISTS `RevistaEntity`")
            database.execSQL("DROP TABLE IF EXISTS `UsuarioEntity`")*/
        }
    }
}