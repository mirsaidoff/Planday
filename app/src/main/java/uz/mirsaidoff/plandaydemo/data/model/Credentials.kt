package uz.mirsaidoff.plandaydemo.data.model

import com.google.gson.annotations.SerializedName


data class Credentials(
    @SerializedName("id_token")
    val idToken: String,
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("expires_in")
    val expiresIn: Long,
    @SerializedName("token_type")
    val tokenType: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("scope")
    val scope: String
)