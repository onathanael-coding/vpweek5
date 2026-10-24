package com.oliver.vpweek5.Soal1.viewmodel

import androidx.lifecycle.ViewModel
import com.oliver.vpweek5.Soal1.model.Expenses
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ExpenseViewModel: ViewModel() {
    private val _expenseList = MutableStateFlow<List<Expenses>>(emptyList())
    val expenseList: StateFlow<List<Expenses>> = _expenseList.asStateFlow()

    fun total(): Int {
        return _expenseList.value.sumOf { it.amount }
    }

    //segala input user
    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()
    private val _amount = MutableStateFlow("")
    val amount: StateFlow<String> = _amount.asStateFlow()
    private val _nameError = MutableStateFlow<String?>(null)
    val nameError: StateFlow<String?> = _nameError.asStateFlow()
    private val _amountError = MutableStateFlow<String?>(null)
    val amountError: StateFlow<String?> = _amountError.asStateFlow()
    private val _ngeEdit = MutableStateFlow<Expenses?>(null)
    val ngeEdit: StateFlow<Expenses?> = _ngeEdit.asStateFlow()

    private val _editName = MutableStateFlow("")
    val editName = _editName.asStateFlow()
    private val _editAmount = MutableStateFlow("")
    val editAmount = _editAmount.asStateFlow()
    private val _editNameError = MutableStateFlow<String?>(null)
    val editNameError = _editNameError.asStateFlow()
    private val _editAmountError = MutableStateFlow<String?>(null)
    val editAmountError = _editAmountError.asStateFlow()

    private fun nextId(): Int {
        val idNow = if (_expenseList.value.isEmpty()) 0 else _expenseList.value.last().id
        return idNow + 1
    }

    fun namaChange(newNama: String) {
        _name.value = newNama
        _nameError.value = null
    }

    fun amountChange(newAmount: String) {
        _amount.value = newAmount.filter { it.isDigit() }
        _amountError.value = null
    }

    fun addExpense() {
        val namaTemp = _name.value.trim()
        val amountTemp = _amount.value.toIntOrNull()
        if (namaTemp.isEmpty()) {
            _nameError.value = "Isi nama WOI!"
            return
        }
        if (amountTemp == null || amountTemp <= 0) {
            _amountError.value = "MASA IYA GAK ADA PENGELUARAN"
            return
        }
        _expenseList.update { it + Expenses(nextId(), namaTemp, amountTemp) }
        _name.value = ""
        _amount.value = ""
        _nameError.value = null
        _amountError.value = null
    }

    fun deleteExpense(id: Int) {
        _expenseList.update { list ->
            list.filter { it.id != id }
        }
    }

    fun edit(expense: Expenses) {
        _ngeEdit.value = expense
        _editName.value = expense.name
        _editAmount.value = expense.amount.toString()
        _editNameError.value = null
        _editAmountError.value = null
    }

    fun editNameChange(newName: String) {
        _editName.value = newName
        _editNameError.value = null
    }

    fun editAmountChange(newAmount: String) {
        _editAmount.value = newAmount.filter { it.isDigit() }
        _editAmountError.value = null
    }

    fun cancel() {
        _ngeEdit.value = null
    }

    fun save() {
        val expense = _ngeEdit.value ?: return
        val nameTemp = _editName.value.trim()
        val amountTemp = _editAmount.value.toIntOrNull()

        var isValid = true
        if (nameTemp.isEmpty()) {
            _editNameError.value = "Isi nama WOI!"
            isValid = false
        }
        if (amountTemp == null || amountTemp <= 0) {
            _editAmountError.value = "MASA IYA GAK ADA PENGELUARAN"
            isValid = false
        }

        if (!isValid) return

        _expenseList.update { list ->
            list.map {
                if (it.id == expense.id) {
                    it.copy(name = nameTemp, amount = amountTemp!!)
                } else {
                    it
                }
            }
        }
        _ngeEdit.value = null
    }

    fun nominalFormat(amount: Int): String {
        return "Rp " + String.format("%,d", amount).replace(',', '.')
    }
}
