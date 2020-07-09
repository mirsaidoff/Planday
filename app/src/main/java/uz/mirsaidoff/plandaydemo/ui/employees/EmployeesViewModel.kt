package uz.mirsaidoff.plandaydemo.ui.employees

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import uz.mirsaidoff.plandaydemo.data.model.Employee
import uz.mirsaidoff.plandaydemo.network.ApiService

class EmployeesViewModel(private val apiService: ApiService) : ViewModel() {

    val resultLive = MutableLiveData<List<Employee>>()  //todo encapsulate and improve
    val errorLive = MutableLiveData<String>()       //todo encapsulate and improve
    private val disposables = CompositeDisposable()

    fun fetchEmployees() {
        disposables.add(
            //todo add paginated request
            apiService.getEmployeeListV1(limit = 40)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isSuccess()) {
                        resultLive.value = it.data ?: listOf()
                    } else {
                        errorLive.value = it.error!!.status
                    }
                }, {
                    Log.d(EmployeesViewModel::class.java.name, it.message.toString())
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}