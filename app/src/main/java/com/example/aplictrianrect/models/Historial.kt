package com.example.aplictrianrect.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Historial(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "ident") val ident: String,
    @ColumnInfo(name = "grupo") val grupo: String,
    @ColumnInfo(name = "fecha") val fecha: Date,
    @ColumnInfo(name = "puntos") val puntos: Int,
    @ColumnInfo(name = "cantEjercicios") val cantEjercicios: Int,
    @ColumnInfo(name = "tipoActividad") val tipoActividad: String,
    @ColumnInfo(name = "tiempoEnHoras") val tiempoEnHoras: Int,
    @ColumnInfo(name = "tiempoEnMinutos") val tiempoEnMinutos: Int,
    @ColumnInfo(name = "tiempoEnSegundos") val tiempoEnSegundos: Int
)

