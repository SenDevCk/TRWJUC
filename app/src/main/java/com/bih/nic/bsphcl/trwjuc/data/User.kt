package com.bih.nic.bsphcl.trwjuc.data

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class User(
    @SerializedName("staffId") val staffId: String?,
    @SerializedName("staffName") val staffName: String?,
    @SerializedName("mobileNo") val mobileNo: String?,
    @SerializedName("emailId") val emailId: String?,
    @SerializedName("authenticate") val authenticate: String?,
    @SerializedName("latitude") val latitude: String?,
    @SerializedName("longitude") val longitude: String?,
    @SerializedName("entryDate") val entryDate: String?,
    @SerializedName("imageUrl") val imageUrl: String?,
    @SerializedName("role") val role: String?,
    @SerializedName("locationId") val locationId: String?,
    @SerializedName("password") val password: String?,
    @SerializedName("subDivId") val subDivId: String?,
    @SerializedName("section") val section: String?,
    @SerializedName("division") val division: String?,
    @SerializedName("circle") val circle: String?,
    @SerializedName("enabled") val enabled: Boolean?,
    @SerializedName("accountNonExpired") val accountNonExpired: Boolean?,
    @SerializedName("credentialsNonExpired") val credentialsNonExpired: Boolean?,
    @SerializedName("accountNonLocked") val accountNonLocked: Boolean?,
    @SerializedName("authorities") val authorities: List<String>?,
    @SerializedName("username") val username: String?
)
