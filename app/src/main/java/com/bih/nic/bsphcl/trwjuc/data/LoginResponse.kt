package com.bih.nic.bsphcl.trwjuc.data

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")val token: String?,
    @SerializedName("expiresIn")val expiresIn: Long?,
    @SerializedName("data") val data: User?,
    @SerializedName("msg")val msg: String?
)
