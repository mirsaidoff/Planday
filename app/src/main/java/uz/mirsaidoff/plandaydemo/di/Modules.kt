package uz.mirsaidoff.plandaydemo.di

import android.content.Context
import android.content.SharedPreferences
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.mirsaidoff.plandaydemo.common.ACCESS_TOKEN_KEY
import uz.mirsaidoff.plandaydemo.common.BASE_URL
import uz.mirsaidoff.plandaydemo.common.CLIENT_ID_KEY
import uz.mirsaidoff.plandaydemo.data.EmployeeRepository
import uz.mirsaidoff.plandaydemo.data.LoginDataSource
import uz.mirsaidoff.plandaydemo.data.LoginRepository
import uz.mirsaidoff.plandaydemo.network.ApiService
import uz.mirsaidoff.plandaydemo.ui.employees.edit.EmployeeEditViewModel
import uz.mirsaidoff.plandaydemo.ui.login.LoginViewModel

val appModule = module {
    single { LoginDataSource() }
    single { LoginRepository(get(), get()) }
    single { provideDefaultSharedPreferences(get()) }
    single { provideApiService(get()) }
    single { EmployeeRepository(get()) }

    viewModel { LoginViewModel(get(), get()) }
    viewModel { EmployeeEditViewModel(get()) }
}

fun provideDefaultSharedPreferences(context: Context): SharedPreferences =
    context.getSharedPreferences("${context.packageName}_prefs", Context.MODE_PRIVATE)


fun provideApiService(prefs: SharedPreferences): ApiService {
    val okHttp = OkHttpClient.Builder()
        .addInterceptor {
            val request = it.request()
            val newRequestBuilder = request.newBuilder()

            if (request.url().host().contains("openapi"))
                newRequestBuilder.addHeader(
                    "X-ClientId",
                    prefs.getString(CLIENT_ID_KEY, "") ?: ""
                ).addHeader(
                    "Authorization",
                    prefs.getString(ACCESS_TOKEN_KEY, "") ?: ""
                )

            return@addInterceptor it.proceed(newRequestBuilder.build())
        }
        .build()

    val retrofit = Retrofit.Builder()
        .client(okHttp)
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(ApiService::class.java)
}