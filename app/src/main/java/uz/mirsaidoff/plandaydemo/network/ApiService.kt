package uz.mirsaidoff.plandaydemo.network

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.*
import uz.mirsaidoff.plandaydemo.common.GRANT_TYPE
import uz.mirsaidoff.plandaydemo.data.model.Employee
import uz.mirsaidoff.plandaydemo.data.model.Credentials
import uz.mirsaidoff.plandaydemo.data.model.Result

interface ApiService {

    @GET("v1.0/employees")
    fun getEmployeeListV1(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int = 0,
        @Query("createdFrom") createdFrom: String? = null,
        @Query("createdTo") createdTo: String? = null,
        @Query("modifiedFrom") modifiedFrom: String? = null,
        @Query("modifiedTo") modifiedTo: String? = null,
        @Query("special") special: String? = null
    ): Single<Result<List<Employee>>>

    @FormUrlEncoded
    @POST
    fun authorize(
        @Url url: String = "https://id.planday.com/connect/token",
        @Field("client_id") clientId: String,
        @Field("grant_type") grantType: String,
        @Field("refresh_token") refreshToken: String
    ): Single<Response<Credentials>>

    @PUT("v1.0/employees/{id}")
    fun editEmployeeV1(
        @Path("id") id: Long,
        @Body body: Employee
    )

    @GET("v1.0/employees/{id}")
    fun getEmployeeByIdV1(@Path("id") id: Long): Single<Response<Employee>>
}