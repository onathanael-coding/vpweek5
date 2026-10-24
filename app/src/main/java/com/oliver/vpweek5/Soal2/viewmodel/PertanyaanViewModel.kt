package com.oliver.vpweek5.Soal2.viewmodel

import androidx.lifecycle.ViewModel
import com.oliver.vpweek5.Soal2.model.Pertanyaan
import com.oliver.vpweek5.Soal2.model.semuaPertanyaan
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PertanyaanViewModel: ViewModel() {
    private val _pertanyaan = MutableStateFlow<List<Pertanyaan>>(emptyList())
    val pertanyaan: StateFlow<List<Pertanyaan>> = _pertanyaan.asStateFlow()
    private val max = semuaPertanyaan.size
    private val _jawaban = MutableStateFlow<List<String>>(emptyList())
    val jawaban: StateFlow<List<String>> = _jawaban.asStateFlow()
    private val _dipilih = MutableStateFlow<String?>(null)
    val dipilih: StateFlow<String?> = _dipilih.asStateFlow()

    private val _jumlahSoal = MutableStateFlow("")
    val jumlahSoal: StateFlow<String> = _jumlahSoal.asStateFlow()
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()
    private var inputJumlah = 0

    fun updateJumlah(newJumlah: String) {
        _jumlahSoal.value = newJumlah.filter {it.isDigit()}
        _error.value = null
    }
    fun mulai() {
        val num = _jumlahSoal.value.toIntOrNull()
        if (num == null || num <= 0) {
            _error.value = "You need at least 1 question!"
            return
        }
        if (num > max) {
            _error.value = "Maximum is $max questions."
            return
        }
        inputJumlah = num
        _pertanyaan.value = semuaPertanyaan.shuffled().take(inputJumlah)
        _jawaban.value = emptyList()
        _dipilih.value = null
    }
    fun pilih(terpilih: String) {
        _dipilih.value = terpilih
    }
    fun nextSoal() {
        val pilihan = _dipilih.value
        if (pilihan == null) {
            return
        }
        _jawaban.update {it + pilihan}
        _dipilih.value = null
    }
    fun restart() {
        _pertanyaan.value = emptyList()
        _jawaban.value = emptyList()
        _dipilih.value = null
        _jumlahSoal.value = ""
    }
}