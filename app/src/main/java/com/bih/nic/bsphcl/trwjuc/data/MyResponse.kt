package com.bih.nic.bsphcl.trwjuc.data


/**
 *Created by Chandan Singh on 2/27/2025.
 */
data class MyResponse<T>(
    val data: T?,
    val responseCode: Int?,
    val status: String?,
    val remarks: String?
)