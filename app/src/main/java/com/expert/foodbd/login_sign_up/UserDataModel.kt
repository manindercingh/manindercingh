package com.expert.foodbd.login_sign_up

import com.google.gson.annotations.SerializedName

data class UserDataModel(
    @SerializedName("phone_Num") var phone: String? = null,
    @SerializedName("UId") var uid: Int? = null,
    @SerializedName("key") var key: String? = null
)