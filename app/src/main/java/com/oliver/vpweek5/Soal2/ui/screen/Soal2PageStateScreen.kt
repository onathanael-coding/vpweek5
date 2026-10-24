package com.oliver.vpweek5.Soal2.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.oliver.vpweek5.Soal2.viewmodel.PertanyaanViewModel

@Composable
fun Soal2PageStateScreen(viewmodel: PertanyaanViewModel = viewModel()) {
    val questions by viewmodel.pertanyaan.collectAsState()
    val answers by viewmodel.jawaban.collectAsState()
    if (questions.isEmpty()) {
        StartScreen(viewmodel = viewmodel)
    } else if (answers.size >= questions.size) {
        HasilScreen(viewmodel = viewmodel)
    } else {
        PertanyaanScreen(viewmodel = viewmodel)
    }
}
