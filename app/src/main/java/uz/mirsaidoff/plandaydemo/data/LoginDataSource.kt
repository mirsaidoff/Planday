package uz.mirsaidoff.plandaydemo.data

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import uz.mirsaidoff.plandaydemo.common.CLIENT_ID
import uz.mirsaidoff.plandaydemo.common.GRANT_TYPE
import uz.mirsaidoff.plandaydemo.common.REFRESH_TOKEN
import uz.mirsaidoff.plandaydemo.data.model.Credentials
import uz.mirsaidoff.plandaydemo.network.ApiService

class LoginDataSource {

    fun login(api: ApiService): Single<Response<Credentials>> {
        return api.authorize(
            clientId = CLIENT_ID,
            grantType = GRANT_TYPE,
            refreshToken = REFRESH_TOKEN
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    fun logout() {
        // TODO: revoke authentication
    }
}