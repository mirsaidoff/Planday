package uz.mirsaidoff.plandaydemo.data.model

import com.google.gson.annotations.SerializedName

data class AuthBody(
    @SerializedName("client_id")
    val clientId: String,
    @SerializedName("grant_type")
    val grantType: String,
    @SerializedName("refresh_token")
    val refreshToken: String
)