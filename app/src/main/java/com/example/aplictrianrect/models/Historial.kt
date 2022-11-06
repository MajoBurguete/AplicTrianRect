package com.example.aplictrianrect.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Historial(
    @PrimaryKey(autoGenerate = true) var uid: Int,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "ident") val ident: String,
    @ColumnInfo(name = "grupo") val grupo: String,
    @ColumnInfo(name = "fecha") val fecha: String,
    @ColumnInfo(name = "puntos") val puntos: Int,
    @ColumnInfo(name = "cantEjercicios") val cantEjercicios: Int,
    @ColumnInfo(name = "tipoActividad") val tipoActividad: String,
    @ColumnInfo(name = "tiempoCronometro") val tiempoCronometro: String
)

