package uz.mirsaidoff.plandaydemo.data

import uz.mirsaidoff.plandaydemo.network.ApiService


class LoginRepository(
    private val apiService: ApiService,
    private val dataSource: LoginDataSource
) {

//    var user: Credentials? = null
//        private set

//    val isLoggedIn: Boolean
//        get() = user != null

//    init {
//        user = null
//    }

//    fun logout() {
//        user = null
//        dataSource.logout()
//    }

    fun login() = dataSource.login(api = apiService)
}