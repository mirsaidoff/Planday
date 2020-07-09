package uz.mirsaidoff.plandaydemo.ui.employees.edit

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import uz.mirsaidoff.plandaydemo.common.LiveResult
import uz.mirsaidoff.plandaydemo.common.LiveResult.Fail
import uz.mirsaidoff.plandaydemo.common.LiveResult.Success
import uz.mirsaidoff.plandaydemo.data.EmployeeRepository
import uz.mirsaidoff.plandaydemo.data.model.Employee

class EmployeeEditViewModel(private val repository: EmployeeRepository) : ViewModel() {

    val resultLive = MutableLiveData<LiveResult>()     //todo encapsulate
    val progressLive = MutableLiveData<Boolean>()     //todo encapsulate

    fun loadEmployeeById(employeeId: Long) {
        repository.loadById(employeeId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.isSuccessful) {
                    resultLive.value = Success(it.body())
                } else resultLive.value = Fail("${it.code()} ${it.message()}")
            }, {
                Log.d(EmployeeEditViewModel::class.java.name, it.message.toString())
                resultLive.value = Fail(it.message.toString())
            })
    }

    fun saveChanges(changedEmployee: Employee) {
        repository.save(changedEmployee.id, changedEmployee)
    }
}