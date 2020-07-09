package uz.mirsaidoff.plandaydemo.ui.login

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import io.reactivex.rxjava3.disposables.CompositeDisposable
import uz.mirsaidoff.plandaydemo.data.LoginRepository
import uz.mirsaidoff.plandaydemo.data.Result

import uz.mirsaidoff.plandaydemo.R
import uz.mirsaidoff.plandaydemo.common.ACCESS_TOKEN_KEY
import uz.mirsaidoff.plandaydemo.common.EXPIRES_KEY
import uz.mirsaidoff.plandaydemo.common.REFRESH_TOKEN_KEY

class LoginViewModel(
    private val sharedPreferences: SharedPreferences,
    private val loginRepository: LoginRepository
) : ViewModel() {

    val resultLive = MutableLiveData<Boolean>()     //todo encapsulate
    val errorLive = MutableLiveData<String>()       //todo encapsulate
    val progressLive = MutableLiveData<Boolean>()   //todo encapsulate
    private val disposables = CompositeDisposable()

    fun login() {
        progressLive.value = true
        disposables.add(
            loginRepository.login()
                .doFinally { progressLive.value = false }
                .subscribe({
                    if (it.isSuccessful) {
                        resultLive.value = true
                        sharedPreferences.edit()
                            .putString(ACCESS_TOKEN_KEY, it.body()?.accessToken)
                            .putString(REFRESH_TOKEN_KEY, it.body()?.refreshToken)
                            .putLong(EXPIRES_KEY, it.body()?.expiresIn ?: -1)
                            .apply()
                    } else {
                        resultLive.value = false    //todo improve the result livedata
                        errorLive.value = "${it.code()} ${it.message()}"
                    }
                }, {
                    errorLive.value = it.message
                    Log.d(LoginViewModel::class.java.name, it.message.toString())
                })
        )
    }

    fun logout() {

    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}