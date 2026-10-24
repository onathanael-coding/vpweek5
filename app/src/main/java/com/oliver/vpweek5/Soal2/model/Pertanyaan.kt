package com.oliver.vpweek5.Soal2.model

data class Pertanyaan (
    val pertanyaan: String,
    val options: List<String>,
    val jawaban: String
)
val semuaPertanyaan = listOf(
    Pertanyaan("Ibu Kota Di Indonesia?", listOf("Surabaya", "Bandung", "Jakarta", "Medan"), "Jakarta"),
    Pertanyaan("Ada berapa hari dalam seminggu?", listOf("11", "7", "5", "6"), "7"),
    Pertanyaan("Software yang digunakan VP apa?", listOf("Word", "Android Studio", "Excel", "Photoshop"), "Android Studio"),
    Pertanyaan("Ibu Kota Jawa Timur?", listOf("Malang", "Surabaya", "Sidoarjo", "Kediri"), "Surabaya"),
    Pertanyaan("Apakah Mata Kuliah VP susah?", listOf("Sangat Sulit", "Sangat Mudah", "Biasa", "Tidak Tahu"), "Sangat Sulit"),
    Pertanyaan("Siapa dosen yang mengajar VP?", listOf("Pak Daniel", "Pak Mychael", "Bu Eliz", "Pak Christo"), "Pak Mychael"),
    Pertanyaan("Siapa tutor yang mengajar VP?", listOf("Ko Timothy", "Ko Probo", "Ko Galih", "Ko Kenjo"), "Ko Timothy"),
    Pertanyaan("Siapa dosen yang mengajar WebDev?", listOf("Pak Hans", "Pak Johan", "Bu Eliz", "Bu Laura"), "Bu Laura")

)